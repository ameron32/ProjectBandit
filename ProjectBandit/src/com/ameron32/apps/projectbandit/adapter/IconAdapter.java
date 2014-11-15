package com.ameron32.apps.projectbandit.adapter;

import java.lang.ref.WeakReference;

import butterknife.InjectView;
import android.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;



public class IconAdapter extends
    BaseAdapter {
  
  private LayoutInflater inflater;
  private int layoutResource;
  private int imageViewId;
  private int[] imageResources;
  
  public IconAdapter(Context context,
      int layoutResource,
      int imageViewId,
      int[] imageResources) {
    this.layoutResource = layoutResource;
    this.imageViewId = imageViewId;
    this.imageResources = imageResources;
    this.inflater = LayoutInflater.from(context);
  }
  
  @Override public int getCount() {
    return imageResources.length;
  }
  
  @Override public Integer getItem(
      int position) {
    return imageResources[position];
  }
  
  @Override public long getItemId(
      int position) {
    return position;
  }
  
  @Override public View getView(
      int position, View convertView,
      ViewGroup parent) {
    ViewHolder holder;
    if (convertView == null) {
      convertView = createView(inflater, parent, layoutResource);
      holder = new ViewHolder(convertView, imageViewId);
      convertView.setTag(holder);
    } else {
      holder = (ViewHolder) convertView.getTag();
    }
    bindView(holder, convertView, position);
    
    return convertView;
  }

  private View createView(
      LayoutInflater inflater,
      ViewGroup parent,
      int layoutResource) {
    return inflater.inflate(layoutResource, parent, false);
  }
  
  private void bindView(ViewHolder holder, View convertView, int position) {
    holder.imageView.get().setImageResource(getItem(position));
  }
  
  static class ViewHolder {
    WeakReference<View> root;
    WeakReference<ImageView> imageView;
    
    public ViewHolder(View view, int imageViewId) {
      root = new WeakReference<View>(view);
      imageView = new WeakReference<ImageView>((ImageView) view.findViewById(imageViewId));
    }
  }
}
