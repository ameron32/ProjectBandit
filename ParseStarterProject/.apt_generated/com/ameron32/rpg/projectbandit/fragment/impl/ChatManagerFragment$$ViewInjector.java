// Generated code from Butter Knife. Do not modify!
package com.ameron32.rpg.projectbandit.fragment.impl;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class ChatManagerFragment$$ViewInjector {
  public static void inject(Finder finder, final com.ameron32.rpg.projectbandit.fragment.impl.ChatManagerFragment target, Object source) {
    View view;
    view = finder.findRequiredView(source, 0, "field 'mViewPager', field 'characterSpinner', field 'sendButton', field 'sendProgress', field 'edittext', and field 'actionSpinner'");
    target.mViewPager = (android.support.v4.view.ViewPager) view;
    target.characterSpinner = (android.widget.Spinner) view;
    target.sendButton = view;
    target.sendProgress = (android.widget.ProgressBar) view;
    target.edittext = (android.widget.EditText) view;
    target.actionSpinner = (android.widget.Spinner) view;
  }

  public static void reset(com.ameron32.rpg.projectbandit.fragment.impl.ChatManagerFragment target) {
    target.mViewPager = null;
    target.characterSpinner = null;
    target.sendButton = null;
    target.sendProgress = null;
    target.edittext = null;
    target.actionSpinner = null;
  }
}
