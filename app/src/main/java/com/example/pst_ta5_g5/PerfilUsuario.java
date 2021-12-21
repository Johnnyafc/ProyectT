package com.example.pst_ta5_g5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class PerfilUsuario extends AppCompatActivity {
    private  TextView nombre;
    private  TextView apellido;
    private  TextView celular;
    private  TextView correo;
    private Login l;
    private ArrayList<String> n=CrearUsuario.arrayNombres;
    private ArrayList<String> ape= CrearUsuario.arrayApellidos;
    private ArrayList<String> co=CrearUsuario.arrayCorreo;
    private ArrayList<String> ce=CrearUsuario.arrayTetxtPhone;
    private ArrayList<String> us=CrearUsuario.arrayUsu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);
        nombre=findViewById(R.id.txtNombre);
        apellido=findViewById(R.id.txtApellido);
        correo=findViewById(R.id.txtCorreo);
        celular=findViewById(R.id.txtCelular);
        presentarDatos();
    }
    @Override
    public void onBackPressed() { }
    public void salir(View v){
        Intent i= new Intent(getApplicationContext(),MenuOpciones.class);
        startActivity(i);
        this.setTitle("PERFIL DE USUARIO");
    }
public void presentarDatos(){
        for(int i=0; i< n.size();i++){
            if(Login.USU.equals(us.get(i))){
                nombre.setText(n.get(i).toString());
                apellido.setText(ape.get(i).toString());
                correo.setText(co.get(i).toString());
                celular.setText(ce.get(i).toString());
            }
        }
}

}