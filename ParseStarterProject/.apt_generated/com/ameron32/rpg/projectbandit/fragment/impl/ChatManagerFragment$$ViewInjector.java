// Generated code from Butter Knife. Do not modify!
package com.ameron32.rpg.projectbandit.fragment.impl;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class ChatManagerFragment$$ViewInjector {
  public static void inject(Finder finder, final com.ameron32.rpg.projectbandit.fragment.impl.ChatManagerFragment target, Object source) {
    View view;
    view = finder.findRequiredView(source, 0, "field 'sendButton', field 'actionSpinner', field 'edittext', field 'characterSpinner', field 'mViewPager', and field 'sendProgress'");
    target.sendButton = view;
    target.actionSpinner = (android.widget.Spinner) view;
    target.edittext = (android.widget.EditText) view;
    target.characterSpinner = (android.widget.Spinner) view;
    target.mViewPager = (android.support.v4.view.ViewPager) view;
    target.sendProgress = (android.widget.ProgressBar) view;
  }

  public static void reset(com.ameron32.rpg.projectbandit.fragment.impl.ChatManagerFragment target) {
    target.sendButton = null;
    target.actionSpinner = null;
    target.edittext = null;
    target.characterSpinner = null;
    target.mViewPager = null;
    target.sendProgress = null;
  }
}
