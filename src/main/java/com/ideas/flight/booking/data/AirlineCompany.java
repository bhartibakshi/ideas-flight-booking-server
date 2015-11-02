package com.ideas.flight.booking.data;

import java.io.Serializable;


/**
 * The Class AirlineCompany to store airLine company related data.
 * 
 * @author bharti.bakshi
 */
public class AirlineCompany implements Serializable {

    private static final long serialVersionUID = 7750016557137457096L;

    private String companyId;

    private String companyName;

    private String registeredAddress;

    private String registeredCity;

    private String registeredState;

    private String registeredPostalCode;

    private String registeredCountry;
    
    private String contactHelpLineNumber;


    public String getCompanyId() {
        return companyId;
    }


    public void setCompanyId( String companyId ) {
        this.companyId = companyId;
    }


    public String getCompanyName() {
        return companyName;
    }


    public void setCompanyName( String companyName ) {
        this.companyName = companyName;
    }


    public String getRegisteredAddress() {
        return registeredAddress;
    }


    public void setRegisteredAddress( String registeredAddress ) {
        this.registeredAddress = registeredAddress;
    }


    public String getRegisteredCity() {
        return registeredCity;
    }


    public void setRegisteredCity( String registeredCity ) {
        this.registeredCity = registeredCity;
    }


    public String getRegisteredState() {
        return registeredState;
    }


    public void setRegisteredState( String registeredState ) {
        this.registeredState = registeredState;
    }


    public String getRegisteredPostalCode() {
        return registeredPostalCode;
    }


    public void setRegisteredPostalCode( String registeredPostalCode ) {
        this.registeredPostalCode = registeredPostalCode;
    }


    public String getRegisteredCountry() {
        return registeredCountry;
    }


    public void setRegisteredCountry( String registeredCountry ) {
        this.registeredCountry = registeredCountry;
    }


    public String getContactHelpLineNumber() {
        return contactHelpLineNumber;
    }


    public void setContactHelpLineNumber( String contactHelpLineNumber ) {
        this.contactHelpLineNumber = contactHelpLineNumber;
    }


    public AirlineCompany clone() {
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

}
