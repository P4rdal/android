package com.example.marce.teste;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Activity_Detalhes extends AppCompatActivity {

    private int ID = 0;
    private TextView txtTitulo;
    private TextView txtDescricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__detalhes);

        Intent in = getIntent();
        String titulo = in.getStringExtra("TITULO");
        String descricao = in.getStringExtra("DESCRICAO");



         txtTitulo = (TextView)findViewById(R.id.Detalhes_Titulo);
         txtTitulo.setText(titulo);


         txtDescricao = (TextView)findViewById(R.id.Detalhes_decricao);
         txtDescricao.setText(descricao);



    }
}
