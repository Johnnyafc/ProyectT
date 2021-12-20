package com.example.pst_ta5_g5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataLibro {
    private static String FILE_PATH = Constants.RESOURCE_FOLDER+"Libros.txt";
    public static ArrayList<Libro> cargarDatosGenero(String genero){
        ArrayList<Libro> li = new ArrayList<>();
        try(BufferedReader bf = new BufferedReader(new FileReader(FILE_PATH))) {
            String linea;
            while((linea=bf.readLine())!=null){
                System.out.println(linea);
                String p[]=linea.split(",");
                if(p[1].trim().equals(genero)){
                    li.add(new Libro(p[0],p[1],p[2],p[3]));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return li;
    }
    public static ArrayList<Libro> cargarDatosNombre(String nombre){
        ArrayList<Libro> li = new ArrayList<>();
        try(BufferedReader bf = new BufferedReader(new FileReader(FILE_PATH))) {
            String linea;
            while((linea=bf.readLine())!=null){
                System.out.println(linea);
                String p[]=linea.split(",");
                if(p[0].trim().equals(nombre)){
                    li.add(new Libro(p[0],p[1],p[2],p[3]));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return li;
    }
}
