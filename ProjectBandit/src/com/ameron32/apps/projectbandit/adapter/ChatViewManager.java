package com.ameron32.apps.projectbandit.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ameron32.apps.projectbandit.R;

public class ChatViewManager {
  
  private static final String  TAG     = ChatViewManager.class.getSimpleName();
  private static final boolean TOAST   = false;
  private static final boolean LOG     = true;

	// public SparseArray<WeakReference<ChatView>> storage = new
	// SparseArray<WeakReference<ChatView>>();
	private final LayoutInflater inflater;
	private final ViewGroup viewGroup;

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
	
//	public int getViewFromId(String id) {
//	  for (int i = 0; i < chatViews.length; i++) {
//	    Class chatView = chatViews[i];
//	    Log.i(TAG, "chatView: " + chatView.getSimpleName() + " | id: " + id);
//	    if (chatView.getSimpleName().equalsIgnoreCase(id)) { return i; }
//	  }
//	  return 0;
//	}

//	private Class[] chatViews = { ChatViewRecent.class, ChatViewOOC.class, ChatViewStory.class, ChatViewStory.class, ChatViewThousand.class };
	private ChatView generateNewChatView(final int position) {
		ChatView item = null;
		switch (position) {
		case 0:
			item = ChatViewRecent.create(inflater, R.layout.view_recent_chat,
					viewGroup);
			break;
		case 1:
			item = ChatViewOOC.create(inflater, R.layout.view_ooc_chat,
					viewGroup);
			break;
		case 2:
			// left blank for expansion
			// fall-through
		case 3:
			item = ChatViewStory.create(inflater, R.layout.view_story_chat,
					viewGroup);
			break;
		case 4:
			item = ChatViewThousand.create(inflater,
					R.layout.view_thousand_chat, viewGroup);
			break;
		default:
			item = ChatViewRecent.create(inflater, R.layout.view_recent_chat,
					viewGroup);
		}
		// storage.put(position, new WeakReference<ChatView>(item));
		return item;
	}

	public ChatViewManager(ChatViewPagerAdapter adapter) {
		this.viewGroup = adapter.getViewPager();
		this.inflater = adapter.getInflater();
	}

}
