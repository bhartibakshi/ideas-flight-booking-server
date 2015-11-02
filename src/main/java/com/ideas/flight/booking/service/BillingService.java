package com.ideas.flight.booking.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ideas.flight.booking.dao.BillingDao;
import com.ideas.flight.booking.dao.RouteDetailsDao;
import com.ideas.flight.booking.data.AirTerminal;
import com.ideas.flight.booking.data.FlightDetails;
import com.ideas.flight.booking.util.DistanceCalculator;


/**
 * The Class BillingService to provide business logic for Billing and Cost related modules.
 * 
 * @author bharti.bakshi
 */
public class BillingService implements BusinessService {

    RouteDetailsDao routeDetailsDao;

    BillingDao billingDao;


    /**
     * Gets the cost for a flight based on the distance flow, class preferred and the seat preferred.
     *
     * @param flightDetailList the flight detail list
     * @param seatPreference the seat preference
     * @param classPreference the class preference
     * @return the flight cost
     */
    public Map<String, BigDecimal> getFlightCost( List<FlightDetails> flightDetailList, String seatPreference, String classPreference ) {
        Map<String, BigDecimal> costMap = new HashMap<String, BigDecimal>();
        for(FlightDetails flightDetails : flightDetailList){
            AirTerminal sourceTerminal = routeDetailsDao.getAirTerminalDetails( flightDetails.getSourceTerminal() );
            AirTerminal destTerminal = routeDetailsDao.getAirTerminalDetails( flightDetails.getDestinationTerminal() );
            double distance = DistanceCalculator.calculateDistance( sourceTerminal.getLatitude(),
                                                                    sourceTerminal.getLongitude(),
                                                                    destTerminal.getLatitude(),
                                                                    destTerminal.getLongitude(), "KM" );
            BigDecimal cost = billingDao.getCost( flightDetails.getFlightId(), seatPreference, classPreference );
            BigDecimal totalPrice = new BigDecimal( Double.toString( distance ) ).multiply( cost );
            totalPrice = totalPrice.setScale( 2, RoundingMode.CEILING );
            costMap.put( flightDetails.getFlightId(), totalPrice );
        }
        return costMap;
        
        
    }


    public void setRouteDetailsDao( RouteDetailsDao routeDetailsDao ) {
        this.routeDetailsDao = routeDetailsDao;
    }


    public void setBillingDao( BillingDao billingDao ) {
        this.billingDao = billingDao;
    }

}
