// Generated code from Butter Knife. Do not modify!
package com.ameron32.apps.projectbandit.core.trial;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class TableTestFragment$$ViewInjector {
  public static void inject(Finder finder, final com.ameron32.apps.projectbandit.core.trial.TableTestFragment target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296387, "field 'mHeaderView'");
    target.mHeaderView = (android.widget.FrameLayout) view;
    view = finder.findRequiredView(source, 2131296388, "field 'mRecyclerView'");
    target.mRecyclerView = (android.support.v7.widget.RecyclerView) view;
  }

  public static void reset(com.ameron32.apps.projectbandit.core.trial.TableTestFragment target) {
    target.mHeaderView = null;
    target.mRecyclerView = null;
  }
}
