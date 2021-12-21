package com.example.pst_ta5_g5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class Login extends AppCompatActivity {
    private EditText usuario;
    private EditText contrasena;
    private ArrayList<String> usuar= CrearUsuario.arrayUsu;
    private ArrayList<String> contra=CrearUsuario.arrayPassword;
    public static  String USU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setTitle("LOGIN");
        usuario=findViewById(R.id.editUsuario);
        contrasena=findViewById(R.id.editContraseña);
    }
public void ingresar(View v){
        boolean contrac=false;
        boolean usuari=false;
        USU=usuario.getText().toString();
        for(String s: usuar){
         if(s.equals(usuario.getText().toString())){
             contrac=true;
             for(String c: contra){
                 if(c.equals(contrasena.getText().toString())){
                     usuari=true;
                     Intent i= new Intent(getApplicationContext(),MenuOpciones.class);
                     startActivity(i);
                 }

             }
         }
        }
        if(!(contrac & usuari)){
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            dialogBuilder.setMessage("Credenciales incorrectas");
            dialogBuilder.setCancelable(true).setTitle("Alerta");
            dialogBuilder.create().show();
        }
}
    @Override
    public void onBackPressed() { }
public  void crearUsuario(View v){
    Intent i= new Intent(getApplicationContext(),CrearUsuario.class);
    startActivity(i);
}
}