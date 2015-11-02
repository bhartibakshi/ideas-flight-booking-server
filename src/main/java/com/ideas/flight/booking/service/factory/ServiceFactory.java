/*
 * 
 */
package com.ideas.flight.booking.service.factory;

import java.util.HashMap;
import java.util.Map;

import com.ideas.flight.booking.service.BillingService;
import com.ideas.flight.booking.service.BusinessService;
import com.ideas.flight.booking.service.RoutePlannerService;


/**
 * ServiceFactory. A factory for creating Service objects.
 * 
 * @author bharti.bakshi
 */
public class ServiceFactory {

    private static Map<String, String> factoryMap = new HashMap<String, String>();

    private static ServiceFactory factory = new ServiceFactory();


    /**
     * Gets the service factory instance.
     *
     * @return the factory
     */
    public static ServiceFactory getFactory() {
        return factory;
    }

    /**
     * The Enum ServiceName.
     */
    public enum ServiceName {
        ROUTEPLAN, BOOKING, BILLING
    }

    static {
        factoryMap.put( ServiceName.ROUTEPLAN.name(), RoutePlannerService.class.getName() );
        factoryMap.put( ServiceName.BILLING.name(), BillingService.class.getName() );
    }


    /**
     * Gets the service based on the input received
     *
     * @param serviceName the service name
     * @return the service
     * @throws Exception the exception
     */
    public BusinessService getService( ServiceName serviceName ) throws Exception {

        String serviceClassStr = factoryMap.get( serviceName.name() );

        Class<?> serviceClass = Class.forName( serviceClassStr );
        Object object = serviceClass.newInstance();
        BusinessService businessService = (BusinessService) object;
        return businessService;
    }

}
