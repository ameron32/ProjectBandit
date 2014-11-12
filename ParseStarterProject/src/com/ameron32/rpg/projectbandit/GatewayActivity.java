package com.ameron32.rpg.projectbandit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.parse.starter.R;
import com.parse.starter.manager.UserManager;
import com.parse.ui.ParseLoginBuilder;

public class GatewayActivity extends ActionBarActivity {
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_gateway);
  }
  
  @Override
  protected void onResume() {
    super.onResume();

    /**
     * IF USER IS ALREADY LOGGED IN, CONTINUE TO MAIN ACTIVITY. IF USER IS NOT
     * LOGGED IN, GOTO LOGIN ACTIVITY. WILL GO TO MAIN ACTIVITY AFTER SUCCESSFUL
     * LOGIN. WILL ABORT--finish()--IF CANCELLED.
     */
    if (UserManager.getUserManager().isLoggedIn()) {
      moveToStructureActivity();
    }
    else {
      startLoginActivity();
    }
  }
  
  /**
   * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   * LOGIN ACTIVITY RELATED SECTION
   * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   */
  
  private static final int LOGIN_REQUEST_CODE = 4647;
  
  private void startLoginActivity() {
    ParseLoginBuilder builder = new ParseLoginBuilder(GatewayActivity.this);
    builder.setAppLogo(R.drawable.ic_launcher);
    startActivityForResult(builder.build(), LOGIN_REQUEST_CODE);
  }
  
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent arg2) {
    if (requestCode == LOGIN_REQUEST_CODE) {
      if (resultCode == RESULT_OK) {
        moveToStructureActivity();
      }
    }
  }
  
  private void moveToStructureActivity() {
    Intent beginStructureActivity = new Intent(GatewayActivity.this, StructureActivity.class);
    startActivity(beginStructureActivity);
    finish();
  }
}
