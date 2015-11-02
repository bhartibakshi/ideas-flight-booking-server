package com.ideas.flight.booking.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ideas.flight.booking.constants.FlightConstants.Layout;
import com.ideas.flight.booking.constants.FlightConstants.SeatClass;
import com.ideas.flight.booking.constants.FlightConstants.SeatType;

/**
 * The Class AircraftCarrier to store Aircraft Carrier related Data.
 * 
 * @author bharti.bakshi
 */
public class AircraftCarrier implements Serializable {

    private static final long serialVersionUID = 5483534657738301216L;

    private String manufacturer;

    private String modelId;

    private Layout seatingPlan;

    private List<Seat> seatList;
    

    public String getManufacturer() {
        return manufacturer;
    }


    public void setManufacturer( String manufacturer ) {
        this.manufacturer = manufacturer;
    }


    public String getModelId() {
        return modelId;
    }


    public void setModelId( String modelId ) {
        this.modelId = modelId;
    }


    public Layout getSeatingPlan() {
        return seatingPlan;
    }


    public void setSeatingPlan( Layout seatingPlan ) {
        this.seatingPlan = seatingPlan;
    }


    public List<Seat> getSeatList() {
        return seatList;
    }


    public void setSeatList( List<Seat> seatList ) {
        this.seatList = seatList;
    }

    /**
     * The Class Seat to store seat related data.
     */
    public class Seat implements Serializable {
        
        private static final long serialVersionUID = 1931767127422471435L;

        private int seatNo;
        
        private SeatClass seatClass;

        private SeatType seatType;
        
        
        public Seat( int seatNo, SeatClass seatClass, SeatType seatType ) {
            this.seatNo = seatNo;
            this.seatClass = seatClass;
            this.seatType = seatType;
                            
        }

        
        public int getSeatNo() {
            return seatNo;
        }

        
        public SeatClass getSeatClass() {
            return seatClass;
        }


        public SeatType getSeatType() {
            return seatType;
        }


        public Seat clone() {
            Seat newSeat = new Seat( this.getSeatNo(), this.getSeatClass(), this.getSeatType() );
            return newSeat;

        }
        
    }


    public AircraftCarrier clone() {
        AircraftCarrier aircraftCarrier = new AircraftCarrier();
        aircraftCarrier.setModelId( this.modelId );
        aircraftCarrier.setManufacturer( this.manufacturer );
        aircraftCarrier.setSeatingPlan( this.seatingPlan );
        List<Seat> seatList = new ArrayList<Seat>();
        for ( Seat seat : this.seatList ) {
            seatList.add( seat.clone() );
        }
        aircraftCarrier.setSeatList( seatList );
        return aircraftCarrier;
    }

}
