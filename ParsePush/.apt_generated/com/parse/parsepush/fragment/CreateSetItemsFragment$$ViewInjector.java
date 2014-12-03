// Generated code from Butter Knife. Do not modify!
package com.parse.parsepush.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class CreateSetItemsFragment$$ViewInjector {
  public static void inject(Finder finder, final com.parse.parsepush.fragment.CreateSetItemsFragment target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296383, "field 'armorSlots'");
    target.armorSlots = (com.parse.parsepush.MultiSelectSpinner) view;
    view = finder.findRequiredView(source, 2131296368, "field 'name'");
    target.name = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131296347, "field 'otherSets'");
    target.otherSets = (android.widget.ListView) view;
    view = finder.findRequiredView(source, 2131296346, "field 'setTemplate'");
    target.setTemplate = (android.widget.Spinner) view;
    view = finder.findRequiredView(source, 2131296370, "field 'spinnerSetTypes'");
    target.spinnerSetTypes = (android.widget.Spinner) view;
    view = finder.findRequiredView(source, 2131296369, "field 'baseValue'");
    target.baseValue = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131296344, "method 'onSubmit'");
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.onSubmit(p0);
        }
      });
  }

  public static void reset(com.parse.parsepush.fragment.CreateSetItemsFragment target) {
    target.armorSlots = null;
    target.name = null;
    target.otherSets = null;
    target.setTemplate = null;
    target.spinnerSetTypes = null;
    target.baseValue = null;
  }
}
