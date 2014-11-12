package com.parse.starter.object;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

@ParseClassName("CAction")
public class CAction extends ParseObject {
  
  public CAction() {}
  
  public String getAction() {
    return getString("action");
  }
  
  public ParseFile getActionPic() {
    return getParseFile("actionPic");
  }
}
