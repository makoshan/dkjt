package com.sspai.dkjt.ui;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;
import com.squareup.otto.Bus;
import com.sspai.dkjt.R;
import com.sspai.dkjt.model.ApplicationModule;
import com.sspai.dkjt.model.DeviceProvider;
import com.sspai.dkjt.model.Utils;
import com.sspai.dkjt.prefs.ActivityHierarchyServer;
import dagger.ObjectGraph;
import hugo.weaving.DebugLog;
import com.sspai.dkjt.prefs.FirstRun;
import com.sspai.dkjt.model.BooleanPreference;

import javax.inject.Inject;


public class AppInfo extends Application {

    ObjectGraph applicationGraph;
    @Inject
    ActivityHierarchyServer activityHierarchyServer;
    @Inject
    Bus bus;
    @Inject
    WindowManager windowManager;
    @Inject
    DeviceProvider deviceProvider;
    @Inject
    @FirstRun
    BooleanPreference firstRun;


    @Override
    public void onCreate() {
        super.onCreate();

        // Perform Injection
        buildApplicationGraphAndInject();

        registerActivityLifecycleCallbacks(activityHierarchyServer);


        if(!Utils.isStorageAvailable()) {
            Toast.makeText(this, R.string.storage_unavailable, Toast.LENGTH_SHORT).show();
        }

        if(firstRun.get()) {
            String brand=deviceProvider.find(windowManager);
            Log.e("brand", brand);
            SharedPreferences sp=getApplicationContext().getSharedPreferences("dfbrand", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor= sp.edit();
            editor.putString("brand",brand);
            editor.apply();
            firstRun.set(false);
        }
    }

    @DebugLog
    public void buildApplicationGraphAndInject() {
        applicationGraph = ObjectGraph.create(Modules.list(this));
        applicationGraph.inject(this);
    }

    public static AppInfo get(Context context) {
        return (AppInfo) context.getApplicationContext();
    }

    public void inject(Object object) {
        applicationGraph.inject(object);
    }

    public static class Modules {
        static Object[] list(final AppInfo application) {
            return new Object[]{new ApplicationModule(application)};
        }
    }
}
