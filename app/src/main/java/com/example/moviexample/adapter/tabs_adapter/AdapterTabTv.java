package com.example.moviexample.adapter.tabs_adapter;

import com.example.moviexample.fragment.subfragmentTV.Tv_airing_today;
import com.example.moviexample.fragment.subfragmentTV.Tv_on_the_air;
import com.example.moviexample.fragment.subfragmentTV.Tv_popular;
import com.example.moviexample.fragment.subfragmentTV.Tv_toprated;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * Created by Harri Pratomo on 10/05/2020.
 * <p>
 * harrypratomo135@gmail.com
 */
public class AdapterTabTv extends FragmentStatePagerAdapter {
    int numbOfTabs;

    public AdapterTabTv(@NonNull FragmentManager fm, int numbOfTabs) {
        super(fm);
        this.numbOfTabs = numbOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                Tv_airing_today airing_today =  new Tv_airing_today();
                return airing_today;
            case 1:
                Tv_on_the_air on_the_air = new Tv_on_the_air();
                return on_the_air;
            case 2:
                Tv_popular popular = new Tv_popular();
                return popular;
            case 3:
                Tv_toprated toprated  = new Tv_toprated();
                return toprated;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numbOfTabs;
    }
}
