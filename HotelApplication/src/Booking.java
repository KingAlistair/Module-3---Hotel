import java.util.ArrayList;

public class Booking {
    private int id;
    private ArrayList<Guest> guestList;
    private Room room;
    private String startDate;
    private String endDate;
    private double endPrice;
    private int numberOfNights;

    public Booking(int id, ArrayList<Guest> guestList, Room room, String startDate, String endDate, double endPrice, int numberOfNights) {
        this.id = id;
        this.guestList = guestList;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
        this.endPrice = endPrice;
        this.numberOfNights = numberOfNights;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Guest> getGuestList() {
        return guestList;
    }

    public void setGuestList(ArrayList<Guest> guestList) {
        this.guestList = guestList;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public double getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(double endPrice) {
        this.endPrice = endPrice;
    }

    public int getNumberOfNights() {
        return numberOfNights;
    }

    public void setNumberOfNights(int numberOfNights) {
        this.numberOfNights = numberOfNights;
    }
}

