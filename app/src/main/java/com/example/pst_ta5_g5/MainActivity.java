package com.example.pst_ta5_g5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    boolean presentar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciarLogin(1);
    }


    private void iniciarLogin(int segundos){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                  Thread.sleep(1000*segundos);
                    Intent i= new Intent(getApplicationContext(),Login.class);
                    startActivity(i);
                }catch(InterruptedException e){

                }

            }
        }).start();






    }
    public void botonPrueba(View v){
        Intent i= new Intent(getApplicationContext(),Login.class);
        startActivity(i);
    }

}




