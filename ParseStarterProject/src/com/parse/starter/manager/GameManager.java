package com.parse.starter.manager;

import java.util.List;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.starter.object.Game;

public class GameManager {
  
  private static final String TAG         = GameManager.class.getSimpleName();
  private static GameManager  gameManager = null;
  private Game                currentGame;
  
  public static GameManager getGameManager() {
    if (gameManager == null) {
      gameManager = new GameManager();
      gameManager.initialize();
    }
    return gameManager;
  }
  
  protected GameManager() {}

  private void initialize() {
    new ParseQuery<ParseObject>("Game").whereEqualTo("players", UserManager.getUserManager().getCurrentUser()).findInBackground(new FindCallback<ParseObject>() {
      
      @Override
      public void done(List<ParseObject> objects, ParseException e) {
        if (e == null) {
          if (objects.size() == 0) {
            doneWithNoResults();
          }
          if (objects.size() == 1) {
            doneWithOneResult(objects.get(0));
          }
          else {
            doneWithSeveralResults(objects);
          }
        }
        else {
          e.printStackTrace();
        }
      }
      
      private void doneWithNoResults() {
        // TODO game manager initializes with NO GAME
        Log.i(TAG, "game returned with no results.");
      }
      
      private void doneWithOneResult(ParseObject game) {
        currentGame = (Game) game;
      }
      
      private void doneWithSeveralResults(List<ParseObject> games) {
        // TODO game manager initializes with MULTIPLE GAMES, needs a chooser
        Log.i(TAG, "game returned with several results.");
      }
    });
  }
  
  public void changeGame(Game newGame) {
    gameManager.currentGame = newGame;
  }

  public Game getCurrentGame() {
    return currentGame;
  }

  public void destroy() {
    gameManager = null;
  }
}
