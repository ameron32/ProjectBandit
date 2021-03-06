package com.ameron32.apps.projectbandit.manager;

import java.util.ArrayList;
import java.util.List;

import com.ameron32.apps.projectbandit.manager.CharacterManager.OnCharacterManagerInitializationCompleteListener;
import com.ameron32.apps.projectbandit.object.Character;
import com.ameron32.apps.projectbandit.object.Game;
import com.ameron32.apps.projectbandit.object.User;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;


public class UserManager extends AbsManager {
  
  private static UserManager userManager = null;
  
  public static UserManager get() {
    if (userManager == null) {
      userManager = new UserManager();
    }
    return userManager;
  }
  
  List<Game> gamesUserIsPlayer = new ArrayList<Game>();
  
  public void initialize(
      final OnUserManagerInitializationCompleteListener listener) {
    initialize();
    if (listener != null) {
      listener.onUserManagerInitializationComplete();
    }
  }
  
  public void initialize() {
    Character lastChatCharacter = getCurrentUser().getLastChatCharacter();
    if (lastChatCharacter == null) {

      return;
    }
    
    // lastChatCharacter is not null
    try {
      Character lcc = lastChatCharacter.fetchIfNeeded();
    } catch (ParseException e) {
      e.printStackTrace();
    }
    setInitialized(true);
  }
  
  public interface OnUserManagerInitializationCompleteListener {
    public void onUserManagerInitializationComplete();
  }
  
  protected UserManager() {}
  
  public User getCurrentUser() {
    User currentUser = (User) ParseUser.getCurrentUser();
    if (currentUser == null) { throw new IllegalStateException("ParseUser was null, but should never be null."); }
    // User user = ParseUser.createWithoutData(User.class,
    // currentUser.getObjectId());
    return currentUser;
  }
  
  public void setGamesOfCurrentUser(
      List<Game> games) {
    gamesUserIsPlayer = games;
  }
  
  public List<Game> getStoredGamesOfCurrentUser() {
    return gamesUserIsPlayer;
  }
  
  public void findCharactersOfCurrentUser(
      FindCallback<Character> callback) {
    ParseQuery<Character> query = ParseQuery.getQuery(Character.class);
    query.whereEqualTo("owner", getCurrentUser());
    query.findInBackground(callback);
  }
  
  public void logout() {
    CharacterManager.destroy();
    GameManager.destroy();
    ParseUser.logOut();
  }
  
  public boolean isLoggedIn() {
    return !(ParseUser.getCurrentUser() == null);
  }
  
  public static void destroy() {
    userManager = null;
  }
}
