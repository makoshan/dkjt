package com.sspai.dkjt.prefs;

import android.content.SharedPreferences;

import dagger.Module;
import dagger.Provides;
import com.sspai.dkjt.model.BooleanPreference;
import com.sspai.dkjt.model.StringPreference;

import javax.inject.Singleton;

@Module(library = true, complete = false)
public class PreferencesModule {
  private static final String DEFAULT_DEVICE_ID = "nexus_5"; // Nexus 5
  private static final boolean DEFAULT_GLARE_ENABLED = false; // Glare drawn
  private static final boolean DEFAULT_SHADOW_ENABLED = true; // Shadow drawn

  private static final String KEY_PREF_DEFAULT_DEVICE_ID = "KEY_PREF_DEFAULT_DEVICE_ID";
  private static final String KEY_PREF_OPTION_GLARE = "KEY_PREF_OPTION_GLARE";
  private static final String KEY_PREF_OPTION_SHADOW = "KEY_PREF_OPTION_SHADOW";
  private static final String KEY_FIRST_RUN = "KEY_FIRST_RUN";

  @Provides @Singleton @DefaultDevice
  StringPreference provideDefaultDevice(SharedPreferences sharedPreferences) {
    return new StringPreference(sharedPreferences, KEY_PREF_DEFAULT_DEVICE_ID, DEFAULT_DEVICE_ID);
  }

  @Provides @Singleton @GlareEnabled
  BooleanPreference provideGlareEnabled(SharedPreferences sharedPreferences) {
    return new BooleanPreference(sharedPreferences, KEY_PREF_OPTION_GLARE, DEFAULT_GLARE_ENABLED);
  }

  @Provides @Singleton @ShadowEnabled
  BooleanPreference provideShadowEnabled(SharedPreferences sharedPreferences) {
    return new BooleanPreference(sharedPreferences, KEY_PREF_OPTION_SHADOW, DEFAULT_SHADOW_ENABLED);
  }

  @Provides @Singleton @FirstRun
  BooleanPreference provideFirstRun(SharedPreferences sharedPreferences) {
    return new BooleanPreference(sharedPreferences, KEY_FIRST_RUN, true);
  }
}
