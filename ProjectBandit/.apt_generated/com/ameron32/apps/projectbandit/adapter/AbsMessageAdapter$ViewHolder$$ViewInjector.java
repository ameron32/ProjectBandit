// Generated code from Butter Knife. Do not modify!
package com.ameron32.apps.projectbandit.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class AbsMessageAdapter$ViewHolder$$ViewInjector {
  public static void inject(Finder finder, final com.ameron32.apps.projectbandit.adapter.AbsMessageAdapter.ViewHolder target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296424, "field 'channelText'");
    target.channelText = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296426, "field 'objectIdText'");
    target.objectIdText = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296425, "field 'timeText'");
    target.timeText = (android.widget.TextView) view;
    view = finder.findOptionalView(source, 2131296431);
    target.characterImageView = (com.parse.ParseImageView) view;
    view = finder.findRequiredView(source, 2131296428, "field 'characterText'");
    target.characterText = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296430, "field 'actionText'");
    target.actionText = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296427, "field 'usernameText'");
    target.usernameText = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296429, "field 'messageText'");
    target.messageText = (android.widget.TextView) view;
    view = finder.findOptionalView(source, 2131296432);
    target.actionImageView = (com.parse.ParseImageView) view;
  }

  public static void reset(com.ameron32.apps.projectbandit.adapter.AbsMessageAdapter.ViewHolder target) {
    target.channelText = null;
    target.objectIdText = null;
    target.timeText = null;
    target.characterImageView = null;
    target.characterText = null;
    target.actionText = null;
    target.usernameText = null;
    target.messageText = null;
    target.actionImageView = null;
  }
}
