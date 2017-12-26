package com.example.marce.teste;

/**
 * Created by marce on 19/12/2017.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Banco extends SQLiteOpenHelper {
    private static final int versao = 1;

    public Banco(Context context) {
        super(context, "banco.db", null, versao);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE movie(" +
                "  _id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "  posterPath TEXT," +
                "  overview TEXT," +
                "  releaseDate TEXT," +
                "  genreIds TEXT," +
                "  originalTitle TEXT," +
                "  originalLanguage TEXT," +
                "  title TEXT," +
                "  backdropPath TEXT," +
                "  popularity Double," +
                "  voteCount Integer," +
                "  video Boolean," +
                "  voteAverage Double," +
                "  adult Boolean);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion, int newVersion) {
    }
}