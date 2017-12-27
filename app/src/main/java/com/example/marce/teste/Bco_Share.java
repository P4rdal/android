package com.example.marce.teste;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by marce on 27/12/2017.
 */

public class Bco_Share extends AppCompatActivity {



     public void  Bco_Share (String Chave , String Valor){

         SharedPreferences settings = getSharedPreferences("PREFS_NAME", 0);
         SharedPreferences.Editor editor = settings.edit();
         editor.putString(Chave, Valor);
         editor.commit();


    }

    public String RetornaValor (String chave){

        SharedPreferences settings = getSharedPreferences("PREFS_NAME", 0);
        String valor = settings.getString(chave, "");
        return valor;


    }


}
