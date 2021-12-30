package com.example.pst_ta5_g5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.core.Platform;

public class DistanciaRecorrida extends AppCompatActivity implements OnMapReadyCallback {
    private LinearLayout contenedor;
    private double TiempoTrote;
    private static final int LOCATION_REQUEST_CODE = 1;
    private GoogleMap mMap;
    private Location locationIn;
    private Location locationi;
    private Location locationf;
    private TextView txtTiempo;
    private EditText ediTiempo;
    private Button iniciar;
    private LinearLayout contenedorTiempo;
    private LinearLayout.LayoutParams lp;
    private LinearLayout.LayoutParams lp1;
    private LinearLayout.LayoutParams lp2;
    private  TextView contador;
    private boolean agregaContador=true;
    private String layout;
    private int tiempo1;
    private Ubicacion ubicacionInicial;
    private Ubicacion ubicacionFinal;
    private int t1;
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
        this.layout=layout;
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
        contador.setText("0");
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
    public void enviarAlertaDistancia(String mensaje){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setMessage(mensaje);
        dialogBuilder.setCancelable(true).setTitle("Tiempo finalizado");
        dialogBuilder.create().show();
    }
    private View.OnClickListener eventoIniciar= new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            iniciar.setText("Finalizar");
            InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(ediTiempo.getWindowToken(), 0);
            if(agregaContador){
                agregaContador=false;
                contenedorTiempo.addView(contador);
                pocisionInicial();
                System.out.println(locationi.getLatitude());
                ubicacionInicial= new Ubicacion(locationi.getLatitude(),locationi.getLongitude());
            }
            if(layout.equals("Distancia")){
                //enviarAlertaDistancia();
            }
            else if(layout.equals("Tiempo")){
                   iniciarTiempo();
            }


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

    public void pocisionInicial() {
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationi = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));

    }
    public void pocisionFinal() {
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationf = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
    }
    public void pocisionInter() {
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationIn = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
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
    private void iniciarTiempo() {
tiempo1=0;
        new Thread() {
            public void run() {
                t1=Integer.parseInt(ediTiempo.getText().toString());
                while (tiempo1<t1) {
                    tiempo1++;
                    try {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                ediTiempo.setText("");
                                ediTiempo.setEnabled(false);
                                System.out.println("entre");
                                pocisionInter();
                                LatLng coordenada= new LatLng(locationIn.getLatitude(),locationIn.getLongitude());
                                CameraUpdate miUb = CameraUpdateFactory.newLatLngZoom(coordenada,16);
                                mMap.animateCamera(miUb);
                                contador.setText(String.valueOf(tiempo1)+ " min");
                                if(tiempo1>=t1){
                                    System.out.println("entre al final");
                                    pocisionFinal();
                                    System.out.println("salio while"+locationf.getLatitude());
                                    ubicacionFinal= new Ubicacion(locationf.getLatitude(),locationf.getLongitude());
                                    double distancia=Ubicacion.calcularDistancia(ubicacionInicial,ubicacionFinal);
                                    enviarAlertaDistancia("Usted ha recorrido:"+distancia+" m");
                                    iniciar.setText("INICIAR");
                                }
                            }
                        });
                        Thread.sleep(6000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();
    }

    // while (tiempo<Integer.parseInt(contador.getText().toString())) {
    public void personalizado (View v){
        contenedor.removeAllViews();
    }
    public void registros (View v){
        contenedor.removeAllViews();
    }




    }