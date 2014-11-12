package com.parse.starter.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.PushService;
import com.parse.SaveCallback;
import com.parse.starter.R;
import com.parse.starter.object.CAction;
import com.parse.starter.object.Character;
import com.parse.starter.object.Game;
import com.parse.starter.object.Message;
import com.parse.starter.unused.ParseStarterProjectActivityUnused;

public class ParseApplication extends Application {

  private static String YOUR_APPLICATION_ID;
  private static String YOUR_CLIENT_KEY;
//  private static Context applicationContext;
  
  @Override public void onCreate() {
    super.onCreate();
//    applicationContext = getApplicationContext();
    
    YOUR_APPLICATION_ID = getResources().getString(R.string.parse_application_id);
    YOUR_CLIENT_KEY = getResources().getString(R.string.parse_client_key);

		// Add your initialization code here
		Parse.enableLocalDatastore(this);
		Parse.initialize(this, YOUR_APPLICATION_ID, YOUR_CLIENT_KEY);

		// Save the current Installation to Parse.
		ParseInstallation.getCurrentInstallation().saveInBackground(
				new SaveCallback() {

					@Override
					public void done(ParseException e) {
						PushService.setDefaultPushCallback(
								ParseApplication.this,
								ParseStarterProjectActivityUnused.class);
					}
				});
    ParseObject.registerSubclass(Character.class);
    ParseObject.registerSubclass(Message.class);
    ParseObject.registerSubclass(CAction.class);
    ParseObject.registerSubclass(Game.class);

		setDefaultSettings();
	}

	private void setAnonymousUsers() {
		ParseUser.enableAutomaticUser();
		ParseACL defaultACL = new ParseACL();

		// If you would like all objects to be private by default, remove this
		// line.
		defaultACL.setPublicReadAccess(true);

		ParseACL.setDefaultACL(defaultACL, true);
	}

	private void setDefaultSettings() {
		SharedPreferences preferences = getSharedPreferences("size",
				Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putInt("message_row", R.layout.row_message);
		editor.commit();
	}
//
//	public static Context getContext() {
//		return ParseApplication.applicationContext;
//	}

}
