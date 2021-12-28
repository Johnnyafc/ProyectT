package com.example.pst_ta5_g5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DistanciaRecorrida extends AppCompatActivity implements OnMapReadyCallback  {
    private LinearLayout contenedor;
    private double TiempoTrote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distancia_recorrida);
        contenedor= findViewById(R.id.ContenedorPrincipal);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(0, 0))
                .title("Marker"));
    }
    public void iniciarPorTiempo(View v){
        iniciaParteIntroducirTiempo();
        iniciarMapa();
    }
    public void iniciaParteIntroducirTiempo(){
        contenedor.removeAllViews();
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT );
        LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT );
        //contenedores
        LinearLayout contenedorTiempo = new LinearLayout(this);
        // crear elementos
        configuracionesParteTiempo(contenedorTiempo,lp,lp1);
        // agregar elemento al contenedor padre
        this.contenedor.addView(contenedorTiempo);
    }
    public void configuracionesParteTiempo(LinearLayout contenedorTiempo, LinearLayout.LayoutParams lp,LinearLayout.LayoutParams lp1){
        TextView txtTiempo= new TextView(this);
        EditText ediTiempo= new EditText(this);
        // configuraciones
        contenedorTiempo.setOrientation(LinearLayout.VERTICAL);
        ediTiempo.setHint("ingrese tiempo que desea trotar");
        lp.setMargins(10,100,10,10);
        lp1.setMarginStart(10);
        lp1.setMarginEnd(100);
        txtTiempo.setLayoutParams(lp);
        ediTiempo.setLayoutParams(lp1);
        txtTiempo.setText("Tiempo:");
        contenedorTiempo.addView(txtTiempo,0);
        contenedorTiempo.addView(ediTiempo,1);
    }
    private void iniciarMapa(){
        SupportMapFragment mapFragment = SupportMapFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.ContenedorPrincipal, mapFragment)
                .commit();
        mapFragment.getMapAsync(this);
    }

    public void iniciarPorDistancia(View v){
        contenedor.removeAllViews();
    }
    public void personalizado(View v){
        contenedor.removeAllViews();
    }
    public void registros(View v){
        contenedor.removeAllViews();
    }


}