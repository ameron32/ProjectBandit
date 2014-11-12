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


public class RecentChatView extends ChatView {
  
  public static RecentChatView create(LayoutInflater inflater, int resourceId, ViewGroup container) {
    RecentChatView item = (RecentChatView) inflater.inflate(resourceId, container, false);
    item.create();
    return item;
  }

  public RecentChatView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }
  
  public RecentChatView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }
  
  public RecentChatView(Context context) {
    super(context);
  }
  
  public static final String ID = "Recent Chat View";

  @Override
  protected ListAdapter createAdapter(Context context) {
    return new RecentPQAdapter(context);
  }
  
  @Override
  protected void onCreateTitleView(TextView titleView) {
    titleView.setText("Recent");
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
  public static class RecentPQAdapter extends MyParseQueryAdapter {
    
    public RecentPQAdapter(Context context) {
      super(context, makeQuery(), PAGINATION_OFF);
    }
    
    private static QueryFactory<ParseObject> makeQuery() {
      return new ParseQueryAdapter.QueryFactory<ParseObject>() {
        
        @Override
        public ParseQuery<ParseObject> create() {
          ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Message");
          final int hoursToDisplay = -12;
          
          /* sort oldest (first/top) to newest */
          query.orderByAscending("createdAt");
          // query.addAscendingOrder("createdAt");
          
          /* show me just the last 12 hours of chat */
          query.whereGreaterThan("createdAt", makeDate(hoursToDisplay));
          
          Game game = null;
          game = GameManager.get().getCurrentGame();
          if (game != null) {
            int currentSession = game.getCurrentSession();
            query.whereEqualTo("ofGame", game);
            query.whereEqualTo("inSession", currentSession);
          }
          
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
