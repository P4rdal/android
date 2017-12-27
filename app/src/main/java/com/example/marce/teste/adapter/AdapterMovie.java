package com.example.marce.teste.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.marce.teste.R;

import java.util.List;

/**
 * Created by marce on 26/12/2017.
 */

public class AdapterMovie extends BaseAdapter {

    private List<com.example.marce.teste.model.Movie> movies;
    private Context context;

    public AdapterMovie (Context context, List<com.example.marce.teste.model.Movie> movies){

        this.movies = movies;
        this.context = context;

    }



    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int i) {
        return movies.get(i);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        com.example.marce.teste.model.Movie movie=movies.get(position);

        LayoutInflater inflater=LayoutInflater.from(context);

        View view=convertView;

        if (convertView == null){

            view = inflater.inflate(R.layout.list_todos,parent,false);
        }

        TextView campoTitle = (TextView)view.findViewById(R.id.list_todos_titulos);

        if (campoTitle != null) {

            campoTitle.setText(movie.getTitle());
        }


        /*TextView campoDescricao = (TextView)view.findViewById(R.id.list_todos_descricao);

        if (campoDescricao != null) {

            campoDescricao.setText(movie.getOverview());
        }*/

        return view;

    }
}
