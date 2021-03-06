package com.parse.starter.chatview.impl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.starter.adapter.frmwk.MyParseQueryAdapter;
import com.parse.starter.chatview.frmwk.ChatView;
import com.parse.starter.manager.GameManager;
import com.parse.starter.object.Game;

public class OOCChatView extends ChatView {
  
  public static OOCChatView create(LayoutInflater inflater, int resourceId, ViewGroup container) {
    OOCChatView item = (OOCChatView) inflater.inflate(resourceId, container, false);
    item.create();
    return item;
  }

  public OOCChatView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }
  
  public OOCChatView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }
  
  public OOCChatView(Context context) {
    super(context);
  }
  
  public static final String ID = "Out of Character Chat View";

  @Override
  protected ListAdapter createAdapter(Context context) {
    return new OOCPQAdapter(context);
  }
  
  @Override
  protected void onCreateTitleView(TextView titleView) {
    titleView.setText("Out of Character");
  }
  
  @Override
  public String getChatViewId() {
    return ID;
  }
  
  /**
   * 
   * 
   *
   */
  public static class OOCPQAdapter extends MyParseQueryAdapter {
    
    public OOCPQAdapter(Context context) {
      super(context, makeQuery(), PAGINATION_OFF);
    }
    
    private static QueryFactory<ParseObject> makeQuery() {
      return new ParseQueryAdapter.QueryFactory<ParseObject>() {
        
        @Override
        public ParseQuery<ParseObject> create() {
          ParseQuery<ParseObject> queryCharacter = new ParseQuery<ParseObject>("Character");
          queryCharacter.whereEqualTo("ooc", true);
          queryCharacter.include("profilePic");
          queryCharacter.include("owner");
          queryCharacter.include("actionO");

          ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Message");
          
          /* sort oldest (first/top) to newest */
          query.orderByAscending("createdAt");
          query.setLimit(1000);
          // query.addAscendingOrder("createdAt");
          
          /* show me just the last 12 hours of chat */
          // final int hoursToDisplay = -12;
          // GregorianCalendar calendar = new GregorianCalendar();
          // calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
          // calendar.add(GregorianCalendar.HOUR, hoursToDisplay);
          // final Date d = calendar.getTime();
          // query.whereGreaterThan("createdAt", d);
          
          Game game = null;
          game = GameManager.getGameManager().getCurrentGame();
          if (game != null) {
            int currentSession = game.getCurrentSession();
            query.whereEqualTo("ofGame", game);
            query.whereEqualTo("inSession", currentSession);
          }

          query.whereMatchesQuery("character", queryCharacter);
          
          query.include("user");
          query.include("character");
          query.include("actionO");
          query.include("ofGame");

          return query;
        }
      };
    }
  }
  
}
