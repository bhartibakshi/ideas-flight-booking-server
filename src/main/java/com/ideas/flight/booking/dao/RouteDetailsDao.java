package com.ideas.flight.booking.dao;

import java.util.ArrayList;
import java.util.List;

import com.ideas.flight.booking.data.AirTerminal;
import com.ideas.flight.booking.data.FlightDetails;
import com.ideas.flight.booking.data.Route;


/**
 * The Class RouteDetailsDao to query and persist Route related data in the DB
 * 
 * @author bharti.bakshi
 */
public class RouteDetailsDao implements GenericDao {
    
    /**
     * Gets the air terminal details.
     *
     * @param terminalCode the terminal code
     * @return the air terminal details
     */
    public AirTerminal getAirTerminalDetails(String terminalCode) {
        return new AirTerminal();
    }
    
    
    /**
     * Gets the flights by route id provided.
     *
     * @param routeIdList the route id
     * @return the flights by route
     */
    public List<FlightDetails> getFlightsByRoute( String routeId ) {
        return new ArrayList<FlightDetails>();
    }


    /**
     * Gets the flight by flight id provided.
     *
     * @param flightId the flight id
     * @return the flight by flight id
     */
    public FlightDetails getFlightByFlightId( String flightId ) {
        return new FlightDetails();
    }


    /**
     * Gets the routes for the provided source and destination terminals.
     *
     * @param sourceTerminalCode the source terminal code
     * @param destTerminalCode the destination terminal code
     * @return the routes
     */
    public List<Route> getRoutes( String sourceTerminalCode, String destTerminalCode ) {
        return new ArrayList<Route>();
    }


    /**
     * Gets the routes passing through a particular terminal.
     *
     * @param terminalCode the terminal code
     * @return the routes
     */
    public List<Route> getRoutes( String terminalCode ) {
        return new ArrayList<Route>();
    }

}
