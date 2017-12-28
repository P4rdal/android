package com.example.marce.teste;

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
import java.util.List;
import java.util.Map;

public class Activity_Historico extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__historico);
        ListView lista = (ListView) findViewById(R.id.historico_filmesvistos);
        Map mapa = recupera();
        String titulo = mapa.values().toString();
        titulo = titulo.replace("[","");
        titulo = titulo.replace("]","");
        String[] titulos = titulo.split(",");
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


        }
        else
        {
            return false;
        }
        return true;
    }





    public Map recupera () {

        SharedPreferences settings = getSharedPreferences("HISTORICO1", 0);
        Map title =settings.getAll();
        return title;
    }






}

