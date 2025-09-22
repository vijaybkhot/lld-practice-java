package main.java.com.vijay.ridesharingapp.services;

import main.java.com.vijay.ridesharingapp.enums.VehicleType;
import main.java.com.vijay.ridesharingapp.models.Driver;
import main.java.com.vijay.ridesharingapp.models.Location;
import main.java.com.vijay.ridesharingapp.models.Passenger;
import main.java.com.vijay.ridesharingapp.models.Ride;
import main.java.com.vijay.ridesharingapp.payment.PaymentContext;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RideSharingAppService {

    private List<Driver> drivers;
    private List<Passenger> passengers;

    public RideSharingAppService() {
        this.drivers = new ArrayList<>();
        this.passengers = new ArrayList<>();
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void addPassenger(Passenger passenger) {
        this.passengers.add(passenger);
    }

    public List<Driver> getDrivers() {
        return drivers;
    }
    public void addDriver(Driver driver) {
        this.drivers.add(driver);
    }

//    search for nearby rides

    public Ride bookRide(Passenger passenger, Location startPoint, Location destination, VehicleType vehicleType, PaymentContext paymentContext) {
        if (!passengers.contains(passenger)) {
            throw new IllegalArgumentException("Passenger is not registered with the ride service");
        }
        if (drivers == null || drivers.isEmpty()) {
            System.out.println("❌ No drivers available for " + passenger.getName());
            return null;
        }
        Driver nearestDriver = getNearestDriver(startPoint, vehicleType);
        if (nearestDriver == null) {
            System.out.println("❌ No drivers available for vehicle type: " + vehicleType);
            return null;
        }
        // Create Ride
        Ride ride = new Ride(passenger, nearestDriver, paymentContext, startPoint, destination);

        // Calculate Fare
        ride.calculateFare();

        // Start Ride (set ride status and time)
        ride.startRide(System.currentTimeMillis());

        //  Notify both passenger and driver
        String message = "Ride booked with " + nearestDriver.getName() + ". Estimated fare: $" + ride.getFare() + " For Vehicle: " + nearestDriver.getActiveVehicle().getType() + " With License Plate: " + nearestDriver.getActiveVehicle().getLicensePlate();
        passenger.notify(message);
        nearestDriver.notify("New ride assigned to you with passenger " + passenger.getName() + ". Pickup at: " + startPoint + " Drop at: " + destination);


        ride.endRide(System.currentTimeMillis() + 30 * 60 * 1000);
        return ride;
    }


    // Find nearest driver with matching vehicle type
    private Driver getNearestDriver(Location startPoint, VehicleType vehicleType) {
        Driver nearestDriver = drivers.stream()
                .filter(Driver::isAvailable)
                .filter(driver -> driver.getActiveVehicle() != null &&
                        driver.getActiveVehicle().getType() == vehicleType)
                .min(Comparator.comparingDouble(
                        driver -> driver.getActiveVehicle().getLocation().distanceTo(startPoint)
                ))
                .orElse(null);
        return nearestDriver;
    }


}
