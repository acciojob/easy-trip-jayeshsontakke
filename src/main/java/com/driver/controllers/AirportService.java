package com.driver.controllers;

import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.model.Passenger;

import java.util.Date;

public class AirportService {
    AirportRepositary airportRepositary=new AirportRepositary();
    public void addAirport(Airport airport){
        airportRepositary.addAirport(airport);
    }
    public  void addFlight(Flight flight){
        airportRepositary.addFlight(flight);
    }
    public void addPassenger(Passenger passenger){
        airportRepositary.addPassenger(passenger);
    }
    public String getLargestAirportName(){
       return airportRepositary.getLargestAirportName();
    }
    public double getShortestDurationOfPossibleBetweenTwoCities(City fromCity , City toCity){
        return airportRepositary.getShortestDurationOfPossibleBetweenTwoCities(fromCity,toCity);
    }
    public int getNumberOfPeopleOn(Date date , String airportName){
        int ans=airportRepositary.getNumberOfPeopleOn(date,airportName);
        return  ans;
    }
    public int calculateFlightFare(Integer flightId){
        return  airportRepositary.calculateFlightFare(flightId);
    }
    public String bookATicket(int flightId, int passangerId){
        return airportRepositary.bookATicket(flightId,passangerId);
    }
    public String cancelATicket(int flightId,int passengerId){
        return airportRepositary.cancelATicket(flightId,passengerId);
    }
    public int countOfBookingsDoneByPassengerAllCombined(int passengerid){
        return airportRepositary.countOfBookingsDoneByPassengerAllCombined(passengerid);
    }
    public String getAirportNameFromFlightId(int flightId){
        return airportRepositary.getAirportNameFromFlightId(flightId);
    }
    public int calculateRevenueOfAFlight(int flightId){
        return airportRepositary.calculateRevenueOfAFlight(flightId);
    }
}
