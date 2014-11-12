package com.parse.starter.object;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;


@ParseClassName("Message") public class Message
    extends ParseObject {
  
  private ParseUser user;
  private String message = "(no message)";
  private String channel = "root";
  private ParseObject character = null;
  private boolean canon = false;
  private ParseObject actionO = null;
  private String action = "says";
  
  public static Message create() {
    return new Message().setUser();
  }
  
  public Message() {
    
  }
  
  public Message setUser() {
    this.user = ParseUser.getCurrentUser();
    return this;
  }
  
  public Message setMessage(
      String message) {
    this.message = message;
    return this;
  }
  
  public Message setChannel(
      String channel) {
    this.channel = channel;
    return this;
  }
  
  public Message setCharacter(
      ParseObject character) {
    this.character = character;
    if (character != null) {
      this.canon = character.getBoolean("canonDefault");
    }
    return this;
  }
  
  public Message setAction(
      ParseObject action) {
    this.actionO = action;
    if (actionO != null) {
      this.action = actionO.getString("action");
    }
    return this;
  }
  
//  
//   public void send() {
//   send(new SaveObjectAsync.OnSaveCallbacks() {
//  
//   @Override public void onComplete() {}
//  
//   @Override public void onBegin() {}
//   });
//   }
//  
//   public void send(
//   SaveObjectAsync.OnSaveCallbacks listener) {
//   applyToParseObject();
//   saveIt(listener);
//   }
//  
//   public void saveIt() {
//   saveIt(new SaveObjectAsync.OnSaveCallbacks() {
//  
//   @Override public void onComplete() {}
//  
//   @Override public void onBegin() {}
//   });
//   }
//  
//   public void saveIt(
//   SaveObjectAsync.OnSaveCallbacks listener) {
//   new SaveObjectAsync(listener).execute(this);
//   }
  
  public void send() {
    applyToParseObject();
    try {
      this.save();
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }
  
  private void applyToParseObject() {
    this.put("user", user);
    this.put("message", message);
    this.put("channel", channel);
    this.put("character", character);
    this.put("canon", canon);
    this.put("action", action);
    this.put("actionO", actionO);
    
    try {
      ParseObject currentGame;
      currentGame = ParseQuery.getQuery("Game").getFirst();
      this.put("ofGame", currentGame);
      this.put("inSession", currentGame.getInt("currentSession"));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    
    this.put("type", "System");
  }
  
  public Message markAsReceived(
      ParseUser readBy) {
    ParseRelation<ParseUser> relation = this.getRelation("receivedBy");
    relation.add(ParseUser.getCurrentUser());
    return this;
  }
  
  @Override public String toString() {
    final StringBuilder sb = new StringBuilder();
    if (character != null
        && action != null
        && user != null) {
      sb.append(character.get("name"));
      sb.append(" ");
      sb.append(action);
      sb.append(": ");
    }
    sb.append(this.getString("message"));
    return sb.toString();
  }
  
  public Character getCharacter() {
    return (Character) this.getParseObject("character");
  }
}
