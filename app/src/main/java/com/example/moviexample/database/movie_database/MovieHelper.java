package com.example.moviexample.database.movie_database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.example.moviexample.model.movies_models.movies.Movies;

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

public class MovieHelper {
    private static final String DATABASE_TABLE = TABLE_MOVIE;
    private static DatabaseHelper dataBaseHelper;
    private static MovieHelper INSTANCE;

    private static SQLiteDatabase database;

    private MovieHelper(Context context) {
        dataBaseHelper = new DatabaseHelper(context);
    }

    public static MovieHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MovieHelper(context);
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

    public ArrayList<Movies> getAllMovies() {
        ArrayList<Movies> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE, null,
                null,
                null,
                null,
                null,
                BaseColumns._ID + " ASC",
                null);
        cursor.moveToFirst();
        Movies movie;
        if (cursor.getCount() > 0) {
            do {
                movie = new Movies();
                movie.setId(cursor.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID)));
                movie.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                movie.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW)));
                movie.setReleaseDate(cursor.getString(cursor.getColumnIndexOrThrow(RELEASE_DATE)));
                movie.setVoteAverage(cursor.getDouble(cursor.getColumnIndexOrThrow(VOTE)));
                movie.setPopularity(cursor.getDouble(cursor.getColumnIndexOrThrow(POPULARITY)));
                movie.setPosterPath(cursor.getString(cursor.getColumnIndexOrThrow(POSTER)));
                movie.setBackdropPath(cursor.getString(cursor.getColumnIndexOrThrow(BACKDROP)));

                arrayList.add(movie);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(Movies movie) {
        ContentValues args = new ContentValues();
        args.put(BaseColumns._ID, movie.getId());
        args.put(TITLE, movie.getTitle());
        args.put(OVERVIEW, movie.getOverview());
        args.put(RELEASE_DATE, movie.getReleaseDate());
        args.put(LANGUAGE, movie.getOriginalLanguage());
        args.put(POPULARITY, movie.getPopularity());
        args.put(VOTE, movie.getVoteAverage());
        args.put(POSTER, movie.getPosterPath());
        args.put(BACKDROP, movie.getBackdropPath());
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

    public int update(Movies movie) {
        ContentValues args = new ContentValues();
        args.put(TITLE, movie.getTitle());
        args.put(OVERVIEW, movie.getOverview());
        args.put(RELEASE_DATE, movie.getReleaseDate());
        args.put(LANGUAGE, movie.getOriginalLanguage());
        args.put(POPULARITY, movie.getPopularity());
        args.put(VOTE, movie.getVoteAverage());
        args.put(POSTER, movie.getPosterPath());
        args.put(BACKDROP, movie.getBackdropPath());
        return database.update(DATABASE_TABLE, args, BaseColumns._ID + "= '" + movie.getId() + "'", null);
    }

    public int delete(int id) {
        return database.delete(DATABASE_TABLE, BaseColumns._ID + " = '" + id + "'", null);
    }
}
