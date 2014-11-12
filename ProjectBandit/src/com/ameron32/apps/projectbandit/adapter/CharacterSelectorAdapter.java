package com.ameron32.apps.projectbandit.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.ameron32.apps.projectbandit.R;
import com.ameron32.apps.projectbandit.manager.CharacterManager;
import com.ameron32.apps.projectbandit.manager.UserManager;
import com.ameron32.apps.projectbandit.object.Character;
import com.jess.ui.TwoWayGridView;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.squareup.picasso.Picasso;

public class CharacterSelectorAdapter extends
    ParseQueryAdapter<Character> {
  
  @InjectView(R.id.imageview) ImageView imageView;
  
  private Context context;
  private TwoWayGridView gridView;

  public static QueryFactory<Character> makeQueryFactory() {
    QueryFactory<Character> factory = new QueryFactory<Character>() {

      @Override public ParseQuery<Character> create() {
        return _QueryManager._Character.getPlayableCharacters();
      }};
    return factory;
  }

  public CharacterSelectorAdapter(
      Context context,
      TwoWayGridView gridView) {
    super(context, makeQueryFactory());
    this.context = context;
    this.gridView = gridView;
  }

  @Override public View getItemView(
      Character character, View v,
      ViewGroup container) {
    if (v == null) {
      v = LayoutInflater.from(context).inflate(R.layout.row_character_icon, container, false);
    }
    v = super.getItemView(character, v, container);
    ButterKnife.inject(this, v);
    
    final String picUrl = character.getString("profilePicUrl");
    Picasso.with(context).load(picUrl)
      .placeholder(R.drawable.ic_launcher)
      .error(R.drawable.ic_bandit_clear)
      .resizeDimen(R.dimen.image_character_length_squared_large, R.dimen.image_character_length_squared_large)
      .into(imageView);
    characters.add(character);
    imageView.setTag(character);
    
    return v;
  }
  
  private final List<Character> characters = new ArrayList<Character>(); 
  
  @OnClick(R.id.imageview)
  void onClick(View v) {
    Character character = (Character) v.getTag();
    int position = getPositionFromCharacter(character);
    if (position != -1) {
      Toast.makeText(context, "Switched to: " + character.getString("name"), Toast.LENGTH_SHORT).show();
      CharacterManager.get().setCurrentCharacter(character);
      gridView.setSelection(position);
    } else {
      Toast.makeText(context, "No character for " + position, Toast.LENGTH_SHORT).show();
    }
  }
  
  private int getPositionFromCharacter(Character character) {
    for (int i = 0; i < characters.size(); i++) {
      if (characters.get(i).equals(character)) { return i; }
    }
    return -1;
  }
}
