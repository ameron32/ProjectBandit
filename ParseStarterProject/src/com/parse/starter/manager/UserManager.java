package com.parse.starter.manager;

import com.parse.ParseUser;


public class UserManager {
  
  private static UserManager userManager = null;
  
  public static UserManager getUserManager() {
    if (userManager == null) {
      userManager = new UserManager();
    }
    return userManager;
  }
  
  protected UserManager() {
    
  }
  
  public ParseUser getCurrentUser() {
    return ParseUser.getCurrentUser();
  }
  
  public void logout() {
    ParseUser.logOut();
  }

  public boolean isLoggedIn() {
    return !(getCurrentUser() == null);
  }

  public void destroy() {
    userManager = null;
  }

}
