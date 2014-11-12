package com.parse.starter;

import java.util.ArrayList;
import java.util.List;

public class MessageManager {
  
  private static MessageManager manager;
  
  private MessageManager() {}
  
  public static MessageManager getMessageManager() {
    if (manager == null) {
      manager = new MessageManager();
    }
    return manager;
  }
  
  private List<MessageListener> listeners;
  
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

}
