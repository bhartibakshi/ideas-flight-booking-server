package com.ideas.flight.booking.request.dispatcher;

import com.ideas.flight.booking.data.Message;
import com.ideas.flight.booking.request.processor.RequestProcessor;
import com.ideas.flight.booking.request.processor.factory.RequestProcessorFactory;

/**
 * The Class RequestDispatcher to intercept the request coming from the user and route it to suitable processors.
 * 
 * @author bharti.bakshi
 */
public class RequestDispatcher {

    /**
     * Dispatches the request to suitable processor based on the type of message received
     *
     * @param message the message
     * @throws Exception the exception
     */
    public void dispatchRequest( Object message ) throws Exception {
        RequestProcessor processor = null;
        if ( message instanceof Message ) {
        processor = RequestProcessorFactory.getFactory()
                .getRequestProcessor( ( (Message) message ).getRequestType() );
        }
        processor.process( message );
    }

}
