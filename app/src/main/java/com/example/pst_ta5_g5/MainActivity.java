package com.example.pst_ta5_g5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    public ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imagen = findViewById(R.id.CargandoIma);
        iniciarLogin(1);
    }


    private void iniciarLogin(int segundos) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for(int i=0;i<255;i=i+10){
                        Thread.sleep(100 * segundos);
                        imagen.setImageAlpha(i);
                    }
                    Thread.sleep(1000 * segundos);
                    Intent i1 = new Intent(getApplicationContext(), Login.class);
                    startActivity(i1);
                } catch (InterruptedException e) {

                }

            }
        }).start();
    }
}




