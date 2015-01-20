package com.sspai.dkjt.ui.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.*;
import android.widget.Toast;
import butterknife.ButterKnife;
import com.amulyakhare.textdrawable.TextDrawable;
import com.f2prateek.dart.Dart;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.squareup.otto.Bus;

import com.sspai.dkjt.ui.AppInfo;
import com.sspai.navigationdrawer.NavigationDrawer;
import com.sspai.navigationdrawer.NavigationDrawerListener;
import com.sspai.navigationdrawer.item.HeadItem;
import com.sspai.navigationdrawer.menu.*;
import com.sspai.dkjt.model.DeviceProvider;
import com.sspai.dkjt.R;
import com.sspai.dkjt.prefs.GlareEnabled;
import com.sspai.dkjt.prefs.ShadowEnabled;
import com.sspai.dkjt.model.BooleanPreference;
import com.sspai.dkjt.prefs.AppContainer;

import com.sspai.dkjt.ui.fragment.HomeFragment;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@SuppressLint("Registered")
public class MainActivity extends NavigationDrawer implements NavigationDrawerListener {
    NavigationDrawer drawer = null;
    @Inject
    @GlareEnabled
    BooleanPreference glareEnabled;
    @Inject
    @ShadowEnabled
    BooleanPreference shadowEnabled;
    @Inject
    WindowManager windowManager;
    @Inject
    Bus bus;
    @Inject
    AppContainer appContainer;
    private ViewGroup container;
    @Inject
    DeviceProvider deviceProvider;
    Section section1;
    private String brand = "Meizu";
    private SharedPreferences sp;
    private Map<String, String> map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.action_bar_color);
        }
        mitchDevice(brand);


    }

    private void mitchDevice(String brandString) {
        for (String key : map.keySet()) {
            if(key.equals(brandString)) {
                getFragmentManager().beginTransaction().replace(com.sspai.navigationdrawer.R.id.frame_container, (android.app.Fragment) HomeFragment.newInstance(brandString)).commit();
            }
        }
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if(on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        map = new HashMap<String, String>();
        map.put("google", "Nexus");
        map.put("samsung", "Samsung");
        map.put("htc", "HTC");
        map.put("Sony", "SONY");
        map.put("lge", "LG");
        map.put("Huawei", "华为");
        map.put("nubia", "中兴");
        map.put("Xiaomi", "小米");
        map.put("Meizu", "魅族");
        map.put("ONEPLUS", "一加");
        map.put("smartisan", "锤子");
        map.put("other", "其他");
        AppInfo app = AppInfo.get(this);
        app.inject(this);
        Dart.inject(this);

        container = appContainer.get(this, app);
        drawer = this;
        sp = getApplicationContext().getSharedPreferences("dfbrand", Context.MODE_PRIVATE);

        // set drawer menu width in dp
        this.setDrawerDPWidth(300);


        // create HeadItem 1 with menu
        // create menu
        com.sspai.navigationdrawer.menu.Menu menu1 = new com.sspai.navigationdrawer.menu.Menu();

        brand = sp.getString("brand", "Meizu");
        section1 = this.newSection(map.get("google"), TextDrawable.builder().buildRound("", getResources().getColor(R.color.google_color)), HomeFragment.newInstance("google"), false);
        // set on click listener for section 31
        section1.setOnClickListener(new SectionListener() {
            @Override
            public void onClick(Section section) {
                unSelectOldSection(section);
                switchFragment("google");


            }
        });
        Section section2 = this.newSection(map.get("samsung"), TextDrawable.builder().buildRound("", getResources().getColor(R.color.samsung_color)), false);

        // set on click listener for section 2
        section2.setOnClickListener(new SectionListener() {
            @Override
            public void onClick(Section section) {
                unSelectOldSection(section);
                switchFragment("samsung");

            }
        });
        Section section3 = this.newSection(map.get("htc"), TextDrawable.builder().buildRound("", getResources().getColor(R.color.htc_color)), false);

        // set on click listener for section 2
        section3.setOnClickListener(new SectionListener() {
            @Override
            public void onClick(Section section) {
                unSelectOldSection(section);
                switchFragment("htc");

            }
        });
        Section section4 = this.newSection(map.get("Sony"), TextDrawable.builder().buildRound("", getResources().getColor(R.color.sony_color)), false);

        // set on click listener for section 2
        section4.setOnClickListener(new SectionListener() {
            @Override
            public void onClick(Section section) {
                unSelectOldSection(section);
                switchFragment("Sony");

            }
        });
        Section section5 = this.newSection(map.get("lge"), TextDrawable.builder().buildRound("", getResources().getColor(R.color.lge_color)), false);

        section5.setOnClickListener(new SectionListener() {
            @Override
            public void onClick(Section section) {
                unSelectOldSection(section);
                switchFragment("lge");

            }
        });

        Section section6 = this.newSection(map.get("Huawei"), TextDrawable.builder().buildRound("", getResources().getColor(R.color.huawei_color)), false);

        section6.setOnClickListener(new SectionListener() {
            @Override
            public void onClick(Section section) {
                unSelectOldSection(section);
                switchFragment("Huawei");
            }
        });


        Section section7 = this.newSection(map.get("nubia"), TextDrawable.builder().buildRound("", getResources().getColor(R.color.nubia_color)), false);
        section7.setOnClickListener(new SectionListener() {
            @Override
            public void onClick(Section section) {
                unSelectOldSection(section);
                switchFragment("nubia");


            }
        });

        Section section8 = this.newSection(map.get("Xiaomi"), TextDrawable.builder().buildRound("", getResources().getColor(R.color.xiaomi_color)), false);

        section8.setOnClickListener(new SectionListener() {
            @Override
            public void onClick(Section section) {
                unSelectOldSection(section);
                switchFragment("Xiaomi");


            }
        });
        Section section9 = this.newSection(map.get("Meizu"), TextDrawable.builder().buildRound("", getResources().getColor(R.color.meizu_color)), false);
        section9.setOnClickListener(new SectionListener() {
            @Override
            public void onClick(Section section) {
                unSelectOldSection(section);
                switchFragment("Meizu");


            }
        });
        Section section10 = this.newSection(map.get("ONEPLUS"), TextDrawable.builder().buildRound("", getResources().getColor(R.color.oneplue_color)), false);
        section10.setOnClickListener(new SectionListener() {
            @Override
            public void onClick(Section section) {
                unSelectOldSection(section);
                switchFragment("ONEPLUS");


            }
        });
        Section section11 = this.newSection(map.get("smartisan"), TextDrawable.builder().buildRound("", getResources().getColor(R.color.smartisan_color)), false);
        section11.setOnClickListener(new SectionListener() {
            @Override
            public void onClick(Section section) {
                unSelectOldSection(section);
                switchFragment("smartisan");


            }
        });
        Section section12 = this.newSection(map.get("other"), TextDrawable.builder().buildRound("", getResources().getColor(R.color.other_color)), false);
        section12.setOnClickListener(new SectionListener() {
            @Override
            public void onClick(Section section) {
                unSelectOldSection(section);
                switchFragment("other");


            }
        });

        // add sections to the menu
        menu1.getSections().add(section1);
        menu1.getSections().add(section2);
        menu1.getSections().add(section3);
        menu1.getSections().add(section4);
        menu1.getSections().add(section5);
        menu1.getSections().add(section6);
        menu1.getSections().add(section7);
        menu1.getSections().add(section8);
        menu1.getSections().add(section9);
        menu1.getSections().add(section10);
        menu1.getSections().add(section11);
        menu1.getSections().add(section12);
        menu1.getSections().add(new Devisor());
        //create headItem1 and add menu1
        HeadItem headItem1 = new HeadItem("少数派出品", "", null, this.getResources().getDrawable(R.drawable.menu_banner), menu1, 0);
        // add headItem1 to the drawer
        this.addHeadItem(headItem1);
        headItem1.setCloseDrawerOnChanged(true);
        // set this class as onchangedListener
        this.setOnChangedListener(this);
    }

    public void updateGlareSetting(boolean newSettingEnabled) {

        updateBooleanPreference(newSettingEnabled, glareEnabled, getString(R.string.glare_enabled), getString(R.string.glare_disabled));
    }

    public void updateShadowSetting(boolean newSettingEnabled) {

        updateBooleanPreference(newSettingEnabled, shadowEnabled, getString(R.string.shadow_enabled), getString(R.string.shadow_disabled));
    }

    /**
     * Update a boolean preference with the new value.
     * Displays some text to the user dependending on the preference.
     */
    void updateBooleanPreference(boolean newSettingEnabled, BooleanPreference booleanPreference, String enabledText, String disabledText) {
        booleanPreference.set(newSettingEnabled);
        if(newSettingEnabled) {
            Toast.makeText(this, enabledText, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, disabledText, Toast.LENGTH_LONG).show();
        }
        invalidateOptionsMenu();
    }


    void inflateView(int layoutId) {
        getLayoutInflater().inflate(layoutId, container);
        injectViews();
    }

    private void injectViews() {
        ButterKnife.inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        bus.register(this);
    }

    public void switchFragment(String mCategry) {
        drawer.closeDrawer();
        getFragmentManager().beginTransaction().replace(com.sspai.navigationdrawer.R.id.frame_container, (android.app.Fragment) HomeFragment.newInstance(mCategry)).commit();

    }

    @Override
    protected void onPause() {
        bus.unregister(this);
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onBeforeChangedHeadItem(HeadItem newHeadItem) {

    }

    @Override
    public void onAfterChangedHeadItem(HeadItem newHeadItem) {

    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.activity_main, menu);
        initMenuItem(menu.findItem(R.id.menu_checkbox_glare), glareEnabled);
        initMenuItem(menu.findItem(R.id.menu_checkbox_shadow), shadowEnabled);
        return true;
    }

    void initMenuItem(MenuItem menuItem, BooleanPreference preference) {
        menuItem.setChecked(preference.get());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_sspai:
                openUrl("http://sspai.com/dfg");
                return true;
            case R.id.menu_checkbox_glare:
                updateGlareSetting(!item.isChecked());
                return true;
            case R.id.menu_checkbox_shadow:
                updateShadowSetting(!item.isChecked());
                return true;
            case R.id.menu_about:
                Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void openUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

}
