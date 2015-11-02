package com.ideas.flight.booking.request.processor;


/**
 * The Class RequestProcessor. Abstract class for the Request processor
 * 
 * @author bharti.bakshi
 */
public abstract class RequestProcessor {

    /**
     * Abstract method Process. To me implemented accordingly by each processor
     *
     * @param message the message
     * @throws Exception the exception
     */
    public abstract void process( Object message ) throws Exception;

}
