package main.java.com.vijay.ridesharingapp.models;

import main.java.com.vijay.ridesharingapp.enums.VehicleType;

public class LuxuryCar extends Vehicle{

    public LuxuryCar(String licensePlate, int capacity, String color, Location location) {
        super(licensePlate, capacity, color, VehicleType.LUXURY_CAR, location);
    }


}
