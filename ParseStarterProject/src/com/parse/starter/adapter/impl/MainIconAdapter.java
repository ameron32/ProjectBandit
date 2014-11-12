package com.parse.starter.adapter.impl;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.ameron32.rpg.projectbandit.fragment.impl.FragmentChooser.FragmentWrapper;
import com.ameron32.rpg.projectbandit.fragment.impl.NavigationFragment;
import com.parse.starter.R;

public class MainIconAdapter extends BaseAdapter {
  
  private final NavigationFragment    navFragment;
  private final Context               context;
  private final List<FragmentWrapper> wrappers;
  private final LayoutInflater        inflater;
  
  public MainIconAdapter(NavigationFragment navFragment, List<FragmentWrapper> wrappers) {
    this.navFragment = navFragment;
    this.context = navFragment.getActivity();
    this.wrappers = wrappers;
    inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }

  @Override
  public View getView(final int position, View view, final ViewGroup parent) {
    if (view == null) {
      view = inflater.inflate(R.layout.row_listicon, parent, false);
      view.setEnabled(true);
    }
    
    Object tag = view.getTag();
    if (tag == null) {
      tag = new ViewHolder(view);
      view.setTag(tag);
    }
    ViewHolder holder = (ViewHolder) tag;

    final FragmentWrapper wrapper = getItem(position);
    
    // TODO imagebutton image logic here


    return view;
  }
  
  static class ViewHolder {
    
    @InjectView(R.id.imagebutton)
    ImageButton imagebutton;

    //
    public ViewHolder(View v) {
      ButterKnife.inject(this, v);
    }
  }

  @Override
  public int getCount() {
    return wrappers.size();
  }
  
  @Override
  public FragmentWrapper getItem(int position) {
    return wrappers.get(position);
  }
  
  @Override
  public long getItemId(int position) {
    return position;
  }
}
