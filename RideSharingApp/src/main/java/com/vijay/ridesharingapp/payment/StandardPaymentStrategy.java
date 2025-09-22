package main.java.com.vijay.ridesharingapp.payment;

import main.java.com.vijay.ridesharingapp.enums.VehicleType;

public class StandardPaymentStrategy implements PaymentStrategy{

    public double calculateFare(double distance, VehicleType vehicleType) {
        return distance * vehicleType.getRatePerKm();
    }


}
