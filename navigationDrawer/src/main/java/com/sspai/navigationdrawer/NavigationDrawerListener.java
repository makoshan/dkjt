package com.sspai.navigationdrawer;

import com.sspai.navigationdrawer.item.HeadItem;

/**
 * Created by neokree on 11/12/14.
 */
public interface NavigationDrawerListener {

    public void onBeforeChangedHeadItem(HeadItem newHeadItem);

    public void onAfterChangedHeadItem(HeadItem newHeadItem);
}
