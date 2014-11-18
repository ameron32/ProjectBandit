// Generated code from Butter Knife. Do not modify!
package com.parse.starter.adapter.impl;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class InventoryAdapter$ViewHolder$$ViewInjector {
  public static void inject(Finder finder, final com.parse.starter.adapter.impl.InventoryAdapter.ViewHolder target, Object source) {
    View view;
    view = finder.findRequiredView(source, 0, "field 'itemName', field 'itemQuantity', field 'itemImage', and field 'itemValue'");
    target.itemName = (android.widget.TextView) view;
    target.itemQuantity = (android.widget.TextView) view;
    target.itemImage = (android.widget.ImageButton) view;
    target.itemValue = (android.widget.TextView) view;
  }

  public static void reset(com.parse.starter.adapter.impl.InventoryAdapter.ViewHolder target) {
    target.itemName = null;
    target.itemQuantity = null;
    target.itemImage = null;
    target.itemValue = null;
  }
}
