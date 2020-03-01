package com.example.Notas;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class mostrar extends AppCompatActivity {

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
        calculos = calco(cont,arnota1,arnota2,arnota3);
        for (int i=0;i<cont;i++){
            float mid = calculos[i];
            DecimalFormat df = new DecimalFormat("#.#");
            df.setRoundingMode(RoundingMode.CEILING);

            //antes solo tenia calculos[i] para imprimir luego añadi el float
            personNames.add(arnoms[i]+ "----> "+"\t"+arnota1[i]+", "+arnota2[i]+", "+arnota3[i]+" MEDIA---> "+df.format(mid));
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);
        // get the reference of RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        com.example.Notas.CustomAdapter customAdapter = new com.example.Notas.CustomAdapter(mostrar.this, personNames);
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
