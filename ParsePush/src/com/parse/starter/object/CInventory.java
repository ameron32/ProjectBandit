package com.parse.starter.object;

import org.json.JSONArray;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseRelation;

@ParseClassName("CInventory")
public class CInventory extends ParseObject {
  
  private int baseValue;
  private int currentDurability;
  private String name;
  private String type;
  private JSONArray characterOwners;

  public CInventory() {}
  
  public static CInventory assignItemToCharacter(
      ParseObject item,
      ParseObject character,
      int quantity) {
    CInventory link = new CInventory();
    link.addOwner(character);
    link.addItem(item);
    link.setQuantity(quantity);
    return link;
  }
  
  public CInventory setQuantity(
      int quantity) {
    this.put("quantity", quantity);
    return this;
  }

  public CInventory addOwner(
      ParseObject character) {
    ParseRelation<ParseObject> relation = this.getRelation("owner");
    relation.add(character);
    
    String characterName = character.getString("name");
    characterOwners = this.getJSONArray("characterOwners");
    if (characterOwners == null) {
      characterOwners = new JSONArray();
    }
    characterOwners.put(characterName);
    
    this.put("characterOwners", characterOwners);
    return this;
  }
  
  public CInventory addItem(
      ParseObject item) {
    baseValue = item.getInt("baseValue");
    currentDurability = item.getInt("durabilityUses");
    name = item.getString("name");
    type = item.getString("type");

    this.put("currentDurability", currentDurability);
    this.put("baseValue", baseValue);
    this.put("name", name);
    this.put("type", type);

    this.put("item", item);
    return this;
  }
}
