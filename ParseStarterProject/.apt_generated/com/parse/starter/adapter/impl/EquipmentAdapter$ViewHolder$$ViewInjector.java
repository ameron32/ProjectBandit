// Generated code from Butter Knife. Do not modify!
package com.parse.starter.adapter.impl;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class EquipmentAdapter$ViewHolder$$ViewInjector {
  public static void inject(Finder finder, final com.parse.starter.adapter.impl.EquipmentAdapter.ViewHolder target, Object source) {
    View view;
    view = finder.findRequiredView(source, 0, "field 'equipmentSlot', field 'itemValue', field 'itemName', and field 'itemDurability'");
    target.equipmentSlot = (android.widget.TextView) view;
    target.itemValue = (android.widget.Button) view;
    target.itemName = (android.widget.TextView) view;
    target.itemDurability = (android.widget.TextView) view;
  }

  public static void reset(com.parse.starter.adapter.impl.EquipmentAdapter.ViewHolder target) {
    target.equipmentSlot = null;
    target.itemValue = null;
    target.itemName = null;
    target.itemDurability = null;
  }
}
