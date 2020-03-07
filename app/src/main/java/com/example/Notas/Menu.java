package com.example.Notas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;

public class Menu extends AppCompatActivity {
    Button add,peligro,mostrar,aprall,help,seach,change,medglob;

    String [] arnoms = new String[50];
    int [] arnota1 = new int[50];
    int [] arnota2 = new int[50];
    int [] arnota3 = new int[50];
    int cont = 0;
    float medglobal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        add = findViewById(R.id.add);
        mostrar = findViewById(R.id.mostrar);
        aprall = findViewById(R.id.aprall);
        medglob=findViewById(R.id.medglob);
        help = findViewById(R.id.help);
        seach = findViewById(R.id.seach);
        change = findViewById(R.id.change);
        peligro = findViewById(R.id.peligro);


        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent (Menu.this, Addusers.class);
                intent.putExtra("arnoms", arnoms);
                intent.putExtra("arnota1", arnota1);
                intent.putExtra("arnota2", arnota2);
                intent.putExtra("arnota3", arnota3);
                intent.putExtra("cont", cont);
                startActivityForResult(intent, 1);
            }
        });

        mostrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (cont<=0){
                    informacion();
                }
                else{
                    Intent intent = new Intent (Menu.this, Mostrar.class);
                    intent.putExtra("arnoms", arnoms);
                    intent.putExtra("arnota1", arnota1);
                    intent.putExtra("arnota2", arnota2);
                    intent.putExtra("arnota3", arnota3);
                    intent.putExtra("cont", cont);
                    startActivityForResult(intent, 1);
                }
            }
        });

        help.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Menu.this);

                alertDialogBuilder.setTitle("Ayuda");

                alertDialogBuilder
                        .setMessage("Hola, esta es la ventana de ayuda, hay "+cont+" alumnos.\nEsta aplicacion esta dedicada a registros de notas, si cree que\nhay algun error o mejora a incorporar porfavor comuniquenoslo.")
                        .setCancelable(false)
                        .setNegativeButton("Cerrar",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        }).create().show();
            }
        });
        aprall.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (cont<=0){
                    informacion();
                }
                else{
                    for(int i=0;i<cont;i++){
                        if (arnota1[i] <=4){
                            arnota1[i] = 5;
                        }
                        if (arnota2[i] <=4){
                            arnota2[i] = 5;
                        }
                        if (arnota3[i] <=4){
                            arnota3[i] = 5;
                        }
                    }
                    Toast.makeText(getApplicationContext(),"OPERACIÓN REALIZADA, TODOS LOS ALUMNOS HAN SIDO APROBADOS",Toast.LENGTH_SHORT).show();
                }
            }
        });
        medglob.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (cont==0){
                    informacion();
                }
                else {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Menu.this);
                    medglobal = calco(cont, arnota1, arnota2, arnota3);
                    DecimalFormat df = new DecimalFormat("#.#");
                    df.setRoundingMode(RoundingMode.CEILING);
                    alertDialogBuilder.setTitle("Media Global");

                    alertDialogBuilder
                            .setMessage("La media global es: " + df.format(medglobal))
                            .setCancelable(false)
                            .setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            }).create().show();
                }
            }
        });
        seach.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (cont<=0) {
                    informacion();
                }
                else {
                    Intent intent = new Intent(Menu.this, Search.class);
                    intent.putExtra("arnoms", arnoms);
                    intent.putExtra("arnota1", arnota1);
                    intent.putExtra("arnota2", arnota2);
                    intent.putExtra("arnota3", arnota3);
                    intent.putExtra("cont", cont);
                    startActivityForResult(intent, 1);
                }
            }
        });

        change.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (cont<=0) {
                    informacion();
                }
                else {
                    Intent intent = new Intent(Menu.this, Modalum.class);
                    intent.putExtra("arnoms", arnoms);
                    intent.putExtra("arnota1", arnota1);
                    intent.putExtra("arnota2", arnota2);
                    intent.putExtra("arnota3", arnota3);
                    intent.putExtra("cont", cont);
                    startActivityForResult(intent, 1);
                }
            }
        });
        peligro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (cont<=0) {
                    informacion();
                }
                else {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(Menu.this);
                    dialog.setTitle("ADVERTENCIA!");
                    dialog.setMessage("Estas apunto de restaurar toda\nla información de esta app.\nEsta opción es irreversible.\nestás seguro de que quieres continuar? ");
                    dialog.setCancelable(true);
                    dialog.setPositiveButton(
                            "SI",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int
                                        id) {
                                    Arrays.fill(arnoms, null);
                                    Arrays.fill(arnota1, 0);
                                    Arrays.fill(arnota2, 0);
                                    Arrays.fill(arnota3, 0);
                                    cont=0;
                                    Toast.makeText(getApplicationContext(),"INFORMACIÓN RESTABLECIDA CORRECTAMENTE!",Toast.LENGTH_SHORT).show();

                                }
                            });
                    dialog.setNegativeButton(
                            "NO",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    Toast.makeText(getApplicationContext(),"OPERACIÓN CANCELADA POR EL USUARIO",Toast.LENGTH_SHORT).show();
                                }
                            });

                    AlertDialog alert = dialog.create();
                    alert.show();
                }
            }
        });
    }


    //se encarga de Mostrar el primer dialogo de informacion de los alumnos, el else como es unico pues se hace en cada una
    public void informacion(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Menu.this);

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
    public static float calco(int cont, int [] arnota1, int [] arnota2, int [] arnota3){
        float suma=0,medglob;

        for(int i=0;i<cont;i++){
            suma += arnota1[i]+arnota2[i]+arnota3[i]; //La suma de las arrays, de las notas de los alumnos, aunque luego no la imprimo
        }
        medglob = suma/(cont*3);
        return medglob;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1){
            if(resultCode == RESULT_OK){
                arnoms = data.getStringArrayExtra("arnoms");
                arnota1 = data.getIntArrayExtra("arnota1");
                arnota2 = data.getIntArrayExtra("arnota2");
                arnota3 = data.getIntArrayExtra("arnota3");
                cont = data.getIntExtra("cont", 0);
            }
        }

    }

}
