package com.parse.parsepush.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.parse.DeleteCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseQueryAdapter.QueryFactory;
import com.parse.parsepush.table.TableFragment;
import com.parse.parsepush.table.TableListView.Column;
import com.parse.parsepush.table.TableListView.Column.DataType;
import com.parse.parsepush.table.TableListView.OnCellClickListener;

public class ItemTableFragment extends TableFragment implements OnCellClickListener {
  
  private static final String PARSE_QUERY_CLASS = "Item";
  
  public static ItemTableFragment createFragment() {
    ItemTableFragment f = new ItemTableFragment();
    return f;
  }
  
  @Override
  public QueryFactory<ParseObject> create() {
    return new ParseQueryAdapter.QueryFactory<ParseObject>() {
      
      @Override
      public ParseQuery<ParseObject> create() {
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(PARSE_QUERY_CLASS);
        query.orderByAscending("type");
        query.addAscendingOrder("name");
        return query;
      }
    };
  }
  
  @Override
  public List<Column> getColumns() {
    ArrayList<Column> dummyArray = new ArrayList<Column>();
    
    dummyArray.add(new Column("x", 1, DataType.String).setOnCellClickListener(selector));
    dummyArray.add(new Column("objectId", 3, DataType.ObjectId).setOnCellClickListener(this));
    dummyArray.add(new Column("type", 3, DataType.String).setOnCellClickListener(this));
    dummyArray.add(new Column("name", 6, DataType.String).setOnCellClickListener(this));
    dummyArray.add(new Column("baseValue", 2, DataType.Integer).setOnCellClickListener(this));
    dummyArray.add(new Column("usableInGame", 2, DataType.Relation).setOnCellClickListener(this));
    
    return dummyArray;
  }
  
  @Override
  public void onCellClick(ViewGroup parentRowLayout, View cellView, int columnPosition, final ParseObject object) {
    if (columnPosition == 1) {
      new AlertDialog.Builder(getActivity()).setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
        
        @Override
        public void onClick(final DialogInterface dialog, int which) {
          final String objectId = object.getObjectId();
          object.deleteInBackground(new DeleteCallback() {
            
            @Override
            public void done(ParseException e) {
              if (e == null) {
                dialog.dismiss();
                Toast.makeText(getActivity(), "Item deleted. [" + objectId + "]", Toast.LENGTH_SHORT).show();
              }
              else {
                Toast.makeText(getActivity(), "There was an error deleting this item.", Toast.LENGTH_SHORT).show();
              }
            }
          });
        }
      }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        
        @Override
        public void onClick(DialogInterface dialog, int which) {
          dialog.dismiss();
        }
      })
      .create().show();
    }
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
