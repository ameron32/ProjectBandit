package com.ameron32.apps.projectbandit.adapter;

import android.content.Context;

import com.ameron32.apps.projectbandit.R;
import com.ameron32.apps.projectbandit.manager.UserManager;
import com.ameron32.apps.projectbandit.object.CAction;
import com.ameron32.apps.projectbandit.object.Character;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseQueryAdapter.QueryFactory;

public class ChatCActionSpinnerAdapter extends ParseQueryAdapter<CAction> {
  public ChatCActionSpinnerAdapter(Context context) {
    super(context, makeFactory(), R.layout.row_dropdown);
  }

  private static QueryFactory<CAction> makeFactory() {
    return new QueryFactory<CAction>() {

      @Override
      public ParseQuery<CAction> create() {
        return _QueryManager._CAction.getChatCActionsQuery();
      }
    };
  }
}