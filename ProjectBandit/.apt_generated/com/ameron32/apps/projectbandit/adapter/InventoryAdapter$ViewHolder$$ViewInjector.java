// Generated code from Butter Knife. Do not modify!
package com.ameron32.apps.projectbandit.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class InventoryAdapter$ViewHolder$$ViewInjector {
  public static void inject(Finder finder, final com.ameron32.apps.projectbandit.adapter.InventoryAdapter.ViewHolder target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296424, "field 'itemQuantity'");
    target.itemQuantity = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296422, "field 'itemImage'");
    target.itemImage = (android.widget.ImageButton) view;
    view = finder.findRequiredView(source, 2131296423, "field 'itemName'");
    target.itemName = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296425, "field 'itemValue'");
    target.itemValue = (android.widget.TextView) view;
  }

  public static void reset(com.ameron32.apps.projectbandit.adapter.InventoryAdapter.ViewHolder target) {
    target.itemQuantity = null;
    target.itemImage = null;
    target.itemName = null;
    target.itemValue = null;
  }
}
