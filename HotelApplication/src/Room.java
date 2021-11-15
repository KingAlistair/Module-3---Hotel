public class Room {

    private int roomNumber;
    private int amountOfBeds;
    private boolean wifi;
    private double price;

    public Room(int roomNumber, int amountOfBeds, boolean wifi, double price) {
        this.roomNumber = roomNumber;
        this.amountOfBeds = amountOfBeds;
        this.wifi = wifi;
        this.price = price;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
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
}
