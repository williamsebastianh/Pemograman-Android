package com.example.helloworld;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class PagerAdapter extends FragmentPagerAdapter {

   private int numOfTabs;


    public PagerAdapter(FragmentManager fm, int numOfTabs){
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return new Fragment1();
            case 1:
                return new Fragment2();

            default:
                return null;

        }

    }
    public int getCount(){
        return numOfTabs;
    }
}