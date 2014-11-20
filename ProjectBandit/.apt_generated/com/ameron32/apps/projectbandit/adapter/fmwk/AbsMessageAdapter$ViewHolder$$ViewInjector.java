// Generated code from Butter Knife. Do not modify!
package com.ameron32.apps.projectbandit.adapter.fmwk;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class AbsMessageAdapter$ViewHolder$$ViewInjector {
  public static void inject(Finder finder, final com.ameron32.apps.projectbandit.adapter.fmwk.AbsMessageAdapter.ViewHolder target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296432, "field 'usernameText'");
    target.usernameText = (android.widget.TextView) view;
    view = finder.findOptionalView(source, 2131296436);
    target.characterImageView = (com.parse.ParseImageView) view;
    view = finder.findRequiredView(source, 2131296434, "field 'messageText'");
    target.messageText = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296433, "field 'characterText'");
    target.characterText = (android.widget.TextView) view;
    view = finder.findOptionalView(source, 2131296437);
    target.actionImageView = (com.parse.ParseImageView) view;
    view = finder.findRequiredView(source, 2131296429, "field 'channelText'");
    target.channelText = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296435, "field 'actionText'");
    target.actionText = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296430, "field 'timeText'");
    target.timeText = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296431, "field 'objectIdText'");
    target.objectIdText = (android.widget.TextView) view;
  }

  public static void reset(com.ameron32.apps.projectbandit.adapter.fmwk.AbsMessageAdapter.ViewHolder target) {
    target.usernameText = null;
    target.characterImageView = null;
    target.messageText = null;
    target.characterText = null;
    target.actionImageView = null;
    target.channelText = null;
    target.actionText = null;
    target.timeText = null;
    target.objectIdText = null;
  }
}
