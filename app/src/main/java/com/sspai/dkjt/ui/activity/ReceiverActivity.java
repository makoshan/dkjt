package com.sspai.dkjt.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.sspai.dkjt.model.DeviceProvider;
import com.sspai.dkjt.service.AbstractGenerateFrameService;
import com.sspai.dkjt.service.GenerateFrameService;
import com.sspai.dkjt.service.GenerateMultipleFramesService;
import com.sspai.dkjt.model.Device;
import com.sspai.dkjt.prefs.DefaultDevice;
import com.sspai.dkjt.model.StringPreference;

import javax.inject.Inject;
import java.util.ArrayList;

/** A receiver activity, that is registered to receive images. */
public class ReceiverActivity extends BaseActivity {
  @Inject
  DeviceProvider deviceProvider;
  @Inject @DefaultDevice
  StringPreference defaultDevice;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Intent intent = getIntent();
    String action = intent.getAction();
    if (Intent.ACTION_SEND.equals(action)) {
      // Got a single image
      handleReceivedSingleImage(intent);
    } else if (Intent.ACTION_SEND_MULTIPLE.equals(action)) {
      // Got multiple images
      handleReceivedMultipleImages(intent);
    }

    finish();
  }

  /** Handle an intent that provides a single image. */
  private void handleReceivedSingleImage(Intent i) {
    Uri imageUri = i.getParcelableExtra(Intent.EXTRA_STREAM);
    Device device = deviceProvider.getDefaultDevice();
    Intent intent = new Intent(this, GenerateFrameService.class);
    intent.putExtra(AbstractGenerateFrameService.KEY_EXTRA_DEVICE, device);
    intent.putExtra(GenerateFrameService.KEY_EXTRA_SCREENSHOT, imageUri);
    startService(intent);
  }

  /** Handle an intent that provides multiple images. */
  void handleReceivedMultipleImages(Intent i) {
    ArrayList<Uri> imageUris = i.getParcelableArrayListExtra(Intent.EXTRA_STREAM);
    Device device = deviceProvider.getDefaultDevice();
    Intent intent = new Intent(this, GenerateMultipleFramesService.class);
    intent.putExtra(AbstractGenerateFrameService.KEY_EXTRA_DEVICE, device);
    intent.putExtra(GenerateMultipleFramesService.KEY_EXTRA_SCREENSHOTS, imageUris);
    startService(intent);
  }
}
