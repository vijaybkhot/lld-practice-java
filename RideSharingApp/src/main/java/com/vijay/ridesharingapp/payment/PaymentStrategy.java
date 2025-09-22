package main.java.com.vijay.ridesharingapp.payment;

import main.java.com.vijay.ridesharingapp.enums.VehicleType;

public interface PaymentStrategy {
    double calculateFare(double distance, VehicleType vehicleType);
}
