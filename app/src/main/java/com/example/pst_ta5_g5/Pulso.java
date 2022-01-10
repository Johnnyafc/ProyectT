package com.example.pst_ta5_g5;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.bluetoothjhr.BluetoothJhr;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;

import java.util.Random;

public class Pulso extends AppCompatActivity {
    Button Enviar;
    TextView Consola;
    EditText TextoEnviar;

    BluetoothJhr bluetoothJhr2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulso);

        bluetoothJhr2= new BluetoothJhr(Login.class, this);
        //Enviar= (Button)findViewById(R.id.Enviar);
        Consola= (TextView)findViewById(R.id.Consola);
        //TextoEnviar= (EditText)findViewById(R.id.TextoEnviar);


       /* Enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Mensaje= TextoEnviar.getText().toString();
                bluetoothJhr2.Tx(Mensaje);
            }
        });*/

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    Delay();
                    Consola.post(new Runnable() {
                        @Override
                        public void run() {
                            if (bluetoothJhr2.Rx()!= null && bluetoothJhr2.Rx()!= "null" && !bluetoothJhr2.Rx().equalsIgnoreCase("null") && bluetoothJhr2.Rx()!=""){
                                String Dato = bluetoothJhr2.Rx();
                                Consola.setText(Dato);
                                bluetoothJhr2.ResetearRx();
                            }

                        }
                    });
                }
            }
        }).start();

    }
    @Override
    public void onResume(){
        super.onResume();
        bluetoothJhr2.ConectaBluetooth();
    }

    @Override
    public void onPause(){
        super.onPause();
        bluetoothJhr2.CierraConexion();
    }

    private void Delay(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
