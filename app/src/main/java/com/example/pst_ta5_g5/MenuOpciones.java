package com.example.pst_ta5_g5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuOpciones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_opciones);
        this.setTitle("MENU DE OPCIONES");

    }
    public void BuscarGeneral(View v){
        Intent i= new Intent(getApplicationContext(),DistanciaRecorrida.class);
        startActivity(i);
    }
    public void cerrarSesion(View v){
        Intent i= new Intent(getApplicationContext(),Login.class);
        startActivity(i);
    }
    public void buscarCate(View v){
        Intent i= new Intent(getApplicationContext(), Categoris.class);
        startActivity(i);
    }
    public void perfil(View v){
        Intent i= new Intent(getApplicationContext(),PerfilUsuario.class);
        startActivity(i);
    }
    @Override
    public void onBackPressed() {
    }
}