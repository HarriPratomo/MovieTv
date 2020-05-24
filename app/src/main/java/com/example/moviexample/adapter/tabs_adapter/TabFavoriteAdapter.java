package com.example.moviexample.adapter.tabs_adapter;

import com.example.moviexample.fragment.subfragmentMovie.FavoriteMovie;
import com.example.moviexample.fragment.subfragmentMovie.FavoriteTV;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * Created by Harri Pratomo on 10/05/2020.
 * <p>
 * harrypratomo135@gmail.com
 */
public class TabFavoriteAdapter extends FragmentStatePagerAdapter {
    int numbOfTabs;


    public TabFavoriteAdapter(@NonNull FragmentManager fm, int numbOfTabs) {
        super(fm);
        this.numbOfTabs = numbOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                FavoriteMovie movie =  new FavoriteMovie();
                return movie;
            case 1:
                FavoriteTV tv = new FavoriteTV();
                return tv;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numbOfTabs;
    }
}
