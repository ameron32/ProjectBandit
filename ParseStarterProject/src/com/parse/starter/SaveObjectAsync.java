package com.parse.starter;

import android.os.AsyncTask;

import com.parse.ParseException;
import com.parse.ParseObject;

public class SaveObjectAsync extends AsyncTask<ParseObject, Integer, Boolean[]> {
  
  private final OnSaveCallbacks listener;
  
  public SaveObjectAsync(OnSaveCallbacks listener) {
    this.listener = listener;
  }
  
  @Override
  protected void onPreExecute() {
    super.onPreExecute();
    listener.onBegin();
  }

  @Override
  protected Boolean[] doInBackground(ParseObject... params) {
    final int length = params.length;
    final Boolean[] results = new Boolean[length];
    for (int i = 0; i < length; i++) {
      results[i] = Boolean.valueOf(sendMessage(params[i]));
    }
    return results;
  }
  
  private boolean sendMessage(ParseObject object) {
    final int saveObject = Loggy.start("saveObject");

    try {
      object.save();
      Loggy.stop(saveObject);
    }
    catch (ParseException e) {
      e.printStackTrace();
      Loggy.stop(saveObject);
      return false;
    }
    
    // return success
    return true;
  }
  
  @Override
  protected void onPostExecute(Boolean[] result) {
    super.onPostExecute(result);
    listener.onComplete(result);
  }
  
  public interface OnSaveCallbacks {
    
    public void onBegin();
    
    public void onComplete(Boolean... result);
  }
}
