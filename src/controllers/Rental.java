package controllers;

public class Rental {
    private int id;
    private int carId;

    public Rental(int id, int carId) {
        this.id = id;
        this.carId = carId;
    }

    public int getId() { return id; }
    public int getCarId() { return carId; }
}
