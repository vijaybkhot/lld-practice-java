package main.java.com.vijay.ridesharingapp.payment;

import main.java.com.vijay.ridesharingapp.enums.VehicleType;

public class PaymentContext {
    private PaymentStrategy strategy;

    public PaymentContext(PaymentStrategy paymentStrategy) {
        this.strategy = paymentStrategy;
    }

    public PaymentStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public double calculateFare(double distance, VehicleType vehicleType) {
        return strategy.calculateFare(distance, vehicleType);
    }
}
