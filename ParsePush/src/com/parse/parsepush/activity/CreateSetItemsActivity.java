package com.parse.parsepush.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.parsepush.R;
import com.parse.parsepush.fragment.CreateSetItemsFragment;

public class CreateSetItemsActivity extends ActionBarActivity implements
    CreateSetItemsFragment.OnFragmentFinishListener {
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_set_items);
    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction().replace(R.id.container, new CreateSetItemsFragment()).commit();
    }
  }
  
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.create_set_items, menu);
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
  
  @Override
  public void onFinish() {
    getSupportFragmentManager().beginTransaction().replace(R.id.container, new CreateSetItemsFragment()).commit();
  }
}
