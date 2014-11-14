// Generated code from Butter Knife. Do not modify!
package com.ameron32.apps.projectbandit.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class InventoryAdapter$ViewHolder$$ViewInjector {
  public static void inject(Finder finder, final com.ameron32.apps.projectbandit.adapter.InventoryAdapter.ViewHolder target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296418, "field 'itemQuantity'");
    target.itemQuantity = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296419, "field 'itemValue'");
    target.itemValue = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296417, "field 'itemName'");
    target.itemName = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296416, "field 'itemImage'");
    target.itemImage = (android.widget.ImageButton) view;
  }

  public static void reset(com.ameron32.apps.projectbandit.adapter.InventoryAdapter.ViewHolder target) {
    target.itemQuantity = null;
    target.itemValue = null;
    target.itemName = null;
    target.itemImage = null;
  }
}
