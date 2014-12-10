package com.ameron32.apps.projectbandit.adapter;


import android.content.Context;

import com.ameron32.apps.projectbandit.R;
import com.ameron32.apps.projectbandit.adapter.fmwk.ParseSpinnerQueryAdapter;
import com.ameron32.apps.projectbandit.object.Character;
import com.parse.ParseQuery;

public class ChatCharacterSpinnerAdapter extends ParseSpinnerQueryAdapter<Character> {
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