// Generated code from Butter Knife. Do not modify!
package com.ameron32.apps.projectbandit.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class AbsMessageAdapter$ViewHolder$$ViewInjector {
  public static void inject(Finder finder, final com.ameron32.apps.projectbandit.adapter.AbsMessageAdapter.ViewHolder target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296430, "field 'channelText'");
    target.channelText = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296433, "field 'usernameText'");
    target.usernameText = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296435, "field 'messageText'");
    target.messageText = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296436, "field 'actionText'");
    target.actionText = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296434, "field 'characterText'");
    target.characterText = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296431, "field 'timeText'");
    target.timeText = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296432, "field 'objectIdText'");
    target.objectIdText = (android.widget.TextView) view;
    view = finder.findOptionalView(source, 2131296437);
    target.characterImageView = (com.parse.ParseImageView) view;
    view = finder.findOptionalView(source, 2131296438);
    target.actionImageView = (com.parse.ParseImageView) view;
  }

  public static void reset(com.ameron32.apps.projectbandit.adapter.AbsMessageAdapter.ViewHolder target) {
    target.channelText = null;
    target.usernameText = null;
    target.messageText = null;
    target.actionText = null;
    target.characterText = null;
    target.timeText = null;
    target.objectIdText = null;
    target.characterImageView = null;
    target.actionImageView = null;
  }
}
