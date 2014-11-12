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

public class ThousandChatView extends ChatView {
  
  private static final String  TAG   = ThousandChatView.class.getSimpleName();
  private static final boolean TOAST = true;
  private static final boolean LOG   = true;

  public static ThousandChatView create(LayoutInflater inflater, int resourceId, ViewGroup container) {
    ThousandChatView item = (ThousandChatView) inflater.inflate(resourceId, container, false);
    item.create();
    return item;
  }

  public ThousandChatView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }
  
  public ThousandChatView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }
  
  public ThousandChatView(Context context) {
    super(context);
  }
  
  public static final String ID = "Thousand Chat View";

  @Override
  protected ListAdapter createAdapter(Context context) {
    return new ThousandPQAdapter(context);
  }
  
  @Override
  protected void onCreateTitleView(TextView titleView) {
    titleView.setText("1000");
  }
  
  @Override
  public String getChatViewId() {
    return ID;
  }
  
  @Override
  protected void onCreate() {
    super.onCreate();
  }

  /**
   * 
   * 
   *
   */
  public static class ThousandPQAdapter extends MyParseQueryAdapter {
    
    public ThousandPQAdapter(Context context) {
      super(context, makeQuery(), PAGINATION_OFF);
    }
    
    private static QueryFactory<ParseObject> makeQuery() {
      return new ParseQueryAdapter.QueryFactory<ParseObject>() {
        
        @Override
        public ParseQuery<ParseObject> create() {
          ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Message");

          Game game = null;
          game = GameManager.getGameManager().getCurrentGame();
          if (game != null) {
            int currentSession = game.getCurrentSession();
            query.whereEqualTo("ofGame", game);
            query.whereEqualTo("inSession", currentSession);
          }
          query.orderByAscending("createdAt");

          query.setLimit(1000);
          
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
