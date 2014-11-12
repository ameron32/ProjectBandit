package com.ameron32.apps.projectbandit.manager;

import java.util.List;

import android.util.Log;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;

public class _ParseUtils {
  public static String displayAsList(char separator, List<? extends ParseObject> list, String useKey) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < list.size(); i++) {
      if (i != 0) {
        sb.append(separator);
      }
      ParseObject obj = list.get(i);
      sb.append(obj.getString(useKey));
    }
    return sb.toString();
  }
  
  public static ParseObject addUserRelationToObject(String object1Type, String object1Name, String relation, String user2Type, String user2Name) {
    ParseObject targetCharacter1 = null;
    try {
      ParseUser parseUser = new ParseQuery<ParseUser>(user2Type)
          .whereContains("username", user2Name).getFirst();
      Log.d("PIA", "PUser.id = " + ((parseUser != null) ? parseUser.getObjectId() : "null"));
      targetCharacter1 = ParseQuery.getQuery(object1Type)
          .whereContains("name", object1Name).getFirst();
      ParseRelation<ParseUser> relation1 = targetCharacter1.getRelation(relation);
      relation1.add(parseUser);
      Log.d("PIA", relation + " [" + user2Name + "{" +user2Type+ "} into " + object1Name + "{" +object1Type+ "}] added");
    } catch (ParseException pEx) {
      pEx.printStackTrace();
      Log.d("PIA", relation + " [" + user2Name + "{" +user2Type+ "} into " + object1Name + "{" +object1Type+ "}] failed");
    }
    return targetCharacter1;
  }
  
  public static ParseObject addObjectRelationToObject(String object1Type, String object1Name, String relation, String object2Type, String object2Name) {
    ParseObject targetCharacter1 = null;
    try {
      ParseObject parseUser = new ParseQuery<ParseObject>(object2Type)
          .whereContains("name", object2Name).getFirst();
      targetCharacter1 = ParseQuery.getQuery(object1Type)
          .whereContains("name", object1Name).getFirst();
      ParseRelation<ParseObject> relation1 = targetCharacter1
          .getRelation(relation);
      relation1.add(parseUser);
      Log.d("PIA", relation + " [" + object2Name + "{" +object2Type+ "} into " + object1Name + "{" +object1Type+ "}] added");
    } catch (ParseException pEx) {
      pEx.printStackTrace();
      Log.d("PIA", relation + " [" + object2Name + "{" +object2Type+ "} into " + object1Name + "{" +object1Type+ "}] failed");
    }
    return targetCharacter1;
  }
}
