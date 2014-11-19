package com.ameron32.apps.projectbandit.adapter;

import android.content.Context;

import com.ameron32.apps.projectbandit.R;
import com.ameron32.apps.projectbandit.adapter.fmwk.AbsMessageAdapter;
import com.ameron32.apps.projectbandit.object.Message;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseQueryAdapter.QueryFactory;

/**
 * 
 * 
 *
 */
public class ChatViewThousandPQAdapter
    extends AbsMessageAdapter {
  
  public ChatViewThousandPQAdapter(
      Context context) {
    super(context, makeQuery(), R.layout.row_message_standard, R.layout.row_message_system, R.layout.row_message_game);
  }
  
  private static QueryFactory<Message> makeQuery() {
    return new ParseQueryAdapter.QueryFactory<Message>() {
      
      @Override public ParseQuery<Message> create() {
        return _QueryManager._Message.get1000ChatQuery();
      }
    };
  }
}