package com.parse.parsepush;

import java.util.ArrayList;
import java.util.List;

import com.parse.ParseObject;

public class ParseObjectUtils {
  
  public static String getStringFromList(List<String> list) {
    return list.toString();
  }
  
  public static List<String> getStringOfValuesFromKey(String key, List<ParseObject> objects) {
    ArrayList<String> values = new ArrayList<String>();
    for (ParseObject object : objects) {
      values.add(object.getString(key));
    }
    return values;
  }
}
