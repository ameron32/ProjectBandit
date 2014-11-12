package com.ameron32.apps.projectbandit.core;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.ameron32.apps.projectbandit.R;
import com.ameron32.apps.projectbandit.adapter.CharacterSelectorAdapter;
import com.ameron32.apps.projectbandit.manager.ContentManager;
import com.crashlytics.android.Crashlytics;
import com.jess.ui.TwoWayGridView;

public class CoreActivity extends
    ActionBarActivity
    implements
    ContentManager.OnContentChangeListener {
  
  // @InjectView(R.id.drawer_layout)
  DrawerLayout mDrawerLayout;
  private Toolbar mToolbar;
  
  /**
   * Fragment managing the behaviors, interactions and presentation of the
   * navigation drawer.
   */
  private NavigationDrawerFragment mNavigationDrawerFragment;
  
  /**
   * Used to store the last screen title. For use in {@link #restoreActionBar()}
   * .
   */
  private CharSequence mTitle;
  
  @Override protected void onCreate(
      Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Crashlytics.start(this);
    setContentView(R.layout.activity_core);
    ButterKnife.inject(this);
    
    mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
    setSupportActionBar(mToolbar);
    getSupportActionBar().setDisplayShowHomeEnabled(true);
    
    mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
    mTitle = getTitle();
    
    mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    // drawerLayout.setStatusBarBackgroundColor(getResources().getColor(R.color.colorPrimary));
    mNavigationDrawerFragment.setup(R.id.navigation_drawer, mDrawerLayout, mToolbar);
  }
  
  @Override protected void onDestroy() {
    super.onDestroy();
    ButterKnife.reset(this);
  };
  
  @Override protected void onResume() {
    super.onResume();
    ContentManager.get().addOnContentChangeListener(this);
  }
  
  @Override protected void onPause() {
    super.onPause();
    ContentManager.get().removeOnContentChangeListener(this);
  }
  
  @Override public void onContentChange(
      ContentManager manager,
      int position) {
    // update the main content by replacing fragments
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    transaction.replace(R.id.container, manager.getNewFragmentForPosition(position));
    transaction.commit();
  };
  
  // @Override
  // public void onNavigationDrawerItemSelected(int position) {
  // // update the main content by replacing fragments
  // // FragmentManager fragmentManager = getSupportFragmentManager();
  // // FragmentTransaction transaction = fragmentManager.beginTransaction();
  // // transaction.replace(R.id.container,
  // // ContentManager.get().getNewFragmentForPosition(position));
  // // transaction.commit();
  // }
  
  public void onSectionAttached(
      int number) {
    // switch (number) {
    // case 1:
    // mTitle = getString(R.string.title_section1);
    // break;
    // case 2:
    // mTitle = getString(R.string.title_section2);
    // break;
    // case 3:
    // mTitle = getString(R.string.title_section3);
    // break;
    // }
    supportInvalidateOptionsMenu();
  }
  
//  public void restoreActionBar() {
//    ActionBar actionBar = getSupportActionBar();
//    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
//    actionBar.setDisplayShowTitleEnabled(true);
//    actionBar.setTitle(mTitle);
//    
////    restoreCharacterIcons(actionBar);
//  }
  
//  private void restoreCharacterIcons(
//      ActionBar actionBar) {
//    View layout = LayoutInflater.from(getActivity()).inflate(R.layout.view_character_icon_toolbar, null);
//    TwoWayGridView characterIconToolbar = (TwoWayGridView) layout.findViewById(R.id.twowaygridview_icon_toolbar);
//    characterIconToolbar.setAdapter(new CharacterSelectorAdapter(getActivity()));
//    actionBar.setCustomView(layout, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//    actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME);
//  }
  
  @Override public boolean onCreateOptionsMenu(
      Menu menu) {
    if (!mNavigationDrawerFragment.isDrawerOpen()) {
      // Only show items in the action bar relevant to this screen
      // if the drawer is not showing. Otherwise, let the drawer
      // decide what to show in the action bar.
      getMenuInflater().inflate(R.menu.core, menu);
//      restoreActionBar();
      return true;
    }
    return super.onCreateOptionsMenu(menu);
  }
  
  @Override public boolean onOptionsItemSelected(
      MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    if (id == R.id.action_settings) { return true; }
    return super.onOptionsItemSelected(item);
  }
  
  private final Context getActivityContext() {
    return CoreActivity.this;
  }
  
}
