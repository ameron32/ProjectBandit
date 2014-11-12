package com.ameron32.apps.projectbandit.core.trial;

import com.ameron32.apps.projectbandit.R;
import com.ameron32.apps.projectbandit.adapter.EquipmentAdapter;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class EquipmentTestFragment
    extends
    SectionContainerTestFragment {
  
  @InjectView(R.id.listView1) ListView listView1;
  
  @Override public void onViewCreated(
      View view,
      Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.inject(this, view);
    
    listView1.setAdapter(new EquipmentAdapter(getActivity(), R.layout.row_equipment));
  }
}
