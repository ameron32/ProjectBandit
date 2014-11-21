package com.ameron32.apps.projectbandit.core.trial;

import java.util.List;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.ameron32.apps.projectbandit.R;
import com.ameron32.apps.projectbandit.core.fragment.AbsContentFragment;
import com.ameron32.apps.projectbandit.object.Advantage;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

public class AdvantageCheckerFragment 
  extends
    AbsContentFragment
{

  @Override protected int getCustomLayoutResource() {
    return R.layout.fragment_basic_recyclerview;
  }
  
  @InjectView(R.id.recyclerview) RecyclerView mRecyclerView;
  
  @Override public void onViewCreated(
      View view,
      Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.inject(this, view);
    
    mRecyclerView.setHasFixedSize(true);
    
    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    
    ParseQuery.getQuery(Advantage.class).setLimit(1000).orderByDescending("isForbidden").addAscendingOrder("sAorD").addAscendingOrder("sName").findInBackground(new FindCallback<Advantage>() {
      
      @Override public void done(
          final List<Advantage> advs,
          ParseException e) {
        if (e == null) {
          for (Advantage a : advs) {
            a.pullData();
          }
          
          mRecyclerView.setAdapter(new RecyclerView.Adapter<ViewHolder>() {

            @Override public int getItemCount() {
              return advs.size();
            }

            @Override public void onBindViewHolder(
                ViewHolder holder, int position) {
              Advantage advantage = advs.get(position);
              TextView tv = ((TextView) holder.itemView.findViewById(android.R.id.text1));
              tv.setText(advantage.toString());
            }

            @Override public ViewHolder onCreateViewHolder(
                ViewGroup parent, int viewType) {
              View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_dropdown, parent, false);
              return new RecyclerView.ViewHolder(v) {};
            }});
          
        }
      }
    });
  }
  
}
