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
import com.parse.ParseQuery;
import com.parse.ParseUser;


public class UserManager {
  
  private static UserManager userManager = null;
  
  public static UserManager get() {
    if (userManager == null) {
      userManager = new UserManager();
    }
    return userManager;
  }
  
  public void initialize(
      final OnUserManagerInitializationCompleteListener listener) {
    Character lastChatCharacter = getCurrentUser().getLastChatCharacter();
    lastChatCharacter.fetchIfNeededInBackground(new GetCallback<Character>() {
      
      @Override public void done(
          Character lastChatCharacter,
          ParseException e) {
        if (e == null) {
//          lastChatCharacter.pinInBackground();
          // getCurrentUser().setLastChatCharacter(lastChatCharacter);
          if (listener != null) {
            listener.onUserManagerInitializationComplete();
          }
        } else {
          e.printStackTrace();
        }
      }
    });
    
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
  
  List<Game> gamesUserIsPlayer = new ArrayList<Game>();
  
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
