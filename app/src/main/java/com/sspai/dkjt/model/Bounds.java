package com.sspai.dkjt.model;

import android.auto.value.AutoValue;
import android.os.Parcelable;

@AutoValue
public abstract class Bounds implements Parcelable {

  public abstract int x();

  public abstract int y();

  public static Bounds create(int x, int y) {
    return new AutoValue_Bounds(x, y);
  }
}
