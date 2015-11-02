package com.ideas.flight.booking.data;

import java.io.Serializable;
import java.math.BigDecimal;

import com.ideas.flight.booking.constants.FlightConstants.SeatClass;
import com.ideas.flight.booking.constants.FlightConstants.SeatType;


/**
 * The Class PriceSheet to store the data related to cost of seat on a flight as per the type and class.
 * 
 * @author bharti.bakshi
 */
public class PriceSheet implements Serializable {

    private static final long serialVersionUID = 8825804843789961934L;

    private String flightId;

    private SeatClass seatClass;

    private SeatType seatType;

    private BigDecimal costPerKm;


    public String getFlightId() {
        return flightId;
    }


    public void setFlightId( String flightId ) {
        this.flightId = flightId;
    }


    public SeatClass getSeatClass() {
        return seatClass;
    }


    public void setSeatClass( SeatClass seatClass ) {
        this.seatClass = seatClass;
    }


    public SeatType getSeatType() {
        return seatType;
    }


    public void setSeatType( SeatType seatType ) {
        this.seatType = seatType;
    }


    public BigDecimal getCostPerKm() {
        return costPerKm;
    }


    public void setCostPerKm( BigDecimal costPerKm ) {
        this.costPerKm = costPerKm;
    }

}
