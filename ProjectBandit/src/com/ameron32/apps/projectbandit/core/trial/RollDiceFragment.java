package com.ameron32.apps.projectbandit.core.trial;

import android.os.Bundle;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.ameron32.apps.projectbandit.R;
import com.ameron32.apps.projectbandit.core.fragment.AbsContentFragment;
import com.ameron32.apps.projectbandit.manager._ParseUtils;

public class RollDiceFragment extends AbsContentFragment {

	@Override
	protected int getCustomLayoutResource() {
		return R.layout.fragment_dice_roll;
	}
	
	@Override public void onViewCreated(
	    View view,
	    Bundle savedInstanceState) {
	  super.onViewCreated(view, savedInstanceState);
	  ButterKnife.inject(this, view);
	}
	
	@OnClick(R.id.button_roll) void onRoll() {
	  _ParseUtils.rollDice();
	}
	
	@Override public void onDestroyView() {
	  super.onDestroyView();
	  ButterKnife.reset(this);
	}

}
