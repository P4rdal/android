package com.example.marce.teste;

import retrofit2.Response;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Callback;

import com.example.marce.teste.adapter.AdapterMovie;
import com.example.marce.teste.adapter.MoviesAdapter;
import com.example.marce.teste.model.Movie;
import com.example.marce.teste.model.MoviesResponse;
import com.example.marce.teste.rest.ApiClient;
import com.example.marce.teste.rest.ApiInterface;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;


import static java.lang.Boolean.FALSE;

public class Lista_todos extends AppCompatActivity {

    public Activity_Favoritos Ave ;
    public List<Movie> filmes;
    private ListView listaMovies;
    MovieDao movieDao;
    private static final String TAG = MainActivity.class.getSimpleName();
    private final static String API_KEY = "f96f35dad2d93764c36ed623ec9148ff";


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu,v,menuInfo);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        int id = info.targetView.getId();

        menu.setHeaderTitle("Selecione uma opção");
        menu.add(0,v.getId(),0,"Favoritos");
        menu.add(0,v.getId(),0,"Detalhes");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();



                if(item.getTitle()=="Favoritos"){

                    int id = info.position;
                    Movie test = filmes.get(id);
                    String teste = test.getTitle();

                    Context context = getApplicationContext();
                    CharSequence text = teste;
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();



                }
                else if (item.getTitle()=="Detalhes"){

                    int id = info.targetView.getId();
                    String teste = String.valueOf(id);

                    Context context = getApplicationContext();
                    CharSequence text = teste;




                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();



                }
                else
                {

                   return false;

                }

                return true;

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_todos);
        listaMovies = (ListView) findViewById(R.id.lista_todos);
        movieDao = new MovieDao(this);
        movieDao.open();

        filmes = movieDao.getAll();
        AdapterMovie adpter = new AdapterMovie(this,filmes);
        listaMovies.setAdapter(adpter);

        registerForContextMenu(listaMovies);

        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Coloque sua chave", Toast.LENGTH_LONG).show();
            return;


        }



        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MoviesResponse> call = apiService.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                int statusCode = response.code();
                List<Movie> movies = response.body().getResults();
                //*Grava os dados no banco *//

                for (Movie filme : movies){

                    movieDao.insert(filme);

                }





            }



            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
            }






        });







    }


}


