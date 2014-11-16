package com.ameron32.apps.projectbandit.adapter;

import java.util.List;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ameron32.apps.projectbandit.R;
import com.ameron32.apps.projectbandit.manager.ContentManager;
import com.ameron32.apps.projectbandit.manager.ContentManager.ContentItem;

public class ContentAdapter
    extends RecyclerView.Adapter<ContentAdapter.ViewHolder> {
  
  private List<ContentItem> mData;
  private int mSelectedPosition;
  private int mTouchedPosition = -1;
  private boolean isClick = false;
  
  public ContentAdapter(
      List<ContentItem> data) {
    mData = data;
  }
  
  @Override public ContentAdapter.ViewHolder onCreateViewHolder(
      ViewGroup viewGroup, int i) {
    View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_nav_text_drawer, viewGroup, false);
    return new ViewHolder(v);
  }
  
  @Override public void onBindViewHolder(
      ContentAdapter.ViewHolder holder,
      final int position) {
    holder.textView.setText(mData.get(position).title);
    Drawable d = holder.textView.getContext().getResources().getDrawable(mData.get(position).imageResource);
    holder.textView.setCompoundDrawablesWithIntrinsicBounds(d, null, null, null);
    
    holder.itemView.setOnTouchListener(new View.OnTouchListener() {
      @Override public boolean onTouch(
          View v, MotionEvent event) {
        
        switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN:
          touchPosition(position);
          return false;
        case MotionEvent.ACTION_CANCEL:
          touchPosition(-1);
          return false;
        case MotionEvent.ACTION_MOVE:
          return false;
        case MotionEvent.ACTION_UP:
          touchPosition(-1);
          return false;
        }
        return true;
      }
    });
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(
          View v) {
        ContentManager.get().setCurrentSelectedFragmentPosition(position);
      }
    });
    
    // TODO: selected menu position, change layout accordingly
    if (mSelectedPosition == position
        || mTouchedPosition == position) {
      holder.itemView.setBackgroundColor(holder.itemView.getContext().getResources().getColor(R.color.selected_gray));
    } else {
      holder.itemView.setBackgroundColor(Color.TRANSPARENT);
    }
  }
  
  private void touchPosition(
      int position) {
    int lastPosition = mTouchedPosition;
    mTouchedPosition = position;
    if (lastPosition >= 0)
      notifyItemChanged(lastPosition);
    if (position >= 0)
      notifyItemChanged(position);
  }
  
  public void selectPosition(
      int position) {
    int previousPosition = mSelectedPosition;
    mSelectedPosition = position;
    notifyItemChanged(previousPosition);
    notifyItemChanged(position);
  }
  
  @Override public int getItemCount() {
    return mData != null ? mData.size()
        : 0;
  }
  
  public static class ViewHolder extends
      RecyclerView.ViewHolder {
    public TextView textView;
    
    public ViewHolder(View itemView) {
      super(itemView);
      textView = (TextView) itemView.findViewById(R.id.item_name);
    }
  }
}