package com.parse.starter.object;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.starter.SaveObjectAsync;

@ParseClassName("Character")
public class Character extends ParseObject {
  
  private String name = "Nameless";
  private int     currentHealth   = 0;
  private int     maxHealth       = 0;
  private int     currentLevel    = 0;
  private int     currentXP       = 0;
  private boolean isGameCharacter = false;

  public static Character create(String name) {
    return new Character().setName(name);
  }
  
  public static Character getFromParseObject(ParseObject character) {
    return ParseObject.createWithoutData(Character.class, character.getObjectId());
  }
  
  private static Character fromParseObject(ParseObject character) {
    final String objectId = character.getObjectId();
    final String name = character.getString("name");
    final int currentHealth = character.getInt("currentHealth");
    final int maxHealth = character.getInt("maxHealth");
    final int currentLevel = character.getInt("currentLevel");
    final int currentXP = character.getInt("currentXP");
    
    Character newCharacter = new Character();
    newCharacter.setName(name).setHealth(currentHealth, maxHealth).setXP(currentXP, currentLevel);
    newCharacter.setObjectId(objectId);
    return newCharacter;
  }

  public Character() {

  }
  
  public Character setName(String name) {
    this.name = name;
    return this;
  }
  
  public Character setHealth(int currentHealth, int maxHealth) {
    this.currentHealth = currentHealth;
    this.maxHealth = maxHealth;
    return this;
  }
  
  public Character setXP(int currentXP, int currentLevel) {
    this.currentLevel = currentLevel;
    this.currentXP = currentXP;
    return this;
  }
  
  public Character setGameCharacter(boolean isGameCharacter) {
    this.isGameCharacter = isGameCharacter;
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
  
  public void send(SaveObjectAsync.OnSaveCallbacks callback) {
    applyToParseObject();
    
    new SaveObjectAsync(callback).execute(this);
  }
  
  private void applyToParseObject() {
    this.put("name", name);
    this.put("currentHealth", currentHealth);
    this.put("maxHealth", maxHealth);
    this.put("currentLevel", currentLevel);
    this.put("currentXP", currentXP);
    this.put("isGameCharacter", isGameCharacter);
  }
}
