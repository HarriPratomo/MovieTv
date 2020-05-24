package com.example.moviexample.adapter.tabs_adapter;


import com.example.moviexample.fragment.subfragmentMovie.NowPlaying;
import com.example.moviexample.fragment.subfragmentMovie.Popular;
import com.example.moviexample.fragment.subfragmentMovie.TopRated;
import com.example.moviexample.fragment.subfragmentMovie.Upcoming;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * Created by Harri Pratomo
 * harrypratomo135@gmail.com
 */
public class TabAdapterAllMovie extends FragmentStatePagerAdapter {
    int numbOfTabs;


    public TabAdapterAllMovie(@NonNull FragmentManager fm, int numbOfTabs) {
        super(fm);
        this.numbOfTabs = numbOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                NowPlaying nowPlaying =  new NowPlaying();
                return nowPlaying;
            case 1:
                Upcoming upcoming = new Upcoming();
                return upcoming;
            case 2:
                TopRated topRated = new TopRated();
                return topRated;
            case 3:
                Popular popular  = new Popular();
                return popular;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numbOfTabs;
    }
}

