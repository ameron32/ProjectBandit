package com.ameron32.apps.projectbandit.core;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.ameron32.apps.projectbandit.R;
import com.ameron32.apps.projectbandit.manager.CharacterManager;
import com.ameron32.apps.projectbandit.manager.GameManager;
import com.ameron32.apps.projectbandit.manager.GameManager.OnInitializeCompleteListener;
import com.ameron32.apps.projectbandit.manager.ContentManager;
import com.ameron32.apps.projectbandit.manager.MessageManager;
import com.ameron32.apps.projectbandit.manager.ObjectManager;
import com.ameron32.apps.projectbandit.manager.UserManager;
import com.ameron32.apps.projectbandit.object.Game;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ui.ParseLoginBuilder;

public class GatewayActivity extends
    ActionBarActivity implements
    OnInitializeCompleteListener {
  
  private static final String TAG = GatewayActivity.class.getSimpleName();
  private static final Class PRIMARY_ACTIVITY = ExpandedCoreActivity.class;
  
  @Override protected void onCreate(
      Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_gateway);
  }
  
  @Override protected void onResume() {
    super.onResume();
    
    /**
     * IF USER IS ALREADY LOGGED IN, CONTINUE TO MAIN ACTIVITY. IF USER IS NOT
     * LOGGED IN, GOTO LOGIN ACTIVITY. WILL GO TO MAIN ACTIVITY AFTER SUCCESSFUL
     * LOGIN. WILL ABORT--finish()--IF CANCELLED.
     */
    if (!UserManager.get().isLoggedIn()) {
      GameManager.destroy();
      CharacterManager.destroy();
      ObjectManager.destroy();
      MessageManager.destroy();
      ContentManager.destroy();
      startLoginActivity();
    } else {
      moveToStructureActivity();
    }
  }
  
  /**
   * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   * LOGIN ACTIVITY RELATED SECTION
   * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   */
  
  private static final int LOGIN_REQUEST_CODE = 4647;
  
  private void startLoginActivity() {
    ParseLoginBuilder builder = new ParseLoginBuilder(getActivity());
    builder.setAppLogo(R.drawable.ic_launcher);
    startActivityForResult(builder.build(), LOGIN_REQUEST_CODE);
  }
  
  @Override protected void onActivityResult(
      int requestCode, int resultCode,
      Intent arg2) {
    if (requestCode == LOGIN_REQUEST_CODE) {
      if (resultCode == RESULT_OK) {
        moveToStructureActivity();
      }
    }
  }
  
  public boolean lock = false;
  private void moveToStructureActivity() {
    if (!lock) {
      lock = true;
      if (GameManager.get().getCurrentGame() == null) {
        GameManager.get().selectAGame(new FindCallback<Game>() {
          
          @Override public void done(
              List<Game> games,
              ParseException e) {
            if (e == null) {
              switch (games.size()) {
              case 0:
                noGames();
                break;
              case 1:
                oneGame(games.get(0));
                break;
              default:
                multipleGames(games);
              }
            }
          }
        });
      }
    }
    // else {
    // continueToStructureActivity();
    // }
  }
  
  private void continueToStructureActivity() {
    GameManager.get().setOnInitializeCompleteListener(this);
    GameManager.get().initialize();
  }
  
  public void onInitializeComplete() {
    Intent beginStructureActivity = new Intent(getActivity(), PRIMARY_ACTIVITY);
    startActivity(beginStructureActivity);
    finish();
  }
  
  private void noGames() {
    // TODO game manager initializes with NO GAME
    Log.i(TAG, "game returned with no results.");
    // do not continueToStructureActivity() without a game
  }
  
  private void oneGame(Game game) {
    // select this game, transparently
    GameManager.changeGame(game);
    continueToStructureActivity();
  }
  
  private void multipleGames(
      final List<Game> games) {
    // TODO game manager initializes with MULTIPLE GAMES, needs a chooser
    Log.i(TAG, "game returned with several results.");
    
    Builder builder = new AlertDialog.Builder(getActivity());
    builder.setAdapter(new ArrayAdapter<Game>(getActivity(), R.layout.row_dropdown, games), new DialogInterface.OnClickListener() {
      
      @Override public void onClick(
          DialogInterface dialog,
          int which) {
        dialog.dismiss();
        GameManager.changeGame(games.get(which));
        continueToStructureActivity();
      }
    });
    builder.create().show();
  }
  
  private Activity getActivity() {
    return GatewayActivity.this;
  }
}
