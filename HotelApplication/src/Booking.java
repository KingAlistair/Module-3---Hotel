import org.w3c.dom.ls.LSOutput;

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
        System.out.println("----------------------------------------------" + "\n" +
                "Booking ID: " + id + "\n" +
                room + "\n" +
                "Guest: ");

        for (Guest guest: guestList
             ) {
            System.out.println(guest.getFirstName() + " " + guest.getLastName());
        }

        System.out.println(
                "Check in: " + startDate + "\n" +
                        "Check out: " + endDate + "\n" +
                        "Amount of nights: " + calculateLengthOfStay() + "\n" +
                        "Full price: " + fullPrice + "DKK" + "\n" +
                        "----------------------------------------------");
    }

    //can't really use this though ):
    public void bookingReceipt() {
        double fullPrice = calculateLengthOfStay() * room.getPrice();

        System.out.println("\"-------------------------------------------------\"");
        System.out.println(String.format("%-50s%s" ,"|","|"));
        System.out.println(String.format("%-50s%s" ,"|","|"));
        System.out.println("|                                                 |");
        System.out.println("| =============================================== |");
        System.out.println("|      H O T E L   M Å S K E P A R A D I S E      |");
        System.out.println("| =============================================== |");
        System.out.println(String.format("%-50s%s" ,"|  Booking ID: " + id, "|"));
        System.out.println(String.format("%-50s%s" ,"|  Check in: " + startDate, "|"));
        System.out.println(String.format("%-50s%s" ,"|  Check out: " + endDate, "|"));
        if(guestList.size() > 1){
            System.out.println("|  Guests:                                        |");
        }
        else{
            System.out.println("|  Guest:                                         |");
        }
        for (int i = 0; i < guestList.size(); i++) {
            System.out.println(String.format("%-50s%s" ,"|  " + (i + 1) + ": " + guestList.get(i).getFirstName() + " " + guestList.get(i).getLastName(), "|"));
        }

        System.out.println(String.format("%-50s%s" ,"|  Amount of nights: " + calculateLengthOfStay(), "|"));
        System.out.println("| =============================================== |");
        System.out.println(String.format("%-50s%s" ,"|  Full price: " + fullPrice + " DKK", "|"));
        System.out.println("| =============================================== |");
        System.out.println("|                                                 |");
        System.out.println("|                                                 |");
        System.out.println("|    Thank you for staying with Måskeparadise!    |");
        System.out.println("|                                                 |");
        System.out.println("\"-------------------------------------------------\"\n");
    }

    public static void printBookingList(ArrayList<Booking> bookingList) {
        for (Booking booking : bookingList) {
            booking.bookingReceipt();
        }
    }
}

