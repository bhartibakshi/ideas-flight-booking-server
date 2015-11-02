package com.ideas.flight.booking.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * The Class FlightDetails to store data related to a particular flight.
 * 
 * @author bharti.bakshi
 */
public class FlightDetails implements Serializable {

    private static final long serialVersionUID = -2145686224765342825L;

    private String flightId;

    private String routeId;

    private AircraftCarrier carrierDetails;

    private AirlineCompany airlineCompany;

    private List<FlightPlan> flightPlanList;

    private String sourceTerminal;

    private String destinationTerminal;


    public String getFlightId() {
        return flightId;
    }


    public void setFlightId( String flightId ) {
        this.flightId = flightId;
    }


    public String getRouteId() {
        return routeId;
    }


    public void setRouteId( String routeId ) {
        this.routeId = routeId;
    }


    public AircraftCarrier getCarrierDetails() {
        return carrierDetails;
    }


    public void setCarrierDetails( AircraftCarrier carrierDetails ) {
        this.carrierDetails = carrierDetails;
    }


    public AirlineCompany getAirlineCompany() {
        return airlineCompany;
    }


    public void setAirlineCompany( AirlineCompany airlineCompany ) {
        this.airlineCompany = airlineCompany;
    }


    public List<FlightPlan> getFlightPlanList() {
        return flightPlanList;
    }


    public void setFlightPlanList( List<FlightPlan> flightPlanList ) {
        this.flightPlanList = flightPlanList;
    }


    public String getSourceTerminal() {
        return sourceTerminal;
    }


    public void setSourceTerminal( String sourceTerminal ) {
        this.sourceTerminal = sourceTerminal;
    }


    public String getDestinationTerminal() {
        return destinationTerminal;
    }


    public void setDestinationTerminal( String destinationTerminal ) {
        this.destinationTerminal = destinationTerminal;
    }


    @Override
    public FlightDetails clone() {
        FlightDetails newFlightDetails = new FlightDetails();
        newFlightDetails.setFlightId( flightId );
        newFlightDetails.setRouteId( routeId );
        newFlightDetails.setAirlineCompany( airlineCompany.clone() );
        newFlightDetails.setCarrierDetails( carrierDetails.clone() );
        newFlightDetails.setDestinationTerminal( destinationTerminal );
        newFlightDetails.setSourceTerminal( sourceTerminal );
        List<FlightPlan> flightPlanList = new ArrayList<FlightPlan>();
        for ( FlightPlan flightPlan : this.flightPlanList ) {
            flightPlanList.add( flightPlan.clone() );
        }
        newFlightDetails.setFlightPlanList( flightPlanList );
        return newFlightDetails;
    }

}
