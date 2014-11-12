package com.parse.starter.chatview.impl;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

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


public class StoryChatView extends ChatView {
  
  public static StoryChatView create(LayoutInflater inflater, int resourceId, ViewGroup container) {
    StoryChatView item = (StoryChatView) inflater.inflate(resourceId, container, false);
    item.create();
    return item;
  }

  public StoryChatView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }
  
  public StoryChatView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }
  
  public StoryChatView(Context context) {
    super(context);
  }
  
  public static final String ID = "Story Chat View";

  @Override
  protected ListAdapter createAdapter(Context context) {
    return new StoryPQAdapter(context);
  }
  
  @Override
  protected void onCreateTitleView(TextView titleView) {
    titleView.setText("Story");
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
  public static class StoryPQAdapter extends MyParseQueryAdapter {
    
    public StoryPQAdapter(Context context) {
      super(context, makeQuery(), PAGINATION_OFF);
    }
    
    private static QueryFactory<ParseObject> makeQuery() {
      return new ParseQueryAdapter.QueryFactory<ParseObject>() {
        
        @Override
        public ParseQuery<ParseObject> create() {
          ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Message");
          
          /* sort oldest (first/top) to newest */
          query.orderByAscending("createdAt");
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
          
          query.whereEqualTo("canon", true);
          
          query.include("user");
          query.include("character");
          query.include("actionO");
          query.include("ofGame");

          return query;
        }
      };
    }
    
    private static Date makeDate(int hours) {
      final GregorianCalendar calendar = new GregorianCalendar();
      calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
      calendar.add(GregorianCalendar.HOUR, hours);
      final Date d = calendar.getTime();
      return d;
    }
  }
  
}
