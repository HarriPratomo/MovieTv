package com.example.moviexample.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.moviexample.R;
import com.example.moviexample.fragment.FavoriteFragment;
import com.example.moviexample.fragment.MoviesFragment;
import com.example.moviexample.fragment.TvFragment;
import com.example.moviexample.fragment.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.movies:
                        openFragment(MoviesFragment.newInstance());
                        return true;
                    case R.id.tv:
                        openFragment(TvFragment.newInstance());
                        return true;
                    case R.id.fav:
                        openFragment(FavoriteFragment.newInstance());
                        return true;
                    case R.id.user:
                        openFragment(UserFragment.newInstance());
                        return true;
                }
                return false;
            }
        });
        openFragment(MoviesFragment.newInstance());
    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameNavigation, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}

