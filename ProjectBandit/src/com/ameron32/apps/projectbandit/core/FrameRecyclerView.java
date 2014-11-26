package com.ameron32.apps.projectbandit.core;

import com.ameron32.apps.projectbandit.R;
import com.ameron32.apps.projectbandit.adapter.AbsMessageAdapter;
import com.ameron32.lib.recyclertableview.TableAdapter;
import com.ameron32.lib.recyclertableview.TableLayoutManager;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public abstract class FrameRecyclerView
    extends RelativeLayout 
    implements AbsMessageAdapter.OnDataSetChangedListener 
{
  
  private RecyclerView mRecyclerView;
  private final Context context;
  private LinearLayoutManager mLayoutManager;
  private AbsMessageAdapter mAdapter;
  
  public FrameRecyclerView(
      Context context,
      AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    this.context = context;
  }
  
  public FrameRecyclerView(
      Context context,
      AttributeSet attrs) {
    super(context, attrs);
    this.context = context;
  }
  
  public FrameRecyclerView(
      Context context) {
    super(context);
    this.context = context;
  }
  
  public void create() {
    initListView(this);
    initTitle(this);
    onCreate();
  }
  
  private void initTitle(
      View viewContainingTitleView) {
    TextView titleView = (TextView) viewContainingTitleView.findViewById(R.id.textview_chat_title);
    onCreateTitleView(titleView);
  }
  
  protected void onCreateTitleView(
      TextView titleView) {
    titleView.setText(this.getClass().getSimpleName());
  }
  
  private void initListView(
      View viewContainingListView) {
    mRecyclerView = (RecyclerView) viewContainingListView.findViewById(R.id.recyclerlistView1);
    // mRecyclerView.setAdapter(createAdapter(context));
    
    
    mRecyclerView.setHasFixedSize(true);
    
    mLayoutManager = new LinearLayoutManager(context);
    mLayoutManager.setStackFromEnd(true);
    mRecyclerView.setLayoutManager(mLayoutManager);
//    mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    
    mAdapter = createAdapter(context);
    mRecyclerView.setAdapter(mAdapter);
    mAdapter.addOnDataSetChangedListener(this);
  }
  
  @Override public void onDataSetChanged() {
    mRecyclerView.scrollToPosition(mAdapter.getItemCount() - 1);
  }
  
  protected abstract AbsMessageAdapter createAdapter(
      Context context);
  
  protected void onDestroy() {}
  
  protected void onCreate() {}
  
  public void destroy() {
    mAdapter.removeOnDataSetChangedListener(this);
    onDestroy();
  }
  
  protected AbsMessageAdapter getAdapter() {
    return mAdapter;
  }
  
}
