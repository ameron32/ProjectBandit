// Generated code from Butter Knife. Do not modify!
package com.ameron32.rpg.projectbandit.fragment.impl;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class ChatManagerFragment$$ViewInjector {
  public static void inject(Finder finder, final com.ameron32.rpg.projectbandit.fragment.impl.ChatManagerFragment target, Object source) {
    View view;
    view = finder.findRequiredView(source, 0, "field 'edittext', field 'sendProgress', field 'mViewPager', field 'sendButton', field 'characterSpinner', and field 'actionSpinner'");
    target.edittext = (android.widget.EditText) view;
    target.sendProgress = (android.widget.ProgressBar) view;
    target.mViewPager = (android.support.v4.view.ViewPager) view;
    target.sendButton = view;
    target.characterSpinner = (android.widget.Spinner) view;
    target.actionSpinner = (android.widget.Spinner) view;
  }

  public static void reset(com.ameron32.rpg.projectbandit.fragment.impl.ChatManagerFragment target) {
    target.edittext = null;
    target.sendProgress = null;
    target.mViewPager = null;
    target.sendButton = null;
    target.characterSpinner = null;
    target.actionSpinner = null;
  }
}
