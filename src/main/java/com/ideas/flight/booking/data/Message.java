package com.ideas.flight.booking.data;

import java.io.Serializable;


/**
 * The Class Message to store the user input data.
 * 
 * @author bharti.bakshi
 */
public class Message implements Serializable {

    private static final long serialVersionUID = -5736447966263897339L;
    
    private String sourceTerminalCode;

    private String destTerminalCode;

    private String sortPreference;

    private String seatPreference;
    
    private String classPreference;

    private String requestType;

    private Object response;


    public String getSourceTerminalCode() {
        return sourceTerminalCode;
    }


    public void setSourceTerminalCode( String sourceTerminalCode ) {
        this.sourceTerminalCode = sourceTerminalCode;
    }

    
    public String getDestTerminalCode() {
        return destTerminalCode;
    }


    public void setDestTerminalCode( String destTerminalCode ) {
        this.destTerminalCode = destTerminalCode;
    }


    public String getSortPreference() {
        return sortPreference;
    }


    public void setSortPreference( String sortPreference ) {
        this.sortPreference = sortPreference;
    }


    public String getSeatPreference() {
        return seatPreference;
    }


    public void setSeatPreference( String seatPreference ) {
        this.seatPreference = seatPreference;
    }


    public String getClassPreference() {
        return classPreference;
    }


    public void setClassPreference( String classPreference ) {
        this.classPreference = classPreference;
    }


    public String getRequestType() {
        return requestType;
    }


    public void setRequestType( String requestType ) {
        this.requestType = requestType;
    }


    public Object getResponse() {
        return response;
    }


    public void setResponse( Object response ) {
        this.response = response;
    }


    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }

}
