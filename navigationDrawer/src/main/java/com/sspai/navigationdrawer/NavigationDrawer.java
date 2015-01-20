package com.sspai.navigationdrawer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;



import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sspai.navigationdrawer.item.HeadItem;
import com.sspai.navigationdrawer.menu.Menu;
import com.sspai.navigationdrawer.menu.Section;
import com.sspai.navigationdrawer.menu.SectionListener;


import java.util.LinkedList;
import java.util.List;



/**
 * Activity that implements ActionBarActivity with a Navigation Drawer with Material Design style
 *
 * @author created by neokree
 */
@SuppressLint("InflateParams")
public abstract class NavigationDrawer<Fragment> extends ActionBarActivity implements SectionListener {
    public static final int BOTTOM_SECTION_START = 100;
    private DrawerLayout layout;
    private ActionBar actionBar;
    private ActionBarDrawerToggle drawerToggle;
    private ImageView statusBar;
    private Toolbar toolbar;
    private RelativeLayout drawer;
    private ImageView currentHeadItemPhoto;
    private ImageView secondHeadItemPhoto;
    private ImageView thirdHeadItemPhoto;
    private ImageView background;
    private ImageView backgroundTmp;
    private TextView currentHeadItemTitle;
    private TextView currentHeadItemSubTitle;
    private LinearLayout sections;
    private LinearLayout bottomSections;
    //private Boolean closeOnHeadItemChanged;
    private int drawerDPWidth = 0;
    private List<HeadItem> headItemManager;
    private Section currentSection;
    private HeadItem currentHeadItem;
    private Menu currentMenu = null;
    private CharSequence title;
    private float density;
    private int primaryColor;
    private NavigationDrawerListener changedListener = null;
    // Head Item Listener
    private View.OnClickListener currentHeadItemBackgroundListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (currentHeadItem.getOnClickListener() != null) {
                currentHeadItem.getOnClickListener().onBackgroundClick(currentHeadItem);

                if (currentHeadItem.isCloseDrawerOnBackgroundClick()) {
                    layout.closeDrawer(drawer);
                }
            }

        }
    };

    private View.OnClickListener currentHeadItemListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // enter into account properties

            if (currentHeadItem.getOnClickListener() != null) {
                currentHeadItem.getOnClickListener().onClick(currentHeadItem);

                if (currentHeadItem.isCloseDrawerOnClick()) {
                    layout.closeDrawer(drawer);
                }
            }

        }
    };

    private View.OnClickListener secondHeadItemListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // account change
            HeadItem headItem = findHeadItemNumber(HeadItem.SECOND_HEADITEM);
            if (headItem != null) {
                if (changedListener != null)
                    changedListener.onBeforeChangedHeadItem(headItem);

                switchHeadItems(headItem);

                if (changedListener != null)
                    changedListener.onAfterChangedHeadItem(headItem);
            } else {
                if (currentHeadItem.getOnClickListener() != null) {
                    currentHeadItem.getOnClickListener().onBackgroundClick(currentHeadItem);

                    if (currentHeadItem.isCloseDrawerOnBackgroundClick()) {
                        layout.closeDrawer(drawer);
                    }
                }
            }

        }
    };
    private View.OnClickListener thirdHeadItemListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // account change
            HeadItem headItem = findHeadItemNumber(HeadItem.THIRD_HEADITEM);

            if (headItem != null) {
                if (changedListener != null)
                    changedListener.onBeforeChangedHeadItem(headItem);

                switchHeadItems(headItem);

                if (changedListener != null)
                    changedListener.onAfterChangedHeadItem(headItem);
            } else {
                if (currentHeadItem.getOnClickListener() != null) {
                    currentHeadItem.getOnClickListener().onBackgroundClick(currentHeadItem);

                    if (currentHeadItem.isCloseDrawerOnBackgroundClick()) {
                        layout.closeDrawer(drawer);
                    }
                }
            }

        }
    };

    @Override
    /**
     * Do not Override this method!!! <br>
     * Use init() instead
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_navigation_drawer);
        Window window = this.getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // init toolbar & status bar
        statusBar = (ImageView) findViewById(R.id.statusBar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        // init drawer components
        drawer = (RelativeLayout) this.findViewById(R.id.drawer);
        currentHeadItemTitle = (TextView) this.findViewById(R.id.current_head_item_title);
        currentHeadItemSubTitle = (TextView) this.findViewById(R.id.current_head_item_sub_title);
        currentHeadItemPhoto = (ImageView) this.findViewById(R.id.current_head_item_photo);
        secondHeadItemPhoto = (ImageView) this.findViewById(R.id.second_head_item_photo);
        thirdHeadItemPhoto = (ImageView) this.findViewById(R.id.third_head_item_photo);
        background = (ImageView) this.findViewById(R.id.current_back_item_background);
        backgroundTmp = (ImageView) this.findViewById(R.id.current_back_item_background_tmp);
        sections = (LinearLayout) this.findViewById(R.id.sections);
        bottomSections = (LinearLayout) this.findViewById(R.id.bottom_sections);

        headItemManager = new LinkedList<>();

        // init listeners
        currentHeadItemPhoto.setOnClickListener(currentHeadItemListener);

        background.setOnClickListener(currentHeadItemBackgroundListener);
        secondHeadItemPhoto.setOnClickListener(secondHeadItemListener);
        thirdHeadItemPhoto.setOnClickListener(thirdHeadItemListener);

        //get density
        density = this.getResources().getDisplayMetrics().density;

        // get primary color
        Resources.Theme theme = this.getTheme();
        TypedValue typedValue = new TypedValue();
        theme.resolveAttribute(R.attr.colorPrimary, typedValue, true);
        primaryColor = typedValue.data;

        // set darker status bar if device is kitkat
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT)
            this.statusBar.setImageDrawable(new ColorDrawable(darkenColor(primaryColor)));

        // call init from activity
        init(savedInstanceState);

        // INIT ACTION BAR
        this.setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        layout = (DrawerLayout) this.findViewById(R.id.drawer_layout);

        // set drawer width or not ;)
        if (drawerDPWidth > 0) {
            ViewGroup.LayoutParams params = drawer.getLayoutParams();
            Resources r = getResources();
            int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, drawerDPWidth, r.getDisplayMetrics());
            params.width = px;
            drawer.setLayoutParams(params);
        }

        drawerToggle = new ActionBarDrawerToggle(this, layout, toolbar, R.string.nothing, R.string.nothing) {

            public void onDrawerClosed(View view) {
                actionBar.setTitle(title);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                //actionBar.setTitle(getCurrentTitle(-1));
                invalidateOptionsMenu();
            }

        };


        layout.setDrawerListener(drawerToggle);

        // init headitem views
        if (headItemManager.size() > 0) {
            currentHeadItem = headItemManager.get(0);
            notifyAccountDataChanged();
        }

        // loadMenu first time
        loadMenu();
    }

    private void loadMenu() {
        //change menu only if a menu is defined
        if((currentHeadItem.getMenu() != null && currentMenu != currentHeadItem.getMenu()) || currentMenu == null) {

            currentMenu = currentHeadItem.getMenu();

            sections.removeAllViews();
            bottomSections.removeAllViews();
            // create Menu
            List<Object> sectionList = currentMenu.getSections();
            for (int i = 0; i < sectionList.size(); i++) {
                if (sectionList.get(i) instanceof Section) {
                    Section section = (Section) sectionList.get(i);
                    if (section.isBottom())
                        addBottomSection((Section) sectionList.get(i));
                    else
                        addSection((Section) sectionList.get(i));
                } else {
                    addDivisor();
                }
            }

            if (currentHeadItem.isLoadFragmentOnChanged()) {
                // load default fragment
                if (sectionList.get(currentHeadItem.getStartIndex()) instanceof Section) {
                    currentSection = (Section) sectionList.get(currentHeadItem.getStartIndex());
                    currentSection.select();
                    setFragment((Fragment) currentSection.getTargetFragment(), currentSection.getTitle(), true);
                    setStatusBarColor();
                } else {
                    for (int i = 0; i < sectionList.size(); i++) {
                        if (sectionList.get(i) instanceof Section) {
                            currentSection = (Section) sectionList.get(i);
                            currentSection.select();
                            setFragment((Fragment) currentSection.getTargetFragment(), currentSection.getTitle(), true);
                            setStatusBarColor();
                            break;
                        }
                    }
                }
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(android.view.Menu menu) {

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //------------------------------------------------------------------------------------------------------------------------------------------------

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {// al cambio di orientamento dello schermo
        super.onConfigurationChanged(newConfig);

        drawerToggle.onConfigurationChanged(newConfig);

    }

    @Override
    public void setTitle(CharSequence title) {
        this.title = title;
        this.getSupportActionBar().setTitle(title);
    }

    private void setFragment(Fragment fragment, String title, boolean head) {

        // change for default Fragment / support Fragment
        if (fragment instanceof android.app.Fragment)
            getFragmentManager().beginTransaction().replace(R.id.frame_container, (android.app.Fragment) fragment).commit();
        else if (fragment instanceof android.support.v4.app.Fragment)
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, (android.support.v4.app.Fragment) fragment).commit();
        else
            throw new RuntimeException("Fragment must be android.app.Fragment or android.support.v4.app.Fragment");

        setTitle(title);

        if(head == false)
            layout.closeDrawer(drawer);
    }

    private void setStatusBarColor() {
        // setting toolbar color if is setted
        if (currentSection.hasSectionColor()) {
            if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT)
                this.statusBar.setImageDrawable(new ColorDrawable(darkenColor(currentSection.getSectionColor())));
            else
                this.statusBar.setImageDrawable(new ColorDrawable(currentSection.getSectionColor()));
            this.getToolbar().setBackgroundColor(currentSection.getSectionColor());
        } else {
            if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT)
                this.statusBar.setImageDrawable(new ColorDrawable(darkenColor(primaryColor)));
            else
                this.statusBar.setImageDrawable(new ColorDrawable(primaryColor));
            this.getToolbar().setBackgroundColor(primaryColor);
        }
    }

    private HeadItem findHeadItemNumber(int number) {
        for (HeadItem headItem : headItemManager)
            if (headItem.getHeadItemNumber() == number)
                return headItem;


        return null;
    }

    private void switchHeadItems(final HeadItem newHeadItem) {

        int newHeadItemID = 0;

        if (newHeadItem.getHeadItemNumber() == HeadItem.SECOND_HEADITEM) {
            newHeadItemID = R.id.second_head_item_photo;
        } else {
            newHeadItemID = R.id.third_head_item_photo;
        }


    }


    private void setUserEmail(String email) {
        this.currentHeadItemSubTitle.setText(email);
    }

    private void setCurrentHeadItemTitle(String currentHeadItemTitle) {
        this.currentHeadItemTitle.setText(currentHeadItemTitle);
    }

    private void setFirstAccountPhoto(Drawable photo) {
        currentHeadItemPhoto.setImageDrawable(photo);
    }

    private void setSecondAccountPhoto(Drawable photo) {
        secondHeadItemPhoto.setImageDrawable(photo);
    }

    private void setThirdAccountPhoto(Drawable photo) {
        thirdHeadItemPhoto.setImageDrawable(photo);
    }

    private void setDrawerBackground(Drawable background) {
        this.background.setImageDrawable(background);
    }

    protected int darkenColor(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] *= 0.8f; // value component
        return Color.HSVToColor(hsv);
    }

    public void unSelectOldSection(Section section) {
        currentSection.unSelect();
        currentSection = section;
    }

    @Override
    public void onClick(Section section) {
        unSelectOldSection(section);

        if (section.getTarget() == Section.TARGET_FRAGMENT) {
            setFragment((Fragment) section.getTargetFragment(), section.getTitle(), false);

            setStatusBarColor();

        } else {
            this.startActivity(section.getTargetIntent());
        }

    }

    public void setOnChangedListener(NavigationDrawerListener listener) {
        this.changedListener = listener;
    }



    // Method used for customize layout

    private void addSection(Section section) {
        //section.setPosition(sectionList.size());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (48 * density));
        //sectionList.add(section);
        sections.addView(section.getView(), params);
    }

    private void addBottomSection(Section section) {
        //section.setPosition(BOTTOM_SECTION_START + bottomSectionList.size());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (48 * density));
        //bottomSectionList.add(section);
        bottomSections.addView(section.getView(), params);
    }

    private void addDivisor() {
        View view = new View(this);
        view.setBackgroundColor(Color.parseColor("#e0e0e0"));
        // height 1 px
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1);
        params.setMargins(0, (int) (8 * density), 0, (int) (8 * density));

        sections.addView(view, params);
    }

    public void addHeadItem(HeadItem headItem) {
        if (headItemManager.size() == 3)
            throw new RuntimeException("Currently are supported max 3 accounts");

        headItem.setHeadItemNumber(headItemManager.size());
        headItemManager.add(headItem);
    }

    /**
     * Reload Application data from Account Information
     */
    private void notifyAccountDataChanged() {
        switch (headItemManager.size()) {
            case 3:
                this.setThirdAccountPhoto(findHeadItemNumber(HeadItem.THIRD_HEADITEM).getPhoto());
            case 2:
                this.setSecondAccountPhoto(findHeadItemNumber(HeadItem.SECOND_HEADITEM).getPhoto());
            case 1:
                this.setFirstAccountPhoto(currentHeadItem.getPhoto());
                this.setDrawerBackground(currentHeadItem.getBackground());
                this.setCurrentHeadItemTitle(currentHeadItem.getTitle());
                this.setUserEmail(currentHeadItem.getSubTitle());
            default:
        }
    }

    public void closeDrawer() {
        layout.closeDrawer(drawer);

    }

    public void openDrawer() {
        layout.openDrawer(drawer);
    }

    // create sections
    public Section newSection(String title, Drawable icon, boolean bottom) {
        Section section = new Section<Fragment>(this, true, Section.TARGET_FRAGMENT, bottom);
        section.setIcon(icon);
        section.setTitle(title);

        return section;
    }

    public Section newSection(String title, Drawable icon, Fragment target, boolean bottom) {
        Section section = new Section<Fragment>(this, true, Section.TARGET_FRAGMENT, bottom);
        section.setOnClickListener(this);
        section.setIcon(icon);
        section.setTitle(title);
        section.setTarget(target);

        return section;
    }

    public Section newSection(String title, Drawable icon, Intent target, boolean bottom) {
        Section section = new Section<Fragment>(this, true, Section.TARGET_ACTIVITY, bottom);
        section.setOnClickListener(this);
        section.setIcon(icon);
        section.setTitle(title);
        section.setTarget(target);

        return section;
    }

    public Section newSection(String title, Bitmap icon, boolean bottom) {
        Section section = new Section<Fragment>(this, true, Section.TARGET_ACTIVITY, bottom);
        section.setIcon(icon);
        section.setTitle(title);

        return section;
    }

    public Section newSection(String title, Bitmap icon, Fragment target, boolean bottom) {
        Section section = new Section<Fragment>(this, true, Section.TARGET_FRAGMENT, bottom);
        section.setOnClickListener(this);
        section.setIcon(icon);
        section.setTitle(title);
        section.setTarget(target);

        return section;
    }

    public Section newSection(String title, Bitmap icon, Intent target, boolean bottom) {
        Section section = new Section<Fragment>(this, true, Section.TARGET_ACTIVITY, bottom);
        section.setOnClickListener(this);
        section.setIcon(icon);
        section.setTitle(title);
        section.setTarget(target);

        return section;
    }

    public Section newSection(String title, boolean bottom) {
        Section section = new Section<Fragment>(this, false, Section.TARGET_FRAGMENT, bottom);
        section.setTitle(title);

        return section;
    }

    public Section newSection(String title, Fragment target, boolean bottom) {
        Section section = new Section<Fragment>(this, false, Section.TARGET_FRAGMENT, bottom);
        section.setOnClickListener(this);
        section.setTitle(title);
        section.setTarget(target);

        return section;
    }

    public Section newSection(String title, Intent target, boolean bottom) {
        Section section = new Section<Fragment>(this, false, Section.TARGET_ACTIVITY, bottom);
        section.setOnClickListener(this);
        section.setTitle(title);
        section.setTarget(target);

        return section;
    }

    // abstract methods

    public abstract void init(Bundle savedInstanceState);

    // get methods

    public Toolbar getToolbar() {
        return toolbar;
    }

    public Section getCurrentSection() {
        return currentSection;
    }

    public HeadItem getCurrentHeadItem() {
        return currentHeadItem;
    }

    public HeadItem getAccountAtCurrentPosition(int position) {

        if (position < 0 || position >= headItemManager.size())
            throw new RuntimeException("Account Index Overflow");

        return findHeadItemNumber(position);
    }



    public int getDrawerDPWidth() {
        return drawerDPWidth;
    }

    public void setDrawerDPWidth(int drawerDPWidth) {
        this.drawerDPWidth = drawerDPWidth;
    }
}