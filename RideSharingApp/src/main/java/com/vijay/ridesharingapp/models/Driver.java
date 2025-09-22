package main.java.com.vijay.ridesharingapp.models;

import main.java.com.vijay.ridesharingapp.enums.VehicleType;

import java.util.ArrayList;
import java.util.List;

public class Driver extends User{
    private List<Vehicle> vehicles;
    private Vehicle activeVehicle;
    private boolean available;

    public Driver(String id, String name, List<Vehicle> vehicles, Vehicle activeVehicle) {
        super(id, name);

        if ((vehicles == null || vehicles.isEmpty()) && activeVehicle == null) {
            throw new IllegalArgumentException("Driver must have at least one vehicle or provide an active vehicle.");
        }

        this.vehicles = new ArrayList<>();

        // Process vehicles list
        if (vehicles != null) {
            for (Vehicle v : vehicles) {
                if (v.getOwner() != null && v.getOwner() != this) {
                    throw new IllegalStateException("Vehicle " + v.getLicensePlate() + " already belongs to another driver.");
                }
                v.setOwner(this); // Assign ownership
                this.vehicles.add(v);
            }
        }

        // Handle active vehicle
        if (activeVehicle != null) {
            if (activeVehicle.getOwner() != null && activeVehicle.getOwner() != this) {
                throw new IllegalStateException("Active vehicle " + activeVehicle.getLicensePlate() + " already belongs to another driver.");
            }
            if (!this.vehicles.contains(activeVehicle)) {
                activeVehicle.setOwner(this);
                this.vehicles.add(activeVehicle);
            }
        }

        // Set the active vehicle (either given or first in list)
        this.activeVehicle = (activeVehicle != null) ? activeVehicle : this.vehicles.get(0);

        this.available = true;
    }



    public void addVehicle(Vehicle newVehicle, boolean setAsActive) {
        if (newVehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null");
        }

        // Check if the vehicle already belongs to someone else
        if (newVehicle.getOwner() != null && newVehicle.getOwner() != this) {
            throw new IllegalStateException("Vehicle already belongs to another driver.");
        }

        if (this.vehicles == null) {
            this.vehicles = new ArrayList<>();
        }

        if (!this.vehicles.contains(newVehicle)) {
            this.vehicles.add(newVehicle);
            newVehicle.setOwner(this); // assign ownership
        }

        if (setAsActive) {
            this.activeVehicle = newVehicle;
        }
    }

    // Overloaded version with default setAsActive = false
    public void addVehicle(Vehicle newVehicle) {
        addVehicle(newVehicle, false);
    }

    public void removeVehicle(Vehicle vehicleToRemove) {
        if (vehicleToRemove == null || vehicles == null || vehicles.isEmpty()) {
            return; // nothing to remove
        }

        if (vehicles.size() == 1) {
            throw new IllegalStateException("Cannot remove the last vehicle. Driver must have at least one vehicle.");
        }

        if (vehicles.remove(vehicleToRemove)) {
            // clear ownership
            vehicleToRemove.setOwner(null);

            // If the removed vehicle was active, pick a new active vehicle
            if (vehicleToRemove.equals(activeVehicle)) {
                activeVehicle = vehicles.get(0);
            }
        }
    }


    public List<Vehicle> getVehicles() {
        return new ArrayList<>(vehicles);
    }

    public Vehicle getActiveVehicle() {
        return activeVehicle;
    }

    public void setActiveVehicle(Vehicle vehicle) {
        if (vehicle != null && !vehicles.contains(vehicle)) {
            throw new IllegalArgumentException("Active vehicle must belong to driver's vehicles list.");
        }
        this.activeVehicle = vehicle;
    }


    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public void notify(String message) {
        System.out.println(message);
    }
}
