package com.parse.starter.adapter.impl;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.starter.R;
import com.parse.starter.manager.CharacterManager;
import com.parse.starter.object.Character;

public class EquipmentAdapter extends
    ParseQueryAdapter<ParseObject> {
  
  private final Context context;
  private final int itemViewResource;

  public EquipmentAdapter(
      Context context,
      int itemViewResource) {
    super(context, makeQuery(), itemViewResource);
    this.context = context;
    this.itemViewResource = itemViewResource;
  }
  
  private static ParseQueryAdapter.QueryFactory<ParseObject> makeQuery() {
    return new ParseQueryAdapter.QueryFactory<ParseObject>() {
      
      @Override public ParseQuery<ParseObject> create() {
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("CInventory");
        query.include("item");
        query.whereEqualTo("isEquipped", true);
        query.orderByAscending("name");
        Character currentCharacter = CharacterManager.getCharacterManager().getCurrentCharacter();
        query.whereEqualTo("owner", currentCharacter);
        
        return query;
      }
    };
  }
  
  @Override public View getItemView(
      ParseObject object, View v,
      ViewGroup parent) {
    v = super.getItemView(object, v, parent);
    
    ViewHolder holder;
    holder = (ViewHolder) v.getTag();
    if (holder == null) {
      holder = new ViewHolder(v);
      v.setTag(holder);
    }
    
    ParseObject item = object.getParseObject("item");
    String name = object.getString("name");
    int baseValue = object.getInt("baseValue");
    int currentDurability = object.getInt("currentDurability");
    String slots = "none";
    try {
      slots = getSlots(item);
    } catch (JSONException e) {
      e.printStackTrace();
    }
    // int quantity = object.getInt("quantity");
    holder.itemName.setText(name);
    holder.itemDurability.setText("Dur: ["
        + currentDurability + "]");
    holder.itemValue.setText(baseValue
        + "");
    holder.equipmentSlot.setText(slots);
    // if (quantity < 2) {
    // holder.itemQuantity.setVisibility(View.INVISIBLE);
    // holder.itemValue.setText("$ "
    // + baseValue);
    // } else {
    // holder.itemQuantity.setVisibility(View.VISIBLE);
    // holder.itemQuantity.setText(quantity
    // + "");
    // holder.itemValue.setText("$ "
    // + baseValue + " / $ "
    // + baseValue * quantity);
    // }
    
    return v;
  }
  
  private String getSlots(
      ParseObject item)
      throws JSONException {
    // TODO : FIXME
    JSONArray slots = item.getJSONArray("armorSlots");
    return slots.join(", ");
  }

  static class ViewHolder {
    
    @InjectView(R.id.button_value) Button itemValue;
    @InjectView(R.id.textview_equipment_item_name) TextView itemName;
    @InjectView(R.id.textview_equipment_item_value) TextView itemDurability;
    @InjectView(R.id.textview_equipment_item_slot) TextView equipmentSlot;
    
    public ViewHolder(View v) {
      ButterKnife.inject(this, v);
    }
  }
  
}
