// Generated code from Butter Knife. Do not modify!
package com.ameron32.apps.projectbandit.core.trial;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class GridPagerFragment$$ViewInjector {
  public static void inject(Finder finder, final com.ameron32.apps.projectbandit.core.trial.GridPagerFragment target, Object source) {
    com.ameron32.apps.projectbandit.core.fragment.AbsContentFragment$$ViewInjector.inject(finder, target, source);

    View view;
    view = finder.findRequiredView(source, 2131296394, "field 'gridView'");
    target.gridView = (com.jess.ui.TwoWayGridView) view;
  }

  public static void reset(com.ameron32.apps.projectbandit.core.trial.GridPagerFragment target) {
    com.ameron32.apps.projectbandit.core.fragment.AbsContentFragment$$ViewInjector.reset(target);

    target.gridView = null;
  }
}
