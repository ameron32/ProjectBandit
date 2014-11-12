package com.parse.starter.adapter.frmwk;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;
import com.parse.starter.Loggy;
import com.parse.starter.R;
import com.squareup.picasso.Picasso;

public abstract class MyParseQueryAdapter extends ParseQueryAdapter<ParseObject> {
  
  private static final String    TAG            = MyParseQueryAdapter.class.getSimpleName();
  private static final boolean   TOAST          = false;
  private static final boolean   LOG            = true;
  private static final boolean   ERROR          = false;

  protected static final boolean PAGINATION_OFF = false;
  protected static final boolean PAGINATION_ON  = true;
  
  private final Context          context;
  
  protected MyParseQueryAdapter(final Context context, final ParseQueryAdapter.QueryFactory<ParseObject> factory,
      boolean activePagination) {
    super(context, factory);
    this.setPaginationEnabled(activePagination);
    this.context = context;
  }


// Customize the layout by overriding getItemView
  @Override
  public View getItemView(ParseObject object, View v, ViewGroup parent) {
    // if (v == null) {
    /*
     * DETERMINE THE PREFERENCE SIZE OF THE ROW
     */
    int res = context.getSharedPreferences("size", Context.MODE_PRIVATE).getInt("message_row", R.layout.row_message);
    v = View.inflate(getContext(), res, null);
    // }
    
    // super.getItemView(object, v, parent);
    
    // Add and download the image
    // ParseImageView todoImage = (ParseImageView)
    // v.findViewById(R.id.icon);
    // ParseFile imageFile = object.getParseFile("image");
    // if (imageFile != null) {
    // todoImage.setParseFile(imageFile);
    // todoImage.loadInBackground();
    // }
    
    // Add the title view
    // TextView titleTextView = (TextView) v.findViewById(R.id.text1);
    // titleTextView.setText(object.getString("title"));
    
    // Add a reminder of how long this item has been outstanding
    // TextView timestampView = (TextView) v.findViewById(R.id.timestamp);
    // timestampView.setText(object.getCreatedAt().toString());
    
    final String objectId = object.getObjectId();
    final TextView messageText = (TextView) v.findViewById(R.id.textview_message);
    final TextView channelText = (TextView) v.findViewById(R.id.textview_channel);
    final TextView objectIdText = (TextView) v.findViewById(R.id.textview_object_id);
    final TextView usernameText = (TextView) v.findViewById(R.id.textview_username);
    final TextView characterText = (TextView) v.findViewById(R.id.textview_character);
    final TextView actionText = (TextView) v.findViewById(R.id.textview_action);
    final TextView timeText = (TextView) v.findViewById(R.id.textview_created_time);
    final ParseImageView characterImageView = (ParseImageView) v.findViewById(R.id.imageview_character);
    final ParseImageView actionImageView = (ParseImageView) v.findViewById(R.id.imageview_action);
    
    usernameText.setText(".........");
    actionText.setText(".........");
    characterText.setText(".........");
    characterImageView.setImageResource(R.drawable.ic_launcher);
    actionImageView.setImageResource(R.drawable.ic_launcher);
    usernameText.setTag(objectId);
    characterText.setTag(objectId);
    actionText.setTag(objectId);
    characterImageView.setTag(objectId);
    actionImageView.setTag(objectId);

    ParseObject messageObject = object;
    String objectStr = messageObject.getObjectId();
    String message = messageObject.getString("message");
    String channel = messageObject.getString("channel");
    Date createdAt = messageObject.getCreatedAt();
    String time = new SimpleDateFormat("h:mm aa M/d/yyyy", Locale.US).format(createdAt);
    
    // ParseUser user;
    final int logID = Loggy.start("getItemView()--fetch");

    pullAdditionalQueryData(objectId, usernameText, characterText, actionText, characterImageView, actionImageView, messageObject);

    Loggy.stop(logID);
    
    messageText.setText(message + "");
    channelText.setText(channel + "");
    objectIdText.setText(objectStr + "");
    timeText.setText(time + "");
    
    return v;
  }

  private void pullAdditionalQueryData(final String objectId, final TextView usernameText,
      final TextView characterText, final TextView actionText, final ParseImageView characterImageView,
      final ParseImageView actionImageView,
      final ParseObject messageObject) {
    ParseUser userObject = messageObject.getParseUser("user");
    if (userObject != null) {
      userObject.fetchIfNeededInBackground(new GetUserCallback(usernameText, objectId));
    }
    else {
      usernameText.setText("none");
    }

    ParseObject characterObject = messageObject.getParseObject("character");
    if (characterObject != null) {
      characterObject.fetchIfNeededInBackground(new GetCharacterCallback(characterText, objectId, characterImageView));
    }
    else {
      characterText.setText("none");
    }
    
    ParseObject actionObject = messageObject.getParseObject("actionO");
    if (actionObject != null) {
      actionObject.fetchIfNeededInBackground(new GetActionCallback(actionText, objectId, actionImageView));
    }
    else {
      actionImageView.setImageResource(android.R.color.transparent);
    }

//    final String actionStr = messageObject.getString("action");
//    if (actionStr != null) {
//      ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("CAction");
//      query.whereContains("action", actionStr);
//      // query.findInBackground(new FindCallback<ParseObject>() {
//      //
//      // @Override
//      // public void done(List<ParseObject> objects, ParseException e) {
//      // if (e == null) {
//      // // ACTION PIC -- IMAGE
//      // ParseObject object = objects.get(0);
//      // ParseFile actionPic = object.getParseFile("actionPic");
//      // if (actionPic == null) {
//      // Log.d("MPQA", "actionPic is null");
//      // return;
//      // }
//      //
//      // // actionPic.getDataInBackground(new
//      // // GetActionPicCallback(actionImageView, objectId));
//      // Picasso.with(context).load(actionPic.getUrl()).into(actionImageView);
//      // }
//      // else {
//      // e.printStackTrace();
//      // }
//      // }
//      // });
//      query.getFirstInBackground(new GetCallback<ParseObject>() {
//        
//        @Override
//        public void done(ParseObject object, ParseException e) {
//          if (e == null) {
//            // ACTION PIC -- IMAGE
//            ParseFile actionPic = object.getParseFile("actionPic");
//            if (actionPic == null) {
//              Log.d("MPQA", "actionPic is null");
//              return;
//            }
//            
//            // actionPic.getDataInBackground(new
//            // GetActionPicCallback(actionImageView, objectId));
//            Picasso.with(context).load(actionPic.getUrl()).into(actionImageView);
//          }
//          else {
//            e.printStackTrace();
//          }
//        }
//      });
    // }
    // else {
    // // invisible the icon
    // actionImageView.setImageResource(android.R.color.transparent);
    // }
  }
  

  public static class GetUserCallback extends GetCallback<ParseUser> {
    
    private final WeakReference<TextView> weakView;
    private final String                  objectId;
    
    public GetUserCallback(final TextView textView, final String objectId) {
      this.objectId = objectId;
      weakView = new WeakReference<TextView>(textView);
    }

    @Override
    public void done(ParseUser user, ParseException e) {
      if (e == null) {
        doneNoError(user);
      }
      else {
        if (ERROR) e.printStackTrace();
      }
    }
    
    private void doneNoError(ParseUser user) {
      final TextView textView = weakView.get();
      if (textView == null) {
        // listview has moved on
        if (LOG) Log.d(TAG, "textView is null: [" + textView + "]");
        return;
      }
      
      if (user == null) {
        textView.setText("none");
        return;
      }
      
      String userName = user.getString("username");
      if (userName == null) {
        textView.setText("none");
        return;
      }
      
      if (textView.getTag().equals(objectId)) {
        // the view STILL has the same objectId associated with it
        textView.setText(userName + "");
      }
      else {
        if (LOG) Log.d(TAG, "objectId does not match: [" + textView.getTag() + "],[" + objectId + "]");
      }
    }
  }
  
  public class GetCharacterCallback extends GetCallback<ParseObject> {
    
    private final WeakReference<TextView>  weakView;
    private final WeakReference<ImageView> weakImageView;
    private final String                   objectId;
    
    public GetCharacterCallback(final TextView textView, final String objectId, final ImageView imageView) {
      this.objectId = objectId;
      weakView = new WeakReference<TextView>(textView);
      weakImageView = new WeakReference<ImageView>(imageView);
    }
    
    @Override
    public void done(ParseObject characterObject, ParseException e) {
      if (e == null) {
        doneNoError(characterObject);
      }
      else {
        if (ERROR) e.printStackTrace();
      }
    }
    
    private void doneNoError(ParseObject characterObject) {
      final TextView characterText = weakView.get();
      final ImageView imageView = weakImageView.get();
      if (characterText == null || imageView == null) {
        // listview has moved on
        if (LOG) Log.d(TAG, "characterText or imageView is null: [" + characterText + "],[" + imageView + "]");
        return;
      }
      
      if (characterObject == null) {
        characterText.setText("none");
        return;
      }

      // CHARACTER NAME -- TEXT
      String characterName = characterObject.getString("name");
      if (characterName == null) {
        characterText.setText("none");
        return;
      }
      
      if (characterText.getTag().equals(objectId)) {
        // the view STILL has the same objectId associated with it
        characterText.setText(characterName + "");
      }
      else {
        if (LOG) Log.d(TAG, "objectId does not match: [" + characterText.getTag() + "],[" + objectId + "]");
        return;
      }
      
      // CHARACTER PIC -- IMAGE
      ParseFile characterPic = characterObject.getParseFile("profilePic");
      if (characterPic == null) {
        Log.d("MPQA", "characterPic is null");
        return;
      }

      Picasso.with(context).load(characterPic.getUrl()).into(imageView);
      characterPic.getDataInBackground(new GetCharacterPicCallback(imageView, objectId));
    }
  }
  
  public class GetCharacterPicCallback extends GetDataCallback {
    
    private final WeakReference<ImageView> weakView;
    private final String                   objectId;
    
    public GetCharacterPicCallback(final ImageView imageView, String objectId) {
      this.objectId = objectId;
      weakView = new WeakReference<ImageView>(imageView);
    }
    
    @Override
    public void done(byte[] data, ParseException e) {
      if (e == null) {
        if (data != null) {
          doneNoError(data);
        }
        else {
          Log.d("MPQA", "data is null");
        }
      }
      else {
        if (ERROR) e.printStackTrace();
      }
    }
    
    private void doneNoError(byte[] data) {
      final ImageView imageView = weakView.get();
      if (imageView == null) {
        // listview has moved on
        if (LOG) Log.d(TAG, "imageView is null: [" + imageView + "]");
        return;
      }

      Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
      if (imageView.getTag().equals(objectId)) {
        imageView.setImageBitmap(bitmap);
      }
      else {
        if (LOG) Log.d(TAG, "objectId does not match: [" + imageView.getTag() + "],[" + objectId + "]");
      }
    }
  }
  
  public class GetActionCallback extends GetCallback<ParseObject> {
    
    private final WeakReference<TextView>  weakView;
    private final WeakReference<ImageView> weakImageView;
    private final String                   objectId;
    
    public GetActionCallback(final TextView textView, final String objectId, final ImageView imageView) {
      this.objectId = objectId;
      weakView = new WeakReference<TextView>(textView);
      weakImageView = new WeakReference<ImageView>(imageView);
    }
    
    @Override
    public void done(ParseObject characterObject, ParseException e) {
      if (e == null) {
        doneNoError(characterObject);
      }
      else {
        if (ERROR) e.printStackTrace();
      }
    }
    
    private void doneNoError(ParseObject actionObject) {
      final TextView actionText = weakView.get();
      final ImageView imageView = weakImageView.get();
      if (actionText == null || imageView == null) {
        // listview has moved on
        if (LOG) Log.d(TAG, "actionText or imageView is null: [" + actionText + "],[" + imageView + "]");
        return;
      }
      
      if (actionObject == null) {
        actionText.setText("none");
        return;
      }
      
      // ACTION TEXT
      String action = actionObject.getString("action");
      if (action == null) {
        actionText.setText("none");
        return;
      }
      
      if (imageView.getTag().equals(objectId)) {
        // the view STILL has the same objectId associated with it
        actionText.setText(action + "");
      }
      else {
        if (LOG) Log.d(TAG, "objectId does not match: [" + actionText.getTag() + "],[" + objectId + "]");
        return;
      }
      
      // CHARACTER PIC -- IMAGE
      ParseFile actionPic = actionObject.getParseFile("actionPic");
      if (actionPic == null) {
        Log.d("MPQA", "actionPic is null");
        return;
      }
      
      Picasso.with(context).load(actionPic.getUrl()).into(imageView);
      actionPic.getDataInBackground(new GetActionPicCallback(imageView, objectId));
    }
  }

  public class GetActionPicCallback extends GetDataCallback {
    
    private final WeakReference<ImageView> weakView;
    private final String                   objectId;
    
    public GetActionPicCallback(final ImageView imageView, String objectId) {
      this.objectId = objectId;
      weakView = new WeakReference<ImageView>(imageView);
    }
    
    @Override
    public void done(byte[] data, ParseException e) {
      if (e == null) {
        if (data != null) {
          doneNoError(data);
        }
        else {
          Log.d("MPQA", "data is null");
        }
      }
      else {
        if (ERROR) e.printStackTrace();
      }
    }
    
    private void doneNoError(byte[] data) {
      final ImageView imageView = weakView.get();
      if (imageView == null) {
        // listview has moved on
        if (LOG) Log.d(TAG, "imageView is null: [" + imageView + "]");
        return;
      }
      
      Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
      if (imageView.getTag().equals(objectId)) {
        imageView.setImageBitmap(bitmap);
      }
      else {
        if (LOG) Log.d(TAG, "objectId does not match: [" + imageView.getTag() + "],[" + objectId + "]");
      }
    }
  }
}
