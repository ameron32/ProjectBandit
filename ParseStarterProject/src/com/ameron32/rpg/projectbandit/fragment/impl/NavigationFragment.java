package com.ameron32.rpg.projectbandit.fragment.impl;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.ameron32.rpg.projectbandit.fragment.impl.FragmentChooser.FragmentWrapper;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseQueryAdapter.QueryFactory;
import com.parse.starter.R;
import com.parse.starter.adapter.impl.MainIconAdapter;
import com.parse.starter.manager.CharacterManager;
import com.parse.starter.manager.UserManager;
import com.parse.starter.object.Character;

/**
 * A list fragment representing a list of Fragments. This fragment also supports
 * tablet devices by allowing list items to be given an 'activated' state upon
 * selection. This helps indicate which item is currently being viewed in a
 * {@link ContentFragment}.
 * <p>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class NavigationFragment extends Fragment implements OnItemClickListener {
  
  /**
   * Argument Keys
   */
  private static final String ARGUMENT_KEY_LIST_TYPE   = "list_type";
  /**
   * The serialization (saved instance state) Bundle key representing the
   * activated item position. Only used on tablets.
   */
  private static final String STATE_ACTIVATED_POSITION = "activated_position";
  
  /**
   * The fragment's current callback object, which is notified of list item
   * clicks.
   */
  private Callbacks           mCallbacks               = sDummyCallbacks;
  
  /**
   * The current activated item position. Only used on tablets.
   */
  private int                 mActivatedPosition       = ListView.INVALID_POSITION;
  
  private ListView            mListView;

  /**
   * A callback interface that all activities containing this fragment must
   * implement. This mechanism allows activities to be notified of item
   * selections.
   */
  public interface Callbacks {
    
    /**
     * Callback for when an item has been selected.
     */
    public void onItemSelected(String id);
    
    /**
     * Callback for when the Character has been changed
     */
    public void onCharacterChanged(Character character);
    
    /**
     * Callback for option menu items
     */
    public boolean onOptionsItemSelectedWithinFragment(MenuItem item);
  }
  
  /**
   * A dummy implementation of the {@link Callbacks} interface that does
   * nothing. Used only when this fragment is not attached to an activity.
   */
  private static Callbacks sDummyCallbacks = new Callbacks() {
                                             
                                             @Override
                                             public void onItemSelected(String id) {}
                                             
                                             @Override
                                             public void onCharacterChanged(Character character) {}
                                             
                                             @Override
                                             public boolean onOptionsItemSelectedWithinFragment(MenuItem item) {
                                               return false;
                                             }
                                           };
  
  public static NavigationFragment create(NavigationFragment.Type type) {
    NavigationFragment navigationFragment = new NavigationFragment();
    Bundle args = new Bundle();
    args.putString(ARGUMENT_KEY_LIST_TYPE, type.name());
    navigationFragment.setArguments(args);
    return navigationFragment;
  }
  
  public enum Type {
    Text, Icon
  }
  
  private Type getType() {
    Bundle arguments = this.getArguments();
    if (arguments != null && !arguments.isEmpty()) {
      String typeStr = arguments.getString(ARGUMENT_KEY_LIST_TYPE);
      return Type.valueOf(typeStr);
    }
    
    // default to ICON
    return Type.Icon;
  }

  /**
   * Mandatory empty constructor for the fragment manager to instantiate the
   * fragment (e.g. upon screen orientation changes).
   */
  public NavigationFragment() {}
  
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
  }
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    Type type = getType();
    ListAdapter adapter = null;
    switch (type) {
    case Text:
      ArrayAdapter<FragmentWrapper> textAdapter = new ArrayAdapter<FragmentChooser.FragmentWrapper>(getActivity(), R.layout.row_listview, R.id.textview, FragmentChooser.ITEM_LIST);
      adapter = textAdapter;
      setHasOptionsMenu(true);
      mRootView = inflater.inflate(R.layout.side_list_w_header, container, false);
      mListView = (ListView) mRootView.findViewById(R.id.listview_side);
      break;
    case Icon:
      MainIconAdapter iconAdapter = new MainIconAdapter(this, FragmentChooser.ITEM_LIST);
      adapter = iconAdapter;
      mRootView = inflater.inflate(R.layout.side_list_icons, container, false);
      mListView = (ListView) mRootView.findViewById(R.id.listview_side);
      break;
    }

    // TODO get a REAL adapter
    mListView.setAdapter(adapter);
    mListView.setOnItemClickListener(this);
    return mRootView;
  }
  
  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.standard, menu);
  }
  
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    return mCallbacks.onOptionsItemSelectedWithinFragment(item);
  }
  
  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    
    // Restore the previously serialized activated item position.
    if (savedInstanceState != null && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
      setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
    }
  }
  
  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);
    
    // Activities containing this fragment must implement its callbacks.
    if (!(activity instanceof Callbacks)) { throw new IllegalStateException("Activity must implement fragment's callbacks."); }
    
    mCallbacks = (Callbacks) activity;
  }
  
  @Override
  public void onDetach() {
    super.onDetach();
    
    // Reset the active callbacks interface to the dummy implementation.
    mCallbacks = sDummyCallbacks;
  }
  
  public void onItemClick(int position) {
    setActivatedPosition(position);
  }

  @Override
  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    // Notify the active callbacks interface (the activity, if the
    // fragment is attached to one) that an item has been selected.
    String fragmentId = FragmentChooser.getFragmentIdByPosition(position);
    mCallbacks.onItemSelected(fragmentId);
  }
  
  @Override
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    if (mActivatedPosition != ListView.INVALID_POSITION) {
      // Serialize and persist the activated item position.
      outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
    }
  }
  
  /**
   * Turns on activate-on-click mode. When this mode is on, list items will be
   * given the 'activated' state when touched.
   */
  public void setActivateOnItemClick(boolean activateOnItemClick) {
    // When setting CHOICE_MODE_SINGLE, ListView will automatically
    // give items the 'activated' state when touched.
    mListView.setChoiceMode(activateOnItemClick ? ListView.CHOICE_MODE_SINGLE : ListView.CHOICE_MODE_NONE);
  }
  
  public void setActivatedPosition(int position) {
    if (position == ListView.INVALID_POSITION) {
      mListView.setItemChecked(mActivatedPosition, false);
    }
    else {
      mListView.setItemChecked(position, true);
    }
    
    mActivatedPosition = position;
  }
  
  // private ParseObject currentCharacter;
  private Spinner characterSpinner;
  
  private View    mRootView;
  
  private void initSpinner() {
    Type type = getType();
    if (type == Type.Text) {
      characterSpinner = (Spinner) mRootView.findViewById(R.id.spinner_side);
      final ParseQueryAdapter<ParseObject> characterAdapter = new ParseQueryAdapter<ParseObject>(getActivity(), new QueryFactory<ParseObject>() {
        
        @Override
        public ParseQuery<ParseObject> create() {
          final ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Character");
          query.whereEqualTo("owner", UserManager.getUserManager().getCurrentUser());
          query.whereEqualTo("inGameCharacter", true);
          return query;
        }
      });
      characterAdapter.setTextKey("name");
      characterAdapter.setPaginationEnabled(false);
      characterSpinner.setAdapter(characterAdapter);
      characterSpinner.setSelection(1);
    }
  }
  
  private void closeSpinner() {
    Type type = getType();
    if (type == Type.Text) {
      mListView.removeFooterView(characterSpinner);
    }
  }
  
  private void initSpinnerListener() {
    Type type = getType();
    if (type == Type.Text) {
      characterSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
        
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
          final Object characterObject = parent.getAdapter().getItem(position);
          ParseObject newCharacter = (ParseObject) characterObject;
          Character character = Character.getFromParseObject(newCharacter);
          changeCharacter(character);
        }
        
        @Override
        public void onNothingSelected(AdapterView<?> parent) {
          // do nothing
        }
      });
    }
  }
  
  private void closeSpinnerListener() {
    // currentCharacter = null;
  }
  
  private void changeCharacter(Character character) {
    CharacterManager.getCharacterManager().setCurrentCharacter(character);
    mCallbacks.onCharacterChanged(character);
  }
  
  @Override
  public void onStart() {
    super.onStart();
    
    initSpinner();
    initSpinnerListener();
  }
  
  @Override
  public void onStop() {
    closeSpinnerListener();
    closeSpinner();
    
    super.onStop();
  }

}
