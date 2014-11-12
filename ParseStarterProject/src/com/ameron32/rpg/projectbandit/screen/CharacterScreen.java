package com.ameron32.rpg.projectbandit.screen;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import com.ameron32.rpg.projectbandit.StructureActivity;
import com.ameron32.rpg.projectbandit.fragment.impl.ContentFragment;
import com.ameron32.views.TextComboView;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.starter.R;
import com.parse.starter.adapter.impl.EquipmentAdapter;
import com.parse.starter.adapter.impl.InventoryAdapter;
import com.parse.starter.object.Character;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the {@link CharacterScreen.OnCharacter} interface to handle
 * interaction events. Use the {@link CharacterScreen#newInstance} factory
 * method to create an instance of this fragment.
 *
 */
public class CharacterScreen extends ContentFragment {

	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;

	private OnCharacter mListener;
  private Character           currentCharacter;

  private ViewPager viewpager;
	private static final int CHILD_STATS = 0;
	private static final int CHILD_EQUIPMENT = 1;
	private static final int CHILD_INVENTORY = 2;
	private View mInvRootView;
	private View mEquipRootView;
	private View mStatsRootView;

	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 *
	 * @param param1
	 *            Parameter 1.
	 * @param param2
	 *            Parameter 2.
	 * @return A new instance of fragment CharacterScreen.
	 */
	// TODO: Rename and change types and number of parameters
	public static CharacterScreen newInstance(String param1, String param2) {
		CharacterScreen fragment = new CharacterScreen();
		Bundle args = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}

	public CharacterScreen() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mParam1 = getArguments().getString(ARG_PARAM1);
			mParam2 = getArguments().getString(ARG_PARAM2);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View rootView = inflater.inflate(R.layout.fragment_character_screen,
				container, false);
		mStatsRootView = rootView.findViewById(
				R.id.layout_section_character_stats);
		mEquipRootView = rootView.findViewById(
				R.id.layout_section_character_equipment);
		mInvRootView = rootView.findViewById(
				R.id.layout_section_character_inventory);
		return rootView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

    viewpager = (ViewPager) view.findViewById(R.id.viewpager);
    viewpager.setAdapter(arg0);

		View.OnClickListener listener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.button_stats:
          // flipper.setDisplayedChild(CHILD_STATS);
          viewpager.setCurrentItem(CHILD_STATS);
					break;
				case R.id.button_equip:
          // flipper.setDisplayedChild(CHILD_EQUIPMENT);
          viewpager.setCurrentItem(CHILD_EQUIPMENT);
					break;
				case R.id.button_inventory:
          // flipper.setDisplayedChild(CHILD_INVENTORY);
          viewpager.setCurrentItem(CHILD_INVENTORY);
					break;
				}
			}
		};

		View buttonStats = view.findViewById(R.id.button_stats);
		View buttonEquip = view.findViewById(R.id.button_equip);
		View buttonInventory = view.findViewById(R.id.button_inventory);
		buttonStats.setOnClickListener(listener);
		buttonEquip.setOnClickListener(listener);
		buttonInventory.setOnClickListener(listener);

		StructureActivity a = (StructureActivity) getActivity();
		insertParseCharacterValues(a.getCurrentCharacter());
    assignInventoryGridViewAdapter();
    assignEquipmentListViewAdapter();
	}

  private void assignInventoryGridViewAdapter() {
    GridView gridView = (GridView) mInvRootView.findViewById(R.id.gridlayout_inventory);
    gridView.setAdapter(new InventoryAdapter(getActivity(), R.layout.row_griditem_inventory));
  }
  
  private void assignEquipmentListViewAdapter() {
    ListView listView = (ListView) mEquipRootView.findViewById(R.id.listView1);
    listView.setAdapter(new EquipmentAdapter(getActivity(), R.layout.row_equipment));
  }

  private void insertParseCharacterValues(ParseObject character) {
		if (character != null) {
			ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Character");
			query.whereContains("name", character.getString("name"));
			query.getFirstInBackground(new GetCallback<ParseObject>() {

				@Override
        public void done(ParseObject characterParseObject, ParseException e) {
          Character character = Character.getFromParseObject(characterParseObject);
					int currentHealth = character.getInt("currentHealth");
					int maxHealth = character.getInt("maxHealth");
					int currentLevel = character.getInt("currentLevel");
					int currentXP = character.getInt("currentXP");
					String name = character.getString("name");

					TextComboView currentHealthCV = (TextComboView) mStatsRootView
							.findViewById(R.id.textcomboview_current_health);
					TextComboView maxHealthCV = (TextComboView) mStatsRootView
							.findViewById(R.id.textcomboview_max_health);
					TextComboView levelCV = (TextComboView) mStatsRootView
							.findViewById(R.id.textcomboview_level);
					TextComboView experienceCV = (TextComboView) mStatsRootView
							.findViewById(R.id.textcomboview_xp);
					TextComboView characterCV = (TextComboView) mStatsRootView
							.findViewById(R.id.textcomboview_character);

					currentHealthCV.setText(currentHealth + "");
					maxHealthCV.setText(maxHealth + "");
					levelCV.setText(currentLevel + "");
					experienceCV.setText(currentXP + "");
					characterCV.setText(name);
				}
			});
		}
	}
	
  private void updateCharacter(Character character) {
		insertParseCharacterValues(character);
	}

	// TODO: Rename method, update argument and hook method into UI event
	public void onButtonPressed() {
		if (mListener != null) {
			mListener.onCharacter();
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnCharacter) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnFragmentInteractionListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated to
	 * the activity and potentially other fragments contained in that activity.
	 * <p>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnCharacter {

		// TODO: Update argument type and name
		public void onCharacter();
	}

}
