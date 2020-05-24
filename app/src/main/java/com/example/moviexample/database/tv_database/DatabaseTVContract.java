package com.example.moviexample.database.tv_database;

import android.provider.BaseColumns;

public class DatabaseTVContract {
    public static final String TABLE_TV = "movie";

    public static final class TableColumns implements BaseColumns{
        public static final String TITLE_TV = "title";
        public static final String OVERVIEW_TV = "overview";
        public static final String RELEASE_DATE_TV = "release";
        public static final String LANGUAGE_TV = "language";
        public static final String POSTER_TV = "poster";
        public static final String BACKDROP_TV = "backdrop";
        public static final String VOTE_TV = "vote";
        public static final String POPULARITY_TV = "popularity";
    }
}
