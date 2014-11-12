package com.parse.parsepush.fragment;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseQueryAdapter.QueryFactory;
import com.parse.ParseUser;
import com.parse.parsepush.table.TableFragment;
import com.parse.parsepush.table.TableListView.Column;
import com.parse.parsepush.table.TableListView.Column.DataType;
import com.parse.parsepush.table.TableListView.OnCellClickListener;

public class CharacterTableFragment extends TableFragment implements OnCellClickListener {
  
  private static final String PARSE_QUERY_CLASS = "Character";
  
  public static CharacterTableFragment createFragment() {
    CharacterTableFragment f = new CharacterTableFragment();
    return f;
  }
  
  @Override
  public QueryFactory<ParseObject> create() {
    return new ParseQueryAdapter.QueryFactory<ParseObject>() {
      
      @Override
      public ParseQuery<ParseObject> create() {
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(PARSE_QUERY_CLASS);
        
        query.orderByAscending("name");
        // query.addAscendingOrder("baseValue");
        return query;
      }
    };
  }
  
  @Override
  public List<Column> getColumns() {
    ArrayList<Column> dummyArray = new ArrayList<Column>();
    
    dummyArray.add(new Column("x", 1, DataType.String).setOnCellClickListener(selector));
    dummyArray.add(new Column("objectId", 3, DataType.ObjectId).setOnCellClickListener(this));
    dummyArray.add(new Column("name", 3, DataType.String).setOnCellClickListener(this));
    dummyArray.add(new Column("ooc", 3, DataType.Boolean).setOnCellClickListener(this));
    dummyArray.add(new Column("owner", 3, DataType.UserRelation).setOnCellClickListener(this));
    
    return dummyArray;
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
                                           cellView.setTag(selected);
                                         }
                                         
                                         if (selected) {
                                           cellView.setBackgroundColor(Color.RED);
                                         }
                                         else {
                                           cellView.setBackgroundResource(android.R.color.transparent);
                                         }
                                       }
                                     };

  @Override
  public void onCellClick(ViewGroup parentRowLayout, View cellView, int columnPosition, ParseObject object) {
    // TODO Auto-generated method stub
    Toast.makeText(getActivity(), "column#: " + columnPosition + " & objectId: " + object.getObjectId(), Toast.LENGTH_SHORT).show();
  }
}
