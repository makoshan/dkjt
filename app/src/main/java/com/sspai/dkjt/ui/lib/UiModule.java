package com.sspai.dkjt.ui.lib;

import android.content.Context;
import com.squareup.picasso.Picasso;
import com.sspai.dkjt.model.ForApplication;
import com.sspai.dkjt.prefs.ActivityHierarchyServer;
import com.sspai.dkjt.prefs.AppContainer;
import com.sspai.dkjt.ui.activity.BaseActivity;
import com.sspai.dkjt.ui.activity.MainActivity;
import com.sspai.dkjt.ui.activity.ReceiverActivity;
import com.sspai.dkjt.ui.fragment.DeviceFragment;
import com.sspai.dkjt.ui.fragment.HomeFragment;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module(
    injects = {BaseActivity.class, MainActivity.class, ReceiverActivity.class,DeviceFragment.class, HomeFragment.class},
    complete = false,
    library = true)
public class UiModule {
    @Provides
    @Singleton
    AppContainer provideAppContainer() {
        return AppContainer.DEFAULT;
    }

    @Provides
    @Singleton
    ActivityHierarchyServer provideActivityHierarchyServer() {
        return ActivityHierarchyServer.NONE;
    }

    @Provides
    @Singleton
    Picasso providePicasso(@ForApplication Context app) {
        return new Picasso.Builder(app).build();
    }
}
