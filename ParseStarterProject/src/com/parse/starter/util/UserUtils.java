package com.parse.starter.util;

import com.parse.ParseACL;
import com.parse.ParseUser;

public class UserUtils {
  
  public static void setUserACLToFullAccess(ParseUser user) {
    /*
     * I read that this allows near total access to the data across Parse. Not
     * safe but very flexible.
     */
    ParseACL postACL = new ParseACL(user);
    postACL.setPublicReadAccess(true);
    postACL.setPublicWriteAccess(true);
  }
}
