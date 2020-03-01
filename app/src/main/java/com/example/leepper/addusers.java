package com.example.leepper;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class addusers extends AppCompatActivity {

    String [] arnoms;
    int [] arnota1;
    int [] arnota2;
    int [] arnota3;
    int cont;
    int contnom = 0;

    Button bottonsend;
    EditText nombre,nota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addusers);

        bottonsend = findViewById(R.id.btnAfegir);
        nombre = findViewById(R.id.nombre);
        nota = findViewById(R.id.nota);

        Intent recogerDatos = getIntent();

        arnoms = recogerDatos.getStringArrayExtra("arnoms");
        arnota1 = recogerDatos.getIntArrayExtra("arnota1");
        arnota2 = recogerDatos.getIntArrayExtra("arnota2");
        arnota3 = recogerDatos.getIntArrayExtra("arnota3");
        cont = recogerDatos.getIntExtra("cont", 0);

        final Button btnAfegir = findViewById(R.id.btnAfegir);

        btnAfegir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText nomAlumne = findViewById(R.id.nombre);
                EditText notaAlumne = findViewById(R.id.nota);

                String nomGuardar = nomAlumne.getText().toString().trim();

                if (notaAlumne.getText().toString().length() == 0){
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(addusers.this);

                    alertDialogBuilder.setTitle("Información");

                    alertDialogBuilder
                            .setMessage("La nota no puede estar vacía!")
                            .setCancelable(false)
                            .setNegativeButton("Aceptar",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                }
                            }).create().show();
                }
                else {
                    int notaGuardar = Integer.parseInt(notaAlumne.getText().toString());

                    if ((notaGuardar < 0) || (notaGuardar > 10)) {
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(addusers.this);

                        alertDialogBuilder.setTitle("Información");
                        alertDialogBuilder
                                .setMessage("LA NOTA DEBE ESTAR EN EL RANGO DE 0 Y 10 (INCLUYENDO 0 Y 10)")
                                .setCancelable(false)
                                .setNegativeButton("Aceptar",new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                }).create().show();

                    } else if ((vacionulo(nomGuardar))) {
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(addusers.this);

                        alertDialogBuilder.setTitle("Información");
                        alertDialogBuilder
                                .setMessage("EL NOMBRE NO PUEDE ESTAR VACIO")
                                .setCancelable(false)
                                .setNegativeButton("Aceptar",new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                }).create().show();

                    }else if (contnom==0){
                        arnoms[cont] = nombre.getText().toString();
                        arnota1[cont] = Integer.parseInt(nota.getText().toString());
                        nombre.setEnabled(false);
                        nota.setHint("Nota 2");
                        nota.setText("");
                        contnom++;
                    }
                    else if (contnom==1){
                        arnota2[cont] = Integer.parseInt(nota.getText().toString());
                        nota.setHint("Nota 3");
                        nota.setText("");
                        contnom++;
                    }
                    else if (contnom==2){
                        arnota3[cont] = Integer.parseInt(nota.getText().toString());
                        nombre.setEnabled(true);
                        nota.setHint("Nota 1");
                        nota.setText("");
                        nombre.setText("");
                        contnom=0;
                        cont++;
                    }

                }
            }

        });

    }
    public static boolean vacionulo(String str) {
        if(str != null && !str.isEmpty())
            return false;
        return true;
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
