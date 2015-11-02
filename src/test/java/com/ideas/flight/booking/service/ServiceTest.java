package com.ideas.flight.booking.service;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

import com.ideas.flight.booking.constants.FlightConstants.Layout;
import com.ideas.flight.booking.constants.FlightConstants.SeatClass;
import com.ideas.flight.booking.constants.FlightConstants.SeatType;
import com.ideas.flight.booking.dao.BillingDao;
import com.ideas.flight.booking.dao.RouteDetailsDao;
import com.ideas.flight.booking.data.AirTerminal;
import com.ideas.flight.booking.data.AircraftCarrier;
import com.ideas.flight.booking.data.AircraftCarrier.Seat;
import com.ideas.flight.booking.data.AirlineCompany;
import com.ideas.flight.booking.data.FlightDetails;
import com.ideas.flight.booking.data.FlightPlan;
import com.ideas.flight.booking.data.Itinerary;
import com.ideas.flight.booking.data.Route;
import com.ideas.flight.booking.request.processor.RoutePlanRequestProcessorTestHelper;
import com.ideas.flight.booking.service.factory.ServiceFactory;
import com.ideas.flight.booking.service.factory.ServiceFactory.ServiceName;

/**
 * The Class ServiceTest used for testing the Business Logic of the service classes namely RoutePlannerService and
 * BillingService in this particular case
 * 
 * @author bharti.bakshi
 */
@RunWith( PowerMockRunner.class )
public class ServiceTest {

    RoutePlannerService routePlannerService;

    BillingService billingService;

    RouteDetailsDao routeDetailsDao;

    BillingDao billingDao;

    AirlineCompany company1;

    AirlineCompany company2;

    AirlineCompany company3;

    AircraftCarrier carrier1;

    AircraftCarrier carrier2;

    AirTerminal terminal1;

    AirTerminal terminal2;

    AirTerminal terminal3;

    AirTerminal terminal4;

    AirTerminal terminal5;

    AirTerminal terminal6;

    AirTerminal terminal7;

    AirTerminal terminal8;

    AirTerminal terminal9;

    Route route1;

    Route route2;

    Route route3;

    Route route4;

    Route route5;

    Route route6;

    FlightDetails flight1;

    FlightDetails flight2;

    FlightDetails flight3;

    FlightDetails flight4;

    FlightDetails flight5;

    FlightDetails flight6;

    FlightDetails flight7;

    FlightDetails flight8;


    @Before
    public void setUp() throws Exception {
        routePlannerService = (RoutePlannerService) ServiceFactory.getFactory().getService( ServiceName.ROUTEPLAN );
        routeDetailsDao = PowerMockito.mock( RouteDetailsDao.class );
        routePlannerService.setRouteDetailsDao( routeDetailsDao );

        billingService = (BillingService) ServiceFactory.getFactory().getService( ServiceName.BILLING );
        billingService.setRouteDetailsDao( routeDetailsDao );
        billingDao = PowerMockito.mock( BillingDao.class );
        billingService.setBillingDao( billingDao );

        company1 = RoutePlanRequestProcessorTestHelper.getAirlineCompany( "EMI", "Emirates", null, null, null, null,
                                                                          null, null );
        company2 = RoutePlanRequestProcessorTestHelper.getAirlineCompany( "LUF", "Lufthansa", null, null, null, null,
                                                                          null, null );
        company3 = RoutePlanRequestProcessorTestHelper.getAirlineCompany( "ALI", "British Airways", null, null, null,
                                                                          null, null, null );

        List<Seat> seatList = new ArrayList<Seat>();
        carrier1 = RoutePlanRequestProcessorTestHelper.getAircraftCarrier( null, "A100", Layout.THREE_FOUR_THREE,
                                                                           seatList );
        carrier2 = RoutePlanRequestProcessorTestHelper.getAircraftCarrier( null, "A101", Layout.TWO_FIVE_TWO, seatList );

        terminal1 = RoutePlanRequestProcessorTestHelper.getAirTerminal( "LCY", "Heathrow International Airport", false,
                                                                        true, true, null, "London", null, null, null,
                                                                        null, 51.504610, 0.049733, true );
        terminal2 = RoutePlanRequestProcessorTestHelper.getAirTerminal( "DEL", "Indira Gandhi International Airport",
                                                                        false, true, true, null, "Delhi", null, null,
                                                                        null, null, 28.556106, 77.100001, true );
        terminal3 = RoutePlanRequestProcessorTestHelper.getAirTerminal( "SIN", "Changi International Airport", false,
                                                                        true, true, null, "Singapore", null, null,
                                                                        null, null, 1.364152, 103.991831, true );
        terminal4 = RoutePlanRequestProcessorTestHelper.getAirTerminal( "DXB", "Dubai International Airport", false,
                                                                        true, true, null, "Dubai", null, null, null,
                                                                        null, 25.252961, 55.366091, true );
        terminal5 = RoutePlanRequestProcessorTestHelper.getAirTerminal( "HND", "Haneda International Airport", false,
                                                                        true, true, null, "Tokyo", null, null, null,
                                                                        null, 35.549428, 139.779763, true );
        terminal6 = RoutePlanRequestProcessorTestHelper.getAirTerminal( "SYD", "Sydney International Airport", false,
                                                                        true, true, null, "Sydney", null, null, null,
                                                                        null, -33.940208, 151.175137, true );
        terminal7 = RoutePlanRequestProcessorTestHelper.getAirTerminal( "BOM",
                                                                        "Chhatrapati Shivaji International Airport",
                                                                        false, true, true, null, "Mumbai", null, null,
                                                                        null, null, 19.089781, 72.869233, true );
        terminal8 = RoutePlanRequestProcessorTestHelper.getAirTerminal( "MAA", "Chennai International Airport", false,
                                                                        true, true, null, "Chennai", null, null, null,
                                                                        null, 12.993694, 80.171393, true );

        terminal9 = RoutePlanRequestProcessorTestHelper.getAirTerminal( "ORR", "Fake Airport", true, false, true, null,
                                                                        "Atlantis", null, null, null, null, 0.0, 0.0,
                                                                        true );

        List<String> terminalList = new ArrayList<String>();
        terminalList.add( "MAA" );
        terminalList.add( "BOM" );
        terminalList.add( "DXB" );
        terminalList.add( "LCY" );
        route1 = RoutePlanRequestProcessorTestHelper.getRoute( "R1", terminalList );

        terminalList = new ArrayList<String>();
        terminalList.add( "SIN" );
        terminalList.add( "BOM" );
        terminalList.add( "LCY" );
        route2 = RoutePlanRequestProcessorTestHelper.getRoute( "R2", terminalList );

        terminalList = new ArrayList<String>();
        terminalList.add( "LCY" );
        terminalList.add( "DEL" );
        terminalList.add( "HND" );
        route3 = RoutePlanRequestProcessorTestHelper.getRoute( "R3", terminalList );

        terminalList = new ArrayList<String>();
        terminalList.add( "HND" );
        terminalList.add( "SYD" );
        route4 = RoutePlanRequestProcessorTestHelper.getRoute( "R4", terminalList );

        terminalList = new ArrayList<String>();
        terminalList.add( "LCY" );
        terminalList.add( "SIN" );
        route5 = RoutePlanRequestProcessorTestHelper.getRoute( "R5", terminalList );

        terminalList = new ArrayList<String>();
        terminalList.add( "SIN" );
        terminalList.add( "SYD" );
        route6 = RoutePlanRequestProcessorTestHelper.getRoute( "R6", terminalList );

        List<FlightPlan> flightPlanList = new ArrayList<FlightPlan>();
        FlightPlan flightPlan1 = RoutePlanRequestProcessorTestHelper.getFlightPlan( "F1", "MAA", "BOM", "17:30",
                                                                                    "12:00", "19:30", "14:00" );
        FlightPlan flightPlan2 = RoutePlanRequestProcessorTestHelper.getFlightPlan( "F1", "BOM", "DXB", "21:30",
                                                                                    "16:00", "23:00", "19:00" );
        FlightPlan flightPlan3 = RoutePlanRequestProcessorTestHelper.getFlightPlan( "F1", "DXB", "LCY", "01:00",
                                                                                    "21:00", "02:00", "02:00" );
        flightPlanList.add( flightPlan1 );
        flightPlanList.add( flightPlan2 );
        flightPlanList.add( flightPlan3 );
        flight1 = RoutePlanRequestProcessorTestHelper.getFlightDetails( "F1", "R1", carrier1, company3, flightPlanList,
                                                                        "MAA", "LCY" );

        flightPlan1 = RoutePlanRequestProcessorTestHelper.getFlightPlan( "F2", "BOM", "DXB", "23:30", "18:00", "01:00",
                                                                         "21:00" );
        flightPlan2 = RoutePlanRequestProcessorTestHelper.getFlightPlan( "F2", "DXB", "LCY", "03:00", "23:00", "04:00",
                                                                         "04:00" );
        flightPlanList = new ArrayList<FlightPlan>();
        flightPlanList.add( flightPlan1 );
        flightPlanList.add( flightPlan2 );
        flight2 = RoutePlanRequestProcessorTestHelper.getFlightDetails( "F2", "R1", carrier2, company1, flightPlanList,
                                                                        "BOM", "LCY" );

        flightPlan1 = RoutePlanRequestProcessorTestHelper.getFlightPlan( "F3", "SIN", "LCY", "00:30", "04:30", "17:30",
                                                                         "17:30" );
        flightPlanList = new ArrayList<FlightPlan>();
        flightPlanList.add( flightPlan1 );
        flight3 = RoutePlanRequestProcessorTestHelper.getFlightDetails( "F3", "R2", carrier1, company3, flightPlanList,
                                                                        "SIN", "LCY" );

        flightPlan1 = RoutePlanRequestProcessorTestHelper.getFlightPlan( "F4", "LCY", "DEL", "00:00", "00:00", "13:30",
                                                                         "08:00" );
        flightPlan2 = RoutePlanRequestProcessorTestHelper.getFlightPlan( "F4", "DEL", "HND", "15:30", "10:00", "07:00",
                                                                         "22:00" );
        flightPlanList = new ArrayList<FlightPlan>();
        flightPlanList.add( flightPlan1 );
        flightPlanList.add( flightPlan2 );
        flight4 = RoutePlanRequestProcessorTestHelper.getFlightDetails( "F4", "R3", carrier1, company2, flightPlanList,
                                                                        "LCY", "HND" );

        flightPlan1 = RoutePlanRequestProcessorTestHelper.getFlightPlan( "F5", "LCY", "HND", "00:00", "00:00", "03:00",
                                                                         "18:00" );
        flightPlanList = new ArrayList<FlightPlan>();
        flightPlanList.add( flightPlan1 );
        flight5 = RoutePlanRequestProcessorTestHelper.getFlightDetails( "F5", "R3", carrier1, company2, flightPlanList,
                                                                        "LCY", "HND" );

        flightPlan1 = RoutePlanRequestProcessorTestHelper.getFlightPlan( "F6", "HND", "SYD", "22:00", "13:00", "09:00",
                                                                         "23:00" );
        flightPlanList = new ArrayList<FlightPlan>();
        flightPlanList.add( flightPlan1 );
        flight6 = RoutePlanRequestProcessorTestHelper.getFlightDetails( "F6", "R4", carrier1, company3, flightPlanList,
                                                                        "HND", "SYD" );

        flightPlan1 = RoutePlanRequestProcessorTestHelper.getFlightPlan( "F7", "LCY", "SIN", "00:00", "00:00", "21:00",
                                                                         "13:00" );
        flightPlanList = new ArrayList<FlightPlan>();
        flightPlanList.add( flightPlan1 );
        flight7 = RoutePlanRequestProcessorTestHelper.getFlightDetails( "F7", "R5", carrier1, company2, flightPlanList,
                                                                        "LCY", "SIN" );

        flightPlan1 = RoutePlanRequestProcessorTestHelper.getFlightPlan( "F8", "SIN", "SYD", "23:00", "15:00", "11:00",
                                                                         "01:00" );
        flightPlanList = new ArrayList<FlightPlan>();
        flightPlanList.add( flightPlan1 );
        flight8 = RoutePlanRequestProcessorTestHelper.getFlightDetails( "F8", "R6", carrier1, company3, flightPlanList,
                                                                        "SIN", "SYD" );

        when( routeDetailsDao.getAirTerminalDetails( "LCY" ) ).thenReturn( terminal1 );
        when( routeDetailsDao.getAirTerminalDetails( "DEL" ) ).thenReturn( terminal2 );
        when( routeDetailsDao.getAirTerminalDetails( "SIN" ) ).thenReturn( terminal3 );
        when( routeDetailsDao.getAirTerminalDetails( "DXB" ) ).thenReturn( terminal4 );
        when( routeDetailsDao.getAirTerminalDetails( "HND" ) ).thenReturn( terminal5 );
        when( routeDetailsDao.getAirTerminalDetails( "SYD" ) ).thenReturn( terminal6 );
        when( routeDetailsDao.getAirTerminalDetails( "BOM" ) ).thenReturn( terminal7 );
        when( routeDetailsDao.getAirTerminalDetails( "MAA" ) ).thenReturn( terminal8 );
        when( routeDetailsDao.getAirTerminalDetails( "ORR" ) ).thenReturn( terminal9 );
    }


    /**
     * Test direct route only.To test direct routes where the source and destination lie on the same route
     */
    @Test
    public void testDirectRouteOnly() {
        List<Route> routeDetailList = new ArrayList<Route>();
        routeDetailList.add( route1 );
        when( routeDetailsDao.getRoutes( "MAA", "DXB" ) ).thenReturn( routeDetailList );
        List<FlightDetails> flightDetailsList = new ArrayList<FlightDetails>();
        flightDetailsList.add( flight1 );
        when( routeDetailsDao.getFlightsByRoute( route1.getRouteId() ) ).thenReturn( flightDetailsList );
        List<FlightDetails> resultFlightDetailsList = routePlannerService.getShortestRouteByDistance( "MAA", "DXB" );

        when( billingDao.getCost( "F1", SeatType.AISLE.name(), SeatClass.BUSINESS.name() ) ).thenReturn( new BigDecimal(
                                                                                                                         "20" ) );

        Map<String, BigDecimal> costMap = billingService.getFlightCost( resultFlightDetailsList, SeatType.AISLE.name(),
                                                                        SeatClass.BUSINESS.name() );

        printItinerary( resultFlightDetailsList, costMap, SeatType.AISLE.name(), SeatClass.BUSINESS.name(), terminal8,
                        terminal4 );
    }


    /**
     * Test non stop vs direct.To test direct routes where the source and destination lie on the same route but there
     * are direct as well as non stop flights available
     */
    @Test
    public void testNonStopVsDirect() {
        List<Route> routeDetailList = new ArrayList<Route>();
        routeDetailList.add( route3 );
        when( routeDetailsDao.getRoutes( "LCY", "HND" ) ).thenReturn( routeDetailList );
        List<FlightDetails> flightDetailsList = new ArrayList<FlightDetails>();
        flightDetailsList.add( flight4 );
        flightDetailsList.add( flight5 );
        when( routeDetailsDao.getFlightsByRoute( route3.getRouteId() ) ).thenReturn( flightDetailsList );
        List<FlightDetails> resultFlightDetailsList = routePlannerService.getShortestRouteByDistance( "LCY", "HND" );

        when( billingDao.getCost( "F4", SeatType.WINDOW.name(), SeatClass.BUSINESS.name() ) ).thenReturn( new BigDecimal(
                                                                                                                          "30" ) );
        when( billingDao.getCost( "F5", SeatType.WINDOW.name(), SeatClass.BUSINESS.name() ) ).thenReturn( new BigDecimal(
                                                                                                                          "40" ) );

        Map<String, BigDecimal> costMap = billingService.getFlightCost( resultFlightDetailsList,
                                                                        SeatType.WINDOW.name(),
                                                                        SeatClass.BUSINESS.name() );

        printItinerary( resultFlightDetailsList, costMap, SeatType.WINDOW.name(), SeatClass.BUSINESS.name(), terminal1,
                        terminal5 );
    }


    /**
     * Test shortest connecting no direct route. To test connecting routes where the source and the destination do not
     * lie on the same route bu there are connecting routes available
     */
    @Test
    public void testShortestConnectingNoDirectRoute() {
        List<Route> routeDetailList = new ArrayList<Route>();
        routeDetailList.add( route1 );
        routeDetailList.add( route2 );
        routeDetailList.add( route3 );
        routeDetailList.add( route5 );
        when( routeDetailsDao.getRoutes( "LCY" ) ).thenReturn( routeDetailList );
        routeDetailList = new ArrayList<Route>();
        routeDetailList.add( route6 );
        when( routeDetailsDao.getRoutes( "SIN", "SYD" ) ).thenReturn( routeDetailList );
        routeDetailList = new ArrayList<Route>();
        routeDetailList.add( route4 );
        when( routeDetailsDao.getRoutes( "HND", "SYD" ) ).thenReturn( routeDetailList );
        List<FlightDetails> flightDetailsList = new ArrayList<FlightDetails>();
        flightDetailsList.add( flight7 );
        when( routeDetailsDao.getFlightsByRoute( route5.getRouteId() ) ).thenReturn( flightDetailsList );

        flightDetailsList = new ArrayList<FlightDetails>();
        flightDetailsList.add( flight8 );
        when( routeDetailsDao.getFlightsByRoute( route6.getRouteId() ) ).thenReturn( flightDetailsList );
        List<FlightDetails> resultFlightDetailsList = routePlannerService.getShortestRouteByDistance( "LCY", "SYD" );

        when( billingDao.getCost( "F7", SeatType.WINDOW.name(), SeatClass.ECONOMY.name() ) ).thenReturn( new BigDecimal(
                                                                                                                         "35" ) );
        when( billingDao.getCost( "F8", SeatType.WINDOW.name(), SeatClass.ECONOMY.name() ) ).thenReturn( new BigDecimal(
                                                                                                                         "45" ) );

        Map<String, BigDecimal> costMap = billingService.getFlightCost( resultFlightDetailsList,
                                                                        SeatType.WINDOW.name(),
                                                                        SeatClass.ECONOMY.name() );

        printItinerary( resultFlightDetailsList, costMap, SeatType.WINDOW.name(), SeatClass.ECONOMY.name(), terminal1,
                        terminal6 );
    }


    /**
     * Test no available route. To test the case where there is no route available from source to destination
     */
    @Test
    public void testNoAvailableRoute() {
        List<FlightDetails> resultFlightDetailsList = routePlannerService.getShortestRouteByDistance( "MAA", "HND" );
        when( billingDao.getCost( "F7", SeatType.WINDOW.name(), SeatClass.ECONOMY.name() ) ).thenReturn( new BigDecimal(
                                                                                                                         "35" ) );
        Map<String, BigDecimal> costMap = billingService.getFlightCost( resultFlightDetailsList,
                                                                        SeatType.WINDOW.name(),
                                                                        SeatClass.ECONOMY.name() );
        printItinerary( resultFlightDetailsList, costMap, SeatType.WINDOW.name(), SeatClass.ECONOMY.name(), terminal8,
                        terminal5 );
    }


    private void printItinerary( List<FlightDetails> resultFlightDetailsList, Map<String, BigDecimal> costMap,
                                 String seatType, String seatClass, AirTerminal sourceTerminal,
                                 AirTerminal destinationTerminal ) {
        if ( !resultFlightDetailsList.isEmpty() ) {
            Itinerary itinerary = new Itinerary();
            itinerary.setSourceAirTerminal( sourceTerminal );
            itinerary.setDestAirTerminal( destinationTerminal );
            itinerary.setFlightDetails( resultFlightDetailsList );
            itinerary.setSeatPreference( seatType );
            itinerary.setClassPreference( seatClass );
            itinerary.setCostMap( costMap );
            printResults( itinerary );
        }
        else {
            System.out.println( "No route exists between the Source and Destination Terminal" );
        }
    }


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

}
