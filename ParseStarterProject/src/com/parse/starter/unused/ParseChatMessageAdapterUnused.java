package com.parse.starter.unused;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.starter.Loggy;
import com.parse.starter.R;
import com.parse.starter.R.id;
import com.parse.starter.R.layout;

public class ParseChatMessageAdapterUnused extends BaseAdapter {
  
  private static final String     TAG = ParseChatMessageAdapterUnused.class.getSimpleName();
  private final LayoutInflater    inflater;
  private final List<ParseHolder> list;
  
  public ParseChatMessageAdapterUnused(Context context) {
    list = new ArrayList<ParseHolder>();
    inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }
  
  @Override
  public int getCount() {
    return list.size();
  }
  
  @Override
  public ParseHolder getItem(int position) {
    return list.get(position);
  }
  
  @Override
  public long getItemId(int position) {
    return position;
  }
  
  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    if (getCount() <= position) {
      Log.e(TAG, "position >= list.size()");
    }

    ParseHolder ph = getItem(position);
    ViewHolder viewHolder = ph.getH();

    if (viewHolder == null || convertView == null) {
      viewHolder = onCreateViewHolder(parent);
      ph.setH(viewHolder);
      convertView = viewHolder.getRootView();
    }
    
    onBindViewHolder(viewHolder, position);
    return convertView;
  }
  
  public ViewHolder onCreateViewHolder(ViewGroup parent) {
    View root = inflater.inflate(R.layout.row_message, parent, false);
    return new ViewHolder(root);
  }
  
  public void onBindViewHolder(final ViewHolder holder, int position) {
    ParseObject messageObject = getItem(position).getP();
    String object = messageObject.getObjectId();
    String message = messageObject.getString("message");
    String channel = messageObject.getString("channel");
    
    ParseUser user;
    final int logID = Loggy.start("fetchUser");
    try {
      user = messageObject.getParseUser("user").fetchIfNeeded();
      String username = user.getString("username");
      if (username != null) {
        holder.usernameText.setText(username + "");
      }
    }
    catch (ParseException e) {
      e.printStackTrace();
    }
    Loggy.stop(logID);
    
    holder.messageText.setText(message + "");
    holder.channelText.setText(channel + "");
    holder.objectIdText.setText(object + "");
  }
  
  private boolean contains(ParseObject object) {
    int size = getCount();
    for (int i = 0; i < size; i++) {
      ParseHolder ph = getItem(i);
      ParseObject o = ph.getP();
      if (o.getObjectId().equals(object.getObjectId())) { return true; }
    }
    return false;
  }

  public void addParseObject(ParseObject object) {
    if (!contains(object)) {
      list.add(new ParseHolder(object));
    }
  }
  
  public void refresh() {
    this.notifyDataSetChanged();
  }

  public static class ViewHolder {
    
    private final View    root;
    public final TextView messageText;
    public final TextView channelText;
    public final TextView objectIdText;
    public final TextView usernameText;
    
    public ViewHolder(View root) {
      if (root == null) { throw new Error("ViewHolder MUST have a view. Null not acceptable."); }
      this.root = root;
      messageText = (TextView) root.findViewById(R.id.textview_message);
      channelText = (TextView) root.findViewById(R.id.textview_channel);
      objectIdText = (TextView) root.findViewById(R.id.textview_object_id);
      usernameText = (TextView) root.findViewById(R.id.textview_character);
    }
    
    public View getRootView() {
      return root;
    }
  }
  
  public static class ParseHolder {

    private final ParseObject p;
    private ViewHolder        h;
    
    public ParseHolder(ParseObject p) {
      this.p = p;
    }
    
    public ParseObject getP() {
      return p;
    }
    
    public ViewHolder getH() {
      return h;
    }
    
    public void setH(ViewHolder h) {
      this.h = h;
    }
  }
}
