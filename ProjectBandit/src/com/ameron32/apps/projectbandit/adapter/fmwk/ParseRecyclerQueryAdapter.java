package com.ameron32.apps.projectbandit.adapter.fmwk;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.widget.RecyclerView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter.QueryFactory;

public abstract class 
ParseRecyclerQueryAdapter<T extends ParseObject, U extends RecyclerView.ViewHolder>
    extends
    RecyclerView.Adapter<U>
{

  
  private final QueryFactory<T> factory;
  private List<T> items;

  // PRIMARY CONSTRUCTOR
  public ParseRecyclerQueryAdapter(final QueryFactory<T> factory) {
    this.factory = factory;
    this.items = new ArrayList<T>();
    loadObjects();
  }
  
  // ALTERNATE CONSTRUCTOR
  public ParseRecyclerQueryAdapter(final String className) {
    this(new QueryFactory<T>() {
      
      @Override public ParseQuery<T> create() {
        return ParseQuery.getQuery(className);
      }
    });
  }
  
  // ALTERNATE CONSTRUCTOR
  public ParseRecyclerQueryAdapter(final Class<T> c) {
    this(new QueryFactory<T>() {
      
      @Override public ParseQuery<T> create() {
        return ParseQuery.getQuery(c);
      }
    });
  }
  
  
  
  
  
  @Override public int getItemCount() {
    return items.size();
  }
  
  protected T getItem(int position) { return items.get(position); }

  protected void onFilterQuery(ParseQuery<T> query) { 
    // provide override for filtering query
  }
  
  public void loadObjects() {
    dispatchOnLoading();
    ParseQuery<T> query = factory.create();
    onFilterQuery(query);
    query.findInBackground(new FindCallback<T>() {;
    
      @Override public void done(
          List<T> queriedItems,
          ParseException e) {
        if (e == null) {
          items = queriedItems;
          notifyDataSetChanged();
          dispatchOnLoaded(queriedItems, e);
        }
      }
    });
  }
  
  
  
  
  
  public interface OnQueryLoadListener<T> {
    
    public void onLoaded(
        List<T> objects, Exception e);
    
    public void onLoading();
  }
  
  private List<OnQueryLoadListener<T>> listeners = new ArrayList<OnQueryLoadListener<T>>();
  
  public void addOnQueryLoadListener(
      OnQueryLoadListener<T> listener) {
    if (!(listeners.contains(listener))) {
      listeners.add(listener);
    }
  }
  
  public void removeOnQueryLoadListener(
      OnQueryLoadListener<T> listener) {
    if (listeners.contains(listener)) {
      listeners.remove(listener);
    }
  }
  
  private void dispatchOnLoading() {
    for (OnQueryLoadListener<T> l : listeners) {
      l.onLoading();
    }
  }
  
  private void dispatchOnLoaded(List<T> objects, ParseException e) {
    for (OnQueryLoadListener<T> l : listeners) {
      l.onLoaded(objects, e);
    }
  }
}
