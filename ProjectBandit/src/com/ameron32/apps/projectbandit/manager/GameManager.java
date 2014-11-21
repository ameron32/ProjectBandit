package com.ameron32.apps.projectbandit.manager;

import java.util.Iterator;
import java.util.List;

import android.util.Log;

import com.ameron32.apps.projectbandit.adapter._QueryManager;
import com.ameron32.apps.projectbandit.object.Game;
import com.ameron32.apps.projectbandit.object.User;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class GameManager {
  
  private static final String TAG = GameManager.class.getSimpleName();
  private static final boolean TOAST = false;
  private static final boolean LOG = false;
  
  private static GameManager gameManager = null;
  
  public static GameManager get() {
    if (gameManager == null) {
      gameManager = new GameManager();
    }
    return gameManager;
  }
  
  public static void changeGame(
      Game newGame) {
    gameManager.currentGame = newGame;
  }
  
  private Game currentGame;
  private boolean isCurrentUserGM = false;
  
  protected GameManager() {}
  
  public void initialize(final OnGameManagerInitializationCompleteListener listener) {
    try {
      List<User> gamemasters = currentGame.getGM();
      if (LOG) {
        Log.i(TAG, "initializing isCurrentUserGM");
      }
      isCurrentUserGM = false;
      for (User user : gamemasters) {
        if (LOG) {
          Log.i(TAG, "user: "
              + user.getObjectId()
              + " / currentUser: "
              + UserManager.get().getCurrentUser().getObjectId());
        }
        if (user.equals(UserManager.get().getCurrentUser())) {
          isCurrentUserGM = true;
        }
      }
      
      if (listener != null) {
        listener.onGameManagerInitializationComplete();
      }
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }
  
  public void selectAGame(
      FindCallback<Game> callback) {
    _QueryManager._Game.getCurrentGamesQuery().findInBackground(callback);
  }
  
  public boolean isCurrentUserGM() {
    return isCurrentUserGM;
  }
  
  public Game getCurrentGame() {
    // if (currentGame == null) {
    // throw new
    // IllegalStateException("Must initialize a game. Use GameManager#initialize.");
    // }
    return currentGame;
  }
  
  public static void destroy() {
    gameManager = null;
  }
  
  public interface OnGameManagerInitializationCompleteListener {
    public void onGameManagerInitializationComplete();
  }
}
