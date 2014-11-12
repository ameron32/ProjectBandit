package com.ameron32.apps.projectbandit.manager;

import com.ameron32.apps.projectbandit.object.Character;
import com.ameron32.apps.projectbandit.object.Game;
import com.ameron32.apps.projectbandit.object.User;
import com.parse.FindCallback;
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
  
  protected UserManager() {}
  
  public User getCurrentUser() {
    User currentUser = (User) ParseUser.getCurrentUser();
    if (currentUser == null) {
      throw new IllegalStateException("ParseUser was null, but should never be null.");
    }
//    User user = ParseUser.createWithoutData(User.class, currentUser.getObjectId());
    return currentUser;
  }
  
  public void findGamesOfCurrentUser(FindCallback<Game> callback) {
    ParseQuery<Game> query = ParseQuery.getQuery(Game.class);
    query.whereEqualTo("players", getCurrentUser());
    query.findInBackground(callback);
  }
  
  public void findCharactersOfCurrentUser(FindCallback<Character> callback) {
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
