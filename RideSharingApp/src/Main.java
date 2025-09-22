import main.java.com.vijay.ridesharingapp.enums.VehicleType;
import main.java.com.vijay.ridesharingapp.models.*;
import main.java.com.vijay.ridesharingapp.payment.*;
import main.java.com.vijay.ridesharingapp.services.RideSharingAppService;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        RideSharingAppService service = new RideSharingAppService();

        // Define locations
        Location driverLoc1 = new Location(12.9716, 77.5946);
        Location driverLoc2 = new Location(12.9616, 77.5846);
        Location passengerLoc1 = new Location(12.9352, 77.6245);
        Location passengerLoc2 = new Location(12.9400, 77.6200);
        Location passengerLoc3 = new Location(12.9500, 77.6300);
        Location destination = new Location(12.9279, 77.6271);

        // Create vehicles and drivers
        Car car1 = new Car("KA01AB1234", 4, "White", driverLoc1);
        Car car2 = new Car("KA02CD5678", 4, "Black", driverLoc2);
        Driver driver1 = new Driver("D1", "John Doe", List.of(car1), car1);
        Driver driver2 = new Driver("D2", "Jane Smith", List.of(car2), car2);
        service.addDriver(driver1);
        service.addDriver(driver2);

        // Create passengers
        Passenger p1 = new Passenger("P1", "Alice");
        Passenger p2 = new Passenger("P2", "Bob");
        Passenger p3 = new Passenger("P3", "Charlie");
        service.addPassenger(p1);
        service.addPassenger(p2);
        service.addPassenger(p3);

        // Payment context
        PaymentContext context = new PaymentContext(new StandardPaymentStrategy());

        // Book rides
        System.out.println("\nBooking for Alice:");
        service.bookRide(p1, passengerLoc1, destination, VehicleType.CAR, context);

        System.out.println("\nBooking for Bob:");
        service.bookRide(p2, passengerLoc2, destination, VehicleType.CAR, context);

        System.out.println("\nBooking for Charlie (expect no drivers available):");
        service.bookRide(p3, passengerLoc3, destination, VehicleType.CAR, context);
    }
}