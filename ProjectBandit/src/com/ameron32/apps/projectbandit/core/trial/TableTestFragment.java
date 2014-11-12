package com.ameron32.apps.projectbandit.core.trial;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.ameron32.apps.projectbandit.R;
import com.ameron32.apps.projectbandit.adapter._QueryManager;
import com.ameron32.apps.projectbandit.manager.CharacterManager;
import com.ameron32.apps.projectbandit.object.Character;
import com.ameron32.lib.recyclertableview.TableAdapter;
import com.ameron32.lib.recyclertableview.TableAdapter.Columnable;
import com.ameron32.lib.recyclertableview.TableLayoutManager;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;

public class TableTestFragment extends
    SectionContainerTestFragment {
  
  public static TableTestFragment create(
      String parseClassName,
      int layoutResource) {
    TableTestFragment fragment = new TableTestFragment();
    Bundle args = new Bundle();
    args.putString(PARSE_CLASS_NAME, parseClassName);
    args.putInt(LAYOUT_RESOURCE, layoutResource);
    fragment.setArguments(args);
    return fragment;
  }
  
  private static final String LAYOUT_RESOURCE = "layoutResource";
  private int mLayoutResource;
  
  private static final String PARSE_CLASS_NAME = "parseClassName";
  private String mParseClassName;
  
  @Override public void onCreate(
      Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    
    if (getArguments() != null) {
      Bundle args = getArguments();
      mParseClassName = args.getString(PARSE_CLASS_NAME);
      mLayoutResource = args.getInt(LAYOUT_RESOURCE);
    }
  }
  
  @Override protected int onReplaceFragmentLayout(
      int storedLayoutResource) {
    return R.layout.fragment_default_table_layout;
  }
  
  @InjectView(R.id.my_recycler_view) RecyclerView mRecyclerView;
  private TableLayoutManager mLayoutManager;
  private TableAdapter<String> mAdapter;
  
  @Override public void onViewCreated(
      View view,
      Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.inject(this, view);
    
    mRecyclerView.setHasFixedSize(true);
    
    mLayoutManager = new TableLayoutManager(getActivity());
    mRecyclerView.setLayoutManager(mLayoutManager);
    
    mAdapter = new TableAdapter<String>(null,
        // rootview must be TableRowLayout
        R.layout.simple_table_row_layout,
        // rootview must contain id
        R.layout.simple_table_cell_textview_container, R.id.textview);
      mRecyclerView.setAdapter(mAdapter);
      
      mRecyclerView.addOnItemTouchListener(
        new TableLayoutManager.RecyclerCellClickListener(getActivity(), 
          new TableLayoutManager.RecyclerCellClickListener.OnCellClickListener() {
        
        @Override public void onCellClick(
            View view, int rowPosition,
            int columnPosition) {
          // implement logic
        }
      }));
      
      _QueryManager.getDefaultQuery(mParseClassName, getCallback());
      
//    CharacterManager.get().queryAllCharacters();
  }  
  
  private <O extends ParseObject> FindCallback<O> getCallback() {
    return new FindCallback<O>() {
      
      @Override public void done(
          List<O> objects,
          ParseException e) {
        if (e != null) {
          e.printStackTrace();
          return;
        }
        
        List<Columnable<String>> convert = new ArrayList<Columnable<String>>();
        for (int i = 0; i < objects.size(); i++) {
          O object = objects.get(i);
          // cast O to ParseObject type
          if (object instanceof Columnable<?>) {
            Columnable<String> cObject = (Columnable<String>) object;
            convert.add(cObject);
          }
        }
        
        mAdapter = new TableAdapter<String>(convert,
        // rootview must be TableRowLayout
        R.layout.simple_table_row_layout,
        // rootview must contain id
        R.layout.simple_table_cell_textview_container, R.id.textview);
        mRecyclerView.setAdapter(mAdapter);
        
        mRecyclerView.addOnItemTouchListener(new TableLayoutManager.RecyclerCellClickListener(getActivity(), new TableLayoutManager.RecyclerCellClickListener.OnCellClickListener() {
          
          @Override public void onCellClick(
              View view,
              int rowPosition,
              int columnPosition) {
            // implement logic
          }
        }));
      }
    };
  }
}
