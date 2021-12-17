package com.example.pst_ta5_g5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
public void ingresar(View v){
    Intent i= new Intent(getApplicationContext(),Login.class);
    startActivity(i);
}
}