// Generated code from Butter Knife. Do not modify!
package com.ameron32.apps.projectbandit.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class AbsMessageAdapter$ViewHolder$$ViewInjector {
  public static void inject(Finder finder, final com.ameron32.apps.projectbandit.adapter.AbsMessageAdapter.ViewHolder target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296438, "field 'usernameText'");
    target.usernameText = (android.widget.TextView) view;
    view = finder.findOptionalView(source, 2131296442);
    target.characterImageView = (com.parse.ParseImageView) view;
    view = finder.findRequiredView(source, 2131296435, "field 'channelText'");
    target.channelText = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296441, "field 'actionText'");
    target.actionText = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296436, "field 'timeText'");
    target.timeText = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296440, "field 'messageText'");
    target.messageText = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296437, "field 'objectIdText'");
    target.objectIdText = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296439, "field 'characterText'");
    target.characterText = (android.widget.TextView) view;
    view = finder.findOptionalView(source, 2131296443);
    target.actionImageView = (com.parse.ParseImageView) view;
  }

  public static void reset(com.ameron32.apps.projectbandit.adapter.AbsMessageAdapter.ViewHolder target) {
    target.usernameText = null;
    target.characterImageView = null;
    target.channelText = null;
    target.actionText = null;
    target.timeText = null;
    target.messageText = null;
    target.objectIdText = null;
    target.characterText = null;
    target.actionImageView = null;
  }
}
