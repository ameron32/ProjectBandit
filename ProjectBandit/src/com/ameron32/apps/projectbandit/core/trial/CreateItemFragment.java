package com.ameron32.apps.projectbandit.core.trial;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.ameron32.apps.projectbandit.MultiSelectSpinner;
import com.ameron32.apps.projectbandit.R;
import com.ameron32.apps.projectbandit.object.Item;
import com.parse.ParseException;
import com.parse.SaveCallback;


/**
 * A placeholder fragment containing a simple view.
 */
public class CreateItemFragment extends Fragment {
  
  private static final String      TAG          = CreateItemFragment.class.getSimpleName();
  private static final boolean     TOAST        = false;
  private static final boolean     LOG          = true;
  
  // private static final String[] ITEM_TYPES = { "Item", "Weapon", "Armor",
  // "Ingredient", "Ammo", "Consumable" };
  private static final String[]    WEAPON_SLOTS = { "None", "Off-Hand", "MainHand", "Back Weapon", "Ammo" };
  private static final String[]    AMMO_WEAPONS = { "None", "Bow", "Crossbow" };
  private static final String[]    WEAPON_TYPES = Item.WeaponType.nameValues();
  // private static final String[] ARMOR_SLOTS = { "None", "Head", "Neck",
  // "Chest", "Back", "Shoulder", "Arm",
  // "Wrist", "Hand", "Left Finger", "Right Finger", "Belt", "Lower Torso",
  // "Shin", "Feet" };
  
  private View                     mRootView;
  private OnFragmentFinishListener callback;

  public CreateItemFragment() {}
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    mRootView = inflater.inflate(R.layout.fragment_create_item, container, false);
    
    Spinner spinnerItemTypes = (Spinner) mRootView.findViewById(R.id.s_item_type);
    String[] itemTypes = getResources().getStringArray(R.array.rules_item_types);
    spinnerItemTypes.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, itemTypes));
    spinnerItemTypes.setOnItemSelectedListener(new OnItemSelectedListener() {
      
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        hideElements();
        String[] itemTypes = getResources().getStringArray(R.array.rules_item_types);
        String type = itemTypes[position];
        if (type.equalsIgnoreCase("Weapon")) {
          unhideElement(R.id.ll_weapon_elements, R.id.ll_durability_elements);
        }
        if (type.equalsIgnoreCase("Armor")) {
          unhideElement(R.id.ll_armor_elements, R.id.ll_durability_elements);
        }
        if (type.equalsIgnoreCase("Ingredient")) {
          unhideElement(R.id.ll_ingredient_elements);
        }
        if (type.equalsIgnoreCase("Ammo")) {
          unhideElement(R.id.ll_ammo_elements);
        }
        if (type.equalsIgnoreCase("Item")) {
          hideElements();
        }        
      }
      
      @Override
      public void onNothingSelected(AdapterView<?> parent) {
        hideElements();
      }
    });
    
    MultiSelectSpinner spinnerWeaponSlots = (MultiSelectSpinner) mRootView.findViewById(R.id.mss_weapon_slots);
    spinnerWeaponSlots.setItems(WEAPON_SLOTS);
    spinnerWeaponSlots.setSelectedIndex(0);
    
    MultiSelectSpinner spinnerArmorSlots = (MultiSelectSpinner) mRootView.findViewById(R.id.mss_armor_slots);
    spinnerArmorSlots.setItems(getResources().getStringArray(R.array.rules_armor_slots));
    spinnerArmorSlots.setSelectedIndex(0);
    
    MultiSelectSpinner spinnerAmmoTypes = (MultiSelectSpinner) mRootView.findViewById(R.id.mss_ammo_weapons);
    spinnerAmmoTypes.setItems(AMMO_WEAPONS);
    spinnerAmmoTypes.setSelectedIndex(0);
    
    MultiSelectSpinner weaponTypes = (MultiSelectSpinner) mRootView.findViewById(R.id.mss_weapon_type);
    weaponTypes.setItems(WEAPON_TYPES);
    weaponTypes.setSelectedIndex(0);
    
    mRootView.findViewById(R.id.b_submit).setOnClickListener(new View.OnClickListener() {
      
      @Override
      public void onClick(View v) {
        try {
          Spinner spinner = (Spinner) mRootView.findViewById(R.id.s_item_type);
          String string = (String) spinner.getSelectedItem();
          Item.Type type = Item.Type.valueOfIgnoreCase(string);
          Item.Builder builder = Item.Builder.getNewItem(type)
              .from(mRootView).loadView();
          Item createdItem = builder.create(new SaveCallback() {
            
            @Override
            public void done(ParseException e) {
              if (LOG) Log.i(TAG, "item created.");
            }
          });
          // reset the UI
          callback.onFinish();
        }
        catch (NumberFormatException e) {
          Toast.makeText(getActivity(), "Invalid number format (somewhere)", Toast.LENGTH_SHORT).show();
        }
      }
    });
    
    hideElements();
    
    return mRootView;
  }
  
  private void hideElements() {
    mRootView.findViewById(R.id.ll_weapon_elements).setVisibility(View.GONE);
    mRootView.findViewById(R.id.ll_ammo_elements).setVisibility(View.GONE);
    mRootView.findViewById(R.id.ll_armor_elements).setVisibility(View.GONE);
    mRootView.findViewById(R.id.ll_ingredient_elements).setVisibility(View.GONE);
    mRootView.findViewById(R.id.ll_durability_elements).setVisibility(View.GONE);
  }
  
  private void unhideElement(int... resources) {
    for (int res : resources) {
      mRootView.findViewById(res).setVisibility(View.VISIBLE);
    }
  }
  
  @Override
  public void onStart() {
    super.onStart();
  }
  
  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);
    if (!(activity instanceof OnFragmentFinishListener)) { throw new IllegalStateException("Activity must implement Fragment's callback."); }
    callback = (OnFragmentFinishListener) activity;
  }
  
  @Override
  public void onDetach() {
    callback = new OnFragmentFinishListener() {
      
      @Override
      public void onFinish() {}
    };
    super.onDetach();
  };
  
  public interface OnFragmentFinishListener {
    
    public void onFinish();
  }

}
