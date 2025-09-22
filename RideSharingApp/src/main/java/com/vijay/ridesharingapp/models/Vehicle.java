package main.java.com.vijay.ridesharingapp.models;

import main.java.com.vijay.ridesharingapp.enums.VehicleType;

public abstract class Vehicle {
    private String licensePlate;
    private int capacity;
    private String color;


    private Location location;
    private final VehicleType type;

    private Driver owner;



    public Vehicle(String licensePlate, int capacity, String color, VehicleType type, Location location) {
        this.licensePlate = licensePlate;
        this.capacity = capacity;
        this.color = color;
        this.type = type;
        this.location = location;
    }

    public Driver getOwner() {
        return owner;
    }



    public void setOwner(Driver owner) {
        if (this.owner != null && owner != null) {
            throw new IllegalStateException("Vehicle is already assigned to a driver: " + this.owner.getName());
        }
        this.owner = owner;
    }

    public void removeOwner() {
        this.owner = null;
    }

    public VehicleType getType() {
        return type;
    }
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
    public String getLicensePlate() {
        return licensePlate;
    }

    public double getRatePerKm() {
        return type.getRatePerKm();
    }
}
