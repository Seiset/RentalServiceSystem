package controllers;

public class Car {
    private int id;
    private String brand;
    private String model;
    private double price;
    private boolean rented;

    public Car(int id, String brand, String model, double price, String status) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.rented = status.equalsIgnoreCase("RENTED");
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    public String getStatus() {
        return rented ? "RENTED" : "AVAILABLE";
    }

    @Override
    public String toString() {
        return id + " | " + brand + " | " + model + " | " + price + " | " +
                (rented ? "RENTED" : "AVAILABLE");
    }
}
