package com.ameron32.apps.projectbandit.content;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

import com.ameron32.apps.projectbandit.R;
import com.ameron32.apps.projectbandit.adapter.EquipmentAdapter;
import com.ameron32.apps.projectbandit.adapter.EquipmentHeadersAdapter;
import com.ameron32.apps.projectbandit.core.trial.SectionContainerTestFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class EquipmentHeadersTestFragment
    extends
    SectionContainerTestFragment {
  
  @InjectView(R.id.listView1) StickyListHeadersListView listView1;
  
  @Override public void onViewCreated(
      View view,
      Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.inject(this, view);
    
    listView1.setAdapter(new EquipmentHeadersAdapter(getActivity(), R.layout.row_equipment));
  }
}
