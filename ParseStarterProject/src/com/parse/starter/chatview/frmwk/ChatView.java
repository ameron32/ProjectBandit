package com.parse.starter.chatview.frmwk;

import java.util.Locale;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.starter.MessageManager;
import com.parse.starter.MessageManager.MessageListener;
import com.parse.starter.R;
import com.parse.starter.adapter.frmwk.MyParseQueryAdapter;
import com.parse.starter.chatview.impl.ThousandChatView;
import com.parse.starter.view.frmwk.FrameListView;

public abstract class ChatView extends
    FrameListView implements
    MessageListener {
  
  private static final String  TAG   = ThousandChatView.class.getSimpleName();
  private static final boolean TOAST = true;
  private static final boolean LOG   = true;

  private static final String ID = "Chat View";

  protected final Context     context;

  public ChatView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    this.context = context;
  }
  
  public ChatView(Context context, AttributeSet attrs) {
    super(context, attrs);
    this.context = context;
  }
  
  public ChatView(Context context) {
    super(context);
    this.context = context;
  }
  
  @Override public void create() {
    registerAsListener();
    prepareFilters();
    super.create();
  }
  
  private void prepareFilters() {
    final View filterButton = this.findViewById(R.id.button_filter_menu_toggle);
    filterButton.setOnClickListener(new OnClickListener() {
      
      @Override
      public void onClick(View v) {
        // TODO implement filters menu
        if (TOAST) {
          Toast.makeText(context, "Filters not yet implemented", Toast.LENGTH_SHORT).show();
        }
      }
    });
    
    final TextView filtersTextView = (TextView) this.findViewById(R.id.textview_chat_filters);
    filtersTextView.setText(("filter: none").toUpperCase(Locale.US));
  }
  
  public void requeryMessagesFromServer() {
    MyParseQueryAdapter adapter = (MyParseQueryAdapter) getAdapter();
    adapter.loadObjects();
  }
  
  public String getChatViewId() {
    return ID;
  }
  
  private void registerAsListener() {
    MessageManager.getMessageManager().addMessageListener(this);
  }
  
  private void unregisterAsListener() {
    MessageManager.getMessageManager().removeMessageListener(this);
  }
  
  @Override
  public void onMessageReceived() {
    requeryMessagesFromServer();
  }

  @Override
  public void destroy() {
    unregisterAsListener();
    super.destroy();
  }

}
