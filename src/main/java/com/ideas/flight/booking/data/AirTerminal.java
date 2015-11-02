package com.ideas.flight.booking.data;

import java.io.Serializable;
import java.util.TimeZone;


/**
 * The Class AirTerminal to store Air Terminal related data.
 * 
 * @author bharti.bakshi
 */
public class AirTerminal implements Serializable {

    private static final long serialVersionUID = 6732013428395976614L;

    private String terminalCode;

    private String terminalName;

    private boolean isIAFStrip;

    private boolean isCommercial;

    private boolean isOperational;

    private String address;

    private String city;

    private String postalCode;

    private String country;

    private String contactHelpLineNumber;

    private TimeZone localTimeZone;

    private double latitude;

    private double longitude;

    private boolean isInternational;


    public String getTerminalCode() {
        return terminalCode;
    }


    public void setTerminalCode( String terminalCode ) {
        this.terminalCode = terminalCode;
    }


    public String getTerminalName() {
        return terminalName;
    }


    public void setTerminalName( String terminalName ) {
        this.terminalName = terminalName;
    }


    public boolean isIAFStrip() {
        return isIAFStrip;
    }


    public void setIAFStrip( boolean isIAFStrip ) {
        this.isIAFStrip = isIAFStrip;
    }


    public boolean isCommercial() {
        return isCommercial;
    }


    public void setCommercial( boolean isCommercial ) {
        this.isCommercial = isCommercial;
    }


    public boolean isOperational() {
        return isOperational;
    }


    public void setOperational( boolean isOperational ) {
        this.isOperational = isOperational;
    }


    public String getAddress() {
        return address;
    }


    public void setAddress( String address ) {
        this.address = address;
    }


    public String getCity() {
        return city;
    }


    public void setCity( String city ) {
        this.city = city;
    }


    public String getPostalCode() {
        return postalCode;
    }


    public void setPostalCode( String postalCode ) {
        this.postalCode = postalCode;
    }


    public String getCountry() {
        return country;
    }


    public void setCountry( String country ) {
        this.country = country;
    }


    public String getContactHelpLineNumber() {
        return contactHelpLineNumber;
    }


    public void setContactHelpLineNumber( String contactHelpLineNumber ) {
        this.contactHelpLineNumber = contactHelpLineNumber;
    }


    public TimeZone getLocalTimeZone() {
        return localTimeZone;
    }


    public void setLocalTimeZone( TimeZone localTimeZone ) {
        this.localTimeZone = localTimeZone;
    }


    public double getLatitude() {
        return latitude;
    }


    public void setLatitude( double latitude ) {
        this.latitude = latitude;
    }


    public double getLongitude() {
        return longitude;
    }


    public void setLongitude( double longitude ) {
        this.longitude = longitude;
    }


    public boolean isInternational() {
        return isInternational;
    }


    public void setInternational( boolean isInternational ) {
        this.isInternational = isInternational;
    }


    protected AirTerminal clone() {
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

}
