package com.example.pst_ta5_g5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import org.xmlpull.v1.XmlPullParser;

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
        narnia=findViewById(R.id.narnia);
        guerra=findViewById(R.id.guerra);
        imagenes= new ArrayList<>();
        imagenes.add(crepusculo);
        imagenes.add(narnia);
        imagenes.add(guerra);

        this.setTitle("BUSQUEDA GENERAL");
    }
    public  void cerrar(View v){
        Intent i= new Intent(getApplicationContext(),Login.class);
        startActivity(i);
    }
    public void crearArchivo(){
        try {
            OutputStreamWriter fout = new OutputStreamWriter(openFileOutput("Libros.txt", Context.MODE_PRIVATE));
            fout.write("crepusculo,ficcion,Clive Staples Lewis,2008,crepusculo,txtCre,txtDesCre\n" +
                    "narnia,ficcion,george raymond richard martin,2011,narnia,txtNarnia,txtDescrNar\n" +
                    "guerra mundial z,terror,Maximillian Michael Brooks,2005,guerra,txtGuerra,txtDesGuerra");
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
                System.out.println(linea);
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
                System.out.println(linea);
                String p[] = linea.split(",");
                if (p[1].trim().equals(genero)) {
                    li.add(new Libro(p[0], p[1], p[2], p[3],p[4],p[5],p[6]));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return li;
    }
//"Nombre: "+l.getNombre()+"\nCategoria: "+l.getGenero()+"\nAutor: "+l.getAutor()+"\nAño:"+l.getYear()
    public boolean onKeyUp(int keyCode, KeyEvent event){
        switch (keyCode) {
            case KeyEvent.KEYCODE_ENTER:
                ArrayList<Libro> libros= cargarDatosNombre(nombre.getText().toString());
                for(Libro l: libros){
                    if(l!=null){
                       ImageView image = new ImageView(this);
                       if(nombre.getText().toString().equals("narnia")){
                           image.setImageResource(R.drawable.narnia);
                       }
                       else if(nombre.getText().toString().equals("crepusculo")){
                           image.setImageResource(R.drawable.crepusculo);
                       }
                       else if(nombre.getText().toString().equals("guerra mundial z")){
                           image.setImageResource(R.drawable.guerra);
                       }

                        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
                        dialogBuilder.setMessage("Nombre: "+l.getNombre()+"\nCategoria: "+l.getGenero()+"\nAutor: "+l.getAutor()+"\nAño:"+l.getYear());
                        dialogBuilder.setCancelable(true).setTitle("Libro encontrado");
                        image.setPadding(200,20,200,20);
                        dialogBuilder.setView(image);
                        dialogBuilder.create().show();
                    }



                }

                return true;
            default:
                return super.onKeyUp(keyCode, event);
        }
    }

}

