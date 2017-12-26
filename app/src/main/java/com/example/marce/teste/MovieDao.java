package com.example.marce.teste;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.marce.teste.model.Movie;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.FALSE;

/**
 * Created by marce on 19/12/2017.
 */


public class MovieDao {
    Banco dbHelper;
    SQLiteDatabase db;

    public MovieDao(Context context) {
        dbHelper = new Banco(context);
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
        db = null;
    }

    public List<Movie> getAll() {
        List<Movie> lista = new ArrayList<>();

        Cursor cursor = db.query(false, "movie", null, null, null, null, null, "title", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String overview = cursor.getString(2);
            Movie movie = new Movie(null, FALSE, overview, null, null, id, null, null, title, null, null, null, null, null);
            lista.add(movie);
            cursor.moveToNext();
        }

        return lista;
    }



    public void insert(Movie movie) {
        ContentValues cv = new ContentValues();
        cv.put("title", movie.title);
        cv.put("overview", movie.overview);
        long id = db.insert("movie", null, cv);
        Log.d("teste", "inserido filme com id = " + id);
    }
    public void remove(long id) {
        db.delete("movie", "_id = " + id, null);
    }
}