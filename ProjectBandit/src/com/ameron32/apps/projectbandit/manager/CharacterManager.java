package com.ameron32.apps.projectbandit.manager;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.ameron32.apps.projectbandit.adapter._QueryManager;
import com.ameron32.apps.projectbandit.manager.ContentManager.OnContentChangeListener;
import com.ameron32.apps.projectbandit.object.Character;

public class CharacterManager {
  protected static final String TAG = CharacterManager.class.getSimpleName();
  
  private static CharacterManager characterManager;
  
  public static CharacterManager get() {
    if (characterManager == null) {
      characterManager = new CharacterManager();
    }
    return characterManager;
  }
  
  private OnCharacterManagerInitializationCompleteListener mInitializationListener;
  public void initialize(
      final OnCharacterManagerInitializationCompleteListener listener) {
    mInitializationListener = listener;
    try {
      if (mCurrentCharacter == null) {
        findPlayableCharacters();
      }
      if (mCurrentChatCharacter == null) {
        findChatCharacters();
      }
    } catch (ParseException e) {
      e.printStackTrace();
    }
    if (mInitializationListener != null) {
      mInitializationListener.onCharacterManagerInitializationComplete();
      mInitializationListener = null;
    }
  }
  
  private void findPlayableCharacters() throws ParseException {
    List<Character> playableCharacters = _QueryManager._Character.getPlayableCharacters().find();
    
    mPlayableCharacters = playableCharacters;
    final int FIRST = 0;
    final Character character = playableCharacters.get(FIRST);
    setCurrentCharacter(character);
  }

  private void findChatCharacters() throws ParseException {
    List<Character> chatCharacters = _QueryManager._Character.getChatCharacters().find();
    
    mChatCharacters = chatCharacters;
    Character lastChatCharacter = UserManager.get().getCurrentUser().getLastChatCharacter();
    if (lastChatCharacter == null) {
      // did not find
      Log.i(TAG, "chat character was null");
      setChatCharacter(chatCharacters.get(0), 0);
      return; // done
    }
    
    for (int i = 0; i < chatCharacters.size(); i++) {
      if (lastChatCharacter.equals(chatCharacters.get(i))) {
        setChatCharacter(lastChatCharacter, i);
        return; // done
      }
    }
    
    // did not find
    Log.i(TAG, "no chat character found");
    setChatCharacter(chatCharacters.get(0), 0);
  }

  public interface OnCharacterManagerInitializationCompleteListener {
    public void onCharacterManagerInitializationComplete();
  }
  
  private List<Character> mPlayableCharacters;
  private List<Character> mChatCharacters;
  private Character mCurrentCharacter;
  private Character mCurrentChatCharacter;
  private int mCurrentChatCharacterPosition;
  
  protected CharacterManager() {
    mListeners = new ArrayList<OnCharacterChangeListener>();
  }
  
  public void setCurrentCharacter(
      Character character) {
    if (character.getBoolean("inGameCharacter")) {
      mCurrentCharacter = character;
      notifyListenersOfCharacterChange(character);
    }
    // did not set current character
    Log.i(TAG, "did not set current character. was not an inGameCharacter.");
  }
  
  public void setChatCharacter(
      Character character, int position) {
    mCurrentChatCharacter = character;
    mCurrentChatCharacterPosition = position;
    UserManager.get().getCurrentUser().setLastChatCharacter(character);
    notifyListenersOfChatCharacterChange(character);
  }
  
  public int getChatCharacterPosition() {
    return mCurrentChatCharacterPosition;
  }
  
  public Character getChatCharacter() {
    if (mCurrentChatCharacter == null) {
      throw new IllegalStateException("CurrentChatCharacter should not be null.");
    }
    return mCurrentChatCharacter;
  }
  
  public Character getCurrentCharacter() {
    if (mCurrentCharacter == null) {
      throw new IllegalStateException("CurrentCharacter should not be null.");
    }
    return mCurrentCharacter;
  }
  
  public static void destroy() {
    characterManager = null;
  }
  
  public void queryAllCharacters(
      FindCallback<Character> callback) {
    ParseQuery<Character> query = _QueryManager._Character.getChatCharacters();
    query.findInBackground(callback);
  }
  
  
  

  public List<OnCharacterChangeListener> mListeners;
  
  public boolean addOnCharacterChangeListener(
      OnCharacterChangeListener listener) {
    return mListeners.add(listener);
  }
  
  public boolean removeOnCharacterChangeListener(
      OnCharacterChangeListener listener) {
    return mListeners.remove(listener);
  }
  
  private void notifyListenersOfCharacterChange(Character character) {
    for (OnCharacterChangeListener listener : mListeners) {
      listener.onCharacterChange(this, character);
    }
  }
  
  private void notifyListenersOfChatCharacterChange(Character character) {
    for (OnCharacterChangeListener listener : mListeners) {
      listener.onChatCharacterChange(this, character);
    }
  }
  
  public interface OnCharacterChangeListener {
    
    public void onCharacterChange(
        CharacterManager manager,
        Character newCharacter);
    
    public void onChatCharacterChange(
        CharacterManager manager,
        Character newCharacter);
  }
}
