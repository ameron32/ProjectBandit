// Generated code from Butter Knife. Do not modify!
package com.ameron32.rpg.projectbandit.fragment.impl;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class ChatManagerFragment$$ViewInjector {
  public static void inject(Finder finder, final com.ameron32.rpg.projectbandit.fragment.impl.ChatManagerFragment target, Object source) {
    View view;
    view = finder.findRequiredView(source, 0, "field 'actionSpinner', field 'characterSpinner', field 'sendProgress', field 'sendButton', field 'edittext', and field 'mViewPager'");
    target.actionSpinner = (android.widget.Spinner) view;
    target.characterSpinner = (android.widget.Spinner) view;
    target.sendProgress = (android.widget.ProgressBar) view;
    target.sendButton = view;
    target.edittext = (android.widget.EditText) view;
    target.mViewPager = (android.support.v4.view.ViewPager) view;
  }

  public static void reset(com.ameron32.rpg.projectbandit.fragment.impl.ChatManagerFragment target) {
    target.actionSpinner = null;
    target.characterSpinner = null;
    target.sendProgress = null;
    target.sendButton = null;
    target.edittext = null;
    target.mViewPager = null;
  }
}
