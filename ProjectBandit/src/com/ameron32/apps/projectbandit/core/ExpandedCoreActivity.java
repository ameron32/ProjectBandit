package com.ameron32.apps.projectbandit.core;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.widget.Toast;
import butterknife.OnClick;

import com.ameron32.apps.projectbandit.ChatService;
import com.ameron32.apps.projectbandit.ChatService.MyBinder;
import com.ameron32.apps.projectbandit.R;
import com.ameron32.apps.projectbandit.adapter.CharacterSelectorAdapter;
import com.ameron32.apps.projectbandit.core.fragment.ChatManagerFragment;
import com.ameron32.apps.projectbandit.core.fragment.NavigationDrawerFragment;
import com.ameron32.apps.projectbandit.core.trial.AbsResettingContentFragment;
import com.ameron32.apps.projectbandit.core.trial.AbsResettingContentFragment.OnResetCallbacks;
import com.ameron32.apps.projectbandit.manager.AbsManager;
import com.ameron32.apps.projectbandit.manager.CharacterManager;
import com.ameron32.apps.projectbandit.manager.GameManager;
import com.ameron32.apps.projectbandit.manager.UserManager;
import com.parse.ParseUser;

public class ExpandedCoreActivity
    extends CoreActivity
    // TODO remove if unneeded
    implements
      NavigationDrawerFragment.NavigationDrawerCallbacks,
      ChatManagerFragment.OnChatManagerListener,
      OnResetCallbacks 
{
  
  private static final String STATE_CHAT_VISIBLE = "state_chat_fragment_visibility";
  private static final String CURRENT_CHATVIEW_POSITION_KEY = "CurrentViewPosition";
  
  private ChatManagerFragment mChatFragment;
  
  private boolean isChatManagerHidden;
  private boolean mFromSavedInstanceState;
  private int mCurrentChatViewPosition;
  
  @Override protected void onCreate(
      Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    loadSavedState(savedInstanceState);
    
    if (!isCoreInitializationComplete()) {
      CharacterManager.get().initialize();
      UserManager.get().initialize();
      GameManager.get().initialize();
    }
  }
  
  private boolean isCoreInitializationComplete() {
    return AbsManager.isInitializationComplete(CharacterManager.get(), UserManager.get(), GameManager.get());
  }
  
  private void loadSavedState(
      Bundle savedInstanceState) {
    if (savedInstanceState != null) {
      isChatManagerHidden = savedInstanceState.getBoolean(STATE_CHAT_VISIBLE);
      mFromSavedInstanceState = true;
      mCurrentChatViewPosition = savedInstanceState.getInt(CURRENT_CHATVIEW_POSITION_KEY);
    }
  }
  
  private void startChatService() {
    Intent chatServiceIntent = ChatService.makeIntent(this);
    startService(chatServiceIntent);
    mConn = new MyServiceConnection();
    bindService(chatServiceIntent, mConn, BIND_AUTO_CREATE);
  }
  
  private void disconnectFromChatService() {
    unbindService(mConn);
  }
  
  public MyServiceConnection mConn;
  private CharacterSelectorAdapter mAdapter;
  
  public static class MyServiceConnection
      implements ServiceConnection {
    
    private ChatService chatService;
    
    @Override public void onServiceConnected(
        ComponentName name,
        IBinder service) {
      MyBinder myBinder = (MyBinder) service;
      chatService = myBinder.getService();
      chatService.setAppState(ChatService.APP_ON);
    }
    
    @Override public void onServiceDisconnected(
        ComponentName name) {
      chatService = null;
    }
    
    public void pushAppStateToOff() {
      chatService.setAppState(ChatService.APP_OFF);
    }
  }
  
  @Override public boolean onOptionsItemSelected(
      MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.action_logout) {
      ParseUser.logOut();
      Toast.makeText(getApplicationContext(), "User Logged Out", Toast.LENGTH_SHORT).show();
      logout();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
  
  public void toggleShowHideChat() {
    isChatManagerHidden = !isChatManagerHidden;
    setChatFragmentVisibleState(isChatManagerHidden);
  }
  
  public void setChatFragmentVisibleState(
      boolean visible) {
    if (visible) {
      hideChatFragment();
    } else {
      showChatFragment();
    }
  }
  
  private void hideChatFragment() {
    if (mChatFragment != null) {
    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
    ft.setCustomAnimations(R.anim.slide_in_right, android.R.anim.slide_out_right);
    ft.hide(mChatFragment).commit();
    }
  }
  
  private void showChatFragment() {
    if (mChatFragment != null) {
    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
    ft.setCustomAnimations(R.anim.slide_in_right, android.R.anim.slide_out_right);
    ft.show(mChatFragment).commit();
    }
  }
  
  @OnClick(R.id.button_toggle_show_hide) public void toggleChatManagerFragment() {
    toggleShowHideChat();
  }
  
  @Override protected void onPause() {
    super.onPause();
    mChatFragment = null;
    mConn.pushAppStateToOff();
    disconnectFromChatService();
  }
  
  @Override protected void onResume() {
    super.onResume();
    loadChatFragment();
    setChatFragmentVisibleState(isChatManagerHidden);
    startChatService();
  }
  
  private void loadChatFragment() {
    mChatFragment = ChatManagerFragment.newInstance(mCurrentChatViewPosition, null);
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    transaction.replace(R.id.chat_container, mChatFragment);
    transaction.commit();
  }
  
  private void startGatewayActivityAndLogout() {
    UserManager.get().logout();
    Intent gateway = new Intent(ExpandedCoreActivity.this, GatewayActivity.class);
    startActivity(gateway);
    finish();
  }
  
  @Override public void onChatViewPositionChange(
      int position) {
    mCurrentChatViewPosition = position;
  };
  
  @Override public void onLogoutClick() {
    logout();
  }
  
  private void logout() {
    startGatewayActivityAndLogout();
  }
  
  @Override public void onRequestReset(
    AbsResettingContentFragment fragment) {
    try {
      FragmentManager fragmentManager = getSupportFragmentManager();
      FragmentTransaction transaction = fragmentManager.beginTransaction();
      transaction.replace(R.id.container, fragment.getClass().newInstance());
      transaction.commit();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
  }
  
  @Override public void onSaveInstanceState(
      Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putBoolean(STATE_CHAT_VISIBLE, isChatManagerHidden);
    outState.putInt(CURRENT_CHATVIEW_POSITION_KEY, mCurrentChatViewPosition);
  }
}
