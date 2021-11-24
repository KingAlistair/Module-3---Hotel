import java.time.LocalDate;
import java.util.ArrayList;

import static java.time.temporal.ChronoUnit.DAYS;

public class Booking implements java.io.Serializable {
    private int id;
    private ArrayList<Guest> guestList;
    private Room room;
    private LocalDate startDate;
    private LocalDate endDate;
    private double endPrice;
    private int numberOfNights;

    public Booking(int id, ArrayList<Guest> guestList, Room room, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.guestList = guestList;
        this.room = room;
        //do it in a separate class
        this.startDate = startDate;
        this.endDate = endDate;
        this.endPrice = 0;
        this.numberOfNights = 0;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
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


    public int calculateLengthOfStay() {
        return (int) DAYS.between(getStartDate(), getEndDate());
    }

    public void printBooking() {
        double fullPrice = calculateLengthOfStay() * room.getPrice();
        System.out.println("----------------------------------------------" +
                           "Booking ID: " + id + "\n" +
                            room + "\n" +
                           "Check in: " + startDate + "\n" +
                           "Check out: " + endDate +"\n" +
                           "Amount of nights: " + calculateLengthOfStay() + "\n" +
                           "Full price: " + fullPrice + "\n" +
                           "----------------------------------------------");
    }

    public static void printBookingList(ArrayList<Booking> bookingList) {
        for (Booking booking : bookingList) {
            booking.printBooking();
        }
    }
}

