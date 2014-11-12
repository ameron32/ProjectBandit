package com.example.tableadaptertest;

import java.util.ArrayList;
import java.util.List;

import com.ameron32.lib.recyclertableview.TableAdapter;
import com.ameron32.lib.recyclertableview.TableAdapter.Columnable;
import com.ameron32.lib.recyclertableview.TableLayoutManager;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;



public class MainActivity extends
    ActionBarActivity {
  
  @Override protected void onCreate(
      Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
    }
  }
  
  
  @Override public boolean onCreateOptionsMenu(
      Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }
  
  @Override public boolean onOptionsItemSelected(
      MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    if (id == R.id.action_settings) { return true; }
    return super.onOptionsItemSelected(item);
  }
  
  /**
   * A placeholder fragment containing a simple view.
   */
  public static class PlaceholderFragment
      extends Fragment {
    
    private RecyclerView mRecyclerView;
    private TableLayoutManager mLayoutManager;
    private TableAdapter<ObjectMinor> mAdapter;
    
    public PlaceholderFragment() {}
    
    @Override public View onCreateView(
        LayoutInflater inflater,
        ViewGroup container,
        Bundle savedInstanceState) {
      View rootView = inflater.inflate(R.layout.fragment_main, container, false);
      return rootView;
    }
    
    @Override public void onViewCreated(
        View view,
        Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      
      Context context = getActivity();
      Activity contextOrView = getActivity();
      
      
      // instantiate your data set here
      List<Columnable<ObjectMinor>> DATA_SET = null;
      DATA_SET = generateDataset(100);
      
      mRecyclerView = (RecyclerView) contextOrView.findViewById(R.id.my_recycler_view);
      
      mRecyclerView.setHasFixedSize(true);
      
      mLayoutManager = new TableLayoutManager(context);
      mRecyclerView.setLayoutManager(mLayoutManager);
      
      mAdapter = new TableAdapter<ObjectMinor>(DATA_SET,
        // rootview must be TableRowLayout
        R.layout.simple_table_row_layout,
        // rootview must contain id
        R.layout.simple_table_cell_textview_container, R.id.textview);
      mRecyclerView.setAdapter(mAdapter);
      
      mRecyclerView.addOnItemTouchListener(new TableLayoutManager.RecyclerCellClickListener(context, new TableLayoutManager.RecyclerCellClickListener.OnCellClickListener() {
        
        @Override public void onCellClick(
            View view, int rowPosition,
            int columnPosition) {
          // implement logic
        }
      }));
    }
    
    private List<Columnable<ObjectMinor>> generateDataset(
        int rows) {
      List<Columnable<ObjectMinor>> c = new ArrayList<Columnable<ObjectMinor>>();
      for (int i = 0; i < rows; i++) {
        c.add(new ObjectPlus(i, 10));
      }
      return c;
    }
    
    public static class ObjectPlus
        implements Columnable<ObjectMinor> {
      
      private int columns;
      private ObjectMinor[] objects;
      
      public ObjectPlus(int row, int columns) {
        this.columns = columns;
        objects = new ObjectMinor[columns];
        for (int i = 0; i < columns; i++) {
          objects[i] = new ObjectMinor(row, i);
        }
      }
      
      @Override public ObjectMinor get(
          int columnPosition) {
        return objects[columnPosition];
      }
      
      @Override public int getColumnCount() {
        return columns;
      }
    }
    
    public static class ObjectMinor {
      int row;
      int column;
      
      public ObjectMinor(int row, int column) {
        this.row = row;
        this.column = column;
      }
      
      @Override public String toString() {
        return "( " + row + ", " + column + " )";
      }
    }
  }
}
