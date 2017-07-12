package com.example.room304.touchword;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by room3.04 on 12/07/2017.
 */

public class PagerCustomAdapter extends FragmentPagerAdapter {

    List<Fragment> fragmentList;

    public PagerCustomAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        fragmentList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String[] names = new String[]{"H", "About", "Contact"};
        return names[position];
    }
}
