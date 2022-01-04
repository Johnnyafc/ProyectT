package com.example.pst_ta5_g5;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;

import java.util.Random;

public class Pulso extends AppCompatActivity {
    private TextView txt500;
    private Button pulso;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulso);

        txt500 = findViewById(R.id.txt500);
        pulso = findViewById(R.id.button);
    }

    public void botonPulso (View v){
        Random random = new Random();
        int val = 80+random.nextInt(110);
        //Toast.makeText(this, "pulso:"+val, Toast.LENGTH_SHORT).show();
        txt500.setText(Integer.toString(val));
    }
}
