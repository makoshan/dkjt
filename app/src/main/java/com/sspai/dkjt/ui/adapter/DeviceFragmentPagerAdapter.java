package com.sspai.dkjt.ui.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;
import com.sspai.dkjt.model.Device;
import com.sspai.dkjt.ui.fragment.DeviceFragment;


import java.util.List;

public class DeviceFragmentPagerAdapter extends FragmentStatePagerAdapter {

  private final List<Device> devices;

  public DeviceFragmentPagerAdapter(FragmentManager fm, List<Device> devices) {
    super(fm);
    this.devices = devices;
  }

  @Override
  public Fragment getItem(int position) {
    return DeviceFragment.newInstance(devices.get(position));
  }

  @Override
  public int getCount() {
    return devices.size();
  }

  public int getDeviceIndex(Device device) {
    return devices.indexOf(device);
  }

  @Override public CharSequence getPageTitle(int position) {
    return devices.get(position).name();
  }
}
