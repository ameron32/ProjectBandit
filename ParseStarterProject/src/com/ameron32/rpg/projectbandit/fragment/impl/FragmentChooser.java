package com.ameron32.rpg.projectbandit.fragment.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View.OnClickListener;

import com.ameron32.rpg.projectbandit.screen.CharacterScreen;
import com.parse.starter.R;

public class FragmentChooser {
  
  /**
   * A map of sample (dummy) items, by ID.
   */
  public static List<FragmentWrapper>        ITEM_LIST = new ArrayList<FragmentWrapper>();
  public static Map<String, FragmentWrapper> ITEM_MAP  = new HashMap<String, FragmentWrapper>();
  
  static {
    // Add 3 sample items.
    addItem(new FragmentWrapper("1", "Chat", 0, ChatManagerFragment.newInstance("ChatManager", "ChatManager2")));
    addItem(new FragmentWrapper("2", "Character", 0, CharacterScreen.newInstance("CharacterScreen", "CharacterScreen2")));
    addItem(new FragmentWrapper("3", "None Yet", 0, new ContentFragment()));
    addItem(new FragmentWrapper("4", "None Yet", 0, new ContentFragment()));
  }
  
  private static void addItem(FragmentWrapper item) {
    ITEM_LIST.add(item);
    ITEM_MAP.put(item.id, item);
  }
  
  private static ContentFragment createFragmentFromId(String id) {
    return ITEM_MAP.get(id).getFragment();
  }
  
  //
  // private static boolean is(String id, String equals) {
  // return id.equals(equals);
  // }
  
  public static ContentFragment changeContentFragmentFromId(FragmentActivity activity, String id) {
    ContentFragment contentFragment = FragmentChooser.createFragmentFromId(id);
    
    FragmentManager sfm = activity.getSupportFragmentManager();
    FragmentTransaction transaction = sfm.beginTransaction();
    transaction.setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out);
    transaction.replace(R.id.content_pane, contentFragment);
    transaction.commit();
    
    return contentFragment;
  }
  
  /**
   * A dummy item representing a piece of content.
   */
  public static class FragmentWrapper {
    
    public String                 id;
    public String                 content;
    public int                    imageResource;
    private final ContentFragment fragment;
    public OnClickListener        clickListener;
    
    public FragmentWrapper(String id, String content, int imageResource, ContentFragment fragment) {
      this.id = id;
      this.content = content;
      this.imageResource = imageResource;
      this.fragment = fragment;
    }
    
    @Override
    public String toString() {
      return content;
    }
    
    public ContentFragment getFragment() {
      return fragment;
    }

  }
  
  public static String getFragmentIdByPosition(int position) {
    return ITEM_LIST.get(position).id;
  }
}
