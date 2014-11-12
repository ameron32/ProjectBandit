// Generated code from Butter Knife. Do not modify!
package com.ameron32.apps.projectbandit.core;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class ExpandedCoreActivity$$ViewInjector {
  public static void inject(Finder finder, final com.ameron32.apps.projectbandit.core.ExpandedCoreActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296346, "method 'toggleChatManagerFragment'");
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.toggleChatManagerFragment();
        }
      });
  }

  public static void reset(com.ameron32.apps.projectbandit.core.ExpandedCoreActivity target) {
  }
}
