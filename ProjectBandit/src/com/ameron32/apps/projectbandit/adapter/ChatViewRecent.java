package com.ameron32.apps.projectbandit.adapter;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseQueryAdapter.QueryFactory;
import com.ameron32.apps.projectbandit.R;
import com.ameron32.apps.projectbandit.adapter.fmwk.AbsMessageAdapter;
import com.ameron32.apps.projectbandit.adapter.fmwk.AbsMessageAdapter.ViewHolder;
import com.ameron32.apps.projectbandit.manager.GameManager;
import com.ameron32.apps.projectbandit.object.Game;
import com.ameron32.apps.projectbandit.object.Message;

public class ChatViewRecent extends ChatView {
  
  public static final String ID = ChatViewRecent.class.getSimpleName();

	public static ChatViewRecent create(LayoutInflater inflater,
			int resourceId, ViewGroup container) {
		ChatViewRecent item = (ChatViewRecent) inflater.inflate(resourceId,
				container, false);
		item.create();
		return item;
	}

	public ChatViewRecent(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public ChatViewRecent(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ChatViewRecent(Context context) {
		super(context);
	}

	@Override
	protected Adapter<ViewHolder> createAdapter(Context context) {
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
	public static class RecentPQAdapter extends AbsMessageAdapter {

		public RecentPQAdapter(Context context) {
		  super(context, makeQuery(), R.layout.row_message_standard, R.layout.row_message_system, R.layout.row_message_game);
		}

		private static QueryFactory<Message> makeQuery() {
			return new ParseQueryAdapter.QueryFactory<Message>() {

				@Override
				public ParseQuery<Message> create() {
					return _QueryManager._Message.getRecentQuery();
				}
			};
		}

	}

}
