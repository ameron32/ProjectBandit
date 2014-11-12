package com.parse.starter;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.parse.starter.MessageManager.MessageListener;

public class ChatService extends Service implements MessageListener {
  
  private static final String  TAG                         = ChatService.class.getSimpleName();
  private static final boolean TOAST                       = false;
  private static final boolean LOG                         = true;
  
  private static final String  NEW_MESSAGE_NOTIFICATION_ID = "77424";
  private static final String  QUIET_STATE                 = "quiet_state";


  public ChatService() {}
  
  public static Intent makeIntent(Context context) {
    return new Intent(context, ChatService.class);
  }
  
  @Override
  public IBinder onBind(Intent intent) {
    return getMyBinder();
  }
  
  @Override
  public boolean onUnbind(Intent intent) {
    return super.onUnbind(intent);
  }
  
  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    // super.onStartCommand(intent, flags, startId);
    return START_STICKY;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    if (LOG) Log.i(TAG, "onCreate");
    
    restoreQuietSetting();

    if (LOG) Log.i(TAG, "quiet is " + isQuiet());
    MessageManager.getMessageManager().addMessageListener(this);
  }
  
  @Override
  public void onDestroy() {
    // watchers.clear();
    // stopNotification(getSTOP_NOTIFICATION_ID());
    MessageManager.getMessageManager().removeMessageListener(this);
    if (LOG) Log.i(TAG, "onDestroy");
    storeQuietSetting(quiet);
    super.onDestroy();
  }
  
  private final Context context    = this;
  private volatile boolean quiet      = true;
  private final int     nextNumber = 0;
  
  private void putNotification(String id) {
    boolean willSend = !isQuiet();
    if (LOG) Log.d(TAG, "willSend is " + willSend);
    if (willSend) {
      NewMessageNotification.notify(context, "Message", nextNumber);
    }
  }
  
  public class MyBinder extends Binder {
    
    public ChatService getService() {
      return ChatService.this;
    }
  }

  private final IBinder myBinder = new MyBinder();
  
  protected IBinder getMyBinder() {
    return myBinder;
  }
  
  @Override
  public void onMessageReceived() {
    setSilentFor10SecondsThenUnsilence();
    putNotification(NEW_MESSAGE_NOTIFICATION_ID);
  }

  private void setSilentFor10SecondsThenUnsilence() {
    final int silentForDelayInSeconds = 10;
    final int silentForDelayInMillis = silentForDelayInSeconds * 1000;
    
    final Thread delay = new Thread(new Runnable() {
      
      @Override
      public void run() {
        try {
          setQuiet(true);
          Thread.sleep(silentForDelayInMillis);
        }
        catch (InterruptedException e) {
          e.printStackTrace();
        }
        finally {
          if (LOG) Log.i(TAG, "sleep end");
          if (appState == APP_OFF) {
            if (LOG) Log.i(TAG, "reset quiet state to: " + false);
            setQuiet(false);
          }
        }
      }
    });
    delay.start();
  }

  public boolean isQuiet() {
    return quiet;
  }

  public void setQuiet(boolean quiet) {
    this.quiet = quiet;
    if (LOG) Log.d(TAG, "quiet is now: " + quiet);
    storeQuietSetting(quiet);
  }
  
  public static final boolean APP_OFF  = false;
  public static final boolean APP_ON   = true;
  private volatile boolean    appState = APP_OFF;
  
  public void setAppState(boolean newState) {
    this.appState = newState;
  }

  private void restoreQuietSetting() {
    SharedPreferences prefs = context.getSharedPreferences("ChatService", Context.MODE_PRIVATE);
    quiet = prefs.getBoolean(QUIET_STATE, true);
  }
  
  private void storeQuietSetting(boolean quiet) {
    Editor editor = context.getSharedPreferences("ChatService", Context.MODE_PRIVATE).edit();
    editor.putBoolean(QUIET_STATE, quiet);
    editor.commit();
  }

}
