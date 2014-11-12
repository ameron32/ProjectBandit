package com.parse.starter;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ListView;


public class TestListView extends ListView {
  
  private static final String TAG = TestListView.class.getSimpleName();

  public TestListView(Context context) {
    super(context);
    // TODO Auto-generated constructor stub
  }
  
  public TestListView(Context context, AttributeSet attrs) {
    super(context, attrs);
    // TODO Auto-generated constructor stub
  }
  
  public TestListView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    // TODO Auto-generated constructor stub
  }
  
  @Override
  public boolean performItemClick(View view, int position, long id) {
    
    Log.d(TAG, "start");
    
    boolean performItemClick = super.performItemClick(view, position, id);
    
    Log.d(TAG, "end w/ " + performItemClick);

    return performItemClick;
  }
  
}
