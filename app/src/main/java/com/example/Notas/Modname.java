package com.example.Notas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Modname extends AppCompatActivity {

    String [] arnoms;
    int contnom = 0;

    int posicionExiste = -1;


    Button cambiarnombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modname);

        cambiarnombre = findViewById(R.id.seachnombre);

        Intent recogerDatos = getIntent();
        arnoms = recogerDatos.getStringArrayExtra("arnoms");

        final TextView nom = findViewById(R.id.nombre);

        cambiarnombre.setOnClickListener(new View.OnClickListener() {
            //EditText nomAlumne = findViewById(R.id.nom);

            //String nomGuardar = nomAlumne.getText().toString();
            @Override
            public void onClick(View v) {
                String nomGuardar = nom.getText().toString().trim();

                String search = nomGuardar;
                boolean existe = false;

                for (int i = 0; i < arnoms.length; i++) {
                    String valorarray = arnoms[i];
                    if (search.equals(valorarray)) {
                        existe = true;
                        posicionExiste = i;
                        break;

                    }
                }
                if (existe && contnom==0) {
                    nom.setHint("Nuevo nombre");

                    nom.setText("");
                    contnom++;

                }else if (contnom==1){
                        arnoms[posicionExiste] = nom.getText().toString();
                        nom.setText("");
                        contnom=0;

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Modname.this);
                    alertDialogBuilder.setTitle("Información");
                    nom.setHint("Nombre actual");
                    alertDialogBuilder
                            .setMessage("Nombre cambiado a "+arnoms[posicionExiste])
                            .setCancelable(false)
                            .setNegativeButton("Cerrar",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                }
                            }).create().show();
                    Log.d("PRUEBA", "Esta es la prueba "+arnoms[0]);
                }
                else {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Modname.this);
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


    public void onBackPressed(){
        Intent returnIntent = new Intent();
        returnIntent.putExtra("arnoms", arnoms);
        setResult(RESULT_OK, returnIntent);
        finish();
    }

}
