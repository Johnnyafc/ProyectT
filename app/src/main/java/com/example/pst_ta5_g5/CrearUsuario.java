package com.example.pst_ta5_g5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class CrearUsuario extends AppCompatActivity {
    private EditText editNombres;
    private EditText editApellidos;
    private EditText editTextPhone;
    private EditText editPassword;
    private EditText editUsu;
    private EditText editCorreo;
    public ArrayList<String> arrayNombres=new ArrayList<String>();
    public ArrayList<String> arrayApellidos=new ArrayList<String>();
    public ArrayList<String> arrayTetxtPhone=new ArrayList<String>();
    public ArrayList<String> arrayPassword=new ArrayList<String>();
    public ArrayList<String> arrayUsu=new ArrayList<String>();
    public ArrayList<String> arrayCorreo=new ArrayList<String>();




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