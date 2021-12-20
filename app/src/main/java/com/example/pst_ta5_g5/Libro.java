package com.example.pst_ta5_g5;

import android.widget.ImageView;

public class Libro {
    private String nombre;
    private String genero;
    private String autor;
    private String year;
    private String codigo;
    private ImageView imagen;
    public Libro(String nombre, String genero, String autor,String year){
     this.codigo=
             this.nombre= nombre;
     this.genero=genero;
     this.autor= autor;
     this.year=year;
    }
    public String getNombre(){
        return  nombre;
    }
    public String getGenero(){
        return genero;
    }
    public String getAutor(){
        return autor;
    }
}
