// Generated code from Butter Knife. Do not modify!
package com.ameron32.apps.projectbandit.core;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class ChatManagerFragment$$ViewInjector {
  public static void inject(Finder finder, final com.ameron32.apps.projectbandit.core.ChatManagerFragment target, Object source) {
    com.ameron32.apps.projectbandit.core.ContentFragment$$ViewInjector.inject(finder, target, source);

    View view;
    view = finder.findRequiredView(source, 2131296375, "field 'titleIndicator'");
    target.titleIndicator = (com.viewpagerindicator.LinePageIndicator) view;
    view = finder.findRequiredView(source, 2131296369, "field 'mViewPager'");
    target.mViewPager = (android.support.v4.view.ViewPager) view;
    view = finder.findRequiredView(source, 2131296376, "field 'sendProgress'");
    target.sendProgress = (android.widget.ProgressBar) view;
    view = finder.findRequiredView(source, 2131296373, "field 'actionSpinner'");
    target.actionSpinner = (android.widget.Spinner) view;
    view = finder.findRequiredView(source, 2131296372, "field 'characterSpinner'");
    target.characterSpinner = (android.widget.Spinner) view;
    view = finder.findRequiredView(source, 2131296374, "field 'sendButton'");
    target.sendButton = view;
    view = finder.findRequiredView(source, 2131296371, "field 'edittext'");
    target.edittext = (android.widget.EditText) view;
  }

  public static void reset(com.ameron32.apps.projectbandit.core.ChatManagerFragment target) {
    com.ameron32.apps.projectbandit.core.ContentFragment$$ViewInjector.reset(target);

    target.titleIndicator = null;
    target.mViewPager = null;
    target.sendProgress = null;
    target.actionSpinner = null;
    target.characterSpinner = null;
    target.sendButton = null;
    target.edittext = null;
  }
}
