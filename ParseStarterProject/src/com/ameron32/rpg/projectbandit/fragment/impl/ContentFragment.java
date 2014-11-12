package com.ameron32.rpg.projectbandit.fragment.impl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.starter.R;

/**
 * A fragment representing a single Fragment detail screen.
 */
public class ContentFragment extends Fragment {
  
  /**
   * The fragment argument representing the item ID that this fragment
   * represents.
   */
  public static final String     ARG_ITEM_ID = "item_id";
  
  /**
   * The dummy content this fragment is presenting.
   */
  private FragmentChooser.FragmentWrapper mItem;
  
  private View                   mRootView;
  
  /**
   * Mandatory empty constructor for the fragment manager to instantiate the
   * fragment (e.g. upon screen orientation changes).
   */
  public ContentFragment() {}
  
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
  }
  
  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    // TODO content fragment default menu options
  }
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    mRootView = inflater.inflate(R.layout.fragment_fragment_detail, container, false);
    
    // Show the dummy content as text in a TextView.
    onCreateView(mRootView, savedInstanceState);
    
    return mRootView;
  }
  
  protected void onCreateView(View view, Bundle savedInstanceState) {
    if (mItem != null) {
      ((TextView) view.findViewById(R.id.fragment_detail)).setText(mItem.content + " ......... " + mItem.content
          + " ......... " + mItem.content);
    }
  }
}
