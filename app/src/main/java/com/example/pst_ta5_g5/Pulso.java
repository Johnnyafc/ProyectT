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
    EditText et1;

    BluetoothJhr bluetoothJhr2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulso);

        bluetoothJhr2= new BluetoothJhr(Login.class, this);
        //Enviar= (Button)findViewById(R.id.Enviar);
        Consola= (TextView)findViewById(R.id.Consola);
        et1= (EditText)findViewById(R.id.et1);


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
                                //verEstado();
                                bluetoothJhr2.ResetearRx();


                            }

                        }
                    });
                }
            }
        }).start();

    }

    public void verEstado(){
        String num= Consola.getText().toString();
        int valor=Integer.parseInt(num);
        if (valor<= 99){
            et1.setText("ESTADO DE REPOSO");
        }
        else if (valor >100 && valor <=118){
            et1.setText("ESTADO DE DESCOMPRESIÓN");
        }
        else if (valor >119 && valor <=138){
            et1.setText("ESTADO DE QUEMA DE GRASA");
        }
        else if (valor >139 && valor <=158){
            et1.setText("ESTADO DE CARDIOPULMONAR");
        }
        else if (valor >159 && valor <=178){
            et1.setText("ESTADO DE ANAERÓBICO");
        }
        else{
            et1.setText("ESTADO LÍMITE");
        }

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
