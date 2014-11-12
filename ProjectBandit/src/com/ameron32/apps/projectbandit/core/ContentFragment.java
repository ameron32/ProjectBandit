package com.ameron32.apps.projectbandit.core;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.Optional;

import com.ameron32.apps.projectbandit.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class ContentFragment extends
    Fragment {
  /**
   * The fragment argument representing the section number for this
   * fragment.
   */
  private static final String ARG_SECTION_NUMBER = "section_number";
  
  /**
   * Returns a new instance of this fragment for the given section
   * number.
   */
  public static ContentFragment newInstance(
  // int sectionNumber
  ) {
    ContentFragment fragment = new ContentFragment();
    Bundle args = new Bundle();
    // args.putInt(ARG_SECTION_NUMBER, sectionNumber);
    fragment.setArguments(args);
    return fragment;
  }
  
  public ContentFragment() {}
  
  @Optional @InjectView(R.id.section_label) TextView label;
  
  @Override public View onCreateView(
      LayoutInflater inflater,
      ViewGroup container,
      Bundle savedInstanceState) {
    // INFLATE FRAGMENT_CORE.XML
    View rootView = inflater.inflate(R.layout.fragment_core, container, false);
    FrameLayout frame = ((FrameLayout) rootView.findViewById(R.id.custom_element));
    if (getCustomResource() != 0) {
      // INFLATE CUSTOM VIEW FROM RESOURCE INTO FRAME
      View customView = inflater.inflate(getCustomResource(), container, false);
      frame.addView(customView);
    }
    ButterKnife.inject(this, rootView);
    return rootView;
  }
  
  protected int getCustomResource() {
    return 0;
  }
  
//  protected void onDestroyCustomView() {}
  
//  protected View onCreateCustomView(
//      LayoutInflater inflater,
//      ViewGroup container,
//      Bundle savedInstanceState) {
//    return inflater.inflate(getCustomResource(), container, false);
//  }
  
  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.reset(this);
//    onDestroyCustomView();
  }
  
  @Override public void onViewCreated(
      View view,
      Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    
    // TODO: why did this stop working?
    // int sectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);
    // label.setText("Section "
    // + sectionNumber);
  }
  
  @Override public void onAttach(
      Activity activity) {
    super.onAttach(activity);
    // int sectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);
    // ((CoreActivity) activity).onSectionAttached(sectionNumber);
  }
}