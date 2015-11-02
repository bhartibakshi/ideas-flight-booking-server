/*
 * 
 */
package com.ideas.flight.booking.request.processor;

import java.math.BigDecimal;
import java.util.List;
import java.util.TimeZone;

import com.ideas.flight.booking.constants.FlightConstants.Layout;
import com.ideas.flight.booking.constants.FlightConstants.SeatClass;
import com.ideas.flight.booking.constants.FlightConstants.SeatType;
import com.ideas.flight.booking.data.AirTerminal;
import com.ideas.flight.booking.data.AircraftCarrier;
import com.ideas.flight.booking.data.AircraftCarrier.Seat;
import com.ideas.flight.booking.data.AirlineCompany;
import com.ideas.flight.booking.data.FlightDetails;
import com.ideas.flight.booking.data.FlightPlan;
import com.ideas.flight.booking.data.PriceSheet;
import com.ideas.flight.booking.data.Route;


/**
 * The Class RoutePlanRequestProcessorTestHelper. This class provides helper utility to the actual test class for object
 * creation etc.
 * 
 * @author bharti.bakshi
 */
public class RoutePlanRequestProcessorTestHelper {
    
    /**
     * Constructs the AircraftCarrier object with parameters provided.
     *
     * @param manufacturer the manufacturer
     * @param modelId the model id
     * @param seatingPlan the seating plan
     * @param seatList the seat list
     * @return the aircraft carrier
     */
    public static AircraftCarrier getAircraftCarrier( String manufacturer, String modelId, Layout seatingPlan,
                                               List<Seat> seatList ) {
        AircraftCarrier aircraftCarrier = new AircraftCarrier();
        aircraftCarrier.setModelId( modelId );
        aircraftCarrier.setManufacturer( manufacturer );
        aircraftCarrier.setSeatingPlan( seatingPlan );
        aircraftCarrier.setSeatList( seatList );
        return aircraftCarrier;
    }


    /**
     * Constructs the AirlineComapny object with parameters provided.
     *
     * @param companyId the company id
     * @param companyName the company name
     * @param registeredAddress the registered address
     * @param registeredCity the registered city
     * @param registeredState the registered state
     * @param registeredPostalCode the registered postal code
     * @param registeredCountry the registered country
     * @param contactHelpLineNumber the contact help line number
     * @return the airline company
     */
    public static AirlineCompany getAirlineCompany( String companyId, String companyName, String registeredAddress,
                                             String registeredCity, String registeredState,
                                             String registeredPostalCode, String registeredCountry,
                              String contactHelpLineNumber ) {
        AirlineCompany airlineCompany = new AirlineCompany();
        airlineCompany.setCompanyId( companyId );
        airlineCompany.setCompanyName( companyName );
        airlineCompany.setContactHelpLineNumber( contactHelpLineNumber );
        airlineCompany.setRegisteredAddress( registeredAddress );
        airlineCompany.setRegisteredCity( registeredCity );
        airlineCompany.setRegisteredCountry( registeredCountry );
        airlineCompany.setRegisteredPostalCode( registeredPostalCode );
        airlineCompany.setRegisteredState( registeredState );
        return airlineCompany;
    }


    /**
     * Constructs the AirTerminal object with parameters provided.
     *
     * @param terminalCode the terminal code
     * @param terminalName the terminal name
     * @param isIAFStrip the is IAF strip
     * @param isCommercial the is commercial
     * @param isOperational the is operational
     * @param address the address
     * @param city the city
     * @param postalCode the postal code
     * @param country the country
     * @param contactHelpLineNumber the contact help line number
     * @param localTimeZone the local time zone
     * @param latitude the latitude
     * @param longitude the longitude
     * @param isInternational the is international
     * @return the air terminal
     */
    public static AirTerminal getAirTerminal( String terminalCode, String terminalName, boolean isIAFStrip,
                                              boolean isCommercial,
                                       boolean isOperational, String address, String city, String postalCode,
                                       String country, String contactHelpLineNumber, TimeZone localTimeZone,
                                       double latitude, double longitude, boolean isInternational ) {
        AirTerminal airTerminal = new AirTerminal();
        airTerminal.setTerminalCode( terminalCode );
        airTerminal.setTerminalName( terminalName );
        airTerminal.setIAFStrip( isIAFStrip );
        airTerminal.setOperational( isOperational );
        airTerminal.setCommercial( isCommercial );
        airTerminal.setAddress( address );
        airTerminal.setCity( city );
        airTerminal.setCountry( country );
        airTerminal.setContactHelpLineNumber( contactHelpLineNumber );
        airTerminal.setPostalCode( postalCode );
        airTerminal.setLatitude( latitude );
        airTerminal.setLongitude( longitude );
        airTerminal.setLocalTimeZone( localTimeZone );
        airTerminal.setInternational( isInternational );
        return airTerminal;
    }
    

    /**
     * Constructs the FlightDetails object with parameters provided.
     *
     * @param flightId the flight id
     * @param routeId the route id
     * @param carrierDetails the carrier details
     * @param airlineCompany the airline company
     * @param flightPlanList the flight plan list
     * @param sourceTerminal the source terminal
     * @param destinationTerminal the destination terminal
     * @return the flight details
     */
    public static FlightDetails getFlightDetails( String flightId, String routeId, AircraftCarrier carrierDetails,
                                           AirlineCompany airlineCompany, List<FlightPlan> flightPlanList,
                                           String sourceTerminal, String destinationTerminal ) {
        FlightDetails newFlightDetails = new FlightDetails();
        newFlightDetails.setFlightId( flightId );
        newFlightDetails.setRouteId( routeId );
        newFlightDetails.setAirlineCompany( airlineCompany );
        newFlightDetails.setCarrierDetails( carrierDetails );
        newFlightDetails.setDestinationTerminal( destinationTerminal );
        newFlightDetails.setSourceTerminal( sourceTerminal );
        newFlightDetails.setFlightPlanList( flightPlanList );
        return newFlightDetails;
    }


    /**
     * Constructs the FlightPlan object with parameters provided.
     *
     * @param flightId the flight id
     * @param fromTerminalCode the from terminal code
     * @param toTerminalCode the to terminal code
     * @param departureTimeLocal the departure time local
     * @param departureTimeUTC the departure time UTC
     * @param arrivalTimeLocal the arrival time local
     * @param arrivalTimeUTC the arrival time UTC
     * @return the flight plan
     */
    public static FlightPlan getFlightPlan( String flightId, String fromTerminalCode, String toTerminalCode,
                                            String departureTimeLocal, String departureTimeUTC,
                                            String arrivalTimeLocal, String arrivalTimeUTC ) {
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


    /**
     * Constructs the PriceSheet object with parameters provided.
     *
     * @param flightId the flight id
     * @param seatClass the seat class
     * @param seatType the seat type
     * @param costPerKm the cost per KM
     * @return the price sheet
     */
    public static PriceSheet getPriceSheet( String flightId, SeatClass seatClass, SeatType seatType,
                                            BigDecimal costPerKm ) {
        PriceSheet priceSheet = new PriceSheet();
        priceSheet.setFlightId( flightId );
        priceSheet.setSeatClass( seatClass );
        priceSheet.setSeatType( seatType );
        priceSheet.setCostPerKm( costPerKm );
        return priceSheet;
    }


    /**
     * Constructs the Route object with parameters provided.
     *
     * @param routeId the route id
     * @param terminalCodeList the terminal code list
     * @return the route
     */
    public static Route getRoute( String routeId, List<String> terminalCodeList ) {
        Route route = new Route();
        route.setRouteId( routeId );
        route.setTerminalCodeList( terminalCodeList );
        return route;
    }

}
