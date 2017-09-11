package com.richardliu.jesmvp.base.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by allen on 2017/4/11.
 */

public class CJSFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] mTitles;
    private List<Fragment> mFragments;

    public void setFragments(List<Fragment> fragments) {
        mFragments.clear();
        mFragments.addAll(fragments);
    }

    public void addFragment(Fragment fragment) {
        mFragments.add(fragment);
    }

    public CJSFragmentPagerAdapter(FragmentManager fm, String[] titles) {
        super(fm);
        this.mTitles = titles;
        mFragments = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
