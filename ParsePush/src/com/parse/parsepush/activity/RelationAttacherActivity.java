package com.parse.parsepush.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.parse.ParseUser;
import com.parse.parsepush.R;
import com.parse.parsepush.UserUtils;
import com.parse.parsepush.fragment.RelationAttacherFragment;

public class RelationAttacherActivity
    extends SubPushActivity {
  
  private RelationAttacherFragment mRelationAttacherFragment;

  @Override protected void onCreate(
      Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_relation_attacher);
    UserUtils.setUserACLToFullAccess(ParseUser.getCurrentUser());
    if (savedInstanceState == null) {
      mRelationAttacherFragment = new RelationAttacherFragment();
      getSupportFragmentManager().beginTransaction().add(R.id.container, mRelationAttacherFragment).commit();
    }
  }

  @Override public boolean onCreateOptionsMenu(
      Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.relation_attacher, menu);
    return true;
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
  
  @Override protected int onStepsCreated() {
    // TODO Auto-generated method stub
    return 0;
  }
  
  @Override protected void initialize() {
    // TODO Auto-generated method stub
    
  }
  
  @Override protected void performInBackground(
      int currentStep, int totalSteps) {
    // TODO Auto-generated method stub
    
  }
  
  @Override protected void done() {
    // TODO Auto-generated method stub
    
  }
  
  public void onSaveRelation(View v) {
    mRelationAttacherFragment.onSaveRelation(v);
  }
  
}
