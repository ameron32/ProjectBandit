package com.ameron32.apps.projectbandit.core.trial;

import com.ameron32.apps.projectbandit.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.ameron32.apps.projectbandit.core.trial.ResettingContentFragment.OnPerformTaskListener;
import com.ameron32.apps.projectbandit.core.trial.ResettingContentFragment.TaskWorker;

public class DEMORCFragment extends
    ResettingContentFragment implements OnPerformTaskListener, TaskWorker {
  
  @Override public View onCreateView(
      LayoutInflater inflater,
      ViewGroup container,
      Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment____demo___resetting, container, false);
  }
  
  @Override public void onViewCreated(
      View view,
      Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  @Override public int provideClickViewId() {
    return R.id.button1;
  }

  @Override public OnPerformTaskListener provideOnPerformTaskListener() {
    return this;
  }

  @Override public TaskWorker provideTaskWorker() {
    return this;
  }

  @Override public void onPrePerformTask() {
    toast("starting");
    ((Button) getActivity().findViewById(R.id.button1)).setText("...Acting...");
  }
  
  @Override public void doTaskInBackground() {
    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Override public void onPostPerformTask() {
    toast("complete");
  }
  
  private void toast(String message) {
    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
  }

}
