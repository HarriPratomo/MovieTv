package com.example.moviexample.database.movie_database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "dbmovie";
    private static final int DATABASE_VERSION = 2;
    private static final String SQL_CREATE_TABLE_MOVIE = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY," +
                    " %s TEXT ," +
                    " %s TEXT ," +
                    " %s TEXT ," +
                    " %s TEXT ," +
                    " %s REAL ," +
                    " %s REAL ," +
                    " %s TEXT ," +
                    " %s TEXT )",
            DatabaseContract.TABLE_MOVIE,
            DatabaseContract.TableColumns._ID,
            DatabaseContract.TableColumns.TITLE,
            DatabaseContract.TableColumns.OVERVIEW,
            DatabaseContract.TableColumns.RELEASE_DATE,
            DatabaseContract.TableColumns.LANGUAGE,
            DatabaseContract.TableColumns.POPULARITY,
            DatabaseContract.TableColumns.VOTE,
            DatabaseContract.TableColumns.POSTER,
            DatabaseContract.TableColumns.BACKDROP
    );
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_MOVIE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_MOVIE);
        onCreate(db);
    }
}
