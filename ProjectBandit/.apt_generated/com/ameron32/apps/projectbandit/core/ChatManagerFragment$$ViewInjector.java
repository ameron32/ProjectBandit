// Generated code from Butter Knife. Do not modify!
package com.ameron32.apps.projectbandit.core;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class ChatManagerFragment$$ViewInjector {
  public static void inject(Finder finder, final com.ameron32.apps.projectbandit.core.ChatManagerFragment target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296376, "field 'sendButton'");
    target.sendButton = view;
    view = finder.findRequiredView(source, 2131296378, "field 'sendProgress'");
    target.sendProgress = (android.widget.ProgressBar) view;
    view = finder.findRequiredView(source, 2131296377, "field 'titleIndicator'");
    target.titleIndicator = (com.viewpagerindicator.LinePageIndicator) view;
    view = finder.findRequiredView(source, 2131296375, "field 'actionSpinner'");
    target.actionSpinner = (android.widget.Spinner) view;
    view = finder.findRequiredView(source, 2131296373, "field 'edittext'");
    target.edittext = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131296371, "field 'mViewPager'");
    target.mViewPager = (android.support.v4.view.ViewPager) view;
    view = finder.findRequiredView(source, 2131296374, "field 'characterSpinner'");
    target.characterSpinner = (android.widget.Spinner) view;
  }

  public static void reset(com.ameron32.apps.projectbandit.core.ChatManagerFragment target) {
    target.sendButton = null;
    target.sendProgress = null;
    target.titleIndicator = null;
    target.actionSpinner = null;
    target.edittext = null;
    target.mViewPager = null;
    target.characterSpinner = null;
  }
}
