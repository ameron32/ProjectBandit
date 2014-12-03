package com.ameron32.apps.projectbandit.core.trial;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.ameron32.apps.projectbandit.MultiSelectObjectSpinner;
import com.ameron32.apps.projectbandit.MultiSelectObjectSpinner.Item;
import com.ameron32.apps.projectbandit.R;
import com.ameron32.apps.projectbandit.adapter._QueryManager;
import com.ameron32.apps.projectbandit.manager.GameManager;
import com.ameron32.apps.projectbandit.manager._ParseUtils;
import com.ameron32.apps.projectbandit.object.Character;
import com.ameron32.apps.projectbandit.object.Game;
import com.ameron32.apps.projectbandit.object.User;
import com.parse.FindCallback;
import com.parse.ParseException;



public class AddCharactersFragment extends
    AbsResettingContentFragment
    implements
    AbsResettingContentFragment.OnPerformTaskListener,
    AbsResettingContentFragment.TaskWorker {

  
  
  @InjectView(R.id.button_save) Button saveButton;
  @InjectView(R.id.mss_query_results) MultiSelectObjectSpinner<Character> mPlayers;
  @InjectView(R.id.edittext_filter) EditText mFilter;
  
  
  
  @Override protected int getCustomLayoutResource() {
    return R.layout.view_filtered_multiselect;
  }
  
  @Override public void onViewCreated(
      View view,
      Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.inject(this, view);

    loadMSS();
    mFilter.setOnFocusChangeListener(new OnFocusChangeListener() {
      
      @Override public void onFocusChange(
          View v, boolean hasFocus) {
        if (!hasFocus
            && (v.getId() == R.id.edittext_filter)) {
          loadMSS();
        }
      }
    });
  }
  
  private void loadMSS() {
    final String filter = mFilter.getText().toString();
    _QueryManager._Character.getAllLibraryCharacters()
        .whereStartsWith("name", filter)
        .findInBackground(new FindCallback<Character>() {
      
      @Override public void done(
          List<Character> characters,
          ParseException e) {
        if (e == null) {
//          List<String> strings = _ParseUtils.toListOfStrings(users, "username");
          mPlayers.setItems(makeItems(characters));
        }
      }
    });
  }
  
  private List<Item<Character>> makeItems(List<Character> characters) {
    List<Item<Character>> items = new ArrayList<Item<Character>>();
    for (int i = 0; i < characters.size(); i++) {
      Character character = characters.get(i);
      Item<Character> characterWrapper = new Item<Character>(character.getName(), character);
      items.add(characterWrapper);
    }
    return items;
  }
  
  @Override public int provideClickViewId() {
    return R.id.button_save;
  }
  
  @Override public OnPerformTaskListener provideOnPerformTaskListener() {
    return this;
  }
  
  @Override public TaskWorker provideTaskWorker() {
    return this;
  }
  
  @Override public void doTaskInBackground() {
    try {
      List<Character> characters = mPlayers.getSelectedItems();
//      List<Character> characters = ParseQuery.getQuery(Character.class).whereContainsAll("name", selectedStrings).find();
      Game currentGame = GameManager.get().getCurrentGame();
      for (Character c : characters) {
        _ParseUtils.addGameToCharacter(c, currentGame);
        c.save();
      }
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }
  
  @Override public void onPrePerformTask() {
    Toast.makeText(getActivity(), "Saving...", Toast.LENGTH_SHORT).show();
  }
  
  @Override public void onPostPerformTask() {
    Toast.makeText(getActivity(), "Players added to Game.", Toast.LENGTH_SHORT).show();
  }
}
