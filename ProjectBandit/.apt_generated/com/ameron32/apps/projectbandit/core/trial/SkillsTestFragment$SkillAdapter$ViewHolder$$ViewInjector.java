// Generated code from Butter Knife. Do not modify!
package com.ameron32.apps.projectbandit.core.trial;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class SkillsTestFragment$SkillAdapter$ViewHolder$$ViewInjector {
  public static void inject(Finder finder, final com.ameron32.apps.projectbandit.core.trial.SkillsTestFragment.SkillAdapter.ViewHolder target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296445, "field 'name'");
    target.name = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296446, "field 'skillImage'");
    target.skillImage = (android.widget.ImageView) view;
    view = finder.findRequiredView(source, 2131296436, "field 'createdTime'");
    target.createdTime = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296437, "field 'objectId'");
    target.objectId = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131296427, "field 'description'");
    target.description = (android.widget.TextView) view;
  }

  public static void reset(com.ameron32.apps.projectbandit.core.trial.SkillsTestFragment.SkillAdapter.ViewHolder target) {
    target.name = null;
    target.skillImage = null;
    target.createdTime = null;
    target.objectId = null;
    target.description = null;
  }
}
