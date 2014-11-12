package com.parse.parsepush.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.parse.parsepush.R;
import com.parse.parsepush.R.id;
import com.parse.parsepush.R.layout;
import com.parse.parsepush.R.menu;
import com.parse.parsepush.fragment.IssueItemFragment;

public class IssueItemActivity extends SubPushActivity implements IssueItemFragment.FragmentCallback {
  
  private IssueItemFragment iif;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_issue_item);
    if (savedInstanceState == null) {
      initNewFragment();
    }
  }
  
  private void initNewFragment() {
    iif = new IssueItemFragment();
    getSupportFragmentManager().beginTransaction().replace(R.id.container, iif).commit();
  }

  @Override
  protected void onPause() {
    super.onPause();
    getSupportFragmentManager().beginTransaction().replace(R.id.container, new Fragment()).commit();
    iif = null;
  }
  
  @Override
  protected void onResume() {
    if (iif == null) {
      initNewFragment();
    }
    super.onResume();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.issue_item, menu);
    return true;
  }
  
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    if (id == R.id.action_settings) { return true; }
    return super.onOptionsItemSelected(item);
  }
  
  public void issueItem(View v) {
    if (iif != null) iif.issueItem(v);
  }

  @Override
  public void onSubmit() {
    initNewFragment();
  }

  @Override
  protected int onStepsCreated() {
    // TODO Auto-generated method stub
    return 0;
  }
  
  @Override
  protected void initialize() {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  protected void performInBackground(int currentStep, int totalSteps) {
    // TODO Auto-generated method stub
    
  }

  @Override
  protected void done() {
    // TODO Auto-generated method stub

  }

}
