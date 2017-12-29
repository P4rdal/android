package com.example.marce.teste;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Activity_Detalhes extends AppCompatActivity implements View.OnClickListener {

    private CheckBox bt_Check;
    private TextView txtTitulo;
    private TextView txtDescricao;

    Map mapa_ids;
    String chave;
    String titulo;
    String id_chave;
    String descricao;
    Button bt_home;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__detalhes);


        bt_home = (Button)findViewById(R.id.Detalhes_home);
        bt_home.setOnClickListener(this);



        mapa_ids = recupera_descricao_ids();


        bt_Check = (CheckBox) findViewById(R.id.Detalhes_check);
        bt_Check.setOnClickListener(this);


        Intent in = getIntent();
        titulo = in.getStringExtra("TITULO");
        descricao = in.getStringExtra("DESCRICAO");
        id_chave = in.getStringExtra("ID");


        txtTitulo = (TextView) findViewById(R.id.Detalhes_Titulo);
        txtTitulo.setText(titulo);




        txtDescricao = (TextView) findViewById(R.id.Detalhes_decricao);
        txtDescricao.setText(descricao);


        if (verificafav(chave) == true) {
            bt_Check.setChecked(true);
            chave = String.valueOf(getKeysByValue(mapa_ids, titulo));
            chave = chave.replace("[", "");
            chave = chave.replace("]", "");
            chave = chave.replace(" ", "");


        } else {

            chave = id_chave;


        }

    }







    public static <T, E> Set<T> getKeysByValue(Map<T, E> map, E value) {

        Set<T> keys = new HashSet<T>();

        for (Map.Entry<T, E> entry : map.entrySet()) {

            if (value.equals(entry.getValue())) {

                keys.add(entry.getKey());

            }
        }

        return keys;

    }


    public Map recupera_descricao_ids() {

        SharedPreferences settings = getSharedPreferences("FAVORITOS_TITULO", 0);
        Map title = settings.getAll();
        return title;
    }


    public String recupera_titulo(String Key) {

        SharedPreferences settings = getSharedPreferences("FAVORITOS_TITULO", 0);
        String title = settings.getString(Key, "");
        return title;
    }


    public String recupera_descricao(String Key) {

        SharedPreferences settings = getSharedPreferences("FAVORITOS_DESCRICAO", 0);
        String title = settings.getString(Key, null);
        return title;
    }


    public void remove_descricao(String Key) {

        SharedPreferences settings = getSharedPreferences("FAVORITOS_DESCRICAO", 0);
        settings.edit().remove(Key).commit();

    }

    public void remove_titulo(String Key) {

        SharedPreferences settings = getSharedPreferences("FAVORITOS_TITULO", 0);
        settings.edit().remove(Key).commit();

    }


    @Override
    public void onClick(View v) {

        if (bt_Check.isChecked()) {

            Toast.makeText(getApplicationContext(),titulo+" Gravado nos Favoritos", Toast.LENGTH_LONG).show();

            Grava_Descricao_Favorito(chave, descricao);
            Grava_Titulo_Favorito(chave, titulo);


        }

        if (bt_home.isClickable()){

            Intent intentPrincipal = new Intent(Activity_Detalhes.this,Activity_Principal.class);
            startActivity(intentPrincipal);

        }


    }





    public boolean verificafav(String key) {

        String ver = String.valueOf(getKeysByValue(mapa_ids, titulo));

        if (ver == "[]") {

            return false;
        } else {
            return true;

        }

    }


    public void Grava_Titulo_Favorito(String Chave, String Valor) {

        SharedPreferences settings = getSharedPreferences("FAVORITOS_TITULO", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(Chave, Valor);
        editor.commit();


    }


    public void Grava_Descricao_Favorito(String Chave, String Valor) {

        SharedPreferences settings = getSharedPreferences("FAVORITOS_DESCRICAO", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(Chave, Valor);
        editor.commit();
    }


    @Override
    protected void onPause() {
        super.onPause();

        if (!bt_Check.isChecked()){

            remove_descricao(chave);
            remove_titulo(chave);

        }

    }
}







