package com.ameron32.apps.projectbandit.core.fragment;

import android.os.Bundle;
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



public abstract class AbsContentFragment
  extends Fragment 
{
  
  public AbsContentFragment() {}
  
  @Optional @InjectView(R.id.section_label) TextView label;
  
  @Override public final View onCreateView(
      LayoutInflater inflater,
      ViewGroup container,
      Bundle savedInstanceState) {
    // INFLATE FRAGMENT_CORE.XML
    View rootView = inflater.inflate(R.layout.fragment_core, container, false);
    FrameLayout frame = ((FrameLayout) rootView.findViewById(R.id.custom_element));
    if (getCustomLayoutResource() != 0) {
      // INFLATE CUSTOM VIEW FROM RESOURCE INTO FRAME
      int layoutResource = onReplaceFragmentLayout(getCustomLayoutResource());
      View customView = inflater.inflate(layoutResource, container, false);
      frame.addView(customView);
    }
    ButterKnife.inject(this, rootView);
    return rootView;
  }
  
  protected abstract int getCustomLayoutResource();
  protected int onReplaceFragmentLayout(int storedLayoutResource) {
    return storedLayoutResource;
  }
  
  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.reset(this);
  }
}