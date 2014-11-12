package com.ameron32.rpg.projectbandit;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import com.ameron32.rpg.projectbandit.fragment.impl.ChatManagerFragment;
import com.ameron32.rpg.projectbandit.fragment.impl.ContentFragment;
import com.ameron32.rpg.projectbandit.fragment.impl.FragmentChooser;
import com.ameron32.rpg.projectbandit.fragment.impl.NavigationFragment;
import com.ameron32.rpg.projectbandit.screen.CharacterScreen;
import com.crashlytics.android.Crashlytics;
import com.parse.GetCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.starter.ChatService;
import com.parse.starter.ChatService.MyBinder;
import com.parse.starter.R;
import com.parse.starter.manager.GameManager;
import com.parse.starter.manager.UserManager;
import com.parse.starter.object.Character;
import com.parse.starter.util.InstallationUtils;
import com.parse.starter.util.UserUtils;

/**
 * An activity representing the represents a a list of Fragments and its details
 * in a Sliding Pane.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link NavigationFragment} and the item details (if present) is a
 * {@link ContentFragment}.
 * <p>
 * This activity also implements the required
 * {@link NavigationFragment.Callbacks} interface to listen for item selections.
 */
public class StructureActivity extends ActionBarActivity implements NavigationFragment.Callbacks,
    CharacterScreen.OnCharacter, ChatManagerFragment.OnChatManagerListener, ServiceConnection {
  
  private static final String  TAG   = StructureActivity.class.getSimpleName();
  private static final boolean TOAST = false;
  private static final boolean LOG   = true;
  
  private SlidingPaneLayout    mSlidingLayout;
  private ActionBar            mActionBar;
  private ChatManagerFragment  chatFragment;
  
  private ParseObject          currentCharacter;

  // private boolean isLoggedIn = false;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Crashlytics.start(this);
    setContentView(R.layout.activity_structural_framework);
    
    initSlidingPaneLayout();

    onPostLoginCreate();
  }
  
  private void onPostLoginCreate() {
    ParseUser user = UserManager.getUserManager().getCurrentUser();
    // force initialize
    GameManager.getGameManager();
    
    UserUtils.setUserACLToFullAccess(user);
    InstallationUtils.detectUnauthorizedInstallation(new GetCallback<ParseInstallation>() {
      
      @Override
      public void done(ParseInstallation installation, ParseException e) {
        if (e != null || installation == null) {
          runDialog(installation);
        }
      }
      
      private void runDialog(final ParseInstallation installation) {
        final Builder b = new AlertDialog.Builder(getActivity());
        final DialogInterface.OnClickListener onOK = new DialogInterface.OnClickListener() {
          
          @Override
          public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
            finish();
          }
        };
        b.setTitle("Installation [" + installation.getObjectId() + "] Retired");
        b.setMessage(getString(R.string.retired_installation));
        b.setPositiveButton("[/sigh] FINE", onOK);
        b.setNegativeButton("I Stubbornly REFUSE", new DialogInterface.OnClickListener() {
          
          @Override
          public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
            Builder b = new AlertDialog.Builder(getActivity());
            DialogInterface.OnClickListener onOK = new DialogInterface.OnClickListener() {
              
              @Override
              public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
              }
            };
            b.setTitle("Refusal Denied");
            b.setMessage(R.string.retired_installation_refusal);
            b.setPositiveButton("[/sigh] FINE", onOK);
            AlertDialog nextDialog = b.create();
            nextDialog.show();
          }
        });
        AlertDialog dialog = b.create();
        dialog.show();
      }
    });
    startChatService();
    
    ParseAnalytics.trackAppOpened(getIntent());
  }
  
  @Override
  protected void onStart() {
    super.onStart();
    onPostLoginStart();
  }

  private void onPostLoginStart() {
    // TODO Auto-generated method stub

  }
  
  private void initSlidingPaneLayout() {
    FragmentTransaction navigationBarTransaction = getSupportFragmentManager().beginTransaction();
    navFragment = NavigationFragment.create(NavigationFragment.Type.Text);
    navigationBarTransaction.replace(R.id.fragment_list, navFragment);
    navigationBarTransaction.commit();
    
    FragmentTransaction iconBarTransaction = getSupportFragmentManager().beginTransaction();
    navIconFragment = NavigationFragment.create(NavigationFragment.Type.Icon);
    iconBarTransaction.replace(R.id.fragment_list_2, navIconFragment);
    iconBarTransaction.commit();

    mActionBar = getSupportActionBar();
    
    mSlidingLayout = (SlidingPaneLayout) findViewById(R.id.sliding_pane_layout);
    
    mSlidingLayout.setPanelSlideListener(new SliderListener());
    mSlidingLayout.openPane();
    
    mSlidingLayout.getViewTreeObserver().addOnGlobalLayoutListener(new FirstLayoutListener());
  }
  
  @Override
  protected void onPause() {
    super.onPause();

    getSupportFragmentManager().beginTransaction().replace(R.id.chat_container, new Fragment()).commit();
    unsilenceNotificationService();
    unbindFromNotificationService();
    chatFragment = null;
  }
  
  @Override
  protected void onResume() {
    onPostLoginResume();
    
    super.onResume();
  }
  
  private void onPostLoginResume() {
    chatFragment = ChatManagerFragment.newInstance(null, null);
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    transaction.replace(R.id.chat_container, chatFragment);
    transaction.commit();
    setActivations();
    
    bindToNotificationService();
    silenceNotificationService();
  }
  
  private void setActivations() {
    // List items should be given the
    // 'activated' state when touched.
    navFragment.setActivateOnItemClick(true);
    navIconFragment.setActivateOnItemClick(true);
  }

  /**
   * Callback method from {@link NavigationFragment.Callbacks} indicating that
   * the item with the given ID was selected.
   */
  @Override
  public void onItemSelected(String id) {
    
    // Show the detail view in this activity by
    // adding or replacing the detail fragment using a
    // fragment transaction.
    changeContentFragment(id);
    
    // ((FragmentDetailFragment)
    // getSupportFragmentManager().findFragmentById(R.id.content_pane)).setTitleText(DummyContent.ITEM_MAP.get(id).content);
    mSlidingLayout.closePane();
  }
  
  private void changeContentFragment(String id) {
    FragmentChooser.changeContentFragmentFromId(getActivity(), id);
    mSlidingLayout.closePane();
  }
  
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    return true;
  }
  
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    if (id == R.id.logout) {
      ParseUser.logOut();
      Toast.makeText(getApplicationContext(), "User Logged Out", Toast.LENGTH_SHORT).show();
      startGatewayActivityAndLogout();
      return true;
    }
    /*
     * The action bar up action should open the slider if it is currently
     * closed, as the left pane contains content one level up in the navigation
     * hierarchy.
     */
    if (item.getItemId() == android.R.id.home && !mSlidingLayout.isOpen()) {
      mSlidingLayout.openPane();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
  
  private void startGatewayActivityAndLogout() {
    UserManager.getUserManager().logout();
    Intent gateway = new Intent(StructureActivity.this, GatewayActivity.class);
    startActivity(gateway);
    finish();
  }

  @Override
  public boolean onOptionsItemSelectedWithinFragment(MenuItem item) {
    return onOptionsItemSelected(item);
  }
  
  /**
   * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   * SLIDINGPANE RELATED SECTION
   * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   */
  
  /**
   * This panel slide listener updates the action bar accordingly for each panel
   * state.
   */
  private class SliderListener extends SlidingPaneLayout.SimplePanelSlideListener {
    
    @Override
    public void onPanelOpened(View panel) {
      if (TOAST) Toast.makeText(panel.getContext(), "Opened", Toast.LENGTH_SHORT).show();
      
      panelOpened();
    }
    
    @Override
    public void onPanelClosed(View panel) {
      if (TOAST) Toast.makeText(panel.getContext(), "Closed", Toast.LENGTH_SHORT).show();
      
      panelClosed();
    }
    
    @Override
    public void onPanelSlide(View view, float v) {}
  }
  
  private void panelClosed() {
    setHomeButtonEnabled(true);
    mActionBar.setDisplayHomeAsUpEnabled(true);
    
    // getSupportFragmentManager().findFragmentById(R.id.content_pane).setHasOptionsMenu(true);
    getSupportFragmentManager().findFragmentById(R.id.fragment_list).setHasOptionsMenu(false);
  }
  
  private void panelOpened() {
    setHomeButtonEnabled(false);
    mActionBar.setDisplayHomeAsUpEnabled(false);
    
    if (mSlidingLayout.isSlideable()) {
      // getSupportFragmentManager().findFragmentById(R.id.content_pane).setHasOptionsMenu(false);
      getSupportFragmentManager().findFragmentById(R.id.fragment_list).setHasOptionsMenu(true);
    }
    else {
      // getSupportFragmentManager().findFragmentById(R.id.content_pane).setHasOptionsMenu(true);
      getSupportFragmentManager().findFragmentById(R.id.fragment_list).setHasOptionsMenu(true);
    }
  }
  
  @SuppressLint("NewApi")
  private void setHomeButtonEnabled(boolean enabled) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
      mActionBar.setHomeButtonEnabled(enabled);
    }
  }

  /**
   * This global layout listener is used to fire an event after first layout
   * occurs and then it is removed. This gives us a chance to configure parts of
   * the UI that adapt based on available space after they have had the
   * opportunity to measure and layout.
   */
  @SuppressLint("NewApi")
  private class FirstLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {
    
    @Override
    public void onGlobalLayout() {
      
      if (mSlidingLayout.isSlideable() && !mSlidingLayout.isOpen()) {
        panelClosed();
      }
      else {
        panelOpened();
      }
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        mSlidingLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
      }
      else {
        mSlidingLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
      }
    }
  }
  
  private boolean hide = false;
  
  public void toggleShowHideChat(View v) {
    hide = !hide;

    if (hide) {
      FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
      ft.setCustomAnimations(R.anim.slide_in_right, android.R.anim.slide_out_right);
      ft.hide(chatFragment).commit();
    }
    else {
      FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
      ft.setCustomAnimations(R.anim.slide_in_right, android.R.anim.slide_out_right);
      ft.show(chatFragment).commit();
    }
  }
  
  public ParseObject getCurrentCharacter() {
    return currentCharacter;
  }
  
  private StructureActivity getActivity() {
    return StructureActivity.this;
  }

  /**
   * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   * SERVICE RELATED SECTION
   * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   */
  
  private ChatService        chatService;
  private NavigationFragment navFragment;
  private NavigationFragment navIconFragment;

  @Override
  public void onServiceConnected(ComponentName name, IBinder service) {
    MyBinder mBinder = (MyBinder) service;
    chatService = mBinder.getService();
  }
  
  @Override
  public void onServiceDisconnected(ComponentName name) {
    chatService = null;
  }

  private void startChatService() {
    Intent chatServiceIntent = ChatService.makeIntent(this);
    startService(chatServiceIntent);
  }

  private void bindToNotificationService() {
    Intent chatServiceIntent = ChatService.makeIntent(this);
    bindService(chatServiceIntent, this, ContextWrapper.BIND_AUTO_CREATE);
  }
  
  private void unbindFromNotificationService() {
    try {
      unbindService(this);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void silenceNotificationService() {
    if (chatService != null) {
      chatService.setAppState(ChatService.APP_ON);
      chatService.setQuiet(true);
    }
  }
  
  private void unsilenceNotificationService() {
    if (chatService != null) {
      chatService.setAppState(ChatService.APP_OFF);
      chatService.setQuiet(false);
    }
  }
  
  /**
   * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   * UNUSED CALLBACKS SECTION
   * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   */
  
  @Override
  public void onCharacter() {
    // TODO unneeded
  }
  
  @Override
  public void onChat() {
    // TODO unneeded
  }
  
  @Override
  public void onCharacterChanged(Character character) {
    currentCharacter = character;
  }

}
