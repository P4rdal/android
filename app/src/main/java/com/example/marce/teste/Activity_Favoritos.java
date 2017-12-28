package com.example.marce.teste;

import android.content.Context;
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
import java.util.List;
import java.util.Map;


public class Activity_Favoritos extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__favoritos);
        ListView lista = (ListView) findViewById(R.id.favoritos_filmesfav);
        Map mapa = recupera();
        String titulo = mapa.values().toString();
        titulo = titulo.replace("[","");
        titulo = titulo.replace("]","");
        String[] titulos = titulo.split(",");
        List<String> itemList = new ArrayList<String>();

        for (String tit : titulos){
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






        }
        else if (item.getTitle()=="Remover"){





        }
        else
        {

            return false;


        }

        return true;

    }




    public Map recupera () {

        SharedPreferences settings = getSharedPreferences("FAVORITOS", 0);
        Map title =settings.getAll();
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




    }




