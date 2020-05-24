package com.example.moviexample.database.tv_database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.example.moviexample.model.movies_models.movies.Movies;
import com.example.moviexample.model.tvshow_models.tv_model.TV;

import java.util.ArrayList;

import static com.example.moviexample.database.movie_database.DatabaseContract.TABLE_MOVIE;
import static com.example.moviexample.database.movie_database.DatabaseContract.TableColumns.BACKDROP;
import static com.example.moviexample.database.movie_database.DatabaseContract.TableColumns.LANGUAGE;
import static com.example.moviexample.database.movie_database.DatabaseContract.TableColumns.OVERVIEW;
import static com.example.moviexample.database.movie_database.DatabaseContract.TableColumns.POPULARITY;
import static com.example.moviexample.database.movie_database.DatabaseContract.TableColumns.POSTER;
import static com.example.moviexample.database.movie_database.DatabaseContract.TableColumns.RELEASE_DATE;
import static com.example.moviexample.database.movie_database.DatabaseContract.TableColumns.TITLE;
import static com.example.moviexample.database.movie_database.DatabaseContract.TableColumns.VOTE;
import static com.example.moviexample.database.tv_database.DatabaseTVContract.TABLE_TV;
import static com.example.moviexample.database.tv_database.DatabaseTVContract.TableColumns.BACKDROP_TV;
import static com.example.moviexample.database.tv_database.DatabaseTVContract.TableColumns.LANGUAGE_TV;
import static com.example.moviexample.database.tv_database.DatabaseTVContract.TableColumns.OVERVIEW_TV;
import static com.example.moviexample.database.tv_database.DatabaseTVContract.TableColumns.POPULARITY_TV;
import static com.example.moviexample.database.tv_database.DatabaseTVContract.TableColumns.POSTER_TV;
import static com.example.moviexample.database.tv_database.DatabaseTVContract.TableColumns.RELEASE_DATE_TV;
import static com.example.moviexample.database.tv_database.DatabaseTVContract.TableColumns.TITLE_TV;
import static com.example.moviexample.database.tv_database.DatabaseTVContract.TableColumns.VOTE_TV;

public class TvHelper {
    private static final String DATABASE_TABLE = TABLE_TV;
    private static DatabaseTVHelper dataBaseHelper;
    private static TvHelper INSTANCE;

    private static SQLiteDatabase database;

    private TvHelper(Context context) {
        dataBaseHelper = new DatabaseTVHelper(context);
    }

    public static TvHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TvHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = dataBaseHelper.getWritableDatabase();
    }

    public void close() {
        dataBaseHelper.close();

        if (database.isOpen())
            database.close();
    }

    public ArrayList<TV> getAllTV() {
        ArrayList<TV> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE, null,
                null,
                null,
                null,
                null,
                BaseColumns._ID + " ASC",
                null);
        cursor.moveToFirst();
        TV tv;
        if (cursor.getCount() > 0) {
            do {
                tv = new TV();
                tv.setId(cursor.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID)));
                tv.setName(cursor.getString(cursor.getColumnIndexOrThrow(TITLE_TV)));
                tv.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW_TV)));
                tv.setFirstAirDate(cursor.getString(cursor.getColumnIndexOrThrow(RELEASE_DATE_TV)));
                tv.setVoteAverage(cursor.getDouble(cursor.getColumnIndexOrThrow(VOTE_TV)));
                tv.setPopularity(cursor.getDouble(cursor.getColumnIndexOrThrow(POPULARITY_TV)));
                tv.setPosterPath(cursor.getString(cursor.getColumnIndexOrThrow(POSTER_TV)));
                tv.setBackdropPath(cursor.getString(cursor.getColumnIndexOrThrow(BACKDROP_TV)));

                arrayList.add(tv);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(TV tv) {
        ContentValues args = new ContentValues();
        args.put(BaseColumns._ID, tv.getId());
        args.put(TITLE_TV, tv.getName());
        args.put(OVERVIEW_TV, tv.getOverview());
        args.put(RELEASE_DATE_TV, tv.getFirstAirDate());
        args.put(LANGUAGE_TV, tv.getOriginalLanguage());
        args.put(POPULARITY_TV, tv.getPopularity());
        args.put(VOTE_TV, tv.getVoteAverage());
        args.put(POSTER_TV, tv.getPosterPath());
        args.put(BACKDROP_TV, tv.getBackdropPath());
        return database.insert(DATABASE_TABLE, null, args);
    }

    public boolean isExist(int id) {
        String query = "SELECT * FROM " + DATABASE_TABLE + " WHERE " + BaseColumns._ID + " =?";
        Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(id)});
        boolean exist = false;
        if (cursor.moveToFirst()) {
            exist = true;
        }
        cursor.close();
        return exist;
    }

    public int update(TV tv) {
        ContentValues args = new ContentValues();
        args.put(TITLE, tv.getName());
        args.put(OVERVIEW, tv.getOverview());
        args.put(RELEASE_DATE, tv.getFirstAirDate());
        args.put(LANGUAGE, tv.getOriginalLanguage());
        args.put(POPULARITY, tv.getPopularity());
        args.put(VOTE, tv.getVoteAverage());
        args.put(POSTER, tv.getPosterPath());
        args.put(BACKDROP, tv.getBackdropPath());
        return database.update(DATABASE_TABLE, args, BaseColumns._ID + "= '" + tv.getId() + "'", null);
    }

    public int delete(int id) {
        return database.delete(DATABASE_TABLE, BaseColumns._ID + " = '" + id + "'", null);
    }
}
