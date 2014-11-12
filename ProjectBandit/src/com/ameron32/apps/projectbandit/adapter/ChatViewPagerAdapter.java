package com.ameron32.apps.projectbandit.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class ChatViewPagerAdapter extends PagerAdapter {

  private static final String  TAG     = ChatViewPagerAdapter.class.getSimpleName();
  private static final boolean TOAST   = false;
  private static final boolean LOG     = true;

  private final Context        context;
  private final ViewPager      mViewPager;
  private final LayoutInflater inflater;
  private final ChatViewManager chatViewManager;
  
  int[]                        viewIds = new int[5];

  public ChatViewPagerAdapter(Context context, ViewPager viewPager) {
    super();
    this.context = context;
    this.mViewPager = viewPager;
    inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    chatViewManager = new ChatViewManager(this);
  }

  @Override
  public int getCount() {
    return viewIds.length;
  }

  @Override
  public boolean isViewFromObject(View view, Object object) {
    return view == ((View) object);
  }
  
  @Override
  public Object instantiateItem(ViewGroup collection, int position) {
    if (LOG) Log.d(TAG, "position: " + position);
    View item = collection.getChildAt(position);
    
    if (item == null) {
      item = chatViewManager.getViewAt(position);
      collection.addView(item);
    }
    
    
    return item;
  }
  
  @Override
  public void destroyItem(ViewGroup container, int position, Object object) {
    // super.destroyItem(container, position, object);
    ChatView item = (ChatView) object;
    item.destroy();
  }

  public ViewPager getViewPager() {
    return mViewPager;
  }
  
  public LayoutInflater getInflater() {
    return inflater;
  }
  
  public ChatViewManager getChatManager() {
    return chatViewManager;
  }
  
//  public int getViewPositionFromId(String id) {
//    int position = chatViewManager.getViewFromId(id);
//    return position;
//  }
}
