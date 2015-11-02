package com.ideas.flight.booking.data;

import java.io.Serializable;


/**
 * The Class FlightPlan to store the details for a Flight Plan.
 * 
 * @author bharti.bakshi
 */
public class FlightPlan implements Serializable {

    private static final long serialVersionUID = -6747107242080788741L;

    private String flightId;

    private String fromTerminalCode;

    private String toTerminalCode;

    private String departureTimeLocal;

    private String departureTimeUTC;

    private String arrivalTimeLocal;

    private String arrivalTimeUTC;


    public String getFlightId() {
        return flightId;
    }


    public void setFlightId( String flightId ) {
        this.flightId = flightId;
    }


    public String getFromTerminalCode() {
        return fromTerminalCode;
    }


    public void setFromTerminalCode( String fromTerminalCode ) {
        this.fromTerminalCode = fromTerminalCode;
    }


    public String getToTerminalCode() {
        return toTerminalCode;
    }


    public void setToTerminalCode( String toTerminalCode ) {
        this.toTerminalCode = toTerminalCode;
    }


    public String getDepartureTimeLocal() {
        return departureTimeLocal;
    }


    public void setDepartureTimeLocal( String departureTimeLocal ) {
        this.departureTimeLocal = departureTimeLocal;
    }


    public String getDepartureTimeUTC() {
        return departureTimeUTC;
    }


    public void setDepartureTimeUTC( String departureTimeUTC ) {
        this.departureTimeUTC = departureTimeUTC;
    }


    public String getArrivalTimeLocal() {
        return arrivalTimeLocal;
    }


    public void setArrivalTimeLocal( String arrivalTimeLocal ) {
        this.arrivalTimeLocal = arrivalTimeLocal;
    }


    public String getArrivalTimeUTC() {
        return arrivalTimeUTC;
    }


    public void setArrivalTimeUTC( String arrivalTimeUTC ) {
        this.arrivalTimeUTC = arrivalTimeUTC;
    }


    public FlightPlan clone() {
        FlightPlan flightPlan = new FlightPlan();
        flightPlan.setFlightId( flightId );
        flightPlan.setFromTerminalCode( fromTerminalCode );
        flightPlan.setToTerminalCode( toTerminalCode );
        flightPlan.setDepartureTimeLocal( departureTimeLocal );
        flightPlan.setDepartureTimeUTC( departureTimeUTC );
        flightPlan.setArrivalTimeLocal( arrivalTimeLocal );
        flightPlan.setArrivalTimeUTC( arrivalTimeUTC );
        return flightPlan;
    }

}
