package com.ideas.flight.booking.request.processor.factory;

import java.util.HashMap;
import java.util.Map;

import com.ideas.flight.booking.request.processor.RequestProcessor;
import com.ideas.flight.booking.request.processor.RoutePlanRequestProcessor;


/**
 * RequestProcessorFactory. A factory for creating RequestProcessor objects.
 * 
 * @author bharti.bakshi
 */
public class RequestProcessorFactory {

    private static Map<String, String> factoryMap = new HashMap<String, String>();

    private static RequestProcessorFactory factory = new RequestProcessorFactory();


    /**
     * Gets the requestprocessor factory instance.
     *
     * @return the factory
     */
    public static RequestProcessorFactory getFactory() {
        return factory;
    }

    /**
     * The Enum RequestName.
     */
    public enum RequestName {
        ROUTEPLAN, BOOKING, BILLING
    }

    static {
        factoryMap.put( RequestName.ROUTEPLAN.name(), RoutePlanRequestProcessor.class.getName() );
    }


    /**
     * Gets the request processor based on the input received
     *
     * @param requestName the request name
     * @return the request processor
     * @throws Exception the exception
     */
    public RequestProcessor getRequestProcessor( String requestName ) throws Exception {

        String processorClassStr = factoryMap.get( requestName );

        Class<?> serviceClass = Class.forName( processorClassStr );
        Object object = serviceClass.newInstance();
        RequestProcessor processor = (RequestProcessor) object;
        return processor;
    }
}
