package com.ideas.flight.booking.dao;

import java.math.BigDecimal;


/**
 * The Class BillingDao to query and persist Billing and Cost related data in the DB
 * 
 * @author bharti.bakshi
 */
public class BillingDao implements GenericDao {

    /**
     * Gets the cost for a particular seat and class for a particular fight.
     *
     * @param flightId the flight id
     * @param seatType the seat type
     * @param seatClass the seat class
     * @return the cost
     */
    public BigDecimal getCost( String flightId, String seatType, String seatClass ) {
        return new BigDecimal( 1 );
    }

}
