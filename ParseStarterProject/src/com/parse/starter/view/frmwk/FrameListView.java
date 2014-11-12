package com.parse.starter.view.frmwk;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.parse.starter.R;

public abstract class FrameListView
    extends
    RelativeLayout {
  
  private ListView listView;
  private final Context context;

  public FrameListView(Context context,
      AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    this.context = context;
  }
  
  public FrameListView(Context context,
      AttributeSet attrs) {
    super(context, attrs);
    this.context = context;
  }
  
  public FrameListView(Context context) {
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
    listView = (ListView) viewContainingListView.findViewById(R.id.listView1);
    // listView.setAdapter(new ParseObjectAdapter(getActivity()));
    listView.setAdapter(createAdapter(context));
    // new ParseQueryAdapter<ParseObject>(context, className, itemViewResource);
  }
  
  
  protected abstract ListAdapter createAdapter(
      Context context);


  protected void onDestroy() {}
  
  protected void onCreate() {}


  public void destroy() {
    onDestroy();
  }
  
  protected ListAdapter getAdapter() {
    return listView.getAdapter();
  }

}
