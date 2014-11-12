package com.parse.parsepush.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.parsepush.R;
import com.parse.parsepush.R.id;
import com.parse.parsepush.R.layout;
import com.parse.parsepush.R.menu;
import com.parse.parsepush.fragment.CharacterTableFragment;

public class CharacterViewerActivity extends ActionBarActivity {
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_query_viewer);
    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction().add(R.id.container, CharacterTableFragment.createFragment()).commit();
    }
  }
  
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.character_table, menu);
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
}
