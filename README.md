# ideas-flight-booking-server

Repository for ideas project for shortest flight route

Scenarios considered:

1. Non Stop Flights
2. Direct Flights with stops
3. Connecting Flights

Assumptions:
1. There are predefined routes which the flights follow and the flight plans are predefined as well.
2. These predefined routes are the shortest distance routes as per the theory of Great Circle which the flight navigation follows. (Source : https://en.wikipedia.org/wiki/Great-circle_distance)
3. The distances are calculated using the Haversine formula. (Source : https://en.wikipedia.org/wiki/Great-circle_distance)
4. All the distances are in Kilometers.
5. The cost is in INR.
6. The cost per flight per class(Business, Economy, First) and per seat type (Aisle, Window) is fixed and predefined ans does not vary.
7. The flight listing results are sorted in descending order with respect to number of stops (Non-stop first)
8. In case of connecting flights, only the closest intersection point is considered for displaying the results if there are more than one intersections available.

Routes Defined (For Testing):

1. Chennai -> Mumbai -> Dubai -> London
2. Singapore -> Mumbai -> London
3. London -> Delhi -> Tokyo
4. London -> Singapore
5. Tokyo -> Sydney
6. Singapore -> Sydney

Flights Defined (For Testing):

1. Chennai -> Mumbai -> Dubai -> London
2. Mumbai -> Dubai -> London
3. Singapore -> London
4. London -> Delhi -> Tokyo
5. London -> Tokyo
6. London -> Singapore
7. Tokyo -> Sydney
8. Singapore -> Sydney 


AirLine Companies Defined (For Testing):

1. Emirates
2. Lufthansa
3. British Airways

All the data related to Air Terminals is actual data (latitude, longitude etc.) (Source: Google Maps)