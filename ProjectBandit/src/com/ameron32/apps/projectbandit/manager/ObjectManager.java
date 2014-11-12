package com.ameron32.apps.projectbandit.manager;

import com.ameron32.apps.projectbandit.adapter._QueryManager;
import com.ameron32.apps.projectbandit.object.CInventory;
import com.parse.FindCallback;
import com.parse.ParseQuery;

public class ObjectManager {
  
  public static ObjectManager objectManager;
  
  public static ObjectManager get() {
    if (objectManager == null) {
      objectManager = new ObjectManager();
    }
    return objectManager;
  }
  
  private ObjectManager() {}
  
  public static void destroy() {
    objectManager = null;
  }
  
  public void queryAllInventory(FindCallback<CInventory> callback) {
    ParseQuery<CInventory> query = _QueryManager._Inventory.getAllInventory();
    query.findInBackground(callback);
  }
}
