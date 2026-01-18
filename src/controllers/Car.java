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
