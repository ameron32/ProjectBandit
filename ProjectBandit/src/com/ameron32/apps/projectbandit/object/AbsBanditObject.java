package com.ameron32.apps.projectbandit.object;

import com.ameron32.lib.recyclertableview.TableAdapter.Columnable;
import com.parse.ParseObject;

public abstract class AbsBanditObject<T extends AbsBanditObject.Column> 
  extends ParseObject 
  implements Columnable<T>
{
 
  public AbsBanditObject() {
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
  
  protected static class Column {
    public String key;
    public _DataType dataType;
    
    public Column(String key,
        _DataType dataType) {
      this.key = key;
      this.dataType = dataType;
    }
  }

  private boolean isHeader = false; 
  
  @Override public String getColumnHeader(
      int columnPosition) {
    return this.getString(get(columnPosition).key);
  }

  @Override public void useAsHeaderView(
      boolean b) {
    isHeader = b;
  }

  @Override public boolean isHeaderView() {
    return isHeader;
  }
  
  public String getName() {
    return this.getString("name");
  }
}
