// Generated code from Butter Knife. Do not modify!
package com.ameron32.apps.projectbandit.adapter.fmwk;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class AbsMessageAdapter$ViewHolder$$ViewInjector {
  public static void inject(Finder finder, final com.ameron32.apps.projectbandit.adapter.fmwk.AbsMessageAdapter.ViewHolder target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493033, "field 'objectIdText'");
    target.objectIdText = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131493037, "field 'actionText'");
    target.actionText = (android.widget.TextView) view;
    view = finder.findOptionalView(source, 2131493039);
    target.actionImageView = (com.parse.ParseImageView) view;
    view = finder.findRequiredView(source, 2131493034, "field 'usernameText'");
    target.usernameText = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131493035, "field 'characterText'");
    target.characterText = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131493031, "field 'channelText'");
    target.channelText = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131493032, "field 'timeText'");
    target.timeText = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131493036, "field 'messageText'");
    target.messageText = (android.widget.TextView) view;
    view = finder.findOptionalView(source, 2131493038);
    target.characterImageView = (com.parse.ParseImageView) view;
  }

  public static void reset(com.ameron32.apps.projectbandit.adapter.fmwk.AbsMessageAdapter.ViewHolder target) {
    target.objectIdText = null;
    target.actionText = null;
    target.actionImageView = null;
    target.usernameText = null;
    target.characterText = null;
    target.channelText = null;
    target.timeText = null;
    target.messageText = null;
    target.characterImageView = null;
  }
}
