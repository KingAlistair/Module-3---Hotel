public class Room implements java.io.Serializable {

    private int roomNumber;
    private int floor;
    private int amountOfBeds;
    private boolean wifi;
    private double price;
    private boolean suite;

    public Room(int roomNumber, int floor, int amountOfBeds, boolean wifi, double price, boolean suite) {
        this.roomNumber = roomNumber;
        this.floor = floor;
        this.amountOfBeds = amountOfBeds;
        this.wifi = wifi;
        this.price = price;
        this.suite = suite;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getAmountOfBeds() {
        return amountOfBeds;
    }

    public void setAmountOfBeds(int amountOfBeds) {
        this.amountOfBeds = amountOfBeds;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isSuite() {
        return suite;
    }

    public void setSuite(boolean suite) {
        this.suite = suite;
    }


}
