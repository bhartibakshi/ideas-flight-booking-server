package com.ideas.flight.booking.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.ideas.flight.booking.dao.RouteDetailsDao;
import com.ideas.flight.booking.data.AirTerminal;
import com.ideas.flight.booking.data.FlightDetails;
import com.ideas.flight.booking.data.FlightPlan;
import com.ideas.flight.booking.data.Route;
import com.ideas.flight.booking.util.DistanceCalculator;

/**
 * The Class RoutePlannerService to provide business logic for route related modules.
 * 
 * @author bharti.bakshi
 */
public class RoutePlannerService implements BusinessService {

    RouteDetailsDao routeDetailsDao;


    /**
     * Gets the shortest route by distance between the source and the destination terminal.
     *
     * @param sourceTerminalCode the source terminal code
     * @param destTerminalCode the destination terminal code
     * @return the shortest route by distance
     */
    public List<FlightDetails> getShortestRouteByDistance( String sourceTerminalCode, String destTerminalCode ) {
        List<FlightDetails> finalFlightDetailList = new ArrayList<FlightDetails>();
        List<Route> routeList = routeDetailsDao.getRoutes( sourceTerminalCode, destTerminalCode );
        if ( !routeList.isEmpty() ) {
            Map<String, List<AirTerminal>> routeMap = getRouteMap( routeList, sourceTerminalCode, destTerminalCode );
            Map<String, Double> routeDistanceMap = calculateShortestRouteByDistance( routeMap );
            if ( !routeDistanceMap.isEmpty() ) {
                List<FlightDetails> flightDetailList = routeDetailsDao.getFlightsByRoute( new ArrayList<String>(
                                                                                                                 routeDistanceMap.keySet() ).get( 0 ) );
                finalFlightDetailList = getShortestFlights( sourceTerminalCode, destTerminalCode, flightDetailList );
            }

        }
        else {
            List<Route> sourceRouteList = routeDetailsDao.getRoutes( sourceTerminalCode );
            Map<String, List<Route>> destIntersectionMap = getDestIntersectionMap( sourceTerminalCode,
                                                                                   destTerminalCode, sourceRouteList );
            Map<String, List<Route>> sourceIntersectionMap = getSourceIntersectionMap( sourceRouteList,
                                                                                       new ArrayList<String>(
                                                                                                              destIntersectionMap.keySet() ) );

            Map<String, Entry<String, Double>> sourceIntersectionDistanceMap = getSourceIntersectionDistance( sourceTerminalCode,
                                                                                                              sourceIntersectionMap );
            Map<String, Entry<String, Double>> destIntersectionDistanceMap = getDestIntersectionDistance( destTerminalCode,
                                                                                                          destIntersectionMap );

            Map<String, Double> intersectionTotalDistanceMap = getTotalDistancePerIntersectionMap( sourceIntersectionDistanceMap,
                                                                                                   destIntersectionDistanceMap );
            if ( !intersectionTotalDistanceMap.isEmpty() ) {
                String itersectionKey = new ArrayList<String>( intersectionTotalDistanceMap.keySet() ).get( 0 );
                String sourceTointersectionRouteId = sourceIntersectionDistanceMap.get( itersectionKey ).getKey();
                String intersectionToDestRouteId = destIntersectionDistanceMap.get( itersectionKey ).getKey();
                List<FlightDetails> fromSourceFlightDetailList = routeDetailsDao.getFlightsByRoute( sourceTointersectionRouteId );
                List<FlightDetails> toDestFlightDetailList = routeDetailsDao.getFlightsByRoute( intersectionToDestRouteId );
                List<FlightDetails> finalFromSourceFlightDetailList = getShortestFlights( sourceTerminalCode,
                                                                                          itersectionKey,
                                                                                          fromSourceFlightDetailList );
                List<FlightDetails> finalToDestFlightDetailList = getShortestFlights( itersectionKey, destTerminalCode,
                                                                                      toDestFlightDetailList );
                finalFlightDetailList.addAll( finalFromSourceFlightDetailList );
                finalFlightDetailList.addAll( finalToDestFlightDetailList );
            }

        }
        return finalFlightDetailList;
    }


    /**
     * Gets the air terminal details for the air terminal code provided.
     *
     * @param terminalCode the terminal code
     * @return the air terminal
     */
    public AirTerminal getAirTerminal( String terminalCode ) {
        AirTerminal airTerminal = routeDetailsDao.getAirTerminalDetails( terminalCode );
        return airTerminal;
    }


    /**
     * Gets the shortest flights between a source and a destination air terminal.
     *
     * @param sourceTerminalCode the source terminal code
     * @param destTerminalCode the destination terminal code
     * @param flightDetailList the flight detail list
     * @return the shortest flights
     */
    private List<FlightDetails> getShortestFlights( String sourceTerminalCode, String destTerminalCode,
                                                    List<FlightDetails> flightDetailList ) {
        List<FlightDetails> finalFlightDetailList = new ArrayList<FlightDetails>();
        Map<FlightDetails, Integer> flightStopsMap = new HashMap<FlightDetails, Integer>();
        for ( FlightDetails flightDetails : flightDetailList ) {
            List<FlightPlan> requiredFlightPlanList = new ArrayList<FlightPlan>();
            int stops = 0;
            boolean sourceFound = false;
            FlightDetails requiredFlightDetails = flightDetails.clone();
            for ( FlightPlan flightPlan : flightDetails.getFlightPlanList() ) {
                if ( flightPlan.getFromTerminalCode().equalsIgnoreCase( sourceTerminalCode ) ) {
                    requiredFlightPlanList.add( flightPlan );
                    sourceFound = true;
                    if ( flightPlan.getToTerminalCode().equalsIgnoreCase( destTerminalCode ) ) {
                        break;
                    }
                    else {
                        stops++;

                    }
                }
                else if ( sourceFound && flightPlan.getToTerminalCode().equalsIgnoreCase( destTerminalCode ) ) {
                    requiredFlightPlanList.add( flightPlan );
                    break;
                }
            }
            requiredFlightDetails.setFlightPlanList( requiredFlightPlanList );
            requiredFlightDetails.setSourceTerminal( sourceTerminalCode );
            requiredFlightDetails.setDestinationTerminal( destTerminalCode );
            flightStopsMap.put( requiredFlightDetails, stops );
        }

        sortByLeastStops( flightStopsMap );
        finalFlightDetailList.addAll( new ArrayList<FlightDetails>( flightStopsMap.keySet() ) );
        return finalFlightDetailList;
    }


    /**
     * Gets the total distance between the source and destination terminals via intersections.
     *
     * @param sourceIntersectionDistanceMap the source intersection distance map
     * @param destIntersectionDistanceMap the destination intersection distance map
     * @return the total distance per intersection map
     */
    private Map<String, Double> getTotalDistancePerIntersectionMap( Map<String, Entry<String, Double>> sourceIntersectionDistanceMap,
                                                                    Map<String, Entry<String, Double>> destIntersectionDistanceMap ) {
        Map<String, Double> distanceMap = new HashMap<String, Double>();
        for ( Entry<String, Entry<String, Double>> mapEntry : sourceIntersectionDistanceMap.entrySet() ) {
            double distance = mapEntry.getValue().getValue()
                + destIntersectionDistanceMap.get( mapEntry.getKey() ).getValue();
            distanceMap.put( mapEntry.getKey(), distance );
        }
        sortByShortestDistance( distanceMap );
        return distanceMap;
    }


    /**
     * Gets the distance between destination and intersection terminals.
     *
     * @param destTerminalCode the destination terminal code
     * @param destIntersectionMap the destination intersection map
     * @return the destination intersection distance
     */
    private Map<String, Entry<String, Double>> getDestIntersectionDistance( String destTerminalCode,
                                                                            Map<String, List<Route>> destIntersectionMap ) {
        Map<String, Entry<String, Double>> destIntersectionDistanceMap = new HashMap<String, Entry<String, Double>>();
        for ( Entry<String, List<Route>> mapEntry : destIntersectionMap.entrySet() ) {
            Map<String, List<AirTerminal>> routeMap = getRouteMap( mapEntry.getValue(), mapEntry.getKey(),
                                                                   destTerminalCode );
            Map<String, Double> distanceMap = calculateShortestRouteByDistance( routeMap );
            if ( !distanceMap.isEmpty() ) {
                destIntersectionDistanceMap.put( mapEntry.getKey(),
                                                 new ArrayList<Entry<String, Double>>( distanceMap.entrySet() ).get( 0 ) );
            }
        }
        return destIntersectionDistanceMap;
    }


    /**
     * Gets the distance between source and intersection terminals.
     *
     * @param sourceTerminalCode the source terminal code
     * @param sourceIntersectionMap the source intersection map
     * @return the source intersection distance
     */
    private Map<String, Entry<String, Double>> getSourceIntersectionDistance( String sourceTerminalCode,
                                                                              Map<String, List<Route>> sourceIntersectionMap ) {
        Map<String, Entry<String, Double>> sourceIntersectionDistanceMap = new HashMap<String, Entry<String, Double>>();
        for ( Entry<String, List<Route>> mapEntry : sourceIntersectionMap.entrySet() ) {
            Map<String, List<AirTerminal>> routeMap = getRouteMap( mapEntry.getValue(), sourceTerminalCode,
                                                                   mapEntry.getKey() );
            Map<String, Double> distanceMap = calculateShortestRouteByDistance( routeMap );
            if ( !distanceMap.isEmpty() ) {
                sourceIntersectionDistanceMap.put( mapEntry.getKey(),
                                                   new ArrayList<Entry<String, Double>>( distanceMap.entrySet() ).get( 0 ) );
            }
        }
        return sourceIntersectionDistanceMap;
    }


    /**
     * Gets the intersecting routes from source routes to destination routes
     *
     * @param sourceRouteList the source route list
     * @param intersectionTerminalList the intersection terminal list
     * @return the source intersection map
     */
    private Map<String, List<Route>> getSourceIntersectionMap( List<Route> sourceRouteList,
                                                               List<String> intersectionTerminalList ) {
        Map<String, List<Route>> intersectionMap = new HashMap<String, List<Route>>();
        for ( Route sourceRoute : sourceRouteList ) {
            for ( String terminalCode : intersectionTerminalList ) {
                if ( sourceRoute.getTerminalCodeList().contains( terminalCode ) ) {
                    if ( intersectionMap.get( terminalCode ) != null ) {
                        intersectionMap.get( terminalCode ).add( sourceRoute );
                    }
                    else {
                        List<Route> routeList = new ArrayList<Route>();
                        routeList.add( sourceRoute );
                        intersectionMap.put( terminalCode, routeList );
                    }
                }
            }
        }
        return intersectionMap;
    }


    /**
     * Gets the intersecting routes from destination routes to source routes.
     *
     * @param sourceTerminalCode the source terminal code
     * @param destTerminalCode the dest terminal code
     * @param sourceRouteList the source route list
     * @return the destination intersection map
     */
    private Map<String, List<Route>> getDestIntersectionMap( String sourceTerminalCode, String destTerminalCode,
                                                             List<Route> sourceRouteList ) {
        Map<String, List<Route>> intersectionMap = new HashMap<String, List<Route>>();
        for ( Route sourceRoute : sourceRouteList ) {
            for ( String terminalCode : sourceRoute.getTerminalCodeList() ) {
                if ( intersectionMap.get( terminalCode ) == null && !terminalCode.equalsIgnoreCase( sourceTerminalCode ) ) {
                    List<Route> destRouteList = routeDetailsDao.getRoutes( terminalCode, destTerminalCode );
                    if ( !destRouteList.isEmpty() ) {
                        intersectionMap.put( terminalCode, destRouteList );
                    }
                }
            }
        }
        return intersectionMap;
    }


    /**
     * Gets the actual route on a particular air traffic route between a given source and destination air terminal
     *
     * @param routeList the route list
     * @param sourceTerminalCode the source terminal code
     * @param destTerminalCode the destination terminal code
     * @return the route map
     */
    private Map<String, List<AirTerminal>> getRouteMap( List<Route> routeList, String sourceTerminalCode,
                                                        String destTerminalCode ) {
        Map<String, List<AirTerminal>> routeMap = new HashMap<String, List<AirTerminal>>();

        List<AirTerminal> terminalList = null;
        for ( Route route : routeList ) {
            terminalList = getActualTerminalList( sourceTerminalCode, destTerminalCode, route );
            if ( !terminalList.isEmpty() ) {
                routeMap.put( route.getRouteId(), terminalList );
            }
        }
        return routeMap;

    }


    /**
     * Calculates shortest route by distance between Air Terminals.
     *
     * @param routeMap the route map
     * @return the map
     */
    private Map<String, Double> calculateShortestRouteByDistance( Map<String, List<AirTerminal>> routeMap ) {
        Map<String, Double> distanceMap = new HashMap<String, Double>();
        for ( Entry<String, List<AirTerminal>> mapEntry : routeMap.entrySet() ) {
            List<AirTerminal> airTerminalList = mapEntry.getValue();
            double totalDistance = 0.0;
            for ( int i = 0; i < airTerminalList.size() - 1; i++ ) {
                double distance = DistanceCalculator.calculateDistance( airTerminalList.get( i + 1 ).getLatitude(),
                                                                        airTerminalList.get( i + 1 ).getLongitude(),
                                                                        airTerminalList.get( i ).getLatitude(),
                                                                        airTerminalList.get( i ).getLongitude(), "KM" );
                totalDistance += distance;

            }
            distanceMap.put( mapEntry.getKey(), totalDistance );

        }
        sortByShortestDistance( distanceMap );
        return distanceMap;

    }


    /**
     * Gets the actual terminal list between a source and destination on a particular route.
     *
     * @param sourceTerminalCode the source terminal code
     * @param destTerminalCode the destination terminal code
     * @param route the route
     * @return the actual terminal list
     */
    private List<AirTerminal> getActualTerminalList( String sourceTerminalCode, String destTerminalCode, Route route ) {
        List<AirTerminal> terminalList = new ArrayList<AirTerminal>();
        boolean sourceFound = false;
        boolean destFound = false;

        for ( String terminalCode : route.getTerminalCodeList() ) {
            if ( sourceFound && destFound ) {
                break;
            }
            else if ( sourceFound & !destFound ) {
                terminalList.add( getAirTerminal( terminalCode ) );
                if ( terminalCode.equalsIgnoreCase( destTerminalCode ) ) {
                    destFound = true;
                }
            }
            else if ( !sourceFound && destFound ) {
                break;
            }
            else if ( !sourceFound && !destFound ) {
                if ( terminalCode.equalsIgnoreCase( sourceTerminalCode ) ) {
                    terminalList.add( getAirTerminal( terminalCode ) );
                    sourceFound = true;
                }
            }
        }

        if ( !destFound ) {
            terminalList.clear();
        }
        return terminalList;
    }


    /**
     * Sorts the map of distances by shortest distance first.
     *
     * @param distanceMap the distance map
     */
    private void sortByShortestDistance( Map<String, Double> distanceMap ) {
        Set<Entry<String, Double>> set = distanceMap.entrySet();
        List<Entry<String, Double>> list = new ArrayList<Entry<String, Double>>( set );
        Collections.sort( list, new Comparator<Map.Entry<String, Double>>() {

            public int compare( Map.Entry<String, Double> o1, Map.Entry<String, Double> o2 ) {
                return ( Double.valueOf( o2.getValue() ) ).compareTo( Double.valueOf( o1.getValue() ) );
            }
        } );
        distanceMap.clear();
        for ( Entry<String, Double> entry : list ) {
            distanceMap.put( entry.getKey(), entry.getValue() );
        }
    }


    /**
     * Sort map of flights by least stops first.
     *
     * @param flightStopsMap the flight stops map
     */
    private void sortByLeastStops( Map<FlightDetails, Integer> flightStopsMap ) {
        Set<Entry<FlightDetails, Integer>> set = flightStopsMap.entrySet();
        List<Entry<FlightDetails, Integer>> list = new ArrayList<Entry<FlightDetails, Integer>>( set );
        Collections.sort( list, new Comparator<Map.Entry<FlightDetails, Integer>>() {

            public int compare( Map.Entry<FlightDetails, Integer> o1, Map.Entry<FlightDetails, Integer> o2 ) {
                return ( o2.getValue().compareTo( o1.getValue() ) );
            }
        } );
        flightStopsMap.clear();
        for ( Entry<FlightDetails, Integer> entry : list ) {
            flightStopsMap.put( entry.getKey(), entry.getValue() );
        }
    }


    public void setRouteDetailsDao( RouteDetailsDao routeDetailsDao ) {
        this.routeDetailsDao = routeDetailsDao;
    }

}
