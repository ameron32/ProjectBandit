// Generated code from Butter Knife. Do not modify!
package com.ameron32.apps.projectbandit.core.trial;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class SkillsTestFragment$SkillAdapter$ViewHolder$$ViewInjector {
  public static void inject(Finder finder, final com.ameron32.apps.projectbandit.core.trial.SkillsTestFragment.SkillAdapter.ViewHolder target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296431, "field 'createdTime'");
    target.createdTime = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296440, "field 'name'");
    target.name = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296422, "field 'description'");
    target.description = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296432, "field 'objectId'");
    target.objectId = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296441, "field 'skillImage'");
    target.skillImage = (android.widget.ImageView) view;
  }

  public static void reset(com.ameron32.apps.projectbandit.core.trial.SkillsTestFragment.SkillAdapter.ViewHolder target) {
    target.createdTime = null;
    target.name = null;
    target.description = null;
    target.objectId = null;
    target.skillImage = null;
  }
}
