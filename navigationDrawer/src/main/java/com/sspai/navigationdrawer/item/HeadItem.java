package com.sspai.navigationdrawer.item;

import android.graphics.drawable.Drawable;

import com.sspai.navigationdrawer.menu.Menu;

/**
 * Created by neokree on 11/12/14.
 */
public class HeadItem {
    private Drawable photo;
    private Drawable background;
    private String title;
    private String subTitle;
    private int headItemNumber;
    private HeadItemListener listener = null;
    private boolean closeDrawerOnClick = false;
    private boolean closeDrawerOnBackgroundClick = false;
    private boolean closeDrawerOnChanged = true;
    private Menu menu;
    private int startIndex = 0;
    private boolean loadFragmentOnChanged = true;

    public static final int FIRST_HEADITEM = 0;
    public static final int SECOND_HEADITEM = 1;
    public static final int THIRD_HEADITEM = 2;



    public HeadItem(String title, String subTitle, Drawable photo, Drawable background) {
        this.photo = photo;
        this.title = title;
        this.subTitle = subTitle;
        this.background = background;
        this.menu = null;
        this.startIndex = -1;
    }

    public HeadItem(String title, String subTitle, Drawable photo, Drawable background, Menu menu, int startIndex) {
        this.photo = photo;
        this.title = title;
        this.subTitle = subTitle;
        this.background = background;
        this.menu = menu;
        this.startIndex = startIndex;
    }

    public HeadItem(Drawable background, Menu menu, int startIndex) {
        this.background = background;
        this.menu = menu;
        this.startIndex = startIndex;
    }

    public void setPhoto(Drawable photo) {
        this.photo = photo;
    }

    public void setBackground(Drawable background) {
        this.background = background;
    }



    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public void setHeadItemNumber(int number) {
        this.headItemNumber = number;
    }

    // getter

    public Drawable getPhoto() {
        return photo;
    }

    public Drawable getBackground() {
        return background;
    }


    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public int getHeadItemNumber() {
        return headItemNumber;
    }



    public HeadItemListener getOnClickListener() {
        return listener;
    }

    public void setOnClickListener(HeadItemListener listener) {
        this.listener = listener;
    }

    public boolean isCloseDrawerOnClick() {
        return closeDrawerOnClick;
    }

    public void setCloseDrawerOnClick(boolean closeDrawerOnClick) {
        this.closeDrawerOnClick = closeDrawerOnClick;
    }

    public boolean isCloseDrawerOnChanged() {
        return closeDrawerOnChanged;
    }

    public void setCloseDrawerOnChanged(boolean closeDrawerOnChanged) {
        this.closeDrawerOnChanged = closeDrawerOnChanged;
    }

    public boolean isCloseDrawerOnBackgroundClick() {
        return closeDrawerOnBackgroundClick;
    }

    public void setCloseDrawerOnBackgroundClick(boolean closeDrawerOnBackgroundClick) {
        this.closeDrawerOnBackgroundClick = closeDrawerOnBackgroundClick;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public boolean isLoadFragmentOnChanged() {
        return loadFragmentOnChanged;
    }

    public void setLoadFragmentOnChanged(boolean loadFragmentOnChanged) {
        this.loadFragmentOnChanged = loadFragmentOnChanged;
    }
}
