package com.sspai.dkjt.model;

import android.net.Uri;
import com.sspai.dkjt.model.Device;
import java.util.List;

public class Events {

  private Events() {
    // no instances
  }

  public static class GlareSettingUpdated {
    public final boolean isEnabled;

    public GlareSettingUpdated(boolean isEnabled) {
      this.isEnabled = isEnabled;
    }
  }

  public static class ShadowSettingUpdated {
    public final boolean isEnabled;

    public ShadowSettingUpdated(boolean isEnabled) {
      this.isEnabled = isEnabled;
    }
  }

  public static class DefaultDeviceUpdated {
    public final Device newDevice;

    public DefaultDeviceUpdated(Device newDevice) {
      this.newDevice = newDevice;
    }
  }

  public static class SingleImageProcessed {
    public final Device device;
    public final Uri uri;

    public SingleImageProcessed(Device device, Uri uri) {
      this.device = device;
      this.uri = uri;
    }
  }

  public static class MultipleImagesProcessed {
    public final Device device;
    public final List<Uri> uriList;

    public MultipleImagesProcessed(Device device, List<Uri> uriList) {
      this.device = device;
      this.uriList = uriList;
    }
  }
}
