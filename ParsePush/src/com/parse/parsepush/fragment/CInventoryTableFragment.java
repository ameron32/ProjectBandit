package com.parse.parsepush.fragment;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseQueryAdapter.QueryFactory;
import com.parse.parsepush.table.TableFragment;
import com.parse.parsepush.table.TableListView.Column;
import com.parse.parsepush.table.TableListView.Column.DataType;
import com.parse.parsepush.table.TableListView.OnCellClickListener;

public class CInventoryTableFragment extends TableFragment implements OnCellClickListener {
  
  private static final String PARSE_QUERY_CLASS = "CInventory";
  
  private ArrayList<Column>   dummyArray;
  private String              sortBy            = "name";

  public static CInventoryTableFragment createFragment() {
    CInventoryTableFragment f = new CInventoryTableFragment();
    return f;
  }
  
  @Override
  public QueryFactory<ParseObject> create() {
    return new ParseQueryAdapter.QueryFactory<ParseObject>() {
      
      @Override
      public ParseQuery<ParseObject> create() {
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(PARSE_QUERY_CLASS);
        query.orderByAscending(sortBy);
        // query.addAscendingOrder("type");
        return query;
      }
    };
  }
  
  @Override
  public List<Column> getColumns() {
    dummyArray = new ArrayList<Column>();
    
    dummyArray.add(new Column("x", 1, DataType.String).setOnCellClickListener(selector));
    dummyArray.add(new Column("objectId", 3, DataType.ObjectId).setOnCellClickListener(this));
    dummyArray.add(new Column("owner", 3, DataType.Relation).setOnCellClickListener(this));
    dummyArray.add(new Column("type", 3, DataType.String).setOnCellClickListener(this));
    dummyArray.add(new Column("name", 6, DataType.String).setOnCellClickListener(this));
    dummyArray.add(new Column("quantity", 2, DataType.Integer).setOnCellClickListener(this));
    dummyArray.add(new Column("baseValue", 2, DataType.Integer).setOnCellClickListener(this));

    return dummyArray;
  }
  
  @Override
  public void onCellClick(ViewGroup parentRowLayout, View cellView, int columnPosition, final ParseObject object) {
    sortBy = dummyArray.get(columnPosition).name;

    Toast.makeText(getActivity(), "Now sorting by: " + sortBy, Toast.LENGTH_SHORT).show();
    resetTableAdapter();
  }
  
  final OnCellClickListener selector = new OnCellClickListener() {
                                       
                                       @Override
                                       public void onCellClick(ViewGroup parentRowLayout, View cellView,
                                           int columnPosition, ParseObject object) {
                                         boolean selected = false;
                                         if (cellView.getTag() == null) {
                                           selected = true;
                                           cellView.setTag(selected);
                                         }
                                         else {
                                           selected = !((Boolean) cellView.getTag());
                                           cellView.setTag(new Boolean(selected));
                                         }
                                         
                                         if (selected) {
                                           cellView.setBackgroundColor(Color.RED);
                                         }
                                         else {
                                           cellView.setBackgroundResource(android.R.color.transparent);
                                         }
                                       }
                                     };


}
