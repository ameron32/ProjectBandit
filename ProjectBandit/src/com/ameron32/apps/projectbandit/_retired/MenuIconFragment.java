package com.ameron32.apps.projectbandit._retired;

import com.ameron32.apps.projectbandit.R;
import com.ameron32.apps.projectbandit.manager.ContentManager;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * A fragment representing a list of Items.
 * <p />
 * <p />
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class MenuIconFragment
//extends
//    ListFragment
//    implements
//    ContentManager.OnContentChangeListener
// implements OnFragmentInteractionListener
{
  
//  // TODO: Rename parameter arguments, choose names that match
//  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//  private static final String ARG_PARAM1 = "param1";
//  private static final String ARG_PARAM2 = "param2";
//  
//  // TODO: Rename and change types of parameters
//  private String mParam1;
//  private String mParam2;
//  
//  // private OnFragmentInteractionListener mListener;
//  
//  // TODO: Rename and change types of parameters
//  public static MenuIconFragment newInstance(
//      String param1, String param2) {
//    MenuIconFragment fragment = new MenuIconFragment();
//    Bundle args = new Bundle();
//    args.putString(ARG_PARAM1, param1);
//    args.putString(ARG_PARAM2, param2);
//    fragment.setArguments(args);
//    return fragment;
//  }
//  
//  /**
//   * Mandatory empty constructor for the fragment manager to instantiate the
//   * fragment (e.g. upon screen orientation changes).
//   */
//  public MenuIconFragment() {}
//  
//  @Override public void onCreate(
//      Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//    
//    if (getArguments() != null) {
//      mParam1 = getArguments().getString(ARG_PARAM1);
//      mParam2 = getArguments().getString(ARG_PARAM2);
//    }
//    
//    // TODO: Change Adapter to display your content
//    setListAdapter(new IconAdapter(getActivity(), R.layout.row_nav_icon_only, R.id.imageview, ContentManager.get().getImageIcons()));
//  }
//  
//  
//  @Override public void onAttach(
//      Activity activity) {
//    super.onAttach(activity);
//    try {
//      // mListener = (OnFragmentInteractionListener) activity;
//    } catch (ClassCastException e) {
//      throw new ClassCastException(activity.toString()
//          + " must implement OnFragmentInteractionListener");
//    }
//  }
//  
//  @Override public void onDetach() {
//    super.onDetach();
//    // mListener = null;
//  }
//  
//  @Override public void onPause() {
//    super.onPause();
//    ContentManager.get().removeOnContentChangeListener(this);
//  }
//  
//  @Override public void onResume() {
//    super.onResume();
//    ContentManager.get().addOnContentChangeListener(this);
//  }
//  
//  
//  @Override public void onListItemClick(
//      ListView l, View v, int position,
//      long id) {
//    super.onListItemClick(l, v, position, id);
//    
//    ContentManager.get().setCurrentSelectedFragmentPosition(position);
//    // if (null != mListener) {
//    // Notify the active callbacks interface (the activity, if the
//    // fragment is attached to one) that an item has been selected.
//    // mListener.onFragmentInteraction(DummyContent.ITEMS.get(position).id);
//    // }
//  }
//  
//  @Override public void onContentChange(
//      ContentManager manager, int position) {
//    selectItem(position);
//  }
//  
//  private void selectItem(int position) {
//    getListView().setItemChecked(position, true);
//  }
//  
//  /**
//   * This interface must be implemented by activities that contain this
//   * fragment to allow an interaction in this fragment to be communicated
//   * to the activity and potentially other fragments contained in that
//   * activity.
//   * <p>
//   * See the Android Training lesson <a href=
//   * "http://developer.android.com/training/basics/fragments/communicating.html"
//   * >Communicating with Other Fragments</a> for more information.
//   */
//  // public interface OnFragmentInteractionListener {
//  // // TODO: Update argument type and name
//  // public void onFragmentInteraction(
//  // String id);
//  // }
  
}
