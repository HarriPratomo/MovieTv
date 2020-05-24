package com.example.moviexample.database.tv_database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseTVHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "dbtv";
    private static final int DATABASE_VERSION = 2;
    private static final String SQL_CREATE_TABLE_TV= String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY," +
                    " %s TEXT ," +
                    " %s TEXT ," +
                    " %s TEXT ," +
                    " %s TEXT ," +
                    " %s REAL ," +
                    " %s REAL ," +
                    " %s TEXT ," +
                    " %s TEXT )",
            DatabaseTVContract.TABLE_TV,
            DatabaseTVContract.TableColumns._ID,
            DatabaseTVContract.TableColumns.TITLE_TV,
            DatabaseTVContract.TableColumns.OVERVIEW_TV,
            DatabaseTVContract.TableColumns.RELEASE_DATE_TV,
            DatabaseTVContract.TableColumns.LANGUAGE_TV,
            DatabaseTVContract.TableColumns.POPULARITY_TV,
            DatabaseTVContract.TableColumns.VOTE_TV,
            DatabaseTVContract.TableColumns.POSTER_TV,
            DatabaseTVContract.TableColumns.BACKDROP_TV
    );
    public DatabaseTVHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_TV);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseTVContract.TABLE_TV);
        onCreate(db);
    }
}
