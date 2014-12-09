// Generated code from Butter Knife. Do not modify!
package com.ameron32.apps.projectbandit.core.trial;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class GameFragment$$ViewInjector {
  public static void inject(Finder finder, final com.ameron32.apps.projectbandit.core.trial.GameFragment target, Object source) {
    com.ameron32.apps.projectbandit.core.fragment.AbsContentFragment$$ViewInjector.inject(finder, target, source);

    View view;
    view = finder.findRequiredView(source, 2131296454, "field 'user'");
    target.user = (com.ameron32.library.floatingtext.FloatingHintTextView) view;
    view = finder.findRequiredView(source, 2131296456, "field 'gm'");
    target.gm = (com.ameron32.library.floatingtext.FloatingHintTextView) view;
    view = finder.findRequiredView(source, 2131296455, "field 'game'");
    target.game = (com.ameron32.library.floatingtext.FloatingHintTextView) view;
    view = finder.findRequiredView(source, 2131296457, "field 'players'");
    target.players = (com.ameron32.library.floatingtext.FloatingHintTextView) view;
  }

  public static void reset(com.ameron32.apps.projectbandit.core.trial.GameFragment target) {
    com.ameron32.apps.projectbandit.core.fragment.AbsContentFragment$$ViewInjector.reset(target);

    target.user = null;
    target.gm = null;
    target.game = null;
    target.players = null;
  }
}
