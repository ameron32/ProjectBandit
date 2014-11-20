package com.ameron32.apps.projectbandit.core.trial;

import android.os.Bundle;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.ameron32.apps.projectbandit.R;
import com.ameron32.apps.projectbandit.adapter.SectionGridPagerAdapter;
import com.ameron32.apps.projectbandit.core.fragment.AbsContentFragment;
import com.jess.ui.TwoWayGridView;

public class GridPagerFragment extends
    AbsContentFragment {
  

  /**
   * TODO: consider section number repairs or removal
   */
  private static final String ARG_SECTION_NUMBER = "section_number";

  public static GridPagerFragment newInstance(
      int sectionNumber) {
    GridPagerFragment fragment = new GridPagerFragment();
    Bundle args = new Bundle();
    args.putInt(ARG_SECTION_NUMBER, sectionNumber);
    fragment.setArguments(args);
    return fragment;
  }
  
  public GridPagerFragment() {
    // required empty constructor by fragment
  }
  
  @InjectView(R.id.gridview) TwoWayGridView gridView;
  
  @Override protected int getCustomLayoutResource() {
    return R.layout.fragment_grid_core;
  }
  
  @Override public void onViewCreated(
      View view,
      Bundle savedInstanceState) {
    ButterKnife.inject(this, view);
    super.onViewCreated(view, savedInstanceState);
    
    gridView.setAdapter(new SectionGridPagerAdapter(getActivity()));
  }
}
