// Generated code from Butter Knife. Do not modify!
package com.ameron32.apps.projectbandit.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class EquipmentAdapter$ViewHolder$$ViewInjector {
  public static void inject(Finder finder, final com.ameron32.apps.projectbandit.adapter.EquipmentAdapter.ViewHolder target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296421, "field 'durabilityBar'");
    target.durabilityBar = (android.widget.ProgressBar) view;
    view = finder.findRequiredView(source, 2131296420, "field 'equipmentSlot'");
    target.equipmentSlot = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296419, "field 'itemValue'");
    target.itemValue = (android.widget.Button) view;
    view = finder.findRequiredView(source, 2131296417, "field 'itemName'");
    target.itemName = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296418, "field 'itemDurability'");
    target.itemDurability = (android.widget.TextView) view;
  }

  public static void reset(com.ameron32.apps.projectbandit.adapter.EquipmentAdapter.ViewHolder target) {
    target.durabilityBar = null;
    target.equipmentSlot = null;
    target.itemValue = null;
    target.itemName = null;
    target.itemDurability = null;
  }
}
