package com.example.Notas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class Inicio extends AppCompatActivity {



    Button login;
    EditText usuario,contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        usuario = findViewById(R.id.usuario);
        contrasena = findViewById(R.id.contrasena);
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if ((usuario.getText().toString().trim().equals("Admin")) && (contrasena.getText().toString().trim().equals("12345"))){
                  Intent intent = new Intent (Inicio.this, Menu.class);
                  startActivity(intent);
              }
              else{
                  Snackbar.make(v, "Usuario o contraseña incorrecto!", Snackbar.LENGTH_LONG)
                          .setAction("Action", null).show();
              }
            }
        });
    }




    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
