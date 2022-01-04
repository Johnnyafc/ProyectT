package com.example.pst_ta5_g5;

public class Ubicacion {
    private double latitud;
    private double longitud;
    private double altitud;
    public static final int R = 6371; ;
    public Ubicacion(double latitud, double longitud, double altitud){
        this.latitud=latitud;
        this.longitud=longitud;
        this.altitud=altitud;
    }
    public static double calcularDistancia(Ubicacion ub1, Ubicacion ub2) {

        // Radius of the earth

        double latDistance = Math.toRadians(ub2.getLatitud() - ub1.getLatitud());
        double lonDistance = Math.toRadians(ub2.getLongitud() - ub1.getLongitud());
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(ub1.getLatitud())) * Math.cos(Math.toRadians(ub2.getLatitud()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = ub1.getAltitud() - ub2.getAltitud();

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }
    public double getLatitud(){
        return  latitud;
    }
    public double getLongitud(){
        return longitud;
    }
    public double getAltitud(){
        return altitud;
    }
}
  /*  public static double distance(double lat1, double lat2, double lon1,
                                  double lon2, double el1, double el2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;*/