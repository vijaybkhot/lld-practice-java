package main.java.com.vijay.ridesharingapp.payment;

import main.java.com.vijay.ridesharingapp.enums.VehicleType;

public class SharingPaymentStrategy implements PaymentStrategy{

    public double calculateFare(double distance, VehicleType vehicleType) {
        return distance*vehicleType.getRatePerKm()*0.75; // 25% discount for ride sharing
    }
}
