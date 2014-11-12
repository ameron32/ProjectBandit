package com.parse.starter.characterview.impl;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.parse.starter.R;
import com.parse.starter.adapter.impl.ChatViewPagerAdapter;
import com.parse.starter.chatview.frmwk.ChatView;
import com.parse.starter.chatview.impl.RecentChatView;

public class CharacterViewManager {
  
  // public SparseArray<WeakReference<ChatView>> storage = new
  // SparseArray<WeakReference<ChatView>>();
  private final LayoutInflater         inflater;
  private final ViewGroup              viewGroup;
  
  public ChatView getViewAt(int position) {
    ChatView item = null;
    // try {
    // WeakReference<ChatView> reference = storage.get(position);
    // item = reference.get();
    // }
    // catch (NullPointerException e) {
    // e.printStackTrace();
    // }
    //
    // if (item == null) {
      item = generateNewChatView(position);
    // }
    return item;
  }
  
  private ChatView generateNewChatView(final int position) {
    ChatView item = null;
    switch (position) {
    case 0:
      // item = RecentChatView.create(inflater, R.layout.view_recent_chat,
      // viewGroup);
      break;
    case 1:
      // item = OOCChatView.create(inflater, R.layout.view_ooc_chat, viewGroup);
      break;
    case 2:
      // left blank for expansion
      // fall-through
    case 3:
      // item = StoryChatView.create(inflater, R.layout.view_story_chat,
      // viewGroup);
      break;
    case 4:
      // item = ThousandChatView.create(inflater, R.layout.view_thousand_chat,
      // viewGroup);
      break;
    default:
      item = RecentChatView.create(inflater, R.layout.view_recent_chat, viewGroup);
    }
    // storage.put(position, new WeakReference<ChatView>(item));
    return item;
  }

  public CharacterViewManager(ChatViewPagerAdapter adapter) {
    this.viewGroup = adapter.getViewPager();
    this.inflater = adapter.getInflater();
  }

}
