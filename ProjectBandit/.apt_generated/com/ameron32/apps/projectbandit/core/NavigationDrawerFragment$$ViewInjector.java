// Generated code from Butter Knife. Do not modify!
package com.ameron32.apps.projectbandit.core;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class NavigationDrawerFragment$$ViewInjector {
  public static void inject(Finder finder, final com.ameron32.apps.projectbandit.core.NavigationDrawerFragment target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296403, "field 'gameTitle'");
    target.gameTitle = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296402, "field 'characterTitle'");
    target.characterTitle = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296397, "field 'characterImage'");
    target.characterImage = (android.widget.ImageView) view;
  }

  public static void reset(com.ameron32.apps.projectbandit.core.NavigationDrawerFragment target) {
    target.gameTitle = null;
    target.characterTitle = null;
    target.characterImage = null;
  }
}
