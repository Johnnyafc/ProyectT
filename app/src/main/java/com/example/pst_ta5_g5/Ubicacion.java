package com.example.pst_ta5_g5;

public class Ubicacion {
    private double latitud;
    private double longitud;
    public static final double radioTierra=36378.137;
    public Ubicacion(double latitud, double longitud){
        this.latitud=latitud;
        this.longitud=longitud;
    }
    public static double calcularDistancia(Ubicacion origen, Ubicacion destino){
        double dlat=origen.latitud-destino.latitud;
        double dlong=origen.longitud- destino.longitud;
        double a=Math.pow(Math.sin(Math.toRadians(dlat)/2),2)+
                Math.cos(Math.toRadians(origen.latitud))*
                Math.cos(Math.toRadians(destino.latitud))*
                Math.pow(Math.sin(Math.toRadians(dlong)/2),2);
                double c=2*Math.atan2(Math.sqrt(a),Math.sqrt(1-a));
        double distancia=  (radioTierra*c);
        return distancia*1000;
    }
    public double getLatitud(){
        return  latitud;
    }
    public double getLongitud(){
        return longitud;
    }
}
