package com.example.pst_ta5_g5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class PantallaPrincipal extends AppCompatActivity {
    ArrayList<ImageView> imagenes;
 private EditText nombre;
 private ImageView crepusculo;
 private ImageView narnia;
 private ImageView guerra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);
        nombre= findViewById(R.id.editNombre);
        crepusculo=findViewById(R.id.crepusculo);
        narnia=findViewById(R.id.narn);
        guerra=findViewById(R.id.guerra);

        this.setTitle("BUSQUEDA GENERAL");
    }

    public void crearArchivo(){
        try {
            OutputStreamWriter fout = new OutputStreamWriter(openFileOutput("Libros.txt", Context.MODE_PRIVATE));
            fout.write("Crepusculo,Romance,Stephenie Meyer,2005,Crepusculo,txtCre,txtDesCre\n" +
                    "Narnia,Ficcion,Clive Staples Lewis,1956,Narnia,txtNarnia,txtDescrNar\n" +
                    "Guerra mundial z,Terror,Maximillian Michael Brooks,2006,guerra,txtGuerra,txtDesGuerra\n");
            fout.close();
        } catch(Exception ex) {
            Log.e("Ficheros", "Error al escribir fichero a memoria interna");
        }
    }
    public ArrayList<Libro> cargarDatosNombre(String nombre){
        ArrayList<Libro> li = new ArrayList<>();
        crearArchivo();
        try(BufferedReader bf = new BufferedReader(new InputStreamReader(openFileInput("Libros.txt")))) {
            String linea;
            while((linea=bf.readLine())!=null){
                String p[]=linea.split(",");
                if(p[0].trim().equals(nombre)){
                    li.add(new Libro(p[0],p[1],p[2],p[3],p[4],p[5],p[6]));
                }
            }
        } catch (FileNotFoundException e) {
            Log.e("Ficheros", "Error al encontrar archivo");
        } catch (IOException e) {
            System.out.println("Hubo un problema al cargar datos2");
        }
        return li;
    }
    public static ArrayList<Libro> cargarDatosGenero(String genero) {
        ArrayList<Libro> li = new ArrayList<>();
        try (BufferedReader bf = new BufferedReader(new FileReader("Libros.txt"))) {
            String linea;
            while ((linea = bf.readLine()) != null) {
                String p[] = linea.split(",");
                if (p[1].trim().equals(genero)) {
                    li.add(new Libro(p[0], p[1], p[2], p[3],p[4],p[5],p[6]));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Problemas en el archivo");
        } catch (IOException e) {
            System.out.println("Problemas al subir");
        }
        return li;
    }
//"Nombre: "+l.getNombre()+"\nCategoria: "+l.getGenero()+"\nAutor: "+l.getAutor()+"\nAño:"+l.getYear()
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_ENTER:

                ArrayList<Libro> libros = cargarDatosNombre(nombre.getText().toString().trim());
               if(libros.size()==0 & nombre.getText().toString().equals("")){
                   AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
                   dialogBuilder.setMessage("Ingrese el nombre de un libro");
                   dialogBuilder.setCancelable(true).setTitle("Alerta");
                   dialogBuilder.create().show();
                   nombre.setText("");
               }
               if(libros.size()==0 & !(nombre.getText().toString().equals(""))){
                   AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
                   dialogBuilder.setMessage("Libro no encontrado");
                   dialogBuilder.setCancelable(true).setTitle("Alerta");
                   dialogBuilder.create().show();
                   nombre.setText("");
               }
                    for (Libro l : libros) {
                        if (libros.size() > 0) {
                            ImageView image = new ImageView(this);
                            if (nombre.getText().toString().equals("Narnia")) {
                                image.setImageResource(R.drawable.narnia);
                                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
                                dialogBuilder.setMessage("Nombre: " + l.getNombre() + "\nCategoria: " + l.getGenero() + "\nAutor: " + l.getAutor() + "\nAño:" + l.getYear());
                                dialogBuilder.setCancelable(true).setTitle("Libro encontrado");
                                image.setPadding(200, 20, 200, 20);
                                dialogBuilder.setView(image);
                                dialogBuilder.create().show();
                            } else if (nombre.getText().toString().equals("Crepusculo")) {
                                image.setImageResource(R.drawable.crepusculo);
                                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
                                dialogBuilder.setMessage("Nombre: " + l.getNombre() + "\nCategoria: " + l.getGenero() + "\nAutor: " + l.getAutor() + "\nAño:" + l.getYear());
                                dialogBuilder.setCancelable(true).setTitle("Libro encontrado");
                                image.setPadding(200, 20, 200, 20);
                                dialogBuilder.setView(image);
                                dialogBuilder.create().show();
                            } else if (nombre.getText().toString().equals("Guerra mundial z")) {
                                image.setImageResource(R.drawable.guerra);
                                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
                                dialogBuilder.setMessage("Nombre: " + l.getNombre() + "\nCategoria: " + l.getGenero() + "\nAutor: " + l.getAutor() + "\nAño:" + l.getYear());
                                dialogBuilder.setCancelable(true).setTitle("Libro encontrado");
                                image.setPadding(200, 20, 200, 20);
                                dialogBuilder.setView(image);
                                dialogBuilder.create().show();
                            }
                        }
                        nombre.setText("");
                    }

                    return true;
                    default:
                        return super.onKeyUp(keyCode, event);
                }
        }

    public void descripcionNarnia(View v){
        ArrayList<Libro> l= cargarDatosNombre("Narnia");
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setMessage("Nombre: "+l.get(0).getNombre()+"\nCategoria: "+l.get(0).getGenero()+"\nAutor: "+l.get(0).getAutor()+"\nAño:"+l.get(0).getYear());
        dialogBuilder.setCancelable(true).setTitle("Descripcion");
        dialogBuilder.create().show();
    }
    public void descripcionGuerra(View v){
        ArrayList<Libro> l= cargarDatosNombre("Guerra mundial z");
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setMessage("Nombre: "+l.get(0).getNombre()+"\nCategoria: "+l.get(0).getGenero()+"\nAutor: "+l.get(0).getAutor()+"\nAño:"+l.get(0).getYear());
        dialogBuilder.setCancelable(true).setTitle("Descripcion");
        dialogBuilder.create().show();
    }
    public void descripcionCrepusculo(View v){
        ArrayList<Libro> l= cargarDatosNombre("Crepusculo");
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setMessage("Nombre: "+l.get(0).getNombre()+"\nCategoria: "+l.get(0).getGenero()+"\nAutor: "+l.get(0).getAutor()+"\nAño:"+l.get(0).getYear());
        dialogBuilder.setCancelable(true).setTitle("Descripcion");
        dialogBuilder.create().show();
    }

}

