package com.ideas.flight.booking.util;


/**
 * The Class DistanceCalculator. A utility class to provide the utility of calculating distances between two
 * AirTerminals based on their geographical coordinates
 * 
 * @author bharti.bakshi
 */
public class DistanceCalculator {

    /**
     * Calculate distance. Calculates distances between two AirTerminals based on their geographical coordinates
     *
     * @param destLat the destination latitude
     * @param destLon the destination longitude
     * @param sourceLat the source latitude
     * @param sourceLon the source longitude
     * @param unit the unit
     * @return the double
     */
    public static double calculateDistance( double destLat, double destLon, double sourceLat, double sourceLon,
                                            String unit ) {
        double theta = destLon - sourceLon;
        double dist = Math.sin( deg2rad( destLat ) ) * Math.sin( deg2rad( sourceLat ) ) + Math.cos( deg2rad( destLat ) )
            * Math.cos( deg2rad( sourceLat ) ) * Math.cos( deg2rad( theta ) );
        dist = Math.acos( dist );
        dist = rad2deg( dist );
        dist = dist * 60 * 1.1515;
        if ( unit == "KM" ) {
            dist = dist * 1.609344;
        }
        return ( dist );
    }


    private static double deg2rad( double deg ) {
        return ( deg * Math.PI / 180.0 );
    }


    private static double rad2deg( double rad ) {
        return ( rad * 180 / Math.PI );
    }

}
