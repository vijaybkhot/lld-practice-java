package main.java.com.vijay.ridesharingapp.enums;

// VehicleType.java
public enum VehicleType {
    CAR(12.0),
    BIKE(8.0),
    LUXURY_CAR(20.0);

    private final double ratePerKm;

    VehicleType(double ratePerKm) {
        this.ratePerKm = ratePerKm;
    }

    public double getRatePerKm() {
        return ratePerKm;
    }
}
