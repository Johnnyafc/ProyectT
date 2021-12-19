package com.example.pst_ta5_g5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.Image;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;


public class PantallaPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);
        this.setTitle("BUSQUEDA GENERAL");
    }

    public  void cerrar(View v){
        Intent i= new Intent(getApplicationContext(),Login.class);
        startActivity(i);
    }
    private void iniciarPantalla(){

    }

}