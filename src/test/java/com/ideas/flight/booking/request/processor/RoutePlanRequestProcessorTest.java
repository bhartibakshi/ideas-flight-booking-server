package com.ideas.flight.booking.request.processor;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.ideas.flight.booking.constants.FlightConstants.SeatClass;
import com.ideas.flight.booking.constants.FlightConstants.SeatType;
import com.ideas.flight.booking.data.AirTerminal;
import com.ideas.flight.booking.data.Message;
import com.ideas.flight.booking.request.processor.factory.RequestProcessorFactory;
import com.ideas.flight.booking.request.processor.factory.RequestProcessorFactory.RequestName;
import com.ideas.flight.booking.service.BillingService;
import com.ideas.flight.booking.service.RoutePlannerService;
import com.ideas.flight.booking.service.factory.ServiceFactory;
import com.ideas.flight.booking.service.factory.ServiceFactory.ServiceName;


/**
 * The Class RoutePlanRequestProcessorTest. Test Class containing test cases for the class RoutePlanRequestProcessor
 * 
 * @author bharti.bakshi
 */
@RunWith( PowerMockRunner.class )
@PrepareForTest( ServiceFactory.class )
public class RoutePlanRequestProcessorTest {

    RoutePlannerService routePlannerService;

    BillingService billingService;

    ServiceFactory serviceFactory;

    RoutePlanRequestProcessor processor;

    private AirTerminal terminal1;

    private AirTerminal terminal2;
    
    @Before
    public void setUp() throws Exception {
        processor = (RoutePlanRequestProcessor) RequestProcessorFactory.getFactory()
            .getRequestProcessor( RequestName.ROUTEPLAN.name() );
        PowerMockito.mockStatic( ServiceFactory.class );
        serviceFactory = PowerMockito.mock( ServiceFactory.class );
        routePlannerService = PowerMockito.mock( RoutePlannerService.class );
        billingService = PowerMockito.mock( BillingService.class );
        processor.setServiceFactory( serviceFactory );
        
        terminal1 = RoutePlanRequestProcessorTestHelper.getAirTerminal( "MAA", "Chennai Airport", false, true, true,
                                                                        null, null, null, null, null, null, 12.993694,
                                                                        80.171393, true );

        terminal2 = RoutePlanRequestProcessorTestHelper.getAirTerminal( "ORR", "Fake Airport", true, false, true, null,
                                                                        null, null, null, null, null, 0.0, 0.0, true );

        when( routePlannerService.getAirTerminal( "MAA" ) ).thenReturn( terminal1 );
        when( routePlannerService.getAirTerminal( "ORR" ) ).thenReturn( terminal2 );
    }


    /**
     * Test validation of Source Terminal
     *
     * @throws Exception the exception
     */
    @Test
    public void testSourceValidate() throws Exception {
        when( ServiceFactory.getFactory() ).thenReturn( serviceFactory );
        when( serviceFactory.getService( ServiceName.ROUTEPLAN ) ).thenReturn( routePlannerService );
        when( serviceFactory.getService( ServiceName.BILLING ) ).thenReturn( billingService );
        Message message = new Message();
        message.setSourceTerminalCode( "ORR" );
        message.setDestTerminalCode( "MAA" );
        message.setSeatPreference( SeatType.AISLE.name() );
        message.setClassPreference( SeatClass.BUSINESS.name() );
        processor.process( message );
    }


    /**
     * Test validation of Destination Terminal
     *
     * @throws Exception the exception
     */
    @Test
    public void testDestinationValidate() throws Exception {
        when( ServiceFactory.getFactory() ).thenReturn( serviceFactory );
        when( serviceFactory.getService( ServiceName.ROUTEPLAN ) ).thenReturn( routePlannerService );
        when( serviceFactory.getService( ServiceName.BILLING ) ).thenReturn( billingService );
        Message message = new Message();
        message.setSourceTerminalCode( "MAA" );
        message.setDestTerminalCode( "ORR" );
        message.setSeatPreference( SeatType.AISLE.name() );
        message.setClassPreference( SeatClass.BUSINESS.name() );
        processor.process( message );
    }

}
