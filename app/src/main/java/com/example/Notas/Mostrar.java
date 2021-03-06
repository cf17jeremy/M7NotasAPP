package com.example.Notas;

import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class Mostrar extends AppCompatActivity {

    String [] arnoms;
    int [] arnota1;
    int [] arnota2;
    int [] arnota3;
    float [] calculos;
    int cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent recogerDatos = getIntent();
        arnoms = recogerDatos.getStringArrayExtra("arnoms");
        arnota1 = recogerDatos.getIntArrayExtra("arnota1");
        arnota2 = recogerDatos.getIntArrayExtra("arnota2");
        arnota3 = recogerDatos.getIntArrayExtra("arnota3");
        cont = recogerDatos.getIntExtra("cont", 0);

        ArrayList<String> personNames = new ArrayList<>();
        ArrayList<Integer> notas1 = new ArrayList<>();
        ArrayList<Integer> notas2 = new ArrayList<>();
        ArrayList<Integer> notas3 = new ArrayList<>();
        ArrayList<String> medias = new ArrayList<>();

        calculos = calco(cont,arnota1,arnota2,arnota3);

        for (int i=0;i<cont;i++){
            float mid = calculos[i];
            DecimalFormat df = new DecimalFormat("#.#");

            //antes solo tenia calculos[i] para imprimir luego añadi el float
            personNames.add(arnoms[i]);
            notas1.add(arnota1[i]);
            notas2.add(arnota2[i]);
            notas3.add(arnota3[i]);
            medias.add(df.format(mid));
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);
        // get the reference of RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        //  call the constructor of RecycleView to send the reference and data to Adapter
        com.example.Notas.RecycleView customAdapter = new com.example.Notas.RecycleView(Mostrar.this, personNames, notas1, notas2, notas3, medias);
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView
    }

    public static float [] calco(int cont, int [] arnota1, int [] arnota2, int [] arnota3){
        float [] result = new float [cont];
        float suma;
        for(int i=0;i<cont;i++){
            suma=arnota1[i]+arnota2[i]+arnota3[i];
            result [i] = suma/3.0f;
            suma=0;
        }
        return result;

    }

}
