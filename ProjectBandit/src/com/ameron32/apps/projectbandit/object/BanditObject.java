package com.ameron32.apps.projectbandit.object;

import com.parse.ParseObject;

public abstract class BanditObject 
  extends ParseObject 
{
 
  public BanditObject() {
    // REQUIRED
  }
  
  protected String asString(final Column c) {
    switch(c.dataType) {
    case Integer:
      return String.valueOf(this.getInt(c.key));
    case String:
      return this.getString(c.key);
    case Boolean:
      return String.valueOf(this.getBoolean(c.key));

    case Array:
    case Date:
    case ListOfStrings:
    case Pointer:
    case Relation:
    default:
      return "unhandled";
    }
  }
  
  protected static abstract class Column {
    public String key;
    public _DataType dataType;
    
    public Column(String key,
        _DataType dataType) {
      this.key = key;
      this.dataType = dataType;
    }
  }
}
