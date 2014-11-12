package com.ameron32.apps.projectbandit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ameron32.apps.projectbandit.R;
import com.ameron32.apps.projectbandit.adapter.fmwk.AbsMessageAdapter;
import com.ameron32.apps.projectbandit.adapter.fmwk.AbsMessageAdapter.ViewHolder;
import com.ameron32.apps.projectbandit.object.Message;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseQueryAdapter.QueryFactory;

public class ChatViewStory extends ChatView {
  
  public static final String ID = ChatViewStory.class.getSimpleName();

	public static ChatViewStory create(LayoutInflater inflater, int resourceId,
			ViewGroup container) {
		ChatViewStory item = (ChatViewStory) inflater.inflate(resourceId,
				container, false);
		item.create();
		return item;
	}

	public ChatViewStory(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public ChatViewStory(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ChatViewStory(Context context) {
		super(context);
	}

	@Override
	protected Adapter<ViewHolder> createAdapter(Context context) {
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
	public static class StoryPQAdapter extends AbsMessageAdapter {

		public StoryPQAdapter(Context context) {
		  super(context, makeQuery(), R.layout.row_message_standard, R.layout.row_message_system, R.layout.row_message_game);
		}

		private static QueryFactory<Message> makeQuery() {
			return new ParseQueryAdapter.QueryFactory<Message>() {

				@Override
				public ParseQuery<Message> create() {
					return _QueryManager._Message.getStoryChatQuery();
				}
			};
		}


	}

}
