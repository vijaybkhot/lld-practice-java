package main.java.com.vijay.ridesharingapp.models;

public class Passenger extends User{
    public Passenger(String id, String name) {
        super(id, name);

    }

    @Override
    public void notify(String message) {
        System.out.println(message);
    }
}
