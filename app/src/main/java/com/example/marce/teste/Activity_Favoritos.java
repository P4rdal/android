package com.example.marce.teste;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Activity_Favoritos extends AppCompatActivity {

    String TEXTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__favoritos);

        TextView myAwesomeTextView = (TextView)findViewById(R.id.favoritos_teste);
        myAwesomeTextView.setText(TEXTO);



    }


    public void capturaitem (String entrada){

        TEXTO = entrada;

    }

}
