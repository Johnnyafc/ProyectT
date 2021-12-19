package com.example.pst_ta5_g5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import java.sql.SQLOutput;

public class Login extends AppCompatActivity {
    private EditText usuario;
    private EditText contrasena;
    private  final  static String  USUARIO="1";
    private  final  static String  CONTRASENA="2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setTitle("LOGIN");
        usuario=findViewById(R.id.editUsuario);
        contrasena=findViewById(R.id.editContraseña);
    }
public void ingresar(View v){
    if (usuario.getText().toString().equals(USUARIO) & contrasena.getText().toString().equals(CONTRASENA)) {
        Intent i= new Intent(getApplicationContext(),MenuOpciones.class);
        startActivity(i);
    } else {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setMessage("Credenciles incorrectas");
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