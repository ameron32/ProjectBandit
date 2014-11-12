package com.ameron32.apps.projectbandit.core.trial;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.ameron32.apps.projectbandit.R;
import com.ameron32.apps.projectbandit.adapter.InventoryAdapter;

public class InventoryTestFragment
    extends
    SectionContainerTestFragment {
  
  @InjectView(R.id.gridlayout_inventory) GridView gridView1;
  
  @Override public void onViewCreated(
      View view,
      Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.inject(this, view);
    
    gridView1.setAdapter(new InventoryAdapter(getActivity(), R.layout.row_griditem_inventory));
  }
}
