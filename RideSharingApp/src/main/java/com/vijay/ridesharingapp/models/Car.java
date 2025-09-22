package main.java.com.vijay.ridesharingapp.models;

import main.java.com.vijay.ridesharingapp.enums.VehicleType;

public class Car extends Vehicle{

    public Car(String licensePlate, int capacity, String color, Location location) {
        super(licensePlate, capacity, color, VehicleType.CAR, location);
    }

}
