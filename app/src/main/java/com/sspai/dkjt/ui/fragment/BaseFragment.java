package com.sspai.dkjt.ui.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import butterknife.ButterKnife;
import com.f2prateek.dart.Dart;

import com.squareup.otto.Bus;
import com.sspai.dkjt.ui.AppInfo;

import javax.inject.Inject;

public class BaseFragment extends Fragment {
  @Inject Bus bus;
  Context activityContext;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    activityContext = getActivity();

    AppInfo app = AppInfo.get(activityContext);
    app.inject(this);

    Dart.inject(this);
  }

  @Override
  public void onResume() {
    super.onResume();
    bus.register(this);
  }

  @Override
  public void onPause() {
    bus.unregister(this);
    super.onPause();
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.inject(this, view);
  }
}
