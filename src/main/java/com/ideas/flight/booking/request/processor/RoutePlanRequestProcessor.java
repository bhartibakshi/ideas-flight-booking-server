/*
 * 
 */
package com.ideas.flight.booking.request.processor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ideas.flight.booking.data.AirTerminal;
import com.ideas.flight.booking.data.FlightDetails;
import com.ideas.flight.booking.data.FlightPlan;
import com.ideas.flight.booking.data.Itinerary;
import com.ideas.flight.booking.data.Message;
import com.ideas.flight.booking.service.BillingService;
import com.ideas.flight.booking.service.RoutePlannerService;
import com.ideas.flight.booking.service.factory.ServiceFactory;
import com.ideas.flight.booking.service.factory.ServiceFactory.ServiceName;

/**
 * The Class RoutePlanRequestProcessor. To process requests for Route Plans received by the system
 * 
 * @author bharti.bakshi
 */
public class RoutePlanRequestProcessor extends RequestProcessor {

    ServiceFactory serviceFactory;


    @Override
    public void process( Object message ) throws Exception {

        Message request = ( (Message) message );
        RoutePlannerService routePlanService = (RoutePlannerService) serviceFactory.getService( ServiceName.ROUTEPLAN );
        BillingService billingService = (BillingService) serviceFactory.getService( ServiceName.BILLING );
        AirTerminal sourceTerminal = routePlanService.getAirTerminal( request.getSourceTerminalCode() );
        AirTerminal destTerminal = routePlanService.getAirTerminal( request.getDestTerminalCode() );
        if ( validate( sourceTerminal, destTerminal ) ) {
            List<FlightDetails> flightDetailList = routePlanService.getShortestRouteByDistance( request.getSourceTerminalCode(),
                                                                                                request.getDestTerminalCode() );
            Map<String, BigDecimal> costMap = billingService.getFlightCost( flightDetailList,
                                                                            request.getSeatPreference(),
                                                                            request.getClassPreference() );
            if ( !flightDetailList.isEmpty() ) {
                Itinerary itinerary = new Itinerary();
                itinerary.setSourceAirTerminal( sourceTerminal );
                itinerary.setDestAirTerminal( destTerminal );
                itinerary.setFlightDetails( flightDetailList );
                itinerary.setSeatPreference( request.getSeatPreference() );
                itinerary.setClassPreference( request.getClassPreference() );
                itinerary.setCostMap( costMap );
                printResults( itinerary );
            }
            else {
                System.out.println( "No route exists between the Source and Destination Terminal" );
            }
        }

    }


    /**
     * Validate. To validate the request on various parameters for correctness.
     *
     * @param sourceTerminal the source terminal
     * @param destTerminal the destination terminal
     * @return true, if successful
     */
    private boolean validate( AirTerminal sourceTerminal, AirTerminal destTerminal ) {
        if ( sourceTerminal.isCommercial() && sourceTerminal.isOperational() && destTerminal.isCommercial()
            && destTerminal.isOperational() ) {
            return true;
        }
        else if ( sourceTerminal.isIAFStrip() ) {
            if ( !( sourceTerminal.isCommercial() && sourceTerminal.isOperational() ) ) {
                System.out.println( "The Source Terminal is an IAF strip which is either not commercial or operational" );

            }
        }
        else if ( destTerminal.isIAFStrip() ) {
            if ( !( destTerminal.isCommercial() && destTerminal.isOperational() ) ) {
                System.out.println( "The Destination Terminal is an IAF strip which is either not commercial or operational" );
            }

        }
        return false;
    }


    /**
     * Prints the results.
     *
     * @param itinerary the itinerary
     */
    private void printResults( Itinerary itinerary ) {
        System.out.println( "Shortest Route Results" );
        System.out.println( itinerary.getSourceAirTerminal().getTerminalName() + ","
            + itinerary.getSourceAirTerminal().getCity() + "(" + itinerary.getSourceAirTerminal().getTerminalCode()
            + ")" + " ---> " + itinerary.getDestAirTerminal().getTerminalName() + ","
            + itinerary.getDestAirTerminal().getCity() + "(" + itinerary.getDestAirTerminal().getTerminalCode() + ")" );
        System.out.println( "\n"
            + "===========================================================================================================================" );
        System.out.println( "==========================================================================================================================="
            + "\n" );
        for ( FlightDetails flightDetails : itinerary.getFlightDetails() ) {
            System.out.println( flightDetails.getAirlineCompany().getCompanyName() + " - "
                + flightDetails.getCarrierDetails().getModelId() );
            System.out.println( "Flight ID :" + flightDetails.getFlightId() + " --- SEAT :"
                + itinerary.getClassPreference() + "(" + itinerary.getSeatPreference() + ")" + "   ::   "
                + flightDetails.getSourceTerminal() + " ------------> " + flightDetails.getDestinationTerminal()
                + "       Price :" + itinerary.getCostMap().get( flightDetails.getFlightId() ) + " INR" );
            System.out.println( "__________________________________________________________________________________________________________________________" );
            System.out.println( "Schedule ::" );
            for ( FlightPlan flightPlan : flightDetails.getFlightPlanList() ) {
                System.out.println( flightPlan.getFromTerminalCode() + "  ---------> " + flightPlan.getToTerminalCode() );
                System.out.println( flightPlan.getDepartureTimeLocal() + "(Local)" + "             "
                    + flightPlan.getArrivalTimeLocal() + "(Local)" );
                System.out.println( flightPlan.getDepartureTimeUTC() + "(UTC)" + "               "
                    + flightPlan.getArrivalTimeUTC() + "(UTC)" );
                System.out.println( "__________________________________________________________________________________________________________________________" );
            }
        }
    }


    public void setServiceFactory( ServiceFactory serviceFactory ) {
        this.serviceFactory = serviceFactory;
    }

}
