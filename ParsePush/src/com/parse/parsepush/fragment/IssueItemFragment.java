package com.parse.parsepush.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.EditText;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.parsepush.MultiSelectSpinner;
import com.parse.parsepush.R;
import com.parse.starter.object.CInventory;

/**
 * A placeholder fragment containing a simple view.
 */
public class IssueItemFragment extends Fragment {
  
  private MultiSelectSpinner itemSpinner;
  private MultiSelectSpinner characterSpinner;
  
  private List<ParseObject>  items;
  private List<ParseObject>  characters;

  public IssueItemFragment() {}
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_issue_item, container, false);
    prepareViews(rootView);
    return rootView;
  }
  
  private void prepareViews(View rootView) {
    EditText itemTypeET = (EditText) rootView.findViewById(R.id.edittext_item_type);
    EditText characterET = (EditText) rootView.findViewById(R.id.edittext_character);
    
    itemSpinner = (MultiSelectSpinner) rootView.findViewById(R.id.mss_item_selector);
    characterSpinner = (MultiSelectSpinner) rootView.findViewById(R.id.mss_character_selector);
    
    OnFocusChangeListener listener = new OnFocusChangeListener() {
      
      @Override
      public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus) {
          final int vId = v.getId();
          EditText editText = (EditText) v;
          String input = editText.getText().toString().trim();
          ParseQuery<ParseObject> query = null;

          switch (vId) {
          case R.id.edittext_item_type:
            query = new ParseQuery<ParseObject>("Item");
            if (input.length() != 0) {
              query.whereEqualTo("type", input);
            }
            break;
          case R.id.edittext_character:
            query = new ParseQuery<ParseObject>("Character");
            if (input.length() != 0) {
              query.whereEqualTo("name", input);
            }
            break;
          }

          query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> objects, ParseException e) {
              if (e == null && (objects.size() > 0)) {
                List<String> names = new ArrayList<String>();
                for (int i = 0; i < objects.size(); i++) {
                  ParseObject object = objects.get(i);
                  names.add(object.getString("name"));
                }

                switch (vId) {
                case R.id.edittext_item_type:
                  items = objects;
                  itemSpinner.setItems(names);
                  break;
                case R.id.edittext_character:
                  characters = objects;
                  characterSpinner.setItems(names);
                  break;
                }
              }
            }
          });
        }
      }
    };
    
    itemTypeET.setOnFocusChangeListener(listener);
    characterET.setOnFocusChangeListener(listener);
  }
  
  public void issueItem(View v) {
    if (itemSpinner != null && characterSpinner != null && items != null && characters != null) {
      final List<ParseObject> toSave = new ArrayList<ParseObject>();

      final List<Integer> selectedItems = itemSpinner.getSelectedIndicies();
      final List<Integer> selectedCharacters = characterSpinner.getSelectedIndicies();
      
      for (int i = 0; i < selectedItems.size(); i++) {
        int itemNumber = selectedItems.get(i);
        ParseObject itemObject = items.get(itemNumber);
        
        for (int j = 0; j < selectedCharacters.size(); j++) {
          int characterNumber = selectedCharacters.get(j);
          ParseObject characterObject = characters.get(characterNumber);
          
          // by default, this will generate a new item per character
          ParseObject inventoryObject = createCInventoryFromItem(itemObject, characterObject);
          toSave.add(inventoryObject);
        }
      }
      
      try {
        ParseObject.saveAll(toSave);
      }
      catch (ParseException e) {
        e.printStackTrace();
      }
      
      mCallback.onSubmit();
    }
  }
  
  protected ParseObject createCInventoryFromItem(ParseObject item, ParseObject character) {
    return CInventory.assignItemToCharacter(item, character, 1);
  }
  
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mCallback = new FragmentCallback() {
      
      @Override
      public void onSubmit() {
        // TODO Auto-generated method stub
        
      }
    };
  };
  
  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);
    if (activity instanceof FragmentCallback) {
      mCallback = (FragmentCallback) activity;
    }
    else {
      throw new IllegalStateException("activity must implement FragmentCallback");
    }
  }
  
  @Override
  public void onDetach() {
    mCallback = new FragmentCallback() {
      
      @Override
      public void onSubmit() {
        // TODO Auto-generated method stub
        
      }
    };
    super.onDetach();
  }
  
  private FragmentCallback mCallback;
  
  public interface FragmentCallback {
    
    public void onSubmit();
  }
}