package com.example.pst_ta5_g5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CrearUsuario extends AppCompatActivity {
    private EditText editNombres;
    private EditText editApellidos;
    private EditText editTextPhone;
    private EditText editPassword;
    private EditText editUsu;
    private EditText editCorreo;
    public static ArrayList<String> arrayNombres=new ArrayList<String>(Arrays.asList("Johnny Alexander", "Christian James", "Hernan segundo", "Isaac Ronaldo"));
    public static ArrayList<String> arrayApellidos=new ArrayList<String>(Arrays.asList("Flores Cedeño", "Noriega Ramirez", "Campos Guzman", "Garcia Alvarez"));
    public static ArrayList<String> arrayTetxtPhone=new ArrayList<String>(Arrays.asList("0994841901", "0999999991", "0999999992", "0999999993"));
    public static ArrayList<String> arrayPassword=new ArrayList<String>(Arrays.asList("1234", "administrador2", "administrador3", "administrador4"));
    public static ArrayList<String> arrayUsu=new ArrayList<String>(Arrays.asList("johnny", "christian", "hernan", "isaac"));
    public static ArrayList<String> arrayCorreo=new ArrayList<String>(Arrays.asList("Correo1", "Correo2", "Correo3", "Correo4"));




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);

        editNombres=findViewById(R.id.editNombres);
        editApellidos=findViewById(R.id.editApellidos);
        editTextPhone=findViewById(R.id.editTextPhone);
        editPassword=findViewById(R.id.editPassword);
        editUsu=findViewById(R.id.editUsu);
        editCorreo=findViewById(R.id.editCorreo);


    }


    public void crearUsuario(View v){
        String textoNombres=editNombres.getText().toString();
        String textoApellidos=editApellidos.getText().toString();
        String textoTextPhone=editTextPhone.getText().toString();
        String textoPassword=editPassword.getText().toString();
        String textoUsu=editUsu.getText().toString();
        String textoCorreo=editCorreo.getText().toString();


        arrayNombres.add(textoNombres);
        arrayApellidos.add(textoApellidos);
        arrayTetxtPhone.add(textoTextPhone);
        arrayPassword.add(textoPassword);
        arrayUsu.add(textoUsu);
        arrayCorreo.add(textoCorreo);

        Intent i= new Intent(getApplicationContext(),Login.class);
        startActivity(i);
    }
}