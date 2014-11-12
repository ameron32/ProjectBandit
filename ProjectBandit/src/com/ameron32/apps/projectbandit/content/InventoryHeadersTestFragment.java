package com.ameron32.apps.projectbandit.content;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.ameron32.apps.projectbandit.R;
import com.ameron32.apps.projectbandit.adapter.InventoryAdapter;
import com.ameron32.apps.projectbandit.adapter.InventoryHeadersAdapter;
import com.ameron32.apps.projectbandit.core.trial.SectionContainerTestFragment;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersGridView;

public class InventoryHeadersTestFragment
    extends
    SectionContainerTestFragment {
  
  @InjectView(R.id.gridlayout_inventory) StickyGridHeadersGridView gridView1;
  
  @Override public void onViewCreated(
      View view,
      Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.inject(this, view);
    
    gridView1.setAdapter(new InventoryHeadersAdapter(getActivity(), R.layout.row_griditem_inventory));
  }
}
