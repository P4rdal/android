package com.example.marce.teste;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.SortedList;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.marce.teste.model.Movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Activity_Favoritos extends AppCompatActivity {


    List<String> itemList;
    Map mapa_ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__favoritos);
        ListView lista = (ListView) findViewById(R.id.favoritos_filmesfav);

        mapa_ids = recupera_descricao_ids();
        String id_titulo = mapa_ids.keySet().toString();
        id_titulo = id_titulo.replace("[","");
        id_titulo = id_titulo.replace("]","");
        id_titulo = id_titulo.replace(" ","");
        String[] titulos = id_titulo.split(",");

        itemList = new ArrayList<String>();



        for (String titid : titulos){

            String tit = recupera_titulo(titid);
            itemList.add(tit);
        }


        ordenaPorNome(itemList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_activated_1,itemList);
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
        menu.add(0,v.getId(),0,"Remover");
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();



        if(item.getTitle()=="Detalhes"){

            int id = info.position;

            String titulo = itemList.get(id);
            String descricao = String.valueOf(getKeysByValue(mapa_ids,titulo));
            descricao = descricao.replace("[","");
            descricao = descricao.replace("]","");
            descricao = descricao.replace(" ","");
            descricao = recupera_descricao(descricao);

            Intent intent = new Intent(Activity_Favoritos.this,Activity_Detalhes.class);
            intent.putExtra("TITULO",titulo);
            intent.putExtra("DESCRICAO",descricao);
            startActivity(intent);




        }
        else if (item.getTitle()=="Remover"){





        }
        else
        {

            return false;


        }

        return true;

    }



    public Map recupera_descricao_ids () {

        SharedPreferences settings = getSharedPreferences("FAVORITOS_TITULO", 0);
        Map title =settings.getAll();
        return title;
    }


    public String recupera_descricao (String Key) {

        SharedPreferences settings = getSharedPreferences("FAVORITOS_DESCRICAO", 0);
        String title =settings.getString(Key,null);
        return title;
    }

    public String recupera_titulo (String Key) {

        SharedPreferences settings = getSharedPreferences("FAVORITOS_TITULO", 0);
        String title =settings.getString(Key,"");


        if (title == null) {

            return "Item Não Encontrado";

        }else
        return title;
    }


    private static void ordenaPorNome(List<String> lista) {
        Collections.sort(lista, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
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

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());

    }
}




