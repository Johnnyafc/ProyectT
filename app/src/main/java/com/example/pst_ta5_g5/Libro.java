package com.example.pst_ta5_g5;

public class Libro {
    private String nombre;
    private String genero;
    private String autor;
    private String year;
    private String codigo;
    private String id;
    private String idLabel;
    private String iditText;
    public Libro(String nombre, String genero, String autor,String year,String id, String idLabel,String iditText){
     this.codigo=
             this.nombre= nombre;
     this.genero=genero;
     this.autor= autor;
     this.year=year;
     this.id=id;
     this.idLabel=idLabel;
     this.iditText=iditText;
    }
    public String getId(){
        return id;
    }
    public String getIdLabel(){
        return  idLabel;
    }
    public String getIditText(){
        return iditText;
    }
    public String getYear(){
        return year;
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
