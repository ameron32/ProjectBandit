// Generated code from Butter Knife. Do not modify!
package com.ameron32.apps.projectbandit.core.trial;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class CreateSetItemsFragment$$ViewInjector {
  public static void inject(Finder finder, final com.ameron32.apps.projectbandit.core.trial.CreateSetItemsFragment target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493080, "field 'armorSlots'");
    target.armorSlots = (com.ameron32.apps.projectbandit.MultiSelectSpinner) view;
    view = finder.findRequiredView(source, 2131493066, "field 'baseValue'");
    target.baseValue = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131493065, "field 'name'");
    target.name = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131492990, "field 'otherSets'");
    target.otherSets = (android.widget.ListView) view;
    view = finder.findRequiredView(source, 2131493067, "field 'spinnerSetTypes'");
    target.spinnerSetTypes = (android.widget.Spinner) view;
    view = finder.findRequiredView(source, 2131492989, "field 'setTemplate'");
    target.setTemplate = (android.widget.Spinner) view;
    view = finder.findRequiredView(source, 2131492987, "method 'onSubmit'");
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.onSubmit(p0);
        }
      });
  }

  public static void reset(com.ameron32.apps.projectbandit.core.trial.CreateSetItemsFragment target) {
    target.armorSlots = null;
    target.baseValue = null;
    target.name = null;
    target.otherSets = null;
    target.spinnerSetTypes = null;
    target.setTemplate = null;
  }
}
