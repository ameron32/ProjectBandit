package com.ameron32.apps.projectbandit.core;

import com.ameron32.apps.projectbandit.R;
import com.ameron32.apps.projectbandit.adapter.ContentAdapter;
import com.ameron32.apps.projectbandit.manager.ContentManager;

import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

public class TextNavigationDrawerFragment
    extends NavigationDrawerFragment {
  
  @Override protected int getFragmentLayoutResourceId() {
    // TODO Auto-generated method stub
    return super.getFragmentLayoutResourceId();
  }
  
  @Override protected int getRecyclerViewResourceId() {
    // TODO Auto-generated method stub
    return super.getRecyclerViewResourceId();
  }
  
  @Override protected ContentAdapter createAdapter() {
    // return new ArrayAdapter<String>(getActivity(),
    // R.layout.row_nav_text_drawer, R.id.item_name,
    // ContentManager.get().getTitles());
    return ContentManager.get().getAdapter();
  }
  
}
