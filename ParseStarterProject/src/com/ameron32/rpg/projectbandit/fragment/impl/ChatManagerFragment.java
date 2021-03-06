package com.ameron32.rpg.projectbandit.fragment.impl;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseQueryAdapter.QueryFactory;
import com.parse.starter.MessageManager;
import com.parse.starter.MessageManager.MessageListener;
import com.parse.starter.R;
import com.parse.starter.SaveObjectAsync;
import com.parse.starter.adapter.impl.ChatViewPagerAdapter;
import com.parse.starter.chatview.impl.RecentChatView;
import com.parse.starter.manager.UserManager;
import com.parse.starter.object.Character;
import com.parse.starter.object.Message;
import com.viewpagerindicator.LinePageIndicator;

public class ChatManagerFragment extends ContentFragment implements MessageListener, SaveObjectAsync.OnSaveCallbacks {
  
  /**
   * This interface must be implemented by activities that contain this fragment
   * to allow an interaction in this fragment to be communicated to the activity
   * and potentially other fragments contained in that activity.
   * <p>
   * See the Android Training lesson <a href=
   * "http://developer.android.com/training/basics/fragments/communicating.html"
   * >Communicating with Other Fragments</a> for more information.
   */
  public interface OnChatManagerListener {
    
    // TODO: Update argument type and name
    public void onChat();
  }
  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String  ARG_PARAM1       = "param1";
  private static final String  ARG_PARAM2       = "param2";
  
  private static final String  CURRENT_VIEW_KEY = "CurrentView";
  
  private static final boolean LOG              = true;
  private static final String  TAG              = ChatManagerFragment.class.getSimpleName();
  
  private static final boolean TOAST            = false;
  
  /**
   * Use this factory method to create a new instance of this fragment using the
   * provided parameters.
   *
   * @param param1
   *          Parameter 1.
   * @param param2
   *          Parameter 2.
   * @return A new instance of fragment ChatManager.
   */
  // TODO: Rename and change types and number of parameters
  public static ChatManagerFragment newInstance(String param1, String param2) {
    final ChatManagerFragment fragment = new ChatManagerFragment();
    final Bundle args = new Bundle();
    args.putString(ARG_PARAM1, param1);
    args.putString(ARG_PARAM2, param2);
    fragment.setArguments(args);
    return fragment;
  }

  @InjectView(R.id.spinner_action)
  Spinner                       actionSpinner;
  @InjectView(R.id.spinner_character)
  Spinner                       characterSpinner;
  @InjectView(R.id.edittext_message_to_send)
  EditText                      edittext;
  @InjectView(R.id.viewpager)
  ViewPager                     mViewPager;
  @InjectView(R.id.bSend)
  View                          sendButton;
  @InjectView(R.id.progress_send)
  ProgressBar                   sendProgress;
  @InjectView(R.id.titles)
  LinePageIndicator             titleIndicator;

  private OnClickListener       clickListener;
  private OnLongClickListener   longClickListener;
  private ParseObject           currentAction;
  private Character             currentCharacter;
  private String                currentChatViewId;
  private String                currentHintMessage;
  
  private ChatViewPagerAdapter  mChatViewPagerAdapter;
  private OnChatManagerListener mListener;
  

  // TODO: Rename and change types of parameters
  private String                mParam1;
  private String                mParam2;
  
  
  private View                  mRootView;



  
  public ChatManagerFragment() {
    // Required empty public constructor
  }

  private void closeButtons() {
    // View sendButton = findViewById(R.id.bSend);
    // sendButton.setOnClickListener(null);
  }
  
  private void closeChatViewsWithPager() {
    // ViewPager mViewPager = (ViewPager) findViewById(R.id.viewpager);
    // mViewPager.setAdapter(null);
    //
    // LinePageIndicator titleIndicator = (LinePageIndicator)
    // findViewById(R.id.titles);
    // titleIndicator.setViewPager(null);
  }

  private void closeListeners() {
    clickListener = null;
    longClickListener = null;
  }

  private void closeListView() {
    
  }
  
  private void closeSpinner() {
    
  }

  private void closeSpinnerListener() {
    currentCharacter = null;
    currentAction = null;
  }

  private void initButtons() {
    // final View sendButton = findViewById(R.id.bSend);
    sendButton.setOnClickListener(clickListener);
  }
  
  private void initChatViewsWithPager() {
    // mViewPager = (ViewPager) findViewById(R.id.viewpager);
    mChatViewPagerAdapter = new ChatViewPagerAdapter(getActivity(), mViewPager);
    mViewPager.setOffscreenPageLimit(3);
    mViewPager.setAdapter(mChatViewPagerAdapter);
    
    // final LinePageIndicator titleIndicator = (LinePageIndicator)
    // findViewById(R.id.titles);
    titleIndicator.setViewPager(mViewPager);

    // switchChatFragmentTo(currentChatFragmentId);
  }

  private void initListeners() {
    clickListener = new OnClickListener() {
      
      @Override
      public void onClick(View v) {
        switch (v.getId()) {

        case R.id.bSend:
          final EditText edittext = ButterKnife.findById(mRootView, R.id.edittext_message_to_send);
          final String message = edittext.getText().toString();
          
          final Message chatMessage = makeMessage(message, "root");
          chatMessage.send(ChatManagerFragment.this);
          break;
        }
      }
    };
    
    longClickListener = new View.OnLongClickListener() {
      
      @Override
      public boolean onLongClick(View v) {

        return false;
      }
    };
  }
  
  private void initListView() {

  }

  private void initSpinner() {
    // final Spinner characterSpinner = (Spinner)
    // findViewById(R.id.spinner_character);
    final ParseQueryAdapter<ParseObject> characterAdapter = new ParseQueryAdapter<ParseObject>(getActivity(), new QueryFactory<ParseObject>() {
      
      @Override
      public ParseQuery<ParseObject> create() {
        final ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Character");
        query.whereEqualTo("owner", UserManager.getUserManager().getCurrentUser());
        return query;
      }
    });
    characterAdapter.setTextKey("name");
    characterAdapter.setPaginationEnabled(false);
    characterSpinner.setAdapter(characterAdapter);
    characterSpinner.setSelection(1);
    
    // final Spinner actionSpinner = (Spinner)
    // findViewById(R.id.spinner_action);
    final ParseQueryAdapter<ParseObject> actionAdapter = new ParseQueryAdapter<ParseObject>(getActivity(), new QueryFactory<ParseObject>() {

      @Override
      public ParseQuery<ParseObject> create() {
        final ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("CAction");
        return query;
      }
    });
    actionAdapter.setTextKey("action");
    actionAdapter.setPaginationEnabled(false);
    actionSpinner.setAdapter(actionAdapter);
    actionSpinner.setSelection(1);
  }
  
  // @Override
  // private Activity getActivity() {
  // return getActivity();
  // }
  
  // private View findViewById(int resourceId) {
  // return mRootView.findViewById(resourceId);
  // }

  private void initSpinnerListener() {
    // final Spinner characterSpinner = (Spinner)
    // findViewById(R.id.spinner_character);
    characterSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
      
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        final Object characterObject = parent.getAdapter().getItem(position);
        final ParseObject characterParseObject = (ParseObject) characterObject;
        currentCharacter = Character.getFromParseObject(characterParseObject);
        updateEditTextHint();
      }
      
      @Override
      public void onNothingSelected(AdapterView<?> parent) {
        // do nothing
      }
    });
    // final Spinner actionSpinner = (Spinner)
    // findViewById(R.id.spinner_action);
    actionSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
      
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        final Object actionObject = parent.getAdapter().getItem(position);
        currentAction = (ParseObject) actionObject;
        updateEditTextHint();
      }
      
      @Override
      public void onNothingSelected(AdapterView<?> parent) {
        // do nothing
      }
    });
  }
  
  private Message makeMessage(String message, String channel) {
    return Message.create().setMessage(message).setChannel(channel).setCharacter(currentCharacter).setAction(currentAction);
  }
  
  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);
    try {
      mListener = (OnChatManagerListener) activity;
    }
    catch (final ClassCastException e) {
      throw new ClassCastException(activity.toString() + " must implement OnChatManagerListener");
    }
    
    ((ActionBarActivity) getActivity()).getSupportActionBar().setSubtitle(mParam1);
  }

  @Override
  public void onBegin() {
    // final EditText edittext = ButterKnife.findById(mRootView,
    // R.id.edittext_message_to_send);
    edittext.getText().clear();
    
    // final ProgressBar sendProgress = ButterKnife.findById(mRootView,
    // R.id.progress_send);
    sendProgress.setVisibility(View.VISIBLE);
  }

  /**
   * C L E A N
   * 
   */
  // TODO: Rename method, update argument and hook method into UI event
  public void onButtonPressed() {
    if (mListener != null) {
      mListener.onChat();
    }
  }
  
  @Override
  public void onComplete(Boolean... result) {
    // final ProgressBar sendProgress = ButterKnife.findById(mRootView,
    // R.id.progress_send);
    sendProgress.setVisibility(View.INVISIBLE);

    boolean allSuccessful = true;
    int numberFail = 0;
    for (int i = 0; i < result.length; i++) {
      if (!result[i].booleanValue()) {
        allSuccessful = false;
        numberFail++;
      }
    }
    
    if (!allSuccessful) {
      final String message = numberFail + " message failed to send.";
      Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
  }
  
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    restoreFromBundle(savedInstanceState);
    restoreFromFragmentArguments();
  }
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    mRootView = inflater.inflate(R.layout.fragment_chat_manager, container, false);
    ButterKnife.inject(this, mRootView);
    return mRootView;
  }
  
  @Override
  public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.reset(this);
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mListener = null;
  }
  
  @Override
  public void onMessageReceived() {
    requeryMessagesFromServer();
  }

  @Override
  public void onPause() {
    super.onPause();
    unregisterAsListener();
  }
  
  @Override
  public void onResume() {
    registerAsListener();
    requeryMessagesFromServer();
    super.onResume();
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    storeToBundle(outState);
  }
  
  @Override
  public void onStart() {
    super.onStart();
    
    initChatViewsWithPager();
    initListeners();
    initButtons();

    initListView();
    initSpinner();
    initSpinnerListener();
  }

  @Override
  public void onStop() {
    closeSpinnerListener();
    closeSpinner();
    closeListView();
    
    closeButtons();
    closeListeners();
    closeChatViewsWithPager();
    
    super.onStop();
  }

  private void registerAsListener() {
    MessageManager.getMessageManager().addMessageListener(this);
  }
  
  private void requeryMessagesFromServer() {
    // moved to Fragment
    mChatViewPagerAdapter.notifyDataSetChanged();
  }
  
  private void restoreFromBundle(Bundle savedInstanceState) {
    if (savedInstanceState != null) {
      currentChatViewId = savedInstanceState.getString(CURRENT_VIEW_KEY);
    }
    
    if (currentChatViewId == null) {
      currentChatViewId = RecentChatView.ID;
    }
  }
  
  private void restoreFromFragmentArguments() {
    if (getArguments() != null) {
      mParam1 = getArguments().getString(ARG_PARAM1);
      mParam2 = getArguments().getString(ARG_PARAM2);
    }
  }
  
  private void storeToBundle(Bundle outState) {
    if (outState != null) {
      outState.putString(CURRENT_VIEW_KEY, currentChatViewId);
    }
  }
  
  private void unregisterAsListener() {
    MessageManager.getMessageManager().addMessageListener(null);
  }
  
  private void updateEditTextHint() {
    final EditText inputbox = ButterKnife.findById(mRootView, R.id.edittext_message_to_send);
    if (currentAction != null && currentCharacter != null) {
      currentHintMessage = currentCharacter.getString("name") + " " + currentAction.getString("action") + "...";
    }
    else {
      currentHintMessage = "Message as...";
    }
    inputbox.setHint(currentHintMessage);
  }

}
