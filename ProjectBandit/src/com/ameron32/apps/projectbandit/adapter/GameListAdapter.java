package com.ameron32.apps.projectbandit.adapter;

import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.ameron32.apps.projectbandit.R;
import com.ameron32.apps.projectbandit.manager.GameManager;
import com.ameron32.apps.projectbandit.object.Game;

public class GameListAdapter extends RecyclerView.Adapter<GameListAdapter.ViewHolder> {
  
  class ViewHolder extends RecyclerView.ViewHolder {

    @InjectView(android.R.id.button1) Button button;
    
    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.inject(this, itemView);
    }
  }

  private List<Game> mGames;
  private GameChangeListener mListener;
  
  public GameListAdapter(List<Game> games, GameChangeListener listener) {
    mGames = games;
    mListener = listener;
  }

  @Override public int getItemCount() {
    return mGames.size();
  }

  @Override public void onBindViewHolder(
      ViewHolder holder, int position) {
    final Game game = mGames.get(position);
    holder.button.setText(game.getName());
    holder.itemView.setOnClickListener(new OnClickListener() {
      
      /**
       * TODO: Convert to OnItemClickListener in GatewayActivity
       */
      @Override public void onClick(View v) {
        mListener.onGameChange(game);
      }
    });
  }

  @Override public ViewHolder onCreateViewHolder(
      ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_game_listitem_textview, parent, false);
    ViewHolder holder = new ViewHolder(v);
    return holder;
  }
  
  public interface GameChangeListener {
    public void onGameChange(Game game);
  }
}
