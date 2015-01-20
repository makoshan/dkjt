package com.sspai.dkjt.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import butterknife.InjectView;
import butterknife.OnClick;
import com.f2prateek.dart.InjectExtra;

import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;

import com.sspai.dkjt.model.DeviceProvider;
import com.sspai.dkjt.model.Events;
import com.sspai.dkjt.R;
import com.sspai.dkjt.model.Utils;
import com.sspai.dkjt.service.AbstractGenerateFrameService;
import com.sspai.dkjt.service.GenerateFrameService;
import com.sspai.dkjt.model.Device;

import javax.inject.Inject;
import java.util.List;


public class DeviceFragment extends BaseFragment {
    private static final String EXTRA_DEVICE = "device";
    private static final int RESULT_SELECT_PICTURE = 1;

    @Inject
    Picasso picasso;
    @Inject
    DeviceProvider deviceProvider;
    SharedPreferences sp;

    @InjectView(R.id.iv_device_thumbnail)
    ImageView deviceThumbnailText;
    @InjectView(R.id.iv_device_default)
    ImageView deviceDefaultText;

    @InjectExtra(EXTRA_DEVICE)
    Device device;

    public static DeviceFragment newInstance(Device device) {
        DeviceFragment f = new DeviceFragment();
        Bundle args = new Bundle();
        args.putParcelable(EXTRA_DEVICE, device);
        f.setArguments(args);
        f.setRetainInstance(true);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getActivity().getSharedPreferences("dfbrand", Context.MODE_PRIVATE);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_device, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        picasso.load(Utils.getResourceIdentifierForDrawable(getActivity(), device.getThumbnailResourceName())).fit().centerInside().into(deviceThumbnailText);
        deviceDefaultText.bringToFront();
        deviceDefaultText.setImageResource(isDefault() ? R.drawable.ic_action_star_selected : R.drawable.ic_action_star);
    }

    @OnClick(R.id.iv_device_default)
    public void updateDefaultDevice() {
        if(isDefault()) {
            return;
        }
        deviceProvider.saveDefaultDevice(device);
        if(device.category() != null) {

            SharedPreferences.Editor editor = sp.edit();
            editor.putString("brand", device.category());
            editor.apply();
            Toast.makeText(getActivity(),"已设为默认",Toast.LENGTH_LONG).show();
        }
        bus.post(new Events.DefaultDeviceUpdated(device));
    }

    @Subscribe
    public void onDefaultDeviceUpdated(Events.DefaultDeviceUpdated event) {
        deviceDefaultText.post(new Runnable() {
            @Override
            public void run() {
                deviceDefaultText.setImageResource(isDefault() ? R.drawable.ic_action_star_selected : R.drawable.ic_action_star);
            }
        });
    }

    private boolean isDefault() {
        return deviceProvider.getDefaultDevice().id().equals(device.id());
    }

    @OnClick(R.id.iv_device_thumbnail)
    public void getScreenshotImageFromUser() {
        Toast.makeText(getActivity(),"请选择一张手机截图",Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        if(isAvailable(activityContext, intent)) {
            startActivityForResult(Intent.createChooser(intent, getString(R.string.select_picture)), RESULT_SELECT_PICTURE);
        } else {
            Toast.makeText(getActivity(), R.string.no_apps_available,Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Check if any apps are installed on the app to receive this intent.
     */
    public static boolean isAvailable(Context ctx, Intent intent) {
        final PackageManager mgr = ctx.getPackageManager();
        List<ResolveInfo> list = mgr.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == RESULT_SELECT_PICTURE && resultCode == Activity.RESULT_OK) {
            if(data == null) {
                return;
            }
            Uri selectedImageUri = data.getData();
            Intent intent = new Intent(getActivity(), GenerateFrameService.class);
            intent.putExtra(AbstractGenerateFrameService.KEY_EXTRA_DEVICE, device);
            intent.putExtra(GenerateFrameService.KEY_EXTRA_SCREENSHOT, selectedImageUri);
            getActivity().startService(intent);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}