package com.parse.parsepush.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.parse.parsepush.R;
import com.parse.parsepush.R.id;
import com.parse.parsepush.R.layout;
import com.parse.parsepush.fragment.CreateItemFragment;
import com.parse.parsepush.fragment.CreateItemFragment.OnFragmentFinishListener;

public class CreateItemActivity extends ActionBarActivity implements OnFragmentFinishListener {
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_item);
    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction().add(R.id.container, new CreateItemFragment()).commit();
    }
  }
  
  @Override
  public void onFinish() {
    getSupportFragmentManager().beginTransaction().replace(R.id.container, new CreateItemFragment()).commit();
  }
}
