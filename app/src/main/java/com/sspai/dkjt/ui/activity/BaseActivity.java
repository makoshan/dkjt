package com.sspai.dkjt.ui.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.f2prateek.dart.Dart;

import com.squareup.otto.Bus;
import com.sspai.dkjt.ui.AppInfo;
import com.sspai.dkjt.prefs.AppContainer;

import javax.inject.Inject;

@SuppressLint("Registered")
public class BaseActivity extends Activity {

  @Inject Bus bus;
  @Inject
  AppContainer appContainer;

  private ViewGroup container;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    AppInfo app = AppInfo.get(this);
    app.inject(this);

    Dart.inject(this);

    container = appContainer.get(this, app);
  }

  void inflateView(int layoutId) {
    getLayoutInflater().inflate(layoutId, container);
    injectViews();
  }

  private void injectViews() {
    ButterKnife.inject(this);
  }

  @Override
  protected void onResume() {
    super.onResume();
    bus.register(this);
  }

  @Override
  protected void onPause() {
    bus.unregister(this);
    super.onPause();
  }
}
