package me.zdziszkee.openx.util;

public class CoordinatesUtil {
    public static double distance(double lat1,
                                  double lat2, double lon1,
                                  double lon2)
    {
        

        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        
        double dLon = lon2 - lon1;
        double dLat = lat2 - lat1;
        double a = Math.pow(Math.sin(dLat / 2), 2)
                   + Math.cos(lat1) * Math.cos(lat2)
                     * Math.pow(Math.sin(dLon / 2),2);
        
        double c = 2 * Math.asin(Math.sqrt(a));
        

        double r = 6371;
        
        return(c * r);
    }
    
}
