// Generated code from Butter Knife. Do not modify!
package com.ameron32.apps.projectbandit.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class CharacterSelectorAdapter$$ViewInjector {
  public static void inject(Finder finder, final com.ameron32.apps.projectbandit.adapter.CharacterSelectorAdapter target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131296416, "field 'imageView' and method 'onClick'");
    target.imageView = (android.widget.ImageView) view;
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
  }

  public static void reset(com.ameron32.apps.projectbandit.adapter.CharacterSelectorAdapter target) {
    target.imageView = null;
  }
}
