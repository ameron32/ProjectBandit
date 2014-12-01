package com.ameron32.apps.projectbandit.manager;

import java.util.ArrayList;
import java.util.List;

import com.ameron32.apps.projectbandit.SaveObjectAsyncTask;
import com.ameron32.apps.projectbandit.SaveObjectAsyncTask.OnSaveCallbacks;
import com.ameron32.apps.projectbandit.object.Message;
import com.parse.ParseException;

public class MessageManager extends AbsManager {
  
  private static MessageManager messageManager;
  
  private MessageManager() {}
  
  public static MessageManager get() {
    if (messageManager == null) {
      messageManager = new MessageManager();
    }
    return messageManager;
  }
  
  private List<MessageListener> listeners;
  
  public void initialize() {
    setInitialized(true);
  }
  
  public void addMessageListener(MessageListener listener) {
    if (listeners == null) {
      listeners = new ArrayList<MessageListener>();
    }
    if (!listeners.contains(listener)) {
      listeners.add(listener);
    }
  }
  
  public void removeMessageListener(MessageListener listener) {
    if (listeners != null) {
      if (listeners.contains(listener)) {
        listeners.remove(listener);
      }
    }
  }

  public void notifyMessageReceived() {
    if (listeners != null) {
      for (MessageListener listener : listeners) {
        if (listener != null) {
          listener.onMessageReceived();
        }
      }
    }
  }

  public interface MessageListener {
    
    public void onMessageReceived();
  }
  
  public void markMessagesAsReceived(List<Message> messagesToSave) {
    Message[] save = messagesToSave.toArray(new Message[messagesToSave.size()]);
    new SaveObjectAsyncTask(new OnSaveCallbacks() {
      
      @Override public void onComplete() {}
      
      @Override public void onBegin() {}
    }).execute(save);
  }

  public static void destroy() {
    messageManager = null;
  }
}
