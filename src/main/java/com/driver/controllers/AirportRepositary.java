package com.driver.controllers;

import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.model.Passenger;

import java.util.*;

public class AirportRepositary {
    HashMap<String,Airport>airportDb=new HashMap<>();
    HashMap<Integer,Flight>flightDb=new HashMap<>();
    public HashMap<Integer, List<Integer>> flightToPassengerDb = new HashMap<>();

    HashMap<Integer,Passenger>passengerDb=new HashMap<>();
    public void addAirport(Airport airport){
        String key=airport.getAirportName();
        airportDb.put(key,airport);

    }
    public void addFlight(Flight flight){
        Integer key=flight.getFlightId();
        flightDb.put(key,flight);
    }
    public void addPassenger(Passenger passenger){
        Integer key=passenger.getPassengerId();
        passengerDb.put(key,passenger);
    }

    public String getLargestAirportName(){
        String ans="";
        Integer terminal=0;
        for( Airport airport:airportDb.values()){
            if(terminal<airport.getNoOfTerminals()){
                terminal=airport.getNoOfTerminals();
                ans=airport.getAirportName();
            }else if(terminal==airport.getNoOfTerminals()){
                if(airport.getAirportName().compareTo(ans)<0){
                    ans=airport.getAirportName();
                }
            }
        }
        return ans;
    }
    public double getShortestDurationOfPossibleBetweenTwoCities(City fromCity , City toCity){
        double min=10000000;
        for(Flight flight:flightDb.values()){
            if(flight.getFromCity().equals(fromCity) && flight.getToCity().equals(toCity)){
                min=Math.min(flight.getDuration(),min);
            }
        }
        return  min;
    }
    public int getNumberOfPeopleOn(Date date, String airportName){

        Airport airport = airportDb.get(airportName);
        if(Objects.isNull(airport)){
            return 0;
        }
        City city = airport.getCity();
        int count = 0;
        for(Flight flight:flightDb.values()){
            if(date.equals(flight.getFlightDate()))
                if(flight.getToCity().equals(city)||flight.getFromCity().equals(city)){

                    int flightId = flight.getFlightId();
                    count = count + flightToPassengerDb.get(flightId).size();
                }
        }
        return count;
    }
    public int calculateFlightFare(Integer flightId){
        int noOfPeopleBooked = flightToPassengerDb.get(flightId).size();
        return noOfPeopleBooked*50 + 3000;
    }
    public String bookATicket(int flightId,int passengerId){
        if(Objects.nonNull(flightToPassengerDb.get(flightId)) &&(flightToPassengerDb.get(flightId).size()<flightDb.get(flightId).getMaxCapacity())){


            List<Integer> passengers =  flightToPassengerDb.get(flightId);

            if(passengers.contains(passengerId)){
                return "FAILURE";
            }

            passengers.add(passengerId);
            flightToPassengerDb.put(flightId,passengers);
            return "SUCCESS";
        }
        else if(Objects.isNull(flightToPassengerDb.get(flightId))){
            flightToPassengerDb.put(flightId,new ArrayList<>());
            List<Integer> passengers =  flightToPassengerDb.get(flightId);

            if(passengers.contains(passengerId)){
                return "FAILURE";
            }

            passengers.add(passengerId);
            flightToPassengerDb.put(flightId,passengers);
            return "SUCCESS";

        }
        return "FAILURE";
    }
    public String cancelATicket(int flightId, int passengerId){
        List<Integer> passengers = flightToPassengerDb.getOrDefault(flightId,null);
        if(passengers == null){
            return "FAILURE";
        }


        if(passengers.contains(passengerId)){
            passengers.remove(passengerId);
            return "SUCCESS";
        }
        return "FAILURE";
    }
    public int countOfBookingsDoneByPassengerAllCombined(int passengerId){
        HashMap<Integer,List<Integer>> passengerToFlightDb = new HashMap<>();
        //We have a list from passenger To flights database:-
        int count = 0;
        for(Map.Entry<Integer,List<Integer>> entry: flightToPassengerDb.entrySet()){

            List<Integer> passengers  = entry.getValue();
            for(Integer passenger : passengers){
                if(passenger==passengerId){
                    count++;
                }
            }
        }
        return count;
    }
    public String getAirportNameFromFlightId(int flightId){
        if(flightDb.containsKey(flightId)){
            City city = flightDb.get(flightId).getFromCity();
            for(Airport airport:airportDb.values()){
                if(airport.getCity().equals(city)){
                    return airport.getAirportName();
                }
            }
        }
        return null;
    }
    public int calculateRevenueOfAFlight(int flightId){
        int noOfPeopleBooked = flightToPassengerDb.get(flightId).size();
        int variableFare = (noOfPeopleBooked*(noOfPeopleBooked+1))*25;
        int fixedFare = 3000*noOfPeopleBooked;
        int totalFare = variableFare + fixedFare;

        return totalFare+50;
    }
}
