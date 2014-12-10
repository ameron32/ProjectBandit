package com.ameron32.apps.projectbandit.manager;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.ameron32.apps.projectbandit.object.Character;
import com.ameron32.apps.projectbandit.object.Game;
import com.ameron32.apps.projectbandit.object.Item;
import com.ameron32.apps.projectbandit.object.User;
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
  
  public static List<String> toListOfStrings(List<? extends ParseObject> list, String key) {
    List<String> strings = new ArrayList<String>();
    for (int i = 0; i < list.size(); i++) {
      ParseObject o = list.get(i);
      strings.add(o.getString(key));
    }
    return strings;
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
  
  public static Game addPlayerToGame(User user, Game game) {
    addRelation(user, "players", game);
    return game;
  }
  
  public static Character addGameToCharacter(Character character, Game game) {
    addRelation(character, "ofGame", game);
    return character;
  }
  
  private static void addRelation(ParseObject object, String relation, ParseObject objectToAdd) {
    ParseRelation<ParseObject> relation1 = object.getRelation(relation);
    relation1.add(objectToAdd);
  }
  
  public static void addItemToGame(Item item, Game game) {
    ParseRelation<Game> relation = item.getRelation("usableInGame");
    relation.add(game);
  }
}
