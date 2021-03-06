package com.ameron32.apps.projectbandit.object;

import java.util.Locale;

import com.ameron32.apps.projectbandit.SaveObjectAsyncTask;
import com.ameron32.apps.projectbandit.manager.GameManager;
import com.ameron32.apps.projectbandit.manager.UserManager;
import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseRelation;
import com.parse.ParseUser;


@ParseClassName("Message") 
public class Message
    extends AbsBanditObject<AbsBanditObject.Column> {
  
  private ParseUser user;
  private String message = "(no message)";
  private String channel = "root";
  private Character character = null;
  private boolean canon = false;
  private ParseObject actionO = null;
  private String action = "says";
  private MessageType type = MessageType.STANDARD;
  
  public static Message create() {
    return new Message().setUser();
  }
  
  public Message() {
    
  }
  
  public Message setUser() {
    this.user = UserManager.get().getCurrentUser();
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
      Character character) {
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
  
  public Message setType(MessageType type) {
    if (type != null) {
      this.type = type;
    }
    return this;
  }
  
  public void send() {
    send(new SaveObjectAsyncTask.OnSaveCallbacks() {
      
      @Override public void onComplete() {}
      
      @Override public void onBegin() {}
    });
  }
  
  public void send(
      SaveObjectAsyncTask.OnSaveCallbacks listener) {
    applyToParseObject();
    saveIt(listener);
  }
  
  public void saveIt() {
    saveIt(new SaveObjectAsyncTask.OnSaveCallbacks() {
      
      @Override public void onComplete() {}
      
      @Override public void onBegin() {}
    });
  }
  
  public void saveIt(
      SaveObjectAsyncTask.OnSaveCallbacks listener) {
    new SaveObjectAsyncTask(listener).execute(this);
  }
  
  private void applyToParseObject() {
    this.put("user", user);
    this.put("message", message);
    this.put("channel", channel);
    this.put("character", character);
    this.put("canon", canon);
    this.put("action", action);
    this.put("actionO", actionO);
    this.put("type", type.toString());
    
    Game currentGame = GameManager.get().getCurrentGame();
    this.put("ofGame", currentGame);
    this.put("inSession", currentGame.getCurrentSession());
  }
  
  public Message markAsReceived(
      ParseUser readBy) {
    ParseRelation<ParseUser> relation = this.getRelation("receivedBy");
    relation.add(UserManager.get().getCurrentUser());
    return this;
  }
  
  @Override public String toString() {
    final StringBuilder sb = new StringBuilder();
    if (character != null && action != null && user != null) {
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

  private static final AbsBanditObject.Column[] COLUMNS = {
    new Column("action", _DataType.String),
    new Column("canon", _DataType.Boolean),
    new Column("channel", _DataType.String),
    new Column("inSession", _DataType.Integer),
    new Column("message", _DataType.String),
    new Column("type", _DataType.String),
    new Column("character", _DataType.Pointer),
    new Column("actionO", _DataType.Pointer),
    new Column("ofGame", _DataType.Relation),
    new Column("receivedBy", _DataType.Relation),
    new Column("user", _DataType.Pointer)
  };
  
  @Override public AbsBanditObject.Column get(
      int columnPosition) {
    return COLUMNS[columnPosition]; 
  }

  @Override public int getColumnCount() {
    return COLUMNS.length;
  }
  
  public enum MessageType {
    STANDARD, SYSTEM;
    
    public String toString() {
      return this.name().substring(0, 1).toUpperCase(Locale.ENGLISH) 
          + this.name().substring(1).toLowerCase(Locale.ENGLISH);
    }
  }
}
