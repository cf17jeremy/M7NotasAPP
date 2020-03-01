package com.example.Notas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Modalum extends AppCompatActivity {

    String [] arnoms;
    int [] arnota1;
    int [] arnota2;
    int [] arnota3;
    int cont;

    Button cambiarnombre;
    Button cambiarnota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modalum);

        cambiarnombre = findViewById(R.id.changename);
        cambiarnota = findViewById(R.id.changenota);

        Intent recogerDatos = getIntent();
        arnoms = recogerDatos.getStringArrayExtra("arnoms");
        arnota1 = recogerDatos.getIntArrayExtra("arnota1");
        arnota2 = recogerDatos.getIntArrayExtra("arnota2");
        arnota3 = recogerDatos.getIntArrayExtra("arnota3");
        cont = recogerDatos.getIntExtra("cont", 0);

        cambiarnombre.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (cont<=0){
                    informacion();
                }
                else{
                    Intent intent = new Intent (Modalum.this, Modname.class);
                    intent.putExtra("arnoms", arnoms);
                    intent.putExtra("arnota1", arnota1);
                    intent.putExtra("arnota2", arnota2);
                    intent.putExtra("arnota3", arnota3);
                    intent.putExtra("cont", cont);
                    startActivityForResult(intent, 1);
                }
            }
        });

        cambiarnota.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (cont<=0){
                    informacion();
                }
                else{
                    Intent intent = new Intent (Modalum.this, Modnota.class);
                    intent.putExtra("arnoms", arnoms);
                    intent.putExtra("arnota1", arnota1);
                    intent.putExtra("arnota2", arnota2);
                    intent.putExtra("arnota3", arnota3);
                    intent.putExtra("cont", cont);
                    startActivityForResult(intent, 2);
                }
            }
        });

        cambiarnota.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (cont<=0){
                    informacion();
                }
                else{
                    Intent intent = new Intent (Modalum.this, Modnota.class);
                    intent.putExtra("arnoms", arnoms);
                    intent.putExtra("arnota1", arnota1);
                    intent.putExtra("arnota2", arnota2);
                    intent.putExtra("arnota3", arnota3);
                    intent.putExtra("cont", cont);
                    startActivityForResult(intent, 2);
                }
            }
        });
    }

    //se encarga de Mostrar el primer dialogo de informacion de los alumnos, el else como es unico pues se hace en cada una
    public void informacion(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Modalum.this);

        alertDialogBuilder.setTitle("Informacion");

        alertDialogBuilder
                .setMessage("No hay alumnos añadidos!")
                .setCancelable(false)
                .setNegativeButton("Cerrar",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                }).create().show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // devolucion Modname --> arraynames
        if(requestCode==1){
            if(resultCode == RESULT_OK){
                arnoms = data.getStringArrayExtra("arnoms");
            }
        }

        // devolucion Modnota
        if(requestCode == 2){
            if(resultCode == RESULT_OK){
                arnota1 = data.getIntArrayExtra("arnota1");
                arnota2 = data.getIntArrayExtra("arnota2");
                arnota3 = data.getIntArrayExtra("arnota3");

            }
        }
    }

    public void onBackPressed(){
        Intent returnIntent = new Intent();
        returnIntent.putExtra("arnoms", arnoms);
        returnIntent.putExtra("arnota1", arnota1);
        returnIntent.putExtra("arnota2", arnota2);
        returnIntent.putExtra("arnota3", arnota3);
        returnIntent.putExtra("cont", cont);
        setResult(RESULT_OK, returnIntent);
        finish();
    }

}
