package main.java.com.vijay.ridesharingapp.models;

import main.java.com.vijay.ridesharingapp.enums.RideStatus;
import main.java.com.vijay.ridesharingapp.enums.VehicleType;
import main.java.com.vijay.ridesharingapp.payment.PaymentContext;

public class Ride {
    private final Passenger passenger;
    private final Driver driver;
    private PaymentContext paymentContext;
    private Location startPoint;
    private Location destination;
    private RideStatus rideStatus;
    private long startTime;
    private long endTime;
    private double fare;

    public Ride(Passenger passenger, Driver driver, PaymentContext paymentContext, Location startPoint, Location destination) {
        if (passenger == null || driver == null || paymentContext == null) {
            throw new IllegalArgumentException("Passenger, driver, and payment context cannot be null");
        }

        this.passenger = passenger;
        this.driver = driver;
        this.paymentContext = paymentContext;
        this.startPoint = startPoint;
        this.destination = destination;
        this.rideStatus = RideStatus.PENDING;
    }

    public void startRide(long startTime) {
        if (rideStatus != RideStatus.PENDING) {
            throw new IllegalStateException("Ride cannot be started. Current status: " + rideStatus);
        }
        if (startPoint == null || destination == null) {
            throw new IllegalStateException("Start point and destination must be set before starting the ride.");
        }
        if (startTime <= 0) {
            throw new IllegalArgumentException("Start time must be a positive value.");
        }
        this.startTime = startTime;
        updateStatus(RideStatus.ONGOING);
    }


    public void endRide(long endTime) {
        if (rideStatus != RideStatus.ONGOING) {
            throw new IllegalStateException("Ride cannot be ended. Current status: " + rideStatus);
        }
        if (startTime <= 0) {
            throw new IllegalStateException("Ride has not started yet.");
        }
        if (endTime < startTime) {
            throw new IllegalArgumentException("End time cannot be before start time.");
        }

        this.endTime = endTime;
        updateStatus(RideStatus.COMPLETED);
    }


    public void calculateFare() {
        if (startPoint == null || destination == null) {
            throw new IllegalStateException("Start point or destination is not set.");
        }
        if (paymentContext == null) {
            throw new IllegalStateException("Payment context is not set.");
        }

        double distance = startPoint.distanceTo(destination);
        if (distance < 0) {
            throw new IllegalStateException("Calculated distance is invalid.");
        }

        this.fare = paymentContext.calculateFare(distance, this.getVehicleType());
    }

    public void updateStatus(RideStatus newStatus) {
        if (newStatus == null) {
            throw new IllegalArgumentException("New status cannot be null");
        }
        if (newStatus == this.rideStatus) {
            return; // No change
        }
        if (newStatus == RideStatus.ONGOING && this.rideStatus != RideStatus.PENDING || newStatus == RideStatus.COMPLETED && this.rideStatus != RideStatus.ONGOING) {
            throw new IllegalStateException("Ride can only move to ONGOING from PENDING");
        }

        if (this.rideStatus == RideStatus.COMPLETED) {
            this.driver.setAvailable(true);
        } else if (this.rideStatus == RideStatus.ONGOING) {
            this.driver.setAvailable(false);
        }
        this.rideStatus = newStatus;
        notifyUsers(this.rideStatus);
    }

    private void notifyUsers(RideStatus status) {
        driver.notify("Ride status updated to: " + status);
        passenger.notify("Ride status updated to: " + status);
    }

    public double getFare() {
        if (this.fare <= 0) {
            throw new IllegalStateException("Fare has not been calculated yet.");
        }
        return fare;
    }

    public VehicleType getVehicleType() {
        Vehicle activeVehicle = driver.getActiveVehicle();
        if (activeVehicle == null) {
            throw new IllegalStateException("Driver does not have an active vehicle.");
        }
        return activeVehicle.getType();
    }


    public Passenger getPassenger() {
        return passenger;
    }

    public Driver getDriver() {
        return driver;
    }

    public PaymentContext getPaymentContext() {
        return paymentContext;
    }

    public void setPaymentContext(PaymentContext paymentContext) {
        this.paymentContext = paymentContext;
    }

    public Location getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Location startPoint) {
        this.startPoint = startPoint;
    }

    public Location getDestination() {
        return destination;
    }

//    public void setDestination(Location destination) {
//        if (destination == null) throw new IllegalArgumentException("Destination cannot be null");
//        this.destination = destination;
//    }

    public RideStatus getRideStatus() {
        return rideStatus;
    }


    public long getStartTime() {
        return startTime;
    }


    public long getEndTime() {
        return endTime;
    }


}
