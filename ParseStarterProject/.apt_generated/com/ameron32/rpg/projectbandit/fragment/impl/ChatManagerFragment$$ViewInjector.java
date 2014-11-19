// Generated code from Butter Knife. Do not modify!
package com.ameron32.rpg.projectbandit.fragment.impl;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class ChatManagerFragment$$ViewInjector {
  public static void inject(Finder finder, final com.ameron32.rpg.projectbandit.fragment.impl.ChatManagerFragment target, Object source) {
    View view;
    view = finder.findRequiredView(source, 0, "field 'characterSpinner', field 'actionSpinner', field 'sendProgress', field 'edittext', field 'mViewPager', and field 'sendButton'");
    target.characterSpinner = (android.widget.Spinner) view;
    target.actionSpinner = (android.widget.Spinner) view;
    target.sendProgress = (android.widget.ProgressBar) view;
    target.edittext = (android.widget.EditText) view;
    target.mViewPager = (android.support.v4.view.ViewPager) view;
    target.sendButton = view;
  }

  public static void reset(com.ameron32.rpg.projectbandit.fragment.impl.ChatManagerFragment target) {
    target.characterSpinner = null;
    target.actionSpinner = null;
    target.sendProgress = null;
    target.edittext = null;
    target.mViewPager = null;
    target.sendButton = null;
  }
}
