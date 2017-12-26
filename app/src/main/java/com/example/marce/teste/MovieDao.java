package com.example.marce.teste;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
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
            String posterPath = cursor.getString(1);
            String overview = cursor.getString(2);
            String releaseDate = cursor.getString(3);
            String originalTitle = cursor.getString(5);
            String originalLanguage = cursor.getString(6);
            String title = cursor.getString(7);
            String backdropPath = cursor.getString(8);



            Movie movie = new Movie(posterPath,FALSE,overview,releaseDate,null,id,originalTitle,
                    originalLanguage,title,backdropPath,null,null,null,null);

            lista.add(movie);
            cursor.moveToNext();
        }

        return lista;
    }


    public String verifica(String titulo) {
      try {
          String selectQuery = "SELECT * FROM movie WHERE title ="+ titulo;
          Cursor cursor = db.rawQuery(selectQuery,null);
          cursor.moveToFirst();
          String nomeString = cursor.getString(cursor.getColumnIndex("title"));
          StringBuilder conversor = new StringBuilder();
          conversor.append(nomeString);
          return conversor.toString();
      } catch (SQLException erro ){

          return null;
      }



    }





    public void insert(Movie movie) {

          if (verifica(movie.title)== movie.title){

              ContentValues cv = new ContentValues();
              cv.put("title", movie.getTitle());
              cv.put("overview", movie.getOverview());
              long id = db.insert("movie", null, cv);

          }
    }

    //* public void remove(long id) {  db.delete("movie", "_id = " + id, null);}

    public void remove() {
        db.delete("movie",null,null );
        db.execSQL("delete * from"+ "movie");

    }


}
