package com.ameron32.apps.projectbandit.object;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

@ParseClassName("CAction")
public class CAction extends AbsBanditObject<AbsBanditObject.Column> {
  
  public CAction() {}
  
  public String getAction() {
    return getString("action");
  }
  
  public ParseFile getActionPic() {
    return getParseFile("actionPic");
  }
  
  private static final AbsBanditObject.Column[] COLUMNS = {
    new Column("action", _DataType.String)
  };

  @Override public AbsBanditObject.Column get(
      int columnPosition) {
    return COLUMNS[columnPosition];
  }

  @Override public int getColumnCount() {
    return COLUMNS.length;
  }
}
