package com.ameron32.apps.projectbandit.core.fragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

import com.ameron32.apps.projectbandit.R;
import com.ameron32.apps.projectbandit.adapter.IconAdapter_v2;
import com.ameron32.apps.projectbandit.manager.ContentManager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MenuIconFragment_v2 extends
    Fragment
    implements
    ContentManager.OnContentChangeListener {
  
  private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";
  
  @InjectView(R.id.recyclerview) RecyclerView mRecyclerView;
  private IconAdapter_v2 mAdapter;
  private boolean mFromSavedInstanceState;
  private int mCurrentSelectedPosition;
  
  @Override public void onCreate(
      Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // loadFragmentArguments();
    loadSavedState(savedInstanceState);
  }
  
  private void loadSavedState(
      Bundle savedInstanceState) {
    if (savedInstanceState != null) {
      mCurrentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION);
      mFromSavedInstanceState = true;
    }
  }
  
  @Override public View onCreateView(
      LayoutInflater inflater,
      ViewGroup container,
      Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_basic_recyclerview, container, false);
  }
  
  @Override public void onViewCreated(
      View view,
      Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.inject(this, view);
    
    configureRecyclerView();
    selectItem(mCurrentSelectedPosition);
  }
  
  private void configureRecyclerView() {
    
    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    mRecyclerView.setLayoutManager(layoutManager);
    mRecyclerView.setHasFixedSize(true);
    
    mAdapter = new IconAdapter_v2(getActivity(), R.layout.row_nav_icon_only, R.id.imageview, ContentManager.get().getImageIcons());
    mRecyclerView.setAdapter(mAdapter);
  }
  
  @Override public void onResume() {
    super.onResume();
    ContentManager.get().addOnContentChangeListener(this);
  }
  
  @Override public void onPause() {
    super.onPause();
    ContentManager.get().removeOnContentChangeListener(this);
  }
  
  @Override public void onSaveInstanceState(
      Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
  }
  
  @Override public void onContentChange(
      ContentManager manager,
      int position) {
    selectItem(position);
  }
  
  private void selectItem(int position) {
    mCurrentSelectedPosition = position;
    mAdapter.selectPosition(position);
  }
}