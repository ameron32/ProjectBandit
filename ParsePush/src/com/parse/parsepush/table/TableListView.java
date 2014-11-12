package com.parse.parsepush.table;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.parsepush.ParseObjectUtils;

public class TableListView extends ListView {
  
  private static final String        TAG     = TableListView.class.getSimpleName();

  private List<TableListView.Column> columns = new ArrayList<TableListView.Column>();
  private final LayoutInflater       inflater;

  private int                        tableRowLayoutResource;
  private int                        cellLayoutResource;
  private int                        cellTextViewResource;
  
  private LinearLayout               headerView;

  public TableListView(Context context) {
    super(context);
    inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }
  
  public TableListView(Context context, AttributeSet attrs) {
    super(context, attrs);
    inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }
  
  public TableListView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }
  
  public void setColumns(List<TableListView.Column> columns, int tableRowLayoutResource, int cellLayoutResource,
      int cellTextViewResource) {
    cleanInput(tableRowLayoutResource, cellLayoutResource, cellTextViewResource);
    
    this.columns = columns;
    generateHeaderView(tableRowLayoutResource, cellLayoutResource, cellTextViewResource);
  }
  
  private void cleanInput(int tableRowLayoutResource, int cellLayoutResource, int cellTextViewResource) {
    this.tableRowLayoutResource = tableRowLayoutResource;
    this.cellTextViewResource = cellTextViewResource;
    this.cellLayoutResource = cellLayoutResource;
    catchCastExceptions();
  }
  
  private void catchCastExceptions() {
    try {
      LinearLayout layout = (LinearLayout) inflater.inflate(tableRowLayoutResource, null);
    }
    catch (ClassCastException e) {
      Log.e(TAG, "tableRowLayoutResource MUST be of type LINEARLAYOUT");
      e.printStackTrace();
    }
    
    try {
      View cell = inflater.inflate(cellLayoutResource, null);
      TextView textView = (TextView) cell.findViewById(cellTextViewResource);
    }
    catch (ClassCastException e) {
      Log.e(TAG, "cellTextViewResource MUST be of type TEXTVIEW. MUST be within cellLayoutResource. and MUST be ");
      e.printStackTrace();
    }
  }

  private void generateHeaderView(int tableRowResource, int cellResource, int cellTextViewResource) {
    cleanInput(tableRowResource, cellResource, cellTextViewResource);

    if (getHeaderViewsCount() < 1 || headerView == null) {
      LinearLayout layout = (LinearLayout) inflater.inflate(tableRowResource, null);
      
      // moved from

      headerView = layout;
      addHeaderView(layout);
    }
    else {
      headerView.removeAllViews();
      
    }
    
    // moved to
    for (TableListView.Column column : this.columns) {
      View cell = inflater.inflate(cellResource, null);
      cell.setLayoutParams(new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT, column.width));
      TextView cellText = (TextView) cell.findViewById(cellTextViewResource);
      cellText.setText(column.name);
      headerView.addView(cell);
    }
  }
  
  public Row startRow(final View v, final ParseObject object) {
    LinearLayout layout = (LinearLayout) v;
    layout.removeAllViews();

    final ViewGroup parentView = layout;
    for (int i = 0; i < getColumnCount(); i++) {
      // create 1 cell per column
      final View cell = inflater.inflate(cellLayoutResource, null);
      final Column column = getColumn(i);
      final int col = i;
      
      cell.setOnClickListener(new OnClickListener() {
        
        @Override
        public void onClick(View v) {
          column.getOnCellClickListener().onCellClick(parentView, cell, col, object);
        }
      });
      cell.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, column.width));

      parentView.addView(cell);
    }
    final Row row = new Row(layout, object);
    return row;
  }

  public int getColumnCount() {
    return columns.size();
  }
  
  public TableListView.Column getColumn(int position) {
    return columns.get(position);
  }

  public int getTableWidth() {
    int tableSize = 0;
    for (int i = 0; i < columns.size(); i++) {
      tableSize += columns.get(i).width;
    }
    return tableSize;
  }

  @Override
  public void setOnClickListener(OnClickListener listener) {}
  
  @Override
  public void setOnItemClickListener(OnItemClickListener listener) {}
  
  @Override
  public void setOnItemLongClickListener(OnItemLongClickListener listener) {}
  
  @Override
  public void setOnItemSelectedListener(OnItemSelectedListener listener) {}

  public class Row {
    
    private final WeakReference<LinearLayout>      rowLayout;
    private final ParseObject object;
    private Color             bgColor;
    
    public Row(final LinearLayout rowLayout, ParseObject object) {
      this.rowLayout = new WeakReference<LinearLayout>(rowLayout);
      this.object = object;
    }

    public ParseObject getParseObject() {
      return object;
    }
    
    private void setColumnText(int columnPosition, String text) {
      if (rowLayout.get() != null) {
        final View cell = rowLayout.get().getChildAt(columnPosition);
        TextView cellText = (TextView) cell.findViewById(cellTextViewResource);
        cellText.setText(text);
      }
    }
    
    public void commitRow() {
      final Row row = this;
      for (int i = 0; i < getColumnCount(); i++) {
        final int ii = i;
        final int col = i;
        Column column = getColumn(i);
        String cellTextStr = "";
        if (column.isString()) {
          cellTextStr = object.getString(column.name);
        }
        if (column.isInt()) {
          cellTextStr = String.valueOf(object.getInt(column.name));
        }
        if (column.isObjectId()) {
          cellTextStr = object.getObjectId();
        }
        if (column.isBoolean()) {
          cellTextStr = String.valueOf(object.getBoolean(column.name));
        }
        if (column.isPointer()) {
          cellTextStr = "isPointer fail.";
        }
        if (column.isUserRelation()) {
          cellTextStr = "isUserRelation fail.";
          // try {
          // ParseRelation<ParseUser> relation = object
          // .getRelation(column.name);
          // List<ParseUser> users;
          // users = relation.getQuery().find();
          // StringBuilder sb = new StringBuilder();
          // for (int j = 0; j < users.size(); j++) {
          // ParseUser user = users.get(j);
          // if (j != 0) {
          // sb.append(", ");
          // }
          // sb.append(user.getString("username"));
          // }
          // // row.setColumnText(col, sb.toString());
          //
          // cellTextStr = sb.toString();
          // } catch (ParseException e) {
          // // TODO Auto-generated catch block
          // e.printStackTrace();
          // }
        }
        if (column.isRelation()) {
          cellTextStr = "...";
          
          ParseRelation<ParseObject> relation = object.getRelation(column.name);
          ParseQuery<ParseObject> query = relation.getQuery();
          query.fromLocalDatastore();
          query.findInBackground(new FindCallback<ParseObject>() {
            
            @Override public void done(
                List<ParseObject> results,
                ParseException e) {
              if (e == null) {
                // ParseObject.pinAll(results);
                String cellTextStr = ParseObjectUtils.getStringFromList(ParseObjectUtils.getStringOfValuesFromKey("name", results));
                setColumnText(ii, cellTextStr);
              }
            }
          });

        }
        if (column.isArray()) {
          cellTextStr = "isArray fail.";
          // cellTextStr = String.valueOf(object.getBoolean(column.name));
        }
        setColumnText(ii, cellTextStr);
      }
//      rowLayout.clear();
    }
  }

  public static class Column implements Parcelable {
    
    public final String         name;
    public final int            width;
    private final DataType      type;
    
    private OnCellClickListener listener;
    
    @Override
    public int describeContents() {
      return 0;
    }
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
      dest.writeString(name);
      dest.writeInt(width);
      dest.writeString(type.name());
    }
    
    public static final Parcelable.Creator<Column> CREATOR = new Creator<Column>() {

                                                             @Override
                                                             public Column createFromParcel(Parcel source) {
                                                               String name = source.readString();
                                                               int width = source.readInt();
                                                               String type = source.readString();
                                                               Column column = new Column(name, width, DataType.valueOf(type));
                                                               return column;
                                                             }

                                                             @Override
                                                             public Column[] newArray(int size) {
                                                               return new Column[size];
                                                             }
                                                           };

    public Column(String name, int width, DataType type) {
      super();
      this.name = name;
      this.width = width;
      this.type = type;
      this.listener = new OnCellClickListener() {
        
        @Override
        public void onCellClick(ViewGroup parentRowLayout, View cellView, int columnPosition, ParseObject object) {}
      };
    }

    public boolean isString() {
      return (this.type == DataType.String);
    }
    
    public boolean isInt() {
      return (this.type == DataType.Integer);
    }
    
    public boolean isObjectId() {
      return (this.type == DataType.ObjectId);
    }
    
    public boolean isBoolean() {
      return (this.type == DataType.Boolean);
    }
    
    public boolean isPointer() {
      return (this.type == DataType.Pointer);
    }
    
    public boolean isRelation() {
      return (this.type == DataType.Relation);
    }

    public boolean isUserRelation() {
      return (this.type == DataType.UserRelation);
    }
    
    public boolean isArray() {
      return (this.type == DataType.Array);
    }

    public enum DataType {
      String, Integer, ObjectId, Boolean, Pointer, UserRelation, Relation, Array
    }
    
    public Column setOnCellClickListener(OnCellClickListener listener) {
      this.listener = listener;
      return this;
    }
    
    public OnCellClickListener getOnCellClickListener() {
      return listener;
    }
  }

  public interface OnCellClickListener {
    
    public void onCellClick(ViewGroup parentRowLayout, View cellView, int columnPosition, ParseObject object);
  }
}