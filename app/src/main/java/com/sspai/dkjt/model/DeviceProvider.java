package com.sspai.dkjt.model;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.WindowManager;

import java.util.*;

public class DeviceProvider {

    private final Map<String, Device> deviceMap;
    private final StringPreference defaultDevice;
    private List<Device> deviceList;


    private DeviceProvider(Map<String, Device> deviceMap, StringPreference defaultDevice) {
        this.deviceMap = deviceMap;
        this.defaultDevice = defaultDevice;
    }

    public static DeviceProvider fromSet(Set<Device> deviceSet, StringPreference defaultDevice) {
        HashMap<String, Device> deviceMap = new LinkedHashMap<>(deviceSet.size());
        for (Device device : deviceSet) {
            deviceMap.put(device.id(), device);
        }
        return new DeviceProvider(deviceMap, defaultDevice);
    }

    /**
     * Look up a given map of devices to find a device with a matching id.
     * Ideally we would have devices id'd by product name (e.g. crespo instead of nexus_s), but a
     * device could have multiple flavours (e.g. crespo and crespo4g), which will require iteration.
     *
     * @param productId the device id to look for
     * @return the device if found, null otherwise
     */
    private Device findByProductId(String productId) {
        for (Device device : deviceMap.values()) {
            if(device.category().contains(productId)) {
                return device;
            }
        }
        return null;
    }



    /**
     * Find a device that matches the {@link android.os.Build#PRODUCT} of the current device, or the
     * dimensions of the default display of the window manger.
     * <p/>
     * Returns null if not found.
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public String find(WindowManager windowManager) {
        // look up devices by {@link android.os.Build#PRODUCT} value
        //todo
        return Build.BRAND;
    }

    /**
     * Save a default device to the user's preferences.
     */
    public void saveDefaultDevice(Device device) {
        defaultDevice.set(device.id());
    }

    /**
     * Get the user's default device.
     */
    public Device getDefaultDevice() {
        return deviceMap.get(defaultDevice.get());
    }

    /**
     * Return a device with the given id.
     */
    public Device get(String id) {
        return deviceMap.get(id);
    }

    public List<Device> asList(String category) {
        List<Device> devices = new ArrayList<>();
        if(deviceList == null) {
            deviceList = new ArrayList<>(deviceMap.values());
        }
        for (Device device : deviceList) {
            if(device.category().equals(category)) {
                devices.add(device);
            }
        }


        return devices;
    }
}
