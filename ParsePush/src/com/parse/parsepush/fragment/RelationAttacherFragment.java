package com.parse.parsepush.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.parsepush.MultiSelectSpinner;
import com.parse.parsepush.R;
import com.parse.parsepush.activity.SubPushActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class RelationAttacherFragment
    extends Fragment {
  
  private String[] items1s;
  private String[] items2s;
  
  public RelationAttacherFragment() {}
  
  @Override public View onCreateView(
      LayoutInflater inflater,
      ViewGroup container,
      Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_relation_attacher, container, false);
    return rootView;
  }
  
  @Override public void onAttach(
      Activity activity) {
    super.onAttach(activity);
    
    try {
      SubPushActivity spa = (SubPushActivity) activity;
    } catch (ClassCastException e) {
      throw new Error("activity must be a SubPushActivity");
    }
  }
  
  public SubPushActivity getSPActivity() {
    return (SubPushActivity) getActivity();
  }
  
  @Override public void onResume() {
    super.onResume();
  }
  
  public void onSaveRelation(View v) {
    EditText objectET = (EditText) getActivity().findViewById(R.id.edittext_parse_objects_1);
    EditText relationET = (EditText) getActivity().findViewById(R.id.edittext_relationship);
    EditText objectTypeET = (EditText) getActivity().findViewById(R.id.edittext_parse_objects_2);
    
    MultiSelectSpinner object1Spinner = (MultiSelectSpinner) getActivity().findViewById(R.id.mss_query_objects_1);
    MultiSelectSpinner object2Spinner = (MultiSelectSpinner) getActivity().findViewById(R.id.mss_query_objects_2);
    
    String object1Type = objectET.getText().toString();
    String relation = relationET.getText().toString();
    String object2Type = objectTypeET.getText().toString();
    
    List<ParseObject> toSave = new ArrayList<ParseObject>();
    
    List<Integer> selectedObjects1 = object1Spinner.getSelectedIndicies();
    for (int i = 0; i < selectedObjects1.size(); i++) {
      int selectedObject1Position = selectedObjects1.get(i);
      
      List<Integer> selectedObjects2 = object2Spinner.getSelectedIndicies();
      for (int j = 0; j < selectedObjects2.size(); j++) {
        int selectedObject2Position = selectedObjects2.get(j);
        
        String type1 = object1Type;
        String name1 = items1s[selectedObject1Position];
        // String relation = relation;
        String type2 = object2Type;
        String name2 = items2s[selectedObject2Position];
        
        Log.d("PIA", type1 + ","
            + name1 + "," + relation
            + "," + type2 + "," + name2);
        
        if (object2Type.equals("_User")) {
          ParseObject pO = getSPActivity().addUserRelationToObject(type1, name1, relation, type2, name2);
          toSave.add(pO);
        } else {
          ParseObject pO = getSPActivity().addObjectRelationToObject(type1, name1, relation, type2, name2);
          toSave.add(pO);
        }
      }
    }
    
    try {
      ParseObject.saveAll(toSave);
      Log.d("PIA", "save successful");
    } catch (ParseException e) {
      Log.d("PIA", "save failed");
      e.printStackTrace();
    }
  }
  
  @Override public void onStart() {
    super.onStart();
    
    final EditText object1TypeET = (EditText) getActivity().findViewById(R.id.edittext_parse_objects_1);
    object1TypeET.setOnFocusChangeListener(new OnFocusChangeListener() {
      
      @Override public void onFocusChange(
          View v, boolean hasFocus) {
        if (!hasFocus
            && (v.getId() == R.id.edittext_parse_objects_1)) {
          EditText objectTypeEditText = (EditText) v;
          String type = objectTypeEditText.getText().toString();
          Log.e("RELATION", "type: "
              + type);
          if (type.equals("_User")) {
            ParseQuery<ParseUser> userQuery = new ParseQuery<ParseUser>(type);
            userQuery.orderByAscending("username");
            userQuery.findInBackground(new FindCallback<ParseUser>() {
              
              @Override public void done(
                  List<ParseUser> objects,
                  ParseException e) {
                if (e == null
                    && objects.size() > 0) {
                  List<String> names = new ArrayList<String>();
                  for (ParseUser user : objects) {
                    names.add(user.getString("username"));
                  }
                  
                  MultiSelectSpinner objectSpinner = (MultiSelectSpinner) getActivity().findViewById(R.id.mss_query_objects_1);
                  items1s = names.toArray(new String[names.size()]);
                  objectSpinner.setItems(items1s);
                  Log.d("PIA", "ready");
                }
              }
            });
          } else {
            ParseQuery<ParseObject> objectQuery = new ParseQuery<ParseObject>(type);
            objectQuery.orderByAscending("name");
            objectQuery.findInBackground(new FindCallback<ParseObject>() {
              
              @Override public void done(
                  List<ParseObject> objects,
                  ParseException e) {
                if (e == null
                    && objects.size() > 0) {
                  List<String> names = new ArrayList<String>();
                  for (ParseObject object : objects) {
                    names.add(object.getString("name"));
                  }
                  
                  MultiSelectSpinner objectSpinner = (MultiSelectSpinner) getActivity().findViewById(R.id.mss_query_objects_1);
                  items1s = names.toArray(new String[names.size()]);
                  objectSpinner.setItems(items1s);
                  Log.d("PIA", "ready");
                }
              }
            });
          }
        }
      }
    });
    
    final EditText object2TypeET = (EditText) getActivity().findViewById(R.id.edittext_parse_objects_2);
    object2TypeET.setOnFocusChangeListener(new OnFocusChangeListener() {
      
      @Override public void onFocusChange(
          View v, boolean hasFocus) {
        if (!hasFocus
            && (v.getId() == R.id.edittext_parse_objects_2)) {
          EditText objectTypeEditText = (EditText) v;
          String type = objectTypeEditText.getText().toString();
          Log.e("RELATION", "type: "
              + type);
          if (type.equals("_User")) {
            ParseQuery<ParseUser> userQuery = new ParseQuery<ParseUser>(type);
            userQuery.orderByAscending("username");
            userQuery.findInBackground(new FindCallback<ParseUser>() {
              
              @Override public void done(
                  List<ParseUser> objects,
                  ParseException e) {
                if (e == null
                    && objects.size() > 0) {
                  List<String> names = new ArrayList<String>();
                  for (ParseUser user : objects) {
                    names.add(user.getString("username"));
                  }
                  
                  MultiSelectSpinner objectSpinner = (MultiSelectSpinner) getActivity().findViewById(R.id.mss_query_objects_2);
                  items2s = names.toArray(new String[names.size()]);
                  objectSpinner.setItems(items2s);
                  Log.d("PIA", "ready");
                }
              }
            });
          } else {
            ParseQuery<ParseObject> objectQuery = new ParseQuery<ParseObject>(type);
            objectQuery.orderByAscending("name");
            objectQuery.findInBackground(new FindCallback<ParseObject>() {
              
              @Override public void done(
                  List<ParseObject> objects,
                  ParseException e) {
                if (e == null
                    && objects.size() > 0) {
                  List<String> names = new ArrayList<String>();
                  for (ParseObject object : objects) {
                    names.add(object.getString("name"));
                  }
                  
                  MultiSelectSpinner objectSpinner = (MultiSelectSpinner) getActivity().findViewById(R.id.mss_query_objects_2);
                  items2s = names.toArray(new String[names.size()]);
                  objectSpinner.setItems(items2s);
                  Log.d("PIA", "ready");
                }
              }
            });
          }
        }
      }
    });
  }
}