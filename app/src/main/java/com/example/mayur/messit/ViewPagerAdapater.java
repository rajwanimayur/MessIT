package com.example.mayur.messit;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapater extends FragmentPagerAdapter {

    private  final List<Fragment> lstFragment = new ArrayList<>();
    private final List<String> lstHeading = new ArrayList<>();

    public ViewPagerAdapater(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return lstFragment.get(i);
    }

    @Override
    public int getCount() {
        return lstHeading.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return lstHeading.get(position);
    }

    public void addFragment(Fragment fragment, String heading){
        lstFragment.add(fragment);
        lstHeading.add(heading);
    }
}
