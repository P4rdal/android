package com.example.marce.teste;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.marce.teste.model.Movie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Activity_Historico extends AppCompatActivity {

    Map mapa_ids;
    String[] titulos;
    String[] ids;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__historico);
        ListView lista = (ListView) findViewById(R.id.historico_filmesvistos);



        mapa_ids = recupera_descricao_ids();
        String id_titulo = mapa_ids.values().toString();
        String id_keys = mapa_ids.keySet().toString();

        id_titulo = id_titulo.replace("[","");
        id_titulo = id_titulo.replace("]","");

        id_keys = id_keys.replace("[","");
        id_keys = id_keys.replace("]","");
        id_keys = id_keys.replace(" ","");

        ids = id_keys.split(",");
        titulos = id_titulo.split(",");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_activated_1,titulos);
        lista.setAdapter(adapter);
        registerForContextMenu(lista);

    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu,v,menuInfo);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        int id = info.targetView.getId();

        menu.setHeaderTitle("Selecione uma opção");
        menu.add(0,v.getId(),0,"Detalhes");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

        if(item.getTitle()=="Detalhes"){


            int id = info.position;
            String titulo = titulos[id].toString();
            String descricao = ids[id].toString();
            descricao = recupera_descricao(descricao);

            Intent intent = new Intent(Activity_Historico.this,Activity_Detalhes.class);
            intent.putExtra("TITULO",titulo);
            intent.putExtra("DESCRICAO",descricao);
            startActivity(intent);



        }
        else
        {
            return false;
        }
        return true;
    }







    public Map recupera_descricao_ids () {

        SharedPreferences settings = getSharedPreferences("HISTORICO_TITULO", 0);
        Map title =settings.getAll();
        return title;
    }


    public String recupera_descricao (String Key) {

        SharedPreferences settings = getSharedPreferences("HISTORICO_DESCRICAO", 0);
        String title =settings.getString(Key,null);
        return title;
    }

    public String recupera_titulo (String Key) {

        SharedPreferences settings = getSharedPreferences("HISTORICO_TITULO", 0);
        String title =settings.getString(Key,"");


        if (title == null) {

            return "Item Não Encontrado";

        }else
            return title;
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



}

