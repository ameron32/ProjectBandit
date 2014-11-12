package com.ameron32.apps.projectbandit.object;

import com.parse.FindCallback;
import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;

@ParseClassName("Game")
public class Game extends ParseObject {
  
  public Game() {}
  
  public int getCurrentSession() {
    return getInt("currentSession");
  }
  
  public String getName() {
    return getString("name");
  }
  
  
  
  public void getGM(FindCallback<User> callback) {
    ParseRelation<User> relation = this.getRelation("gm");
    ParseQuery<User> query = relation.getQuery();
    query.findInBackground(callback);
  }
  
  public void getPlayers(FindCallback<ParseUser> callback) {
    ParseRelation<ParseUser> relation = this.getRelation("players");
    ParseQuery<ParseUser> query = relation.getQuery();
    query.findInBackground(callback);
  }
  
  @Override public String toString() {
    return getName();
  }
}
