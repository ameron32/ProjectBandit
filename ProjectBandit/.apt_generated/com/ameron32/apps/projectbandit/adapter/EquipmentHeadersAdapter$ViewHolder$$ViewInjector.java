// Generated code from Butter Knife. Do not modify!
package com.ameron32.apps.projectbandit.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class EquipmentHeadersAdapter$ViewHolder$$ViewInjector {
  public static void inject(Finder finder, final com.ameron32.apps.projectbandit.adapter.EquipmentHeadersAdapter.ViewHolder target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296424, "field 'itemValue'");
    target.itemValue = (android.widget.Button) view;
    view = finder.findRequiredView(source, 2131296423, "field 'itemDurability'");
    target.itemDurability = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296425, "field 'equipmentSlot'");
    target.equipmentSlot = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296422, "field 'itemName'");
    target.itemName = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296426, "field 'durabilityBar'");
    target.durabilityBar = (android.widget.ProgressBar) view;
  }

  public static void reset(com.ameron32.apps.projectbandit.adapter.EquipmentHeadersAdapter.ViewHolder target) {
    target.itemValue = null;
    target.itemDurability = null;
    target.equipmentSlot = null;
    target.itemName = null;
    target.durabilityBar = null;
  }
}
