// Generated code from Butter Knife. Do not modify!
package com.ameron32.rpg.projectbandit.fragment.impl;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class ChatManagerFragment$$ViewInjector {
  public static void inject(Finder finder, final com.ameron32.rpg.projectbandit.fragment.impl.ChatManagerFragment target, Object source) {
    View view;
    view = finder.findRequiredView(source, 0, "field 'sendButton', field 'actionSpinner', field 'characterSpinner', field 'edittext', field 'sendProgress', and field 'mViewPager'");
    target.sendButton = view;
    target.actionSpinner = (android.widget.Spinner) view;
    target.characterSpinner = (android.widget.Spinner) view;
    target.edittext = (android.widget.EditText) view;
    target.sendProgress = (android.widget.ProgressBar) view;
    target.mViewPager = (android.support.v4.view.ViewPager) view;
  }

  public static void reset(com.ameron32.rpg.projectbandit.fragment.impl.ChatManagerFragment target) {
    target.sendButton = null;
    target.actionSpinner = null;
    target.characterSpinner = null;
    target.edittext = null;
    target.sendProgress = null;
    target.mViewPager = null;
  }
}
