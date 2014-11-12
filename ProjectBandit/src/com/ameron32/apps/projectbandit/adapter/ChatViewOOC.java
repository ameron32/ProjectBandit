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

public class ChatViewOOC extends ChatView {
  
  public static final String ID = ChatViewOOC.class.getSimpleName();

	public static ChatViewOOC create(LayoutInflater inflater, int resourceId,
			ViewGroup container) {
		ChatViewOOC item = (ChatViewOOC) inflater.inflate(resourceId,
				container, false);
		item.create();
		return item;
	}

	public ChatViewOOC(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public ChatViewOOC(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ChatViewOOC(Context context) {
		super(context);
	}

	@Override
	protected Adapter<ViewHolder> createAdapter(Context context) {
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
	public static class OOCPQAdapter extends AbsMessageAdapter {

		public OOCPQAdapter(Context context) {
      super(context, makeQuery(), R.layout.row_message_standard, R.layout.row_message_system, R.layout.row_message_game);
		}

		private static QueryFactory<Message> makeQuery() {
			return new ParseQueryAdapter.QueryFactory<Message>() {

				@Override
				public ParseQuery<Message> create() {
					return _QueryManager._Message.getOOCQuery();
				}
			};
		}
	}

}
