// Generated code from Butter Knife. Do not modify!
package com.ameron32.apps.projectbandit.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class EquipmentHeadersAdapter$ViewHolder$$ViewInjector {
  public static void inject(Finder finder, final com.ameron32.apps.projectbandit.adapter.EquipmentHeadersAdapter.ViewHolder target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296412, "field 'itemName'");
    target.itemName = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296416, "field 'durabilityBar'");
    target.durabilityBar = (android.widget.ProgressBar) view;
    view = finder.findRequiredView(source, 2131296414, "field 'itemValue'");
    target.itemValue = (android.widget.Button) view;
    view = finder.findRequiredView(source, 2131296413, "field 'itemDurability'");
    target.itemDurability = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296415, "field 'equipmentSlot'");
    target.equipmentSlot = (android.widget.TextView) view;
  }

  public static void reset(com.ameron32.apps.projectbandit.adapter.EquipmentHeadersAdapter.ViewHolder target) {
    target.itemName = null;
    target.durabilityBar = null;
    target.itemValue = null;
    target.itemDurability = null;
    target.equipmentSlot = null;
  }
}
