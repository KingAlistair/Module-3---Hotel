import java.time.LocalDate;
import java.util.ArrayList;

public class DataBase implements java.io.Serializable {
    private ArrayList<Staff> staffList;
    private ArrayList<Guest> guestList;
    private ArrayList<Room> roomList;
    private ArrayList<Booking> bookingList;

    public DataBase() {
        this.staffList = defaultStaffList();
        this.guestList = defaultGuestList();
        this.roomList = defaultRoomList();
        this.bookingList = defaultBookingList();
    }


    public static ArrayList<Staff> defaultStaffList() {
        ArrayList<Staff> staffList = new ArrayList<>();
        Staff staff1 = new Staff(1, "Fé", "Santa", "the cleaning lady", 19999, "+55123456789");
        Staff staff2 = new Staff(2, "Mu", "Bíbic", "the director", 199999, "+45123456789");
        Staff staff3 = new Staff(3, "Danielle", "DelSartó", "the accountant", 199999.9, "+36123456789");
        Staff staff4 = new Staff(4, "Bogi", "Wogi", "the receptionist", 1999.9, "+36123456788");

        staffList.add(staff1);
        staffList.add(staff2);
        staffList.add(staff3);
        staffList.add(staff4);

        return staffList;
    }

    public static ArrayList<Guest> defaultGuestList() {
        ArrayList<Guest> guestList = new ArrayList<>();

        Guest guest1 = new Guest(1, "BarbaPappa", "Pape-Poulsen", "Pappagade 12", "+45 76525434");
        Guest guest2 = new Guest(2, "Moomin", "Troll", "Moomin valley 1", "+358 145677431");
        Guest guest3 = new Guest(3, "Bobobo-bo", "Bo-Bobo", "Goldennoselane 27", "+81 23391263");
        Guest guest4 = new Guest(4, "Mekk", "Elek", "ház", "+36 23532768");
        Guest guest5 = new Guest(5, "Bob", "Fenékpuszta", "Visznek 32", "+36 5626244764");
        Guest guest6 = new Guest(6, "Fofão", "Ay", "R. Do Sol 64", "+55 23678321");
        Guest guest7 = new Guest(7, "Eva", "Johanson", "Vestvej 5", "+45 29314562");
        Guest guest8 = new Guest(8, "Saga", "Lorein", "Knuttsgata 15 ", "+46 35562465");

        guestList.add(guest1);
        guestList.add(guest2);
        guestList.add(guest3);
        guestList.add(guest4);
        guestList.add(guest5);
        guestList.add(guest6);
        guestList.add(guest7);
        guestList.add(guest8);

        return guestList;
    }

    public static ArrayList<Room> defaultRoomList() {
        ArrayList<Room> roomList = new ArrayList<>();


        Room room1 = new Room(11, 3, 1, "Single bed", false, 323);
        Room room2 = new Room(12, 1, 1, "Single bed", false, 100);
        Room room3 = new Room(13, 2, 1,"Single bed", false, 124);
        Room room4 = new Room(22, 4, 2, "Double bed", true, 4242);
        Room room5 = new Room(31, 6, 2, "Suite",true, 10669);
        Room room6 = new Room(32, 6, 3, "Suite", true, 10666);
        Room room7 = new Room(33, 5, 2, "Suite", true, 5300);

        roomList.add(room1);
        roomList.add(room2);
        roomList.add(room3);
        roomList.add(room4);
        roomList.add(room5);
        roomList.add(room6);
        roomList.add(room7);

        return roomList;

    }

    private static ArrayList<Booking> defaultBookingList(){
        ArrayList<Booking> bookingList = new ArrayList();
        ArrayList<Guest> guestList = defaultGuestList();
        ArrayList<Room> roomList = defaultRoomList();

        ArrayList<Guest> currentGuest = new ArrayList<>();
        currentGuest.add(guestList.get(0));
        currentGuest.add(guestList.get(7));

        LocalDate startDate = LocalDate.of(2021,12,01);
        LocalDate endDate = LocalDate.of(2021,12,10);

        Booking booking1 = new Booking(1, currentGuest, roomList.get(5), startDate, endDate);

        currentGuest = new ArrayList<>();
        currentGuest.add(guestList.get(3));
        currentGuest.add(guestList.get(4));

        startDate = LocalDate.of(2021,12,11);
        endDate = LocalDate.of(2021,12,20);

        Booking booking2 = new Booking(2, currentGuest, roomList.get(5), startDate, endDate);

        currentGuest = new ArrayList<>();
        currentGuest.add(guestList.get(5));
        currentGuest.add(guestList.get(6));

        startDate = LocalDate.of(2022,01,01);
        endDate = LocalDate.of(2022,01,31);

        Booking booking3 = new Booking(3, currentGuest, roomList.get(6),startDate, endDate );

        bookingList.add(booking1);
        bookingList.add(booking2);
        bookingList.add(booking3);

        return bookingList;
    }

    public void setStaffList(ArrayList<Staff> staffList) {
        this.staffList = staffList;
    }

    public ArrayList<Staff> getStaffList() {
        return staffList;
    }

    public void setGuestList(ArrayList<Guest> guestList) {
        this.guestList = guestList;
    }

    public ArrayList<Guest> getGuestList() {
        return guestList;
    }

    public void setRoomList(ArrayList<Room> roomList) {
        this.roomList = roomList;
    }

    public ArrayList<Room> getRoomList() {
        return roomList;
    }

    public void setBookingList(ArrayList<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    public ArrayList<Booking> getBookingList() {
        return bookingList;
    }
}
