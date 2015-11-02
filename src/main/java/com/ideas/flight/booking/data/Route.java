package com.ideas.flight.booking.data;

import java.io.Serializable;
import java.util.List;


/**
 * The Class Route to store the data related to established air traffic routes.
 * 
 * @author bharti.bakshi
 */
public class Route implements Serializable {

    private static final long serialVersionUID = -8856922071873979132L;

    private String routeId;
    
    private List<String> terminalCodeList;



    public String getRouteId() {
        return routeId;
    }


    public void setRouteId( String routeId ) {
        this.routeId = routeId;
    }


    public List<String> getTerminalCodeList() {
        return terminalCodeList;
    }


    public void setTerminalCodeList( List<String> terminalCodeList ) {
        this.terminalCodeList = terminalCodeList;
    }

}
