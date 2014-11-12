package com.parse.parsepush;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.PushService;
import com.parse.SaveCallback;
import com.parse.parsepush.activity.SubPushActivity;
import com.parse.starter.object.CInventory;
import com.parse.starter.object.Character;
import com.parse.starter.object.Item;
import com.parse.starter.object.Message;


public class ParseApplication extends Application {
  
  private static String YOUR_APPLICATION_ID;
  private static String YOUR_CLIENT_KEY;
//  private static Context applicationContext;
  
  @Override public void onCreate() {
    super.onCreate();
//    applicationContext = getApplicationContext();
    
    YOUR_APPLICATION_ID = getResources().getString(R.string.parse_application_id);
    YOUR_CLIENT_KEY = getResources().getString(R.string.parse_client_key);
    
    
    Parse.enableLocalDatastore(this);
    // Add your initialization code here
    Parse.initialize(this, YOUR_APPLICATION_ID, YOUR_CLIENT_KEY);
    
    ParseObject.registerSubclass(Item.class);
    ParseObject.registerSubclass(Message.class);
    ParseObject.registerSubclass(CInventory.class);
    ParseObject.registerSubclass(Character.class); // TODO merge Character?

    // Save the current Installation to Parse.
    ParseInstallation.getCurrentInstallation().saveInBackground(new SaveCallback() {
      
      @Override
      public void done(ParseException e) {
        PushService.setDefaultPushCallback(ParseApplication.this, SubPushActivity.class);
      }
    });
  }
}
