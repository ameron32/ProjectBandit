package com.parse.parsepush.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.parse.parsepush.R;
import com.parse.parsepush.fragment.ItemTableFragment;

public class ItemViewerActivity extends ActionBarActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_query_viewer);
    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction().add(R.id.container, ItemTableFragment.createFragment()).commit();
    }
  }
}
