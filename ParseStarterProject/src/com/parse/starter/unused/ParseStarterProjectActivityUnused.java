package com.parse.starter.unused;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ameron32.rpg.projectbandit.SettingsActivity;
import com.ameron32.rpg.projectbandit.fragment.impl.ChatManagerFragment;
import com.crashlytics.android.Crashlytics;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.parse.starter.R;
import com.parse.ui.ParseLoginBuilder;

public class ParseStarterProjectActivityUnused extends FragmentActivity {
  
  private static final String  TAG                  = ParseStarterProjectActivityUnused.class.getSimpleName();
  private static final boolean TOAST                = false;
  private static final boolean LOG                  = true;
  
  private void startLoginActivity() {
    ParseLoginBuilder builder = new ParseLoginBuilder(ParseStarterProjectActivityUnused.this);
    startActivityForResult(builder.build(), 0);
  }
  
  private void createParseUser() {
    ParseUser user = new ParseUser();
    user.setUsername("my name");
    user.setPassword("my pass");
    user.setEmail("email@example.com");
    
    // other fields can be set just like with ParseObject
    user.put("phone", "650-555-0000");
    
    user.signUpInBackground(new SignUpCallback() {
      
      @Override
      public void done(ParseException e) {
        if (e == null) {
          // Hooray! Let them use the app now.
        }
        else {
          // Sign up didn't succeed. Look at the ParseException
          // to figure out what went wrong
        }
      }
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.standard, menu);
    return true;
  }
  
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    if (id == R.id.logout) {
      ParseUser.logOut();
      Toast.makeText(getApplicationContext(), "User Logged Out", Toast.LENGTH_SHORT).show();
      startLoginActivity();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
  
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Crashlytics.start(this);
    
    ParseUser currentUser = ParseUser.getCurrentUser();
    if (currentUser == null) {
      startLoginActivity();
      // if (LOG) Log.d(TAG, "current user == null in onCreate()");
    }
    
    Log.d(TAG, "logged as: " + currentUser.getUsername());
    
    int nullResource = 0;
    setContentView(nullResource);
    
    // createParseUser();
    
    ParseAnalytics.trackAppOpened(getIntent());
    
  }
  
  @Override
  protected void onPause() {
    super.onPause();
    getSupportFragmentManager().beginTransaction().replace(R.id.chat_container, new Fragment()).commit();
  }
  
  @Override
  protected void onResume() {
    getSupportFragmentManager().beginTransaction().replace(R.id.chat_container, new ChatManagerFragment()).commit();
    super.onResume();
  }

  public void clickSettings(MenuItem item) {
    startActivity(new Intent(getBaseContext(), SettingsActivity.class));
  }
}
