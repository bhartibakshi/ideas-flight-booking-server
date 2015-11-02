package com.ideas.flight.booking.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * The Class Itinerary to store the Itinerary data prepared based on user input.
 * 
 * @author bharti.bakshi
 */
public class Itinerary implements Serializable {

    private static final long serialVersionUID = -3590447980494066485L;

    private AirTerminal sourceAirTerminal;

    private AirTerminal destAirTerminal;

    private List<FlightDetails> flightDetails;

    private String seatPreference;

    private String classPreference;

    private Map<String, BigDecimal> costMap;


    public AirTerminal getSourceAirTerminal() {
        return sourceAirTerminal;
    }


    public void setSourceAirTerminal( AirTerminal sourceAirTerminal ) {
        this.sourceAirTerminal = sourceAirTerminal;
    }


    public AirTerminal getDestAirTerminal() {
        return destAirTerminal;
    }


    public void setDestAirTerminal( AirTerminal destAirTerminal ) {
        this.destAirTerminal = destAirTerminal;
    }


    public List<FlightDetails> getFlightDetails() {
        return flightDetails;
    }


    public void setFlightDetails( List<FlightDetails> flightDetails ) {
        this.flightDetails = flightDetails;
    }


    public String getSeatPreference() {
        return seatPreference;
    }


    public void setSeatPreference( String seatPreference ) {
        this.seatPreference = seatPreference;
    }


    public String getClassPreference() {
        return classPreference;
    }


    public void setClassPreference( String classPreference ) {
        this.classPreference = classPreference;
    }


    public Map<String, BigDecimal> getCostMap() {
        return costMap;
    }


    public void setCostMap( Map<String, BigDecimal> costMap ) {
        this.costMap = costMap;
    }

}
