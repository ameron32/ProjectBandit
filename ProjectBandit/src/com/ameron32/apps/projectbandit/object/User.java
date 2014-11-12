package com.ameron32.apps.projectbandit.object;

import com.parse.ParseClassName;
import com.parse.ParseUser;

@ParseClassName("_User") public class User
    extends ParseUser {
  
  public User() {}
  
  public Character getLastChatCharacter() {
    Character lastChatCharacter = (Character) this.getParseObject("lastChatCharacter");
    if (lastChatCharacter == null) {
      lastChatCharacter = Character.getFromName("Spectator");
    }
    return lastChatCharacter;
  }
  
  public void setLastChatCharacter(
      Character chatCharacter) {
    this.put("lastChatCharacter", chatCharacter);
    this.saveInBackground();
  }
  
  public CAction getLastCharacterAction() {
    CAction characterAction = (CAction) this.getParseObject("lastCharacterAction");
    return characterAction;
  }
  
  public void setLastCharacterAction(
      CAction characterAction) {
    this.put("lastCharacterAction", characterAction);
    this.saveInBackground();
  }
  
  public boolean isIdEquals(User user) {
    if (this.getObjectId().equalsIgnoreCase(user.getObjectId())) { return true; }
    return false;
  }
  
}
