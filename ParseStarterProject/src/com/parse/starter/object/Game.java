package com.parse.starter.object;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Game")
public class Game extends ParseObject {
  
  public Game() {}
  
  public int getCurrentSession() {
    return getInt("currentSession");
  }
  
  public String getName() {
    return getString("name");
  }
}
