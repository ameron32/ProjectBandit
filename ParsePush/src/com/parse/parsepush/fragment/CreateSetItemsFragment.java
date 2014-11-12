package com.parse.parsepush.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseQueryAdapter.QueryFactory;
import com.parse.SaveCallback;
import com.parse.parsepush.MultiSelectSpinner;
import com.parse.parsepush.R;
import com.parse.starter.object.Item;
import com.parse.starter.object.ItemSet;
import com.parse.starter.object.ItemSet.Template;

/**
 * A placeholder fragment containing a simple view.
 */
public class CreateSetItemsFragment
    extends Fragment {
  
  private static final String TAG = CreateSetItemsFragment.class.getSimpleName();
  
  @InjectView(R.id.s_set_template) Spinner setTemplate;
  @InjectView(R.id.et_name) EditText name;
  @InjectView(R.id.et_base_value) EditText baseValue;
  @InjectView(R.id.s_item_type) Spinner spinnerSetTypes;
  @InjectView(R.id.mss_armor_slots) MultiSelectSpinner armorSlots;
  @InjectView(R.id.lv_other_sets) ListView otherSets;
  
  View mRootView;
  int templatePosition = 0;

  OnFragmentFinishListener callback;
  
  public CreateSetItemsFragment() {}
  
  @Override public void onCreate(
      Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    
    createTemplates();
  }
  
  //@formatter:off
  List<Template> templates;
  private void createTemplates() {
    templates = ItemSet.getTemplates();
  }
  //@formatter:on

  @Override public View onCreateView(
      LayoutInflater inflater,
      ViewGroup container,
      Bundle savedInstanceState) {
    mRootView = inflater.inflate(R.layout.fragment_create_set_items, container, false);
    ButterKnife.inject(this, mRootView);
    return mRootView;
  }
  
  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.reset(this);
  }
  
  @Override public void onViewCreated(
      View view,
      Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    armorSlots.setItems(getResources().getStringArray(R.array.rules_armor_slots));
    armorSlots.setSelectedIndex(0);
    
    String[] itemTypes = getResources().getStringArray(R.array.rules_item_types);
    spinnerSetTypes.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, itemTypes));
    spinnerSetTypes.setOnItemSelectedListener(new OnItemSelectedListener() {
      
      @Override public void onItemSelected(
          AdapterView<?> parent,
          View view, int position,
          long id) {
        hideElements();
        String[] itemTypes = getResources().getStringArray(R.array.rules_item_types);
        switch (itemTypes[position]) {
        case "Weapon":
          unhideElement(R.id.ll_weapon_elements, R.id.ll_durability_elements);
          break;
        case "Armor":
          unhideElement(R.id.ll_armor_elements, R.id.ll_durability_elements);
          break;
        case "Ingredient":
          unhideElement(R.id.ll_ingredient_elements);
          break;
        case "Ammo":
          unhideElement(R.id.ll_ammo_elements);
          break;
        default:
          // case "Item"
          hideElements();
        }
      }
      
      @Override public void onNothingSelected(
          AdapterView<?> parent) {
        hideElements();
      }
    });

    resetOtherSetsItems();
    
    setTemplate.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, getItems()));
    setTemplate.setOnFocusChangeListener(new OnFocusChangeListener() {
      
      @Override public void onFocusChange(
          View v, boolean hasFocus) {
        if (!hasFocus) {
          templatePosition = setTemplate.getSelectedItemPosition();
        }
      }
    });
    setTemplate.setOnItemSelectedListener(new OnItemSelectedListener() {

      @Override public void onItemSelected(
          AdapterView<?> parent,
          View view, int position,
          long id) {
        templatePosition = setTemplate.getSelectedItemPosition();
      }

      @Override public void onNothingSelected(
          AdapterView<?> parent) {
        templatePosition = setTemplate.getSelectedItemPosition();
      }});
  }
  
  private void resetOtherSetsItems() {
    otherSets.setAdapter(new ParseQueryAdapter<ParseObject>(getActivity(), new QueryFactory<ParseObject>() {
      
      @Override public ParseQuery<ParseObject> create() {
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Item");
        query.whereStartsWith("type", "Setreference");
        query.orderByAscending("createdAt");
        return query;
      }
    }) {
      
      @Override public View getItemView(
          ParseObject object, View v,
          ViewGroup parent) {
        if (v == null) {
          v = LayoutInflater.from(getActivity()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }
        super.getItemView(object, v, parent);
        TextView tv = (TextView) v;
        tv.setText(object.getString("name"));
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);
        return v;
      }
    });
  }
  
  private List<String> getItems() {
    List<String> strings = new ArrayList<String>();
    for (Template t : templates) {
      strings.add(t.getName());
    }
    return strings;
  }
  
  private void hideElements() {
    mRootView.findViewById(R.id.ll_weapon_elements).setVisibility(View.GONE);
    mRootView.findViewById(R.id.ll_ammo_elements).setVisibility(View.GONE);
    mRootView.findViewById(R.id.ll_armor_elements).setVisibility(View.GONE);
    mRootView.findViewById(R.id.ll_ingredient_elements).setVisibility(View.GONE);
    mRootView.findViewById(R.id.ll_durability_elements).setVisibility(View.GONE);
  }
  
  private void unhideElement(
      int... resources) {
    for (int res : resources) {
      mRootView.findViewById(res).setVisibility(View.VISIBLE);
    }
  }


  @Override public void onAttach(
      Activity activity) {
    super.onAttach(activity);
    if (!(activity instanceof OnFragmentFinishListener)) { throw new IllegalStateException("Activity must implement Fragment's callback."); }
    callback = (OnFragmentFinishListener) activity;
  }
  
  @Override public void onDetach() {
    callback = new OnFragmentFinishListener() {
      
      @Override public void onFinish() {}
    };
    super.onDetach();
  };
  
  public interface OnFragmentFinishListener {
    
    public void onFinish();
  }
  
  @OnClick(R.id.b_submit) public void onSubmit(
      View button) {
    pullData();
    // Item.Set.Builder.from(mRootView)
    // Item createdItem = Item.Builder.from(mRootView).create(new SaveCallback()
    // {
    //
    // @Override
    // public void done(ParseException e) {
    Log.i(TAG, "set created.");
    // }
    // });
    // reset the UI
    callback.onFinish();
  }

  private void pullData() {
    ItemSet.Builder setBuilder = ItemSet.Builder.from(mRootView);
    setBuilder.ofTemplate(templates.get(templatePosition));
    // setBuilder.named("Test Leather"); // FIXME ? maybe
    List<Item> createdItems = setBuilder.create(new SaveCallback() {

      @Override public void done(
          ParseException e) {
        // TODO Auto-generated method stub
        
      }
    });

    Log.i(TAG, "createItems.size() = "
        + createdItems.size());
    
    // resetOtherSetsItems();

  }
}