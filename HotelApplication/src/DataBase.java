import java.io.Serializable;
import java.util.ArrayList;

public class DataBase implements java.io.Serializable {
    private ArrayList<Staff> staffList;
    private ArrayList<Guest> guestList;
    private ArrayList<Room> roomList;

    public DataBase(){
        this.staffList = defaultStaffList();
        this.guestList = defaultGuestList();
        this.roomList = defaultRoomList();
    }



    public static ArrayList<Staff> defaultStaffList(){
        ArrayList<Staff> staffList = new ArrayList<>();
        Staff staff1 = new Staff("Fé","Santa", "the cleaning lady", 19999, "+55123456789");
        Staff staff2 = new Staff("Mu","Bíbic", "the director", 199999, "+45123456789");
        Staff staff3 = new Staff("Danielle","DelSartó", "the accountant", 199999.9, "+36123456789");
        Staff staff4 = new Staff("Bogi","Wogi", "the receptionist", 1999.9, "+36123456788");

        staffList.add(staff1);
        staffList.add(staff2);
        staffList.add(staff3);
        staffList.add(staff4);

        return staffList;
    }

    public static ArrayList<Guest> defaultGuestList(){
        ArrayList<Guest> guestList = new ArrayList<>();

        Guest guest1 =new Guest("BarbaPappa","Pape-Poulsen","Pappagade 12", "+45 76525434");
        Guest guest2 =new Guest("Moomin","Troll","Moomin valley 1", "+358 145677431");
        Guest guest3 =new Guest("Bobobo-bo","Bo-Bobo","Goldennoselane 27", "+81 23391263");
        Guest guest4 =new Guest("Mekk","Elek","ház", "+36 23532768");
        Guest guest5 =new Guest("Bob","Fenékpuszta", "Visznek 32", "+36 5626244764");
        Guest guest6 =new Guest("Fofão","Ay","R. Do Sol 64", "+55 23678321");
        Guest guest7 =new Guest("Eva","Johanson","Vestvej 5", "+45 29314562");
        Guest guest8 =new Guest("Saga","Lorein","Knuttsgata 15 ", "+46 35562465");

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
    public static ArrayList<Room> defaultRoomList(){
        ArrayList<Room> roomList = new ArrayList<>();

        Room room1 = new Room(66,6,3,true,10666,true);
        Room room2 = new Room(32,3,3,false,323,false);
        Room room3 = new Room(1,1,1,false,100,true);
        Room room4 = new Room(42,4,2,true,4242,true);
        Room room5 = new Room(24,2,1,false,124,false);
        Room room6 = new Room(69,6,1,true,10669,true);
        Room room7 = new Room(5,5,2,true,5300,true);

        roomList.add(room1);
        roomList.add(room2);
        roomList.add(room3);
        roomList.add(room4);
        roomList.add(room5);
        roomList.add(room6);
        roomList.add(room7);

        return roomList;

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
}
