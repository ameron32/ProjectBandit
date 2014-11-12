package com.parse.parsepush.table;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;

public class TableParseAdapter extends ParseQueryAdapter<ParseObject> {
  
  private static final String  TAG = TableParseAdapter.class.getSimpleName();

  private final LayoutInflater inflater;

  public TableParseAdapter(Context context, com.parse.ParseQueryAdapter.QueryFactory<ParseObject> queryFactory,
      int tableRowLayoutResource, int cellLayoutResource, int cellTextViewResource) {
    super(context, queryFactory, tableRowLayoutResource);
    inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }
    
  @Override
  public View getItemView(final ParseObject object, View v, final ViewGroup parent) {
    v = super.getItemView(object, v, parent);

    TableListView tableListView = (TableListView) parent;
    tableListView.startRow(v, object).commitRow();

    return v;
  }

}