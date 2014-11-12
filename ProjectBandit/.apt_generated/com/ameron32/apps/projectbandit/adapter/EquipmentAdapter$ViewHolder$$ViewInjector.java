// Generated code from Butter Knife. Do not modify!
package com.ameron32.apps.projectbandit.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class EquipmentAdapter$ViewHolder$$ViewInjector {
  public static void inject(Finder finder, final com.ameron32.apps.projectbandit.adapter.EquipmentAdapter.ViewHolder target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493021, "field 'itemValue'");
    target.itemValue = (android.widget.Button) view;
    view = finder.findRequiredView(source, 2131493023, "field 'durabilityBar'");
    target.durabilityBar = (android.widget.ProgressBar) view;
    view = finder.findRequiredView(source, 2131493020, "field 'itemDurability'");
    target.itemDurability = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131493022, "field 'equipmentSlot'");
    target.equipmentSlot = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131493019, "field 'itemName'");
    target.itemName = (android.widget.TextView) view;
  }

  public static void reset(com.ameron32.apps.projectbandit.adapter.EquipmentAdapter.ViewHolder target) {
    target.itemValue = null;
    target.durabilityBar = null;
    target.itemDurability = null;
    target.equipmentSlot = null;
    target.itemName = null;
  }
}
