package com.parse.parsepush.table;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;
import com.parse.ParseQueryAdapter.OnQueryLoadListener;
import com.parse.ParseQueryAdapter.QueryFactory;
import com.parse.parsepush.R;
import com.parse.parsepush.table.TableListView.Column;

/**
 * A placeholder fragment containing a simple view.
 */
public abstract class TableFragment
    extends Fragment {
  
  private static final String TAG = TableFragment.class.getSimpleName();
  
  private TableListView mListView;
  private ParseQueryAdapter<ParseObject> adapter;
  private ProgressBar mProgressBar;

  public TableFragment() {}
  
  @Override public View onCreateView(
      LayoutInflater inflater,
      ViewGroup container,
      Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_query_viewer, container, false);
    mListView = (TableListView) rootView.findViewById(R.id.listView1);
    mProgressBar = (ProgressBar) rootView.findViewById(R.id.progressBar1);

    resetTableAdapter();

    return rootView;
  }

  private void setTableAdapter(
      TableListView tableListView,
      List<Column> columns) {
    
    tableListView.setColumns(columns, R.layout.table_row, R.layout.table_cell, R.id.textview_table_cell_text);
    
    QueryFactory<ParseObject> factory = create();
    
    adapter = new TableParseAdapter(getActivity(), factory, R.layout.table_row, R.layout.table_cell, R.id.textview_table_cell_text);
    adapter.setPaginationEnabled(false);
    adapter.addOnQueryLoadListener(new OnQueryLoadListener<ParseObject>() {
      
      @Override public void onLoaded(
          List<ParseObject> objects,
          Exception e) {
        mProgressBar.setVisibility(View.GONE);
      }
      
      @Override public void onLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
      }
    });
    tableListView.setAdapter(adapter);
  }
  
  public abstract QueryFactory<ParseObject> create();
  
  public abstract List<Column> getColumns();
  
  protected void resetTableAdapter() {
    final List<Column> columns = getColumns();
    setTableAdapter(mListView, columns);
  }
}
