package com.example.Notas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Modnota extends AppCompatActivity {

    String [] arnoms;
    int [] arnota1;
    int [] arnota2;
    int [] arnota3;
    int cont,seleval,notasave;
    int contnom = 0;
    boolean existe = false;
    int posicionExiste = -1;

    Button cambiarnota;
    EditText nombre,nota;
    int maxLength = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modnota);

        cambiarnota = findViewById(R.id.save);

        nombre = findViewById(R.id.nombre);
        nota = findViewById(R.id.nota);

        Intent recogerDatos = getIntent();

        arnoms = recogerDatos.getStringArrayExtra("arnoms");
        arnota1 = recogerDatos.getIntArrayExtra("arnota1");
        arnota2 = recogerDatos.getIntArrayExtra("arnota2");
        arnota3 = recogerDatos.getIntArrayExtra("arnota3");
        cont = recogerDatos.getIntExtra("cont", 0);

        cambiarnota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText guardarnombre = findViewById(R.id.nombre);
                EditText guardarnota = findViewById(R.id.nota);

                String nomGuardar = guardarnombre.getText().toString();


                for (int i = 0; i < arnoms.length; i++) {
                    String valorarray = arnoms[i];
                    if (nomGuardar.equals(valorarray)) {
                        existe = true;
                        posicionExiste = i;
                        break;
                    }
                }if ((vacionulo(nomGuardar))) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Modnota.this);
                    alertDialogBuilder.setTitle("Información");
                    alertDialogBuilder
                            .setMessage("El nombre no puede estar vacío!")
                            .setCancelable(false)
                            .setNegativeButton("Aceptar",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                }
                            }).create().show();
                } else if (!existe){
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Modnota.this);
                    alertDialogBuilder.setTitle("Información");
                    alertDialogBuilder
                            .setMessage("El alumno " + nomGuardar + " no existe!")
                            .setCancelable(false)
                            .setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            }).create().show();
                }else if (guardarnota.getText().toString().length() == 0 && contnom==0) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Modnota.this);
                    alertDialogBuilder.setTitle("Información");
                    alertDialogBuilder
                            .setMessage("La evaluacion no puede estar vacía!")
                            .setCancelable(false)
                            .setNegativeButton("Aceptar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            }).create().show();
                } else if (guardarnota.getText().toString().length() == 0 && contnom>1) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Modnota.this);
                    alertDialogBuilder.setTitle("Información");
                    alertDialogBuilder
                            .setMessage("La nota no puede estar vacía!")
                            .setCancelable(false)
                            .setNegativeButton("Aceptar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            }).create().show();
                } else {
                    if (contnom == 0) {
                        for (int i = 0; i < arnoms.length; i++) {
                            String valorarray = arnoms[i];
                            if (guardarnombre.getText().toString().equals(valorarray)) {
                                existe = true;
                                posicionExiste = i;
                                break;
                            }
                        }
                        seleval = Integer.parseInt(nota.getText().toString());
                        if (existe) {
                            nombre.setEnabled(false);
                            nota.setText("");
                            nota.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                            nota.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
                            if (seleval == 1) {
                                nota.setHint("Nota - evaluacion 1");
                            }
                            if (seleval == 2) {
                                nota.setHint("Nota - evaluacion 2");
                            }
                            if (seleval == 3) {
                                nota.setHint("Nota - evaluacion 3");
                            }
                            contnom++;
                        } else {
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Modnota.this);
                            alertDialogBuilder.setTitle("Información");

                            alertDialogBuilder
                                    .setMessage("El alumno " + nomGuardar + " no existe!")
                                    .setCancelable(false)
                                    .setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    }).create().show();
                        }
                    }
                    else{
                        notasave = Integer.parseInt(nota.getText().toString());
                        if (notasave<0 || notasave>10){
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Modnota.this);
                            alertDialogBuilder.setTitle("Información");

                            alertDialogBuilder
                                    .setMessage("LA NOTA DEBE ESTAR EN EL RANGO DE 0 Y 10 (INCLUYENDO 0 Y 10)")
                                    .setCancelable(false)
                                    .setNegativeButton("Aceptar",new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,int id) {
                                            dialog.cancel();
                                        }
                                    }).create().show();
                        }
                        else {
                            if (seleval == 1 && contnom > 1) {
                                arnota1[posicionExiste] = Integer.parseInt(nota.getText().toString());
                                nombre.setEnabled(true);
                                nombre.setText("");
                                nota.setText("");
                                nota.setHint("Evaluacion a cambiar ");
                                nota.setKeyListener(DigitsKeyListener.getInstance("123"));
                                contnom = -1;

                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Modnota.this);
                                alertDialogBuilder.setTitle("Información");

                                alertDialogBuilder
                                        .setMessage("Nota cambiada a: " + arnota1[posicionExiste] + "\nDel alumno: " + nomGuardar)
                                        .setCancelable(false)
                                        .setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        }).create().show();

                            } else if (seleval == 2 && contnom > 0) {
                                arnota2[posicionExiste] = Integer.parseInt(nota.getText().toString());
                                nombre.setEnabled(true);
                                nombre.setText("");
                                nota.setText("");
                                nota.setHint("Evaluacion a cambiar ");
                                nota.setKeyListener(DigitsKeyListener.getInstance("123"));
                                contnom = -1;

                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Modnota.this);
                                alertDialogBuilder.setTitle("Información");

                                alertDialogBuilder
                                        .setMessage("Nota cambiada a: " + arnota2[posicionExiste] + "\nDel alumno: " + nomGuardar)
                                        .setCancelable(false)
                                        .setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        }).create().show();

                            } else if (seleval == 3 && contnom > 1) {
                                arnota3[posicionExiste] = Integer.parseInt(nota.getText().toString());
                                nombre.setEnabled(true);
                                nombre.setText("");
                                nota.setText("");
                                nota.setHint("Evaluacion a cambiar ");
                                nota.setKeyListener(DigitsKeyListener.getInstance("123"));
                                contnom = -1;

                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Modnota.this);
                                alertDialogBuilder.setTitle("Información");

                                alertDialogBuilder
                                        .setMessage("Nota cambiada a: " + arnota3[posicionExiste] + "\nDel alumno: " + nomGuardar)
                                        .setCancelable(false)
                                        .setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        }).create().show();
                            }
                        }
                    }
                    contnom++;
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
        returnIntent.putExtra("arnota1", arnota1);
        returnIntent.putExtra("arnota2", arnota2);
        returnIntent.putExtra("arnota3", arnota3);
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
