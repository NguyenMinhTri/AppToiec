package com.example.triuit.designtoiec.Dictionary;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by TriUIT on 11/22/2016.
 */

public class FragmentAdapterClass extends FragmentStatePagerAdapter {

    int TabCount;

    public FragmentAdapterClass(FragmentManager fragmentManager, int CountTabs) {

        super(fragmentManager);

        this.TabCount = CountTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Tab_AV tab1 = new Tab_AV();
                return tab1;

            case 1:
                Tab_VA tab2 = new Tab_VA();
                return tab2;



            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return TabCount;
    }
}
