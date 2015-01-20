package com.sspai.dkjt.prefs;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Identifies the user preference of whether shadow should be drawn on the
 * screenshots or not.
 */
@Qualifier @Retention(RUNTIME)
public @interface ShadowEnabled {
}
