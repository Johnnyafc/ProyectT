package com.example.pst_ta5_g5;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.bluetoothjhr.BluetoothJhr;

import java.util.Set;

public class DispositivosVinculados extends AppCompatActivity {

    ListView ListaDispositivos;
    BluetoothJhr bluetoothJhr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispositivos_vinculados);
        ListaDispositivos=(ListView)findViewById(R.id.ListaDispositivos);

        final BluetoothJhr bluetoothJhr= new BluetoothJhr(this, ListaDispositivos);
        bluetoothJhr.EncenderBluetooth();

        ListaDispositivos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                bluetoothJhr.Disp_Seleccionado(view,position,Login.class);
            }
        });
    }



}