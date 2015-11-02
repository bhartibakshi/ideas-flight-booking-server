package com.ideas.flight.booking.constants;


/**
 * The Class FlightConstants to store constants and Enums
 * 
 * @author bharti.bakshi
 */
public class FlightConstants {

    /**
     * The Enum Layout.
     */
    public enum Layout {
        ONE_ONE( "1X1" ), ONE_TWO( "1X2" ), TWO_TWO( "2X2" ), TWO_FIVE_TWO( "2X5X2" ), TWO_THREE( "2X3" ), THREE_THREE(
            "3X3" ), THREE_THREE_THREE( "3X3X3" ), THREE_FOUR_THREE( "3X4X3" );

        private final String type;


        private Layout( String type ) {
            this.type = type;
        }


        public String getType() {
            return type;
        }
    }

    /**
     * The Enum SeatClass.
     */
    public enum SeatClass {
        ECONOMY, BUSINESS, FIRST
    }

    /**
     * The Enum SeatType.
     */
    public enum SeatType {
        WINDOW, AISLE, NONE
    }

    public static String SHORTEST_ROUTE = "ROUTE";

    public static String SHORTEST_TIME = "TIME";

    public static String SHORTEST_DISTANCE = "DISTANCE";

}
