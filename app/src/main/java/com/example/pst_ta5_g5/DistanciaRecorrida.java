package com.example.pst_ta5_g5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DistanciaRecorrida extends AppCompatActivity implements OnMapReadyCallback {
    private LinearLayout contenedor;
    private double TiempoTrote;
    private static final int LOCATION_REQUEST_CODE = 1;
    private GoogleMap mMap;
    private Location location;
    private TextView txtTiempo;
    private EditText ediTiempo;
    private Button iniciar;
    private LinearLayout contenedorTiempo;
    private LinearLayout.LayoutParams lp;
    private LinearLayout.LayoutParams lp1;
    private LinearLayout.LayoutParams lp2;
    private  TextView contador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distancia_recorrida);
        contenedor = findViewById(R.id.ContenedorPrincipal);
        this.setTitle("Distancia");
    }
    public void iniciarPorTiempo(View v) {
        iniciaParteIntroducirTiempoOdistancia("Ingrese tiempo que desea trotar", "Tiempo");
        iniciarMapa();
    }
    public void iniciarPorDistancia (View v){
        iniciaParteIntroducirTiempoOdistancia("Ingrese distancia que desea trotar","Distancia");
        iniciarMapa();

    }
    private void iniciarMapa() {
        SupportMapFragment mapFragment = SupportMapFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.ContenedorPrincipal, mapFragment)
                .commit();
        mapFragment.getMapAsync(this);
    }


    public void iniciaParteIntroducirTiempoOdistancia(String mensaje, String layout) {
        contenedor.removeAllViews();
         lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
         lp1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
         lp2=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                 LinearLayout.LayoutParams.WRAP_CONTENT);
        //contenedores
        contenedorTiempo = new LinearLayout(this);
        // crear elementos
        configuracionesParteTiempo(mensaje,layout);
        // agregar elemento al contenedor padre
        this.contenedor.addView(contenedorTiempo);
    }

    public void configuracionesParteTiempo (String mensaje, String layout){
        txtTiempo = new TextView(this);
        ediTiempo = new EditText(this);
        iniciar = new Button(this);
        contador= new TextView(this);
        contador.setText("00:00");
        contador.setGravity(Gravity.CENTER);
        contador.setTextColor(Color.RED);
        contador.setTextSize(38);
        // configuraciones
        contenedorTiempo.setOrientation(LinearLayout.VERTICAL);
        ediTiempo.setHint(mensaje);
        iniciar.setText("INICIAR");
        lp.setMargins(10, 100, 10, 10);
        lp1.setMarginStart(100);
        lp1.setMarginEnd(100);
        txtTiempo.setLayoutParams(lp);
        ediTiempo.setLayoutParams(lp1);
        iniciar.setLayoutParams(lp);
        contador.setLayoutParams(lp1);
        txtTiempo.setText(layout);
        contenedorTiempo.addView(txtTiempo, 0);
        contenedorTiempo.addView(ediTiempo, 1);
        contenedorTiempo.addView(iniciar,2);
        iniciar.setOnClickListener(eventoIniciar);
    }
    private View.OnClickListener eventoIniciar= new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            iniciar.setText("Finalizar");
            contenedorTiempo.addView(contador);
            //guardarCoordenada();
        }
    };
    @Override
    public void onMapReady(GoogleMap googleMap) {
        permisosMapa(googleMap);
    }

    public void permisosMapa(GoogleMap googleMap) {
        mMap = googleMap;

        // Controles UI
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Mostrar diálogo explicativo
            } else {
                // Solicitar permiso
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        LOCATION_REQUEST_CODE);
            }
        }

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
    }

    public void guardarCoordenada() {
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
        Toast.makeText(this, "LATITUD"+location.getLatitude(), Toast.LENGTH_LONG).show();
        Toast.makeText(this, "LONGITUD"+location.getLatitude(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_REQUEST_CODE) {
            // ¿Permisos asignados?
            if (permissions.length > 0 &&
                    permissions[0].equals(Manifest.permission.ACCESS_FINE_LOCATION) &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                mMap.setMyLocationEnabled(true);
            } else {
                Toast.makeText(this, "Error de permisos", Toast.LENGTH_LONG).show();
            }

        }
    }

    public void personalizado (View v){
        contenedor.removeAllViews();
    }
    public void registros (View v){
        contenedor.removeAllViews();
    }




    }