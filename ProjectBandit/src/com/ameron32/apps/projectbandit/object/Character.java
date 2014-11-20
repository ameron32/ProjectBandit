package com.ameron32.apps.projectbandit.object;

import com.ameron32.apps.projectbandit.SaveObjectAsync;
import com.ameron32.lib.recyclertableview.TableAdapter.Columnable;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;


@ParseClassName("Character") public class Character
    extends ParseObject 
    implements Columnable<String> {
  
  private String name = "Nameless";
  private int currentHealth = 0;
  private int maxHealth = 0;
  private int currentLevel = 0;
  private int currentXP = 0;
  private boolean isGameCharacter = false;
  
  public static Character getFromName(
      String name) {
    try {
      return ParseQuery.getQuery(Character.class).whereEqualTo("name", name).getFirst();
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  // public static Character create(String name) {
  // return new Character().setName(name);
  // }
  //
  // public static Character getFromParseObject(ParseObject character) {
  // return ParseObject.createWithoutData(Character.class,
  // character.getObjectId());
  // }
  //
  // private static Character fromParseObject(ParseObject character) {
  // final String objectId = character.getObjectId();
  // final String name = character.getString("name");
  // final int currentHealth = character.getInt("currentHealth");
  // final int maxHealth = character.getInt("maxHealth");
  // final int currentLevel = character.getInt("currentLevel");
  // final int currentXP = character.getInt("currentXP");
  //
  // Character newCharacter = new Character();
  // newCharacter.setName(name).setHealth(currentHealth,
  // maxHealth).setXP(currentXP, currentLevel);
  // newCharacter.setObjectId(objectId);
  // return newCharacter;
  // }
  
  public Character() {
    
  }
  
  public Character setName(String name) {
    this.name = name;
    return this;
  }
  
  public Character setHealth(
      int currentHealth, int maxHealth) {
    this.currentHealth = currentHealth;
    this.maxHealth = maxHealth;
    return this;
  }
  
  public Character setXP(int currentXP,
      int currentLevel) {
    this.currentLevel = currentLevel;
    this.currentXP = currentXP;
    return this;
  }
  
  public Character setGameCharacter(
      boolean isGameCharacter) {
    this.isGameCharacter = isGameCharacter;
    return this;
  }
  
  public void send() {
    applyToParseObject();
    
    new SaveObjectAsync(new SaveObjectAsync.OnSaveCallbacks() {
      
      @Override public void onComplete() {}
      
      @Override public void onBegin() {}
    }).execute(this);
  }
  
  public void send(
      SaveObjectAsync.OnSaveCallbacks callback) {
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
  
  
  
  public String getName() {
    return this.getString("name");
  }
  
  public int getCurrentHealth() {
    return this.getInt("currentHealth");
  }
  
  public int getMaxHealth() {
    return this.getInt("maxHealth");
  }
  
  public int getLevel() {
    return this.getInt("currentLevel");
  }
  
  public int getXP() {
    return this.getInt("currentXP");
  }
  
  public boolean isPlayable() {
    return this.getBoolean("inGameCharacter");
  }
  
  public int getGold() {
    return this.getInt("currentGold");
  }
  
  public String getUrlFullSize() {
    return this.getString("profilePicFullSizeUrl");
  }
  
  @Override public String get(
      int columnPosition) {
    if (isHeader) { return getColumnHeader(columnPosition); }
    
    switch (columnPosition) {
    case 0:
      return getName();
    case 1:
      return "playable:"
          + isPlayable();
    case 2:
      if (isPlayable())
      return getCurrentHealth() + "/"
          + getMaxHealth();
      else
        return "";
    case 3:
      if (isPlayable())
      return getLevel() + "["
          + getXP() + "]";
      else
        return "";
    case 4:
      if (isPlayable())
      return "$"+getGold();
      else
        return "";
    default:
      return "none";
    }
  }
  
  @Override public int getColumnCount() {
    return 5;
  }
  
  @Override public String getColumnHeader(
      int columnPosition) {
    switch (columnPosition) {
    case 0:
      return "name";
    case 1:
      return "isPlayable";
    case 2:
      return "health/max";
    case 3:
      return "level[xp]";
    case 4:
      return "gold";
    default:
      return "none";
    }
  }
  
  private boolean isHeader = false;
  @Override public void useAsHeaderView(boolean b) {
    isHeader = b;
  }
  
  @Override public boolean isHeaderView() {
    return isHeader;
  }
  
  private boolean isIdEquals(Character character) {
    if (this.getObjectId().equalsIgnoreCase(character.getObjectId())) { return true; }
    return false;
  }
  
  @Override public boolean equals(
      Object o) {
    return this.equals((Character) o);
  }
  
  public boolean equals(
      Character character) {
    return this.isIdEquals(character);
  }
}
