package com.ameron32.apps.projectbandit.core.trial;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.ameron32.apps.projectbandit.R;
import com.ameron32.apps.projectbandit.adapter._QueryManager;
import com.ameron32.apps.projectbandit.core.fragment.ChatManagerFragment;
import com.ameron32.apps.projectbandit.manager.CharacterManager;
import com.ameron32.apps.projectbandit.object.Character;
import com.ameron32.lib.recyclertableview.TableAdapter;
import com.ameron32.lib.recyclertableview.TableRowLayout;
import com.ameron32.lib.recyclertableview.TableAdapter.Columnable;
import com.ameron32.lib.recyclertableview.TableLayoutManager;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;

public class TableTestFragment extends
    SectionContainerTestFragment {
  
  private static final boolean TOAST = false;
  private static final boolean LOG = true;
  private static final String TAG = TableTestFragment.class.getSimpleName();
  
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
  
  @InjectView(R.id.my_recycler_view) 
    RecyclerView mRecyclerView;
  @InjectView(R.id.my_recycler_header) 
    FrameLayout mHeaderView;
  private TableLayoutManager mLayoutManager;
  private TableAdapter<String> mAdapter;
  private Columnable<String> mColumningHeaderObject;
  
  @Override public void onViewCreated(
      View view,
      Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.inject(this, view);
    
    mColumningHeaderObject = null;
    try {
      final String fullClassName = "com.ameron32.apps.projectbandit.object." + mParseClassName;
      final Class parseClass = Class.forName(fullClassName);
      final Object parseObject = parseClass.newInstance();
      final ParseObject headerObject = (ParseObject) parseObject;
      mColumningHeaderObject = (Columnable<String>) parseObject;
      mColumningHeaderObject.useAsHeaderView(true);
      
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (java.lang.InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (ClassCastException e) {
      e.printStackTrace();
    }
    if (mColumningHeaderObject == null) {
      // something is wrong with the PARSE_CLASS_NAME param
      // at this point we abandon our efforts to display any information
      if (LOG) Log.e(TAG, "columningHeaderObject is lacking either: ParseObject, Columnable<String>");
      return;
    }
    
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
        
        if (LOG) Log.i(TAG, "getCallback() results = " + objects.size());
        
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
        
        // TODO: is mRecyclerView the correct "parent"?
        mAdapter.setHeaderObject(mColumningHeaderObject);
        setHeaderRow(mAdapter.getHeaderRow(mRecyclerView));
      }
    };
  }
  
  private void setHeaderRow(View v) {
    mHeaderView.addView(v);
  }
}
