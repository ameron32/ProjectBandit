// Generated code from Butter Knife. Do not modify!
package com.ameron32.apps.projectbandit.core.trial;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class InventoryTestFragment$$ViewInjector {
  public static void inject(Finder finder, final com.ameron32.apps.projectbandit.core.trial.InventoryTestFragment target, Object source) {
    com.ameron32.apps.projectbandit.core.fragment.AbsContentFragment$$ViewInjector.inject(finder, target, source);

    View view;
    view = finder.findRequiredView(source, 2131296449, "field 'gridView1'");
    target.gridView1 = (android.widget.GridView) view;
  }

  public static void reset(com.ameron32.apps.projectbandit.core.trial.InventoryTestFragment target) {
    com.ameron32.apps.projectbandit.core.fragment.AbsContentFragment$$ViewInjector.reset(target);

    target.gridView1 = null;
  }
}
