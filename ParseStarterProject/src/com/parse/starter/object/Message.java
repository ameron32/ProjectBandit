package com.parse.starter.object;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.starter.SaveObjectAsync;
import com.parse.starter.manager.GameManager;
import com.parse.starter.manager.UserManager;

@ParseClassName("Message")
public class Message extends ParseObject {
  
  private ParseUser   user;
  private String      message   = "(no message)";
  private String      channel   = "root";
  private Character   character = null;
  private boolean     canon     = false;
  private ParseObject actionO   = null;
  private String      action    = "says";

  public static Message create() {
    return new Message().setUser();
  }
  
  public Message() {

  }
  
  public Message setUser() {
    this.user = UserManager.getUserManager().getCurrentUser();
    return this;
  }
  
  public Message setMessage(String message) {
    this.message = message;
    return this;
  }
  
  public Message setChannel(String channel) {
    this.channel = channel;
    return this;
  }
  
  public Message setCharacter(Character character) {
    this.character = character;
    if (character != null) {
      this.canon = character.getBoolean("canonDefault");
    }
    return this;
  }
  
  public Message setAction(ParseObject action) {
    this.actionO = action;
    if (actionO != null) {
      this.action = actionO.getString("action");
    }
    return this;
  }
  
  public void send() {
    applyToParseObject();

    new SaveObjectAsync(new SaveObjectAsync.OnSaveCallbacks() {
      
      @Override
      public void onComplete(Boolean... result) {}
      
      @Override
      public void onBegin() {}
    }).execute(this);
  }
  
  public void send(SaveObjectAsync.OnSaveCallbacks listener) {
    applyToParseObject();

    new SaveObjectAsync(listener).execute(this);
  }
  
  private void applyToParseObject() {
    this.put("user", user);
    this.put("message", message);
    this.put("channel", channel);
    this.put("character", character);
    this.put("canon", canon);
    this.put("action", action);
    this.put("actionO", actionO);
    
    Game currentGame = GameManager.getGameManager().getCurrentGame();
    this.put("ofGame", currentGame);
    this.put("inSession", currentGame.getCurrentSession());
  }
}
