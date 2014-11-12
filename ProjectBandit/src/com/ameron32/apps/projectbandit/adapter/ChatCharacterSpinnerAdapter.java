package com.ameron32.apps.projectbandit.adapter;


import android.content.Context;

import com.ameron32.apps.projectbandit.R;
import com.ameron32.apps.projectbandit.manager.UserManager;
import com.ameron32.apps.projectbandit.object.Character;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseQueryAdapter.QueryFactory;

public class ChatCharacterSpinnerAdapter extends ParseQueryAdapter<Character> {
  public ChatCharacterSpinnerAdapter(Context context) {
    super(context, makeFactory(), R.layout.row_dropdown);
  }

  private static QueryFactory<Character> makeFactory() {
    return new QueryFactory<Character>() {

      @Override
      public ParseQuery<Character> create() {
        return _QueryManager._Character.getChatCharacters();
      }
    };
  }
}