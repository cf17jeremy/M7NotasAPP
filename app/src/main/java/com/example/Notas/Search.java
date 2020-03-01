package com.example.Notas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Search extends AppCompatActivity {

    String [] arnoms;
    int [] arnota1;
    int [] arnota2;
    int [] arnota3;
    float [] calculos;
    int cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_Search);
        //nom = findViewById(R.id.nom);

        Intent recogerDatos = getIntent();
        arnoms = recogerDatos.getStringArrayExtra("arnoms");
        arnota1 = recogerDatos.getIntArrayExtra("arnota1");
        arnota2 = recogerDatos.getIntArrayExtra("arnota2");
        arnota3 = recogerDatos.getIntArrayExtra("arnota3");
        cont = recogerDatos.getIntExtra("cont", 0);

        calculos = calco(cont,arnota1,arnota2,arnota3);

        final Button button = findViewById(R.id.seachnombre);
        final TextView nom = findViewById(R.id.nombre);

        button.setOnClickListener(new View.OnClickListener() {
            //EditText nomAlumne = findViewById(R.id.nom);

            //String nomGuardar = nomAlumne.getText().toString();
            @Override
            public void onClick(View v) {
                String search = nom.getText().toString().trim();
                boolean existe = false;

                int posicionExiste = -1;
                for (int i = 0; i < arnoms.length; i++) {
                    String valorarray = arnoms[i];
                    if (search.equals(valorarray)) {
                        existe = true;
                        posicionExiste = i;
                        break;

                    }
                }
                if (existe) {
                    float mid = calculos[posicionExiste];
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Search.this);
                    alertDialogBuilder.setTitle("Alumno: "+search);
                    DecimalFormat df = new DecimalFormat("#.#");
                    df.setRoundingMode(RoundingMode.CEILING);
                    alertDialogBuilder
                            .setMessage("Nota evaluacion 1: "+arnota1[posicionExiste] + "\nNota evaluacion 2: " + arnota2[posicionExiste] + "\nNota evaluacion 3: " + arnota3[posicionExiste]+"\nMedia: "+df.format(mid))
                            .setCancelable(false)
                            .setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            }).create().show();
                } else {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Search.this);
                    alertDialogBuilder.setTitle("Información");

                    alertDialogBuilder
                            .setMessage("EL ALUMNO "+search+" NO EXISTE!!!")
                            .setCancelable(false)
                            .setNegativeButton("Cerrar",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                }
                            }).create().show();
                }
            }
        });
    }


    //hay que buscar una forma de no repetir tanto el bucle este y solicitar estos datos desde aqui a otra ventana
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
