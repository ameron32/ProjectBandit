package com.parse.parsepush.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.parse.ParseUser;
import com.parse.parsepush.R;
import com.parse.ui.ParseLoginBuilder;

public class MenuActivity extends
    ActionBarActivity {
  
  @Override protected void onCreate(
      Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_menu);
    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
    }
    loginIfNeeded();
  }
  
  private boolean loginIfNeeded() {
    ParseUser currentUser = ParseUser.getCurrentUser();
    if (currentUser == null) {
      startLoginActivity();
      // if (LOG)
      // Log.d(TAG, "current user == null in onCreate()");
      return false;
    }
    return true;
  }
  
  private static final int LOGIN_REQUEST_CODE = 5467;
  
  private void startLoginActivity() {
    ParseLoginBuilder builder = new ParseLoginBuilder(MenuActivity.this);
    startActivityForResult(builder.build(), LOGIN_REQUEST_CODE);
  }
  
  @Override public boolean onCreateOptionsMenu(
      Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu, menu);
    return true;
  }
  
  @Override public boolean onOptionsItemSelected(
      MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    if (id == R.id.action_settings) { return true; }
    return super.onOptionsItemSelected(item);
  }
  
  /**
   * A placeholder fragment containing a simple view.
   */
  public static class PlaceholderFragment
      extends Fragment implements
      OnChildClickListener {
    
    @InjectView(android.R.id.list) ExpandableListView listView;
    
    // private static final Map<String, Class> MENU = new HashMap<String,
    // Class>();
    private static final List<MenuGroup> MENU_GROUPS = new ArrayList<MenuGroup>();

    private MenuExpandableAdapter adapter;
    
    public PlaceholderFragment() {}
    
    // @formatter:off
    @Override public void onCreate(
        Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      
      MENU_GROUPS.clear();
      
      MENU_GROUPS.add(
          new MenuGroup("Pushes")
          .add(new MenuItem("MainPush", MainPushActivity.class)));
      MENU_GROUPS.add(
          new MenuGroup("Creators")
          .add(new MenuItem("Create One Item", CreateItemActivity.class))
          .add(new MenuItem("Create Set of Items", CreateSetItemsActivity.class))
          .add(new MenuItem("Issue An Item", IssueItemActivity.class))
          .add(new MenuItem("Attach a Relation", RelationAttacherActivity.class)));
      MENU_GROUPS.add(
          new MenuGroup("Viewers")
          .add(new MenuItem("CharacterViewer", CharacterViewerActivity.class))
          .add(new MenuItem("ItemViewer", ItemViewerActivity.class))
          .add(new MenuItem("InventoryViewer", CInventoryViewerActivity.class)));
    }
    // @formatter:on
    
    @Override public View onCreateView(
        LayoutInflater inflater,
        ViewGroup container,
        Bundle savedInstanceState) {
      View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
      ButterKnife.inject(this, rootView);
      return rootView;
    }
    
    @Override public void onStart() {
      super.onStart();
      adapter = MenuExpandableAdapter.create(getActivity(), MENU_GROUPS);
      setListAdapter(adapter);
      listView.setOnChildClickListener(this);
    }

    private void setListAdapter(
        BaseExpandableListAdapter adapter) {
      listView.setAdapter(adapter);
    }
    
    @Override public boolean onChildClick(
        ExpandableListView parent,
        View v, int groupPosition,
        int childPosition, long id) {
      Class myActivity = MENU_GROUPS.get(groupPosition).items.get(childPosition).myActivity;
      Intent intent = new Intent(getActivity(), myActivity);
      startActivity(intent);
      return true;
    }

    public static class MenuExpandableAdapter
        extends
        BaseExpandableListAdapter {
      
      private final Context _context;
      private final List<String> _listDataHeader;
      private final HashMap<String, List<String>> _listDataChild;
      private final List<MenuGroup> menuGroups;
      
      public static MenuExpandableAdapter create(
          Context context,
          List<MenuGroup> menuGroups) {
        MenuExpandableAdapter adapter = new MenuExpandableAdapter(context, menuGroups);
        adapter.onCreate();
        return adapter;
      }
      
      private MenuExpandableAdapter(
          Context context,
          List<MenuGroup> menuGroups) {
        this._context = context;
        this.menuGroups = menuGroups;
        this._listDataHeader = getGroupHeaders(menuGroups);
        this._listDataChild = getChildLabels(menuGroups);
      }
      
      private void onCreate() {
        //
      }

      @Override public Object getChild(
          int groupPosition,
          int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).get(childPosititon);
      }
      
      @Override public long getChildId(
          int groupPosition,
          int childPosition) {
        return childPosition;
      }
      
      @Override public int getChildrenCount(
          int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).size();
      }
      
      @Override public Object getGroup(
          int groupPosition) {
        return this._listDataHeader.get(groupPosition);
      }
      
      @Override public int getGroupCount() {
        return this._listDataHeader.size();
      }
      
      @Override public long getGroupId(
          int groupPosition) {
        return groupPosition;
      }
      
      @Override public boolean hasStableIds() {
        return false;
      }
      
      @Override public boolean isChildSelectable(
          int groupPosition,
          int childPosition) {
        return true;
      }

      @Override public View getGroupView(
          int groupPosition,
          boolean isExpanded,
          View convertView,
          ViewGroup parent) {
        final String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
          LayoutInflater inflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
          convertView = inflater.inflate(android.R.layout.simple_expandable_list_item_1, parent, false);
        }
        
        TextView lblListHeader = (TextView) convertView.findViewById(android.R.id.text1);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);
        
        return convertView;
      }
      
      @Override public View getChildView(
          int groupPosition,
          int childPosition,
          boolean isLastChild,
          View convertView,
          ViewGroup parent) {
        final String childText = (String) getChild(groupPosition, childPosition);
        if (convertView == null) {
          LayoutInflater inflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
          convertView = inflater.inflate(android.R.layout.simple_expandable_list_item_1, parent, false);
        }
        
        TextView lblListHeader = (TextView) convertView.findViewById(android.R.id.text1);
        // lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(childText);
        
        return convertView;
      }
    }
    
    public static class MenuGroup {
      final String name;
      List<MenuItem> items;
      
      public MenuGroup(String name) {
        this.name = name;
        items = new ArrayList<MenuItem>();
      }
      
      public MenuGroup add(MenuItem item) {
        items.add(item);
        return this;
      }
    }
    
    public static class MenuItem {
      final String name;
      final Class myActivity;
      
      public MenuItem(String name,
          Class myActivity) {
        this.name = name;
        this.myActivity = myActivity;
      }
    }
    
    private static List<String> getGroupHeaders(
        List<MenuGroup> groups) {
      List<String> headers = new ArrayList<String>();
      for (MenuGroup group : groups) {
        String header = group.name;
        headers.add(header);
      }
      return headers;
    }
    
    private static HashMap<String, List<String>> getChildLabels(
        List<MenuGroup> groups) {
      HashMap<String, List<String>> childLabels = new HashMap<String, List<String>>();
      
      for (MenuGroup group : groups) {
        String header = group.name;
        List<String> holder = new ArrayList<String>();
        childLabels.put(header, holder);
      }
      
      for (MenuGroup group : groups) {
        List<String> holder = childLabels.get(group.name);
        
        for (MenuItem item : group.items) {
          String label = item.name;
          
          holder.add(label);
        }
      }
      
      return childLabels;
    }
  }
}
