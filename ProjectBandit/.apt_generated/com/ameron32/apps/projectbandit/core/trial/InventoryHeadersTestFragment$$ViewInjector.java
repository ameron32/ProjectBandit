// Generated code from Butter Knife. Do not modify!
package com.ameron32.apps.projectbandit.core.trial;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class InventoryHeadersTestFragment$$ViewInjector {
  public static void inject(Finder finder, final com.ameron32.apps.projectbandit.core.trial.InventoryHeadersTestFragment target, Object source) {
    com.ameron32.apps.projectbandit.core.ContentFragment$$ViewInjector.inject(finder, target, source);

    View view;
    view = finder.findRequiredView(source, 2131493048, "field 'gridView1'");
    target.gridView1 = (com.tonicartos.widget.stickygridheaders.StickyGridHeadersGridView) view;
  }

  public static void reset(com.ameron32.apps.projectbandit.core.trial.InventoryHeadersTestFragment target) {
    com.ameron32.apps.projectbandit.core.ContentFragment$$ViewInjector.reset(target);

    target.gridView1 = null;
  }
}
