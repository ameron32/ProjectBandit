package com.parse.parsepush.activity;

import java.util.HashMap;
import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.widget.Toast;

import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;
import com.parse.starter.object.Message;

public class MainPushActivity extends
    SubPushActivity {
  
  private List<ParseObject> found;
  
  @Override protected int onStepsCreated() {
    return 5;
  }
  
  @Override protected void initialize() {
    promptProceed();
  }
  
  /**
   * STARTS AT 1, NOT 0
   */
  private String result2;
  
  @Override protected void performInBackground(
      int currentStep, int totalSteps) {
    
    switch (currentStep) {
    case 1:
      
      // addRelation(suser, character);
      // pushCharacterImage(character, imageName);
      // pushActionImage(action, imageName);
      // deleteColumn("Character", "name", "Matt",
      // try {
      // ParseUser kris = ParseUser.getQuery().whereEqualTo("username",
      // "Kris").getFirst();
      // ParseUser matt = ParseUser.getQuery().whereEqualTo("username",
      // "Matt").getFirst();
      // ParseUser mikey = ParseUser.getQuery().whereEqualTo("username",
      // "CradleRobin").getFirst();
      // ParseUser ameron32 = ParseUser.getQuery().whereEqualTo("username",
      // "ameron32").getFirst();
      //
      // List<ParseObject> find = new
      // ParseQuery<ParseObject>("Message").setLimit(1000).find();
      // for (ParseObject o : find) {
      // ParseRelation<ParseObject> relation = o.getRelation("receivedBy");
      // relation.add(kris);
      // relation.add(matt);
      // relation.add(mikey);
      // relation.add(ameron32);
      // }
      // ParseObject.saveAll(find);
      // } catch (ParseException e) {
      // // TODO Auto-generated catch block
      // e.printStackTrace();
      // }
      
      HashMap<String, ParseObject> map = new HashMap<String, ParseObject>();
//      map.put("user", ParseUser.getCurrentUser());
      ParseCloud.callFunctionInBackground("roll", map, new FunctionCallback<String>() {
        
        public void done(String result,
            ParseException e) {
          if (e == null) {
            // result is "Hello world!"
            result2 = result;
            Toast.makeText(MainPushActivity.this, result2, Toast.LENGTH_SHORT).show();
            
            try {
              Message.create()
                .setMessage(result2)
                .setAction(new ParseQuery<ParseObject>("CAction").get("QJJa4LdZUV"))
                .setChannel("root")
                .setCharacter(new ParseQuery<ParseObject>("Character").get("FSuzc8hv7y"))
                .send();
            } catch (ParseException e1) {
              e1.printStackTrace();
            } finally {}
          }
        }
      });

      
      break;
    // case 2:
    // try {
    // ParseQuery<ParseObject> query = new
    // ParseQuery<ParseObject>("CInventory");
    // query.include("item");
    // found = query.find();
    // }
    // catch (ParseException e) {
    // e.printStackTrace();
    // }
    // break;
    // case 3:
    // List<ParseObject> itemsToSave;
    // itemsToSave = new ArrayList<ParseObject>();
    // for (int i = 0; i < found.size(); i++) {
    // ParseObject item = found.get(i);
    // // item = update(item, "isLibrary", true);
    // // ParseObject item2 = item.getParseObject("item");
    // try {
    // ParseRelation<ParseObject> relation = item.getRelation("owner");
    // ParseQuery<ParseObject> query = relation.getQuery();
    // ParseObject owner = query.getFirst();
    // String ownerName = owner.getString("name");
    //
    // JSONArray jsonArray = new JSONArray();
    // jsonArray.put(ownerName);
    // item.put("characterOwners", jsonArray);
    // // List<Object> list = item.getList("characterOwners");
    //
    // // item.put("name", item2.getString("name"));
    // // item.put("type", item2.getString("type"));
    // // item.put("baseValue", item2.getInt("baseValue"));
    // // item.put("isDetailHidden", false);
    // // if (item2.getBoolean("isDurable")) {
    // // item.put("currentDurability", item2.getInt("durabilityUses"));
    // // }
    // // else {
    // // item.put("currentDurability", 0);
    // // }
    // // item.put("quantity", 1);
    // itemsToSave.add(item);
    // // attach(item);
    // }
    // catch (ParseException e) {
    // e.printStackTrace();
    // }
    //
    // try {
    // ParseObject.saveAll(itemsToSave);
    // }
    // catch (ParseException e) {
    // e.printStackTrace();
    // }
    // }
    // break;
    case 4:
      // try {
      // ParseObject game = new
      // ParseQuery<ParseObject>("Game").get("DgYDpgWvqu");
      // List<ParseObject> itemsToSave2 = new
      // ParseQuery<ParseObject>("Item").whereGreaterThan("createdAt", new
      // GregorianCalendar(2014, GregorianCalendar.OCTOBER,
      // 11).getTime()).find();
      // for (ParseObject obj : itemsToSave2) {
      // addObjectRelationToObject("Item", obj.getString("name"),
      // "usableInGame", "Game", "testing");
      // }
      // ParseObject.saveAll(itemsToSave2);
      // } catch (ParseException e) {
      // e.printStackTrace();
      // }
      break;
    default:
      
      break;
    }
  }
  
  // private ParseObject gameObject;
  private void promptProceed() {
    Builder builder = new AlertDialog.Builder(MainPushActivity.this);
    builder.setMessage("Proceed?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
      
      @Override public void onClick(
          DialogInterface dialog,
          int which) {
        doContinue();
      }
    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
      
      @Override public void onClick(
          DialogInterface dialog,
          int which) {
        finish();
      }
    }).create().show();
  }
  
  private void attach(
      final ParseObject messageObject)
      throws ParseException {
    // final String actionStr = messageObject.getString("action");
    // if (gameObject != null) {
    
    // object = query.getFirst();
    ParseObject first = new ParseQuery<ParseObject>("Game").whereEqualTo("name", "testing").getFirst();
    messageObject.put("inSession", 2);
    messageObject.put("ofGame", first);
    messageObject.save();
    // }
  }
  
  private void attach2(
      final ParseObject messageObject) {
    // final String actionStr = messageObject.getString("action");
    // if (actionStr != null) {
    // ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("CAction");
    // query.whereContains("action", actionStr);
    try {
      // ParseObject object;
      // object = query.getFirst();
      
      messageObject.put("canon", false);
      
      messageObject.save();
      
    } catch (ParseException e) {
      e.printStackTrace();
    }
    // }
  }
  
  private ParseObject update(
      final ParseObject object,
      String columnKey, boolean newValue) {
    object.put(columnKey, newValue);
    return object;
  }
  
  @Override protected void done() {

  }
}
