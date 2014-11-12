package com.ameron32.apps.projectbandit.manager;

import java.util.ArrayList;
import java.util.List;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.ameron32.apps.projectbandit.adapter._QueryManager;
import com.ameron32.apps.projectbandit.manager.ContentManager.OnContentChangeListener;
import com.ameron32.apps.projectbandit.object.Character;

public class CharacterManager {
  
  private static CharacterManager characterManager;
  
  public static CharacterManager get() {
    if (characterManager == null) {
      characterManager = new CharacterManager();
    }
    return characterManager;
  }
  
  private Character currentCharacter;
  private Character currentChatCharacter;
  private int currentChatCharacterPosition;
  
  protected CharacterManager() {
    listeners = new ArrayList<OnCharacterChangeListener>();
  }
  
  public void setCurrentCharacter(
      Character character) {
    if (character.getBoolean("inGameCharacter")) {
      currentCharacter = character;
    }
    
    notifyListenersOfCharacterChange(character);
  }
  
  public void setChatCharacter(
      Character character, int position) {
    currentChatCharacter = character;
    currentChatCharacterPosition = position;
  }
  
  public int getChatCharacterPosition() {
    return currentChatCharacterPosition;
  }
  
  public Character getChatCharacter() {
    return currentChatCharacter;
  }
  
  public Character getCurrentCharacter() {
    if (currentCharacter == null) {
      // TODO: get off of UI Thread
      try {
        setCurrentCharacter(_QueryManager._Character.getPlayableCharacters().getFirst());
      } catch (ParseException e) {
        e.printStackTrace();
      }
    }
    return currentCharacter;
  }
  
  public static void destroy() {
    characterManager = null;
  }
  
  public void queryAllCharacters(
      FindCallback<Character> callback) {
    ParseQuery<Character> query = _QueryManager._Character.getChatCharacters();
    query.findInBackground(callback);
  }
  
  
  

  public List<OnCharacterChangeListener> listeners;
  
  public boolean addOnCharacterChangeListener(
      OnCharacterChangeListener listener) {
    return listeners.add(listener);
  }
  
  public boolean removeOnCharacterChangeListener(
      OnCharacterChangeListener listener) {
    return listeners.remove(listener);
  }
  
  private void notifyListenersOfCharacterChange(Character character) {
    for (OnCharacterChangeListener listener : listeners) {
      listener.onCharacterChange(this, character);
    }
  }
  
  public interface OnCharacterChangeListener {
    public void onCharacterChange(
        CharacterManager manager,
        Character newCharacter);
  }
}
