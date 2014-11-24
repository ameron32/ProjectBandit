package com.ameron32.apps.projectbandit;

import java.util.Arrays;

import android.os.AsyncTask;

import com.parse.ParseException;
import com.parse.ParseObject;

public class SaveObjectAsyncTask {
  
  private static boolean LOGGY = false;
  
  private final OnSaveCallbacks listener;
  
  public SaveObjectAsyncTask(
      OnSaveCallbacks listener) {
    this.listener = listener;
    listener.onBegin();
    
  }
  
  public void execute(
      ParseObject... params) {
    final int length = params.length;
    // final Boolean[] results = new Boolean[length];
    // for (int i = 0; i < length; i++) {
    // results[i] = Boolean.valueOf(sendMessage(params[i]));
    // }
    ParseObject.saveAllInBackground(Arrays.asList(params));
    
    listener.onComplete();
  }
  
  private boolean sendMessage(
      ParseObject object) {
    int saveObject = 0;
    if (LOGGY) {
      saveObject = Loggy.start("saveObject");
    }
    
    try {
      object.save();
      if (LOGGY)
        Loggy.stop(saveObject);
    } catch (ParseException e) {
      e.printStackTrace();
      if (LOGGY)
        Loggy.stop(saveObject);
      return false;
    }
    
    // return success
    return true;
  }
  
  public interface OnSaveCallbacks {
    
    public void onBegin();
    
    public void onComplete();
  }
}
