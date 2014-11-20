package com.ameron32.apps.projectbandit.core;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.ameron32.apps.projectbandit.R;
import com.ameron32.apps.projectbandit._retired.CharacterSelectorAdapter;
import com.ameron32.apps.projectbandit.core.fragment.NavigationDrawerFragment;
import com.ameron32.apps.projectbandit.core.fragment.ToolbarFragment;
import com.ameron32.apps.projectbandit.manager.ContentManager;
import com.crashlytics.android.Crashlytics;
import com.jess.ui.TwoWayGridView;

public class CoreActivity extends
    ActionBarActivity
    implements
    ContentManager.OnContentChangeListener,
    ToolbarFragment.OnToolbarFragmentCallbacks {
  
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
  private ToolbarFragment mToolbarFragment;
  
  @Override protected void onCreate(
      Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Crashlytics.start(this);
    setContentView(R.layout.activity_core);
    ButterKnife.inject(this);
    
    loadToolbarFragment();
  }
  
  protected ToolbarFragment getToolbarFragment() {
    return mToolbarFragment;
  }
  
  @Override public void onToolbarCreated(
      Toolbar toolbar) {
    Log.d("Core", "onToolbarCreated()");

//    mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
    mToolbar = toolbar;
    setSupportActionBar(mToolbar);
    getSupportActionBar().setDisplayShowHomeEnabled(true);
    
    mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
    mTitle = getTitle();
    
    mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    // drawerLayout.setStatusBarBackgroundColor(getResources().getColor(R.color.colorPrimary));
    mNavigationDrawerFragment.setup(R.id.navigation_drawer, mDrawerLayout, mToolbar);
  }
    
  private void loadToolbarFragment() {
    mToolbarFragment = ToolbarFragment.newInstance();
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    transaction.replace(R.id.toolbar_actionbar_container, mToolbarFragment);
    transaction.commit();
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
  
  public void onSectionAttached(
      int number) {
    supportInvalidateOptionsMenu();
  }
  
  @Override public boolean onCreateOptionsMenu(
      Menu menu) {
    if (!mNavigationDrawerFragment.isDrawerOpen()) {
      // Only show items in the action bar relevant to this screen
      // if the drawer is not showing. Otherwise, let the drawer
      // decide what to show in the action bar.
//      inflateCoreMenu(menu);
      return true;
    }
    return super.onCreateOptionsMenu(menu);
  }

  private void inflateCoreMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.core, menu);
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
//  
//  private final Context getActivityContext() {
//    return CoreActivity.this;
//  }
  
}
