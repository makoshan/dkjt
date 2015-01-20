
package com.sspai.dkjt.model;

import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.view.WindowManager;


import com.squareup.otto.Bus;
import com.sspai.dkjt.AddDeviceModule;
import com.sspai.dkjt.service.AbstractGenerateFrameService;
import com.sspai.dkjt.service.GenerateFrameService;
import com.sspai.dkjt.service.GenerateMultipleFramesService;
import com.sspai.dkjt.ui.AppInfo;
import dagger.Module;
import dagger.Provides;
import com.sspai.dkjt.prefs.DefaultDevice;
import com.sspai.dkjt.prefs.PreferencesModule;
import com.sspai.dkjt.ui.lib.UiModule;

import java.util.Set;
import javax.inject.Singleton;

@Module(
    includes = {AddDeviceModule.class, PreferencesModule.class, UiModule.class},
    injects = {AppInfo.class, AbstractGenerateFrameService.class, GenerateFrameService.class, GenerateMultipleFramesService.class})
public class ApplicationModule {

    private final AppInfo application;

    public ApplicationModule(AppInfo application) {
        this.application = application;
    }

    @Provides
    @Singleton
    @ForApplication
    Context provideAppContext() {
        return application;
    }

    @Provides
    @Singleton
        //
    SharedPreferences provideDefaultSharedPreferences(@ForApplication Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @Singleton
    Resources provideResources(@ForApplication Context context) {
        return context.getResources();
    }


    @SuppressWarnings("unchecked")
        //
    <T> T getSystemService(final Context context, final String serviceConstant) {
        return (T) context.getSystemService(serviceConstant);
    }

    @Provides
    @Singleton
        //
    NotificationManager provideNotificationManager(@ForApplication Context context) {
        return getSystemService(context, Context.NOTIFICATION_SERVICE);
    }

    @Provides
    @Singleton
    WindowManager provideWindow(@ForApplication Context context) {
        return getSystemService(context, Context.WINDOW_SERVICE);
    }

    @Provides
    @Singleton
    Bus provideOttoBus() {
        return new Bus();
    }

    @Provides
    @Singleton
        //
    DeviceProvider devices(Set<Device> deviceSet, @DefaultDevice StringPreference defaultDevice) {
        return DeviceProvider.fromSet(deviceSet, defaultDevice);
    }


}