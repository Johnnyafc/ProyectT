package com.example.pst_ta5_g5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Categoris extends AppCompatActivity {

    private EditText ficcion;
    private PantallaPrincipal p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoris);
        ficcion = findViewById(R.id.idBuscarFic);
        this.setTitle("BUSQUEDA POR CATEGORIA");
    }

    public void narnia(View v) {
        ArrayList<Libro> l = cargarDatosNombre("narnia");
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setMessage("Nombre: " + l.get(0).getNombre() + "\nCategoria: " + l.get(0).getGenero() + "\nAutor: " + l.get(0).getAutor() + "\nAño:" + l.get(0).getYear());
        dialogBuilder.setCancelable(true).setTitle("Descripcion");
        dialogBuilder.create().show();
    }

    public void guerra(View v) {
        ArrayList<Libro> l = cargarDatosNombre("guerra mundial z");
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setMessage("Nombre: " + l.get(0).getNombre() + "\nCategoria: " + l.get(0).getGenero() + "\nAutor: " + l.get(0).getAutor() + "\nAño:" + l.get(0).getYear());
        dialogBuilder.setCancelable(true).setTitle("Descripcion");
        dialogBuilder.create().show();
    }

    public void descrepusculo(View view) {
        ArrayList<Libro> l = cargarDatosNombre("crepusculo");
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setMessage("Nombre: " + l.get(0).getNombre() + "\nCategoria: " + l.get(0).getGenero() + "\nAutor: " + l.get(0).getAutor() + "\nAño:" + l.get(0).getYear());
        dialogBuilder.setCancelable(true).setTitle("Descripcion");
        dialogBuilder.create().show();
    }

    public ArrayList<Libro> cargarDatosGenero(String genero) {
        ArrayList<Libro> li = new ArrayList<>();
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(openFileInput("Libros.txt")))) {
            String linea;
            while ((linea = bf.readLine()) != null) {
                System.out.println(linea);
                String p[] = linea.split(",");
                if (p[1].trim().equals(genero)) {
                    li.add(new Libro(p[0], p[1], p[2], p[3], p[4], p[5], p[6]));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return li;
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        boolean hayLibro = false;
        switch (keyCode) {
            case KeyEvent.KEYCODE_ENTER:
                ArrayList<Libro> libros = cargarDatosGenero(ficcion.getText().toString().trim());
                if (libros.size() == 0 & ficcion.getText().toString().equals("")) {
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
                    dialogBuilder.setMessage("Ingrese una categoria");
                    dialogBuilder.setCancelable(true).setTitle("Alerta");
                    dialogBuilder.create().show();
                    ficcion.setText("");
                }
                if (libros.size() == 0 & !(ficcion.getText().toString().equals(""))) {
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
                    dialogBuilder.setMessage("categoria no disponible");
                    dialogBuilder.setCancelable(true).setTitle("Alerta");
                    dialogBuilder.create().show();
                    ficcion.setText("");
                }
                ArrayList<View> vista = new ArrayList<>();
                for (Libro l : libros) {
                    if (l != null) {
                        if (ficcion.getText().toString().equals("ficcion")) {
                            hayLibro = true;
                            ImageView image1 = new ImageView(this);
                            image1.setImageResource(R.drawable.narnia);
                            AlertDialog.Builder dialogBuilder1 = new AlertDialog.Builder(this);
                            dialogBuilder1.setMessage("Nombre: " + l.getNombre() + "\nCategoria: " + l.getGenero() + "\nAutor: " + l.getAutor() + "\nAño:" + l.getYear());
                            dialogBuilder1.setCancelable(true).setTitle("Libro encontrado");
                            image1.setPadding(200, 20, 200, 20);
                            dialogBuilder1.setView(image1);
                            dialogBuilder1.create().show();
                        } else if (ficcion.getText().toString().equals("ficcion") & hayLibro) {
                            ImageView image0 = new ImageView(this);
                            image0.setImageResource(R.drawable.crepussculo);
                            AlertDialog.Builder dialogBuilder0 = new AlertDialog.Builder(this);
                            dialogBuilder0.setMessage("Nombre: " + l.getNombre() + "\nCategoria: " + l.getGenero() + "\nAutor: " + l.getAutor() + "\nAño:" + l.getYear());
                            dialogBuilder0.setCancelable(true).setTitle("Libro encontrado");
                            image0.setPadding(200, 20, 200, 20);
                            dialogBuilder0.setView(image0);
                            dialogBuilder0.create().show();
                        } else if (ficcion.getText().toString().equals("terror")) {
                            ImageView image2 = new ImageView(this);
                            image2.setImageResource(R.drawable.guerra);
                            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
                            dialogBuilder.setMessage("Nombre: " + l.getNombre() + "\nCategoria: " + l.getGenero() + "\nAutor: " + l.getAutor() + "\nAño:" + l.getYear());
                            dialogBuilder.setCancelable(true).setTitle("Libro encontrado");
                            int ancho = 10;
                            int alto = 10;
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ancho, alto);
                            image2.setLayoutParams(params);
                            image2.setPadding(0, 0, 0, 0);
                            dialogBuilder.setView(image2);
                            dialogBuilder.create().show();

                        }
                    }
                    ficcion.setText("");
                }

                return true;
            default:
                return super.onKeyUp(keyCode, event);
        }
    }

    public ArrayList<Libro> cargarDatosNombre(String nombre) {
        ArrayList<Libro> li = new ArrayList<>();
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(openFileInput("Libros.txt")))) {
            String linea;
            while ((linea = bf.readLine()) != null) {
                String p[] = linea.split(",");
                if (p[0].trim().equals(nombre)) {
                    li.add(new Libro(p[0], p[1], p[2], p[3], p[4], p[5], p[6]));
                }
            }
        } catch (FileNotFoundException e) {
            Log.e("Ficheros", "Error al encontrar archivo");
        } catch (IOException e) {
            System.out.println("Hubo un problema al cargar datos2");
        }
        return li;
    }
}