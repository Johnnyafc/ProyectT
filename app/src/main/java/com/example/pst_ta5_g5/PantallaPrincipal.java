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

   /* public static ArrayList<Libro> cargarDatosGenero(String genero) {
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
    }*/
//"Nombre: "+l.getNombre()+"\nCategoria: "+l.getGenero()+"\nAutor: "+l.getAutor()+"\nAño:"+l.getYear()


}

