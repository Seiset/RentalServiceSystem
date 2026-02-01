package models;

public class Rental {
    private int id;
    private int userId;
    private int carId;

    public Rental(int id, int userId, int carId) {
        this.id = id;
        this.userId = userId;
        this.carId = carId;
    }

    public int getUserId() { return userId; }
    public int getCarId() { return carId; }
}
