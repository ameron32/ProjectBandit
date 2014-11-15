package com.ameron32.apps.projectbandit.manager;

import java.util.ArrayList;
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
import com.ameron32.apps.projectbandit.content.CreateItemFragment;
import com.ameron32.apps.projectbandit.content.CreateSetItemsFragment;
import com.ameron32.apps.projectbandit.content.EquipmentHeadersTestFragment;
import com.ameron32.apps.projectbandit.content.InventoryHeadersTestFragment;
import com.ameron32.apps.projectbandit.content.IssueItemFragment;
import com.ameron32.apps.projectbandit.core.ChatManagerFragment;
import com.ameron32.apps.projectbandit.core.ContentFragment;
import com.ameron32.apps.projectbandit.core.trial.DEMORCFragment;
import com.ameron32.apps.projectbandit.core.trial.EquipmentTestFragment;
import com.ameron32.apps.projectbandit.core.trial.GameFragment;
import com.ameron32.apps.projectbandit.core.trial.InventoryTestFragment;
import com.ameron32.apps.projectbandit.core.trial.RelationAttacherFragment;
import com.ameron32.apps.projectbandit.core.trial.SectionContainerTestFragment;
import com.ameron32.apps.projectbandit.core.trial.SkillsTestFragment;
import com.ameron32.apps.projectbandit.core.trial.StatsTestFragment;
import com.ameron32.apps.projectbandit.core.trial.TableTestFragment;

public class ContentManager {
  
  private static ContentManager contentManager;
  
  public static ContentManager get() {
    if (contentManager == null) {
      contentManager = new ContentManager();
      contentManager.initialize();
    }
    return contentManager;
  }


  public static void destroy() {
    contentManager = null;
  }
  
  private List<ContentItem> contentItems;
  
  private ContentManager() {}
  
  
  private void initialize() {
    listeners = new ArrayList<OnContentChangeListener>();
    contentItems = createContentItems();
  }
  
  private List<ContentItem> createContentItems() {
    List<ContentItem> items = new ArrayList<ContentItem>();

    items.add(new ContentItem("Test:Game", R.drawable.ic_construction,
        GameFragment.newInstance(R.layout.section_game)));

    
//  TODO: instantiate GridPagerFragment correctly
//    items.add(new ContentItem("GridTest", R.drawable.ic_construction, 
//        GridPagerFragment.newInstance(3)));
    
    items.add(new ContentItem("Test:Stats", R.drawable.ic_construction, 
        SectionContainerTestFragment.newInstance(StatsTestFragment.class, R.layout.section_character_stats)));
    items.add(new ContentItem("Test:Equipment2", R.drawable.ic_construction, 
        SectionContainerTestFragment.newInstance(EquipmentHeadersTestFragment.class, R.layout.section_character_equipment_headers)));
    items.add(new ContentItem("Test:Inventory2", R.drawable.ic_construction, 
        SectionContainerTestFragment.newInstance(InventoryHeadersTestFragment.class, R.layout.section_character_inventory_headers)));
    
    
//  TODO: reenable later
//    items.add(new ContentItem("Test:Characters", R.drawable.ic_construction,
//        TableTestFragment.create("Character", R.layout.fragment_default_table_layout)));
    
    if (GameManager.get().isCurrentUserGM()) {
      items.add(new ContentItem("GM:Demo:blank", R.drawable.ic_gm, 
          ContentFragment.newInstance(/*1*/)));
      items.add(new ContentItem("GM:Chat", R.drawable.ic_gm, 
              ChatManagerFragment.newInstance(null, null)));
      items.add(new ContentItem("GM:RETIRED:Equipment", R.drawable.ic_gm, 
          SectionContainerTestFragment.newInstance(EquipmentTestFragment.class, R.layout.section_character_equipment)));
      items.add(new ContentItem("GM:RETIRED:Inventory", R.drawable.ic_gm, 
          SectionContainerTestFragment.newInstance(InventoryTestFragment.class, R.layout.section_character_inventory)));
      
      items.add(new ContentItem("GM:Test:Skills", R.drawable.ic_gm, 
          SectionContainerTestFragment.newInstance(SkillsTestFragment.class, R.layout.section_skills)));
      items.add(new ContentItem("GM:Test:Table Character", R.drawable.ic_gm, 
          TableTestFragment.create("Character", R.layout.section_)));
      items.add(new ContentItem("GM:Test:Table CInventory", R.drawable.ic_gm, 
          TableTestFragment.create("CInventory", R.layout.section_)));
      items.add(new ContentItem("GM:Test:Table Item", R.drawable.ic_gm, 
          TableTestFragment.create("Item", R.layout.section_)));
      
      items.add(new ContentItem("GM: Create Item", R.drawable.ic_gm, new CreateItemFragment()));
      items.add(new ContentItem("GM: Issue Item", R.drawable.ic_gm, new IssueItemFragment()));
      items.add(new ContentItem("Database: Create Item Set", R.drawable.ic_gm, new CreateSetItemsFragment()));
      items.add(new ContentItem("Database: Attach Relation", R.drawable.ic_gm, new RelationAttacherFragment()));
    }
    
    return items;
  }
  
  public List<OnContentChangeListener> listeners;
  
  public boolean addOnContentChangeListener(
      OnContentChangeListener listener) {
    return listeners.add(listener);
  }
  
  public boolean removeOnContentChangeListener(
      OnContentChangeListener listener) {
    return listeners.remove(listener);
  }

  private void notifyListenersOfContentChange(int position) {
    for (OnContentChangeListener listener : listeners) {
      listener.onContentChange(this, position);
    }
  }
  
  public interface OnContentChangeListener {
    public void onContentChange(
        ContentManager manager,
        int position);
  }

  private int mCurrentSelectedFragment = 0;
  
  public void setCurrentSelectedFragmentPosition(
      int position) {
    mCurrentSelectedFragment = position;
    notifyListenersOfContentChange(position);
  }
  
  public int getCurrentSelectedFragment() {
    return mCurrentSelectedFragment;
  }
  
  public String getTitleForPosition(
      int position) {
    return contentItems.get(position).title;
  }
  
  public ContentFragment getNewFragmentForPosition(
      int position) {
    return contentItems.get(position).fragment;
  }
  
  public String[] getTitles() {
    String[] titles = new String[contentItems.size()];
    for (int i = 0; i < titles.length; i++) {
      titles[i] = contentItems.get(i).title;
    }
    return titles;
  }
  
  public int[] getImageIcons() {
    int[] icons = new int[contentItems.size()];
    for (int i = 0; i < icons.length; i++) {
      icons[i] = contentItems.get(i).imageResource;
    }
    return icons;
  }
  
  public static class ContentItem {
    String title;
    int imageResource;
    ContentFragment fragment;
    
    public ContentItem(String title,
        int imageResource, 
        ContentFragment fragment) {
      super();
      this.title = title;
      this.imageResource = imageResource;
      this.fragment = fragment;
    }
  }
  
  public ContentAdapter getAdapter() {
    return new ContentAdapter(contentItems);
  }
  
  public static class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ViewHolder> {

    private List<ContentItem> mData;
//    private NavigationDrawerCallbacks mNavigationDrawerCallbacks;
    private int mSelectedPosition;
    private int mTouchedPosition;
    private boolean isClick = false;

    public ContentAdapter(List<ContentItem> data) {
        mData = data;
    }
//
//    public NavigationDrawerCallbacks getNavigationDrawerCallbacks() {
//        return mNavigationDrawerCallbacks;
//    }
//
//    public void setNavigationDrawerCallbacks(NavigationDrawerCallbacks navigationDrawerCallbacks) {
//        mNavigationDrawerCallbacks = navigationDrawerCallbacks;
//    }

    @Override
    public ContentAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_nav_text_drawer, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ContentAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.textView.setText(mData.get(i).title);
        Drawable d = viewHolder.textView.getContext().getResources().getDrawable(mData.get(i).imageResource);
        viewHolder.textView.setCompoundDrawablesWithIntrinsicBounds(d, null, null, null);

        viewHolder.itemView.setOnTouchListener(new View.OnTouchListener() {
                                                   @Override
                                                   public boolean onTouch(View v, MotionEvent event) {

                                                       switch (event.getAction()) {
                                                           case MotionEvent.ACTION_DOWN:
                                                               touchPosition(i);
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
                                               }
        );
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                                                   @Override
                                                   public void onClick(View v) {
//                                                       if (mNavigationDrawerCallbacks != null)
//                                                           mNavigationDrawerCallbacks.onNavigationDrawerItemSelected(i);
                                                     ContentManager.get().setCurrentSelectedFragmentPosition(i);
                                                   }
                                               }
        );

        //TODO: selected menu position, change layout accordingly
        if (mSelectedPosition == i || mTouchedPosition == i) {
            viewHolder.itemView.setBackgroundColor(viewHolder.itemView.getContext().getResources().getColor(R.color.selected_gray));
        } else {
            viewHolder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    private void touchPosition(int position) {
        int lastPosition = mTouchedPosition;
        mTouchedPosition = position;
        if (lastPosition >= 0)
            notifyItemChanged(lastPosition);
        if (position >= 0)
            notifyItemChanged(position);
    }

    public void selectPosition(int position) {
        int lastPosition = mSelectedPosition;
        mSelectedPosition = position;
        notifyItemChanged(lastPosition);
        notifyItemChanged(position);
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_name);
        }
    }}
}
