package main.java.com.vijay.ridesharingapp.payment;

import main.java.com.vijay.ridesharingapp.enums.VehicleType;

public class LuxuryPaymentStrategy implements PaymentStrategy{
    public double calculateFare(double distance, VehicleType vehicleType) {
        return distance * vehicleType.getRatePerKm() * 1.5; // 50% extra for luxury rides
    }
}
