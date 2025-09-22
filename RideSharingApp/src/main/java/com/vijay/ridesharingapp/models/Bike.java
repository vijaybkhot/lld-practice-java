package main.java.com.vijay.ridesharingapp.models;

import main.java.com.vijay.ridesharingapp.enums.VehicleType;

public class Bike extends Vehicle{

    public Bike(String licensePlate, int capacity, String color, Location location){
        super(licensePlate, capacity, color, VehicleType.BIKE, location);

    }
}
