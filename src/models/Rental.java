
package models;

public class Rental {
    private int id;
    private int carId;
    private String rentaltime;
    private String exptime;

    public Rental( String rentaltime,String exptime) {
        this.rentaltime = rentaltime;
        this.exptime=exptime;
    }

    public int getId() { return id; }
    public int getCarId() { return carId; }
}
