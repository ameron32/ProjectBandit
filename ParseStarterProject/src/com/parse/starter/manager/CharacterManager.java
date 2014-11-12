package com.parse.starter.manager;

import com.parse.starter.object.Character;

public class CharacterManager {
  
  private static CharacterManager characterManager;
  
  public static CharacterManager getCharacterManager() {
    if (characterManager == null) {
      characterManager = new CharacterManager();
    }
    return characterManager;
  }
  
  private Character currentCharacter;
  
  private CharacterManager() {}
  
  public void setCurrentCharacter(Character character) {
    currentCharacter = character;
  }
  
  public Character getCurrentCharacter() {
    return currentCharacter;
  }

  public void destroy() {
    characterManager = null;
  }
}
