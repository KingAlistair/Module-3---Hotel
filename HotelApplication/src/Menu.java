import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Menu {
    //--------------------------------MAIN MENU------------------------------------------
    public void mainMenu() {
        System.out.println();
        System.out.println("======================================================");
        System.out.println("        H O T E L   M Å S K E P A R A D I S E  ");
        System.out.println("======================================================");
        System.out.println("                1 Administer Booking");
        System.out.println("                2 Administer Staff ");
        System.out.println("                3 Administer Guests");
        System.out.println("                4 Administer Room");
        System.out.println("                Press \"r\" to reset Database");
        System.out.println("                5 to quit");
        System.out.println("======================================================");
        System.out.println();
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        switch (input) {
            case "1":
                administerBooking();
                break;

            case "2":
                administerStaff();
                break;

            case "3":
                administerGuest();
                break;

            case "r":
                DataBase dataBase = new DataBase();
                FileIo.databaseSerialization(dataBase);
                System.out.println("Database has been reset!");
                mainMenu();
                break;

            case "5":
                System.exit(1);
                break;

            default:
                System.out.println("Wrong input, returning to <Main Menu> ");
                mainMenu();
                break;
        }
    }

    //------------------------------BOOKING MENU PART--------------------------------------
    public void administerBooking() {

        DataBase dataBase = FileIo.databaseDeserialization();
        ArrayList<Booking> bookingList = dataBase.getBookingList();

        System.out.println("======================================================");
        System.out.println("              B O O K I N G  M E N U ");
        System.out.println("======================================================");
        System.out.println("              1. Create booking");
        System.out.println("              2. Manage booking");
        System.out.println("              3. Delete booking");
        System.out.println("              4. Show all bookings" + "\n");
        System.out.println("         Press \"enter\" to exit to main menu ");
        System.out.println("======================================================");
        System.out.println();
        System.out.println("Please input choice: ");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        switch (input) {
            case "1":
                createBooking();
                administerBooking(); //do we need this when we already have it in the method?
                break;

            case "2":
                manageBooking();
                break;
            case "3":
                deleteBooking();
                break;

            case "4":
                Booking.printBookingList(dataBase.getBookingList());
                administerBooking();
                break;

            case "":
                mainMenu();
                break;

            default:
                System.out.println("Wrong input, please try again! ");
                administerStaff();
                break;
        }

    }

    public void createBooking() {
        //Get database from file
        DataBase dataBase = FileIo.databaseDeserialization();
        ArrayList<Guest> guestList = dataBase.getGuestList();
        ArrayList<Room> roomList = dataBase.getRoomList();
        ArrayList<Booking> bookingList = dataBase.getBookingList();

        //maybe we print out available rooms before input?
        Room.printRoomList(roomList);

        //Create room
        System.out.println("======================================================");
        //should we just get the room by the ID?
        System.out.println("Please choose room number");
        // get user input to choose a room number
        int roomNum = intInput();
        Room room = null;

        for (Room roomArr: roomList
             ) {
            if(roomNum == roomArr.getRoomNumber()){
                room = roomArr;
            }
        }
        //print out list of guests
        Guest.printGuestList(guestList); //- Why do we print here? to show the guestlist to choose from - couldn't we just put the creating guest method here?
        ArrayList<Guest> currentGuest = new ArrayList<>();

        System.out.println("Please input name of Guest");
        // input the guest first name
        Guest newGuest = null;
        String guestName = stringInput();
        for (Guest guestArr : guestList
             ) {
            if(guestName == guestArr.getFirstName()){
               newGuest = guestArr;
            }
        }
        currentGuest.add(newGuest);

        System.out.println("Please input check in date");
        int startDate = intInput();
        System.out.println("Please input check out date");
        int endDate = intInput();

        //should we perhaps calculate price and number of nights and just give the info?
        System.out.println("======================================================");

        //Create Id
        int id = -1;
        int checkId = 1;

        //Going through booking list to findID
        for (Booking booking : bookingList) {
            if (checkId == booking.getId()) {
                checkId++;
            } else {
                id = checkId;
            }
        }
        if (id == -1) {
            id = bookingList.size() + 1;
        }

        LocalDate start = LocalDate.of(2021,12,startDate);
        LocalDate end = LocalDate.of(2021,12,endDate);

        Booking booking = new Booking(id, currentGuest , room, start, end);
        booking.printBooking();
        bookingList.add(booking);


        //Organize bookingList
        ArrayList<Integer> idList = new ArrayList<>();
        for (Booking b : bookingList) {
            idList.add(b.getId());
        }
        Collections.sort(idList);
        ArrayList<Booking> organisedBookingList = new ArrayList<>();
        for (int i : idList) {
            for (Booking b : bookingList) {
                if (i == b.getId()) {
                    organisedBookingList.add(b);
                }
            }
        }

        //Saving it into file
        dataBase.setBookingList(organisedBookingList);
        FileIo.databaseSerialization(dataBase);

        //returns to administer booking
        administerBooking();
    }

    public void manageBooking() {

        DataBase dataBase = FileIo.databaseDeserialization();
        ArrayList<Guest> guestList = dataBase.getGuestList();
        ArrayList<Room> roomList = dataBase.getRoomList();
        ArrayList<Booking> bookingList = dataBase.getBookingList();

        //chose ID of staff we want to change
        System.out.println("Chose the ID of the Booking you want to change: ");
        int inputID = intInput() - 1;
        Booking booking = dataBase.getBookingList().get(inputID);

        System.out.println();
        System.out.println("======================================================");
        System.out.println("            1. Change name of guest");
        System.out.println("            2. Change room"         );
        System.out.println("            3. Change check in date");
        System.out.println("            4. Change check out date" + "\n");
        System.out.println("      Press enter to exit to administer booking");
        System.out.println("======================================================");
        System.out.println();

        switch (stringInput()) {
            case "1":
                System.out.println("Please input new guest" + "\n");
                break;
            case "2":
                System.out.println("Please input new room \n");
                break;
            case "3":
                System.out.println("Please input new check in date \n");
                break;
            case "4":
                System.out.println("Please input new check out date" + "\n");
                break;
            case "":
                administerBooking();
                break;

            default:
                System.out.println("Wrong input, please try again! ");
                manageBooking();
                break;
        }

        //We save the updated dataBase into File
        FileIo.databaseSerialization(dataBase);

        //returns to administer guest menu
        administerBooking();
    }

    public void deleteBooking() {

        //Get database from file
        DataBase dataBase = FileIo.databaseDeserialization();
        ArrayList<Guest> guestList = dataBase.getGuestList();
        ArrayList<Room> roomList = dataBase.getRoomList();
        ArrayList<Booking> bookingList = dataBase.getBookingList();

        System.out.println("Booking list: ");
        Booking.printBookingList(bookingList);
        //why wont it let me get the method?!?!??!     But it does >:) hehe thanks
        Booking.printBookingList(bookingList);



        System.out.println("Chose the ID of the booking you wish to remove: ");
        int ID = intInput();


        //remove if: for each booking, remove booking which equals the ID input by user
        bookingList.removeIf(booking -> booking.getId() == (ID));
        //we then set the bookingList
        dataBase.setBookingList(bookingList);
        //and serialise it
        FileIo.databaseSerialization(dataBase);

        System.out.println("Booking has now been removed!");

        //returns to administer booking menu
        administerBooking();
    }


    //-------------------------------STAFF MENU PART----------------------------------------
    public void administerStaff() {
        DataBase dataBase = FileIo.databaseDeserialization();
        System.out.println("======================================================");
        System.out.println("               S T A F F  M E N U ");
        System.out.println("======================================================");
        System.out.println("              1. Add new staff member");
        System.out.println("              2. Manage staff member");
        System.out.println("              3. Remove staff member");
        System.out.println("              4. Show all staff" + "\n");
        System.out.println("         Press \"enter\" to exit to main menu ");
        System.out.println("======================================================");
        System.out.println();
        System.out.println("Please input choice: ");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        switch (input) {
            case "1":
                createStaff();
                break;

            case "2":
                manageStaff();
                break;
            case "3":
                removeStaff();
                break;

            case "4":
                Staff.printStaffList(dataBase.getStaffList());
                administerStaff();
                break;

            case "":
                mainMenu();
                break;

            default:
                System.out.println("Wrong input, please try again! ");
                administerStaff();
                break;
        }

    }

    public void manageStaff() {
        //Get database from file
        DataBase dataBase = FileIo.databaseDeserialization();
        ArrayList<Staff> staffList = dataBase.getStaffList();

        Staff.printStaffList(staffList);

        //chose ID of staff we want to change
        System.out.println("Chose the ID of the Staff you want to administer: ");
        int inputID = intInput() - 1;
        Staff staff = dataBase.getStaffList().get(inputID);

        System.out.println();
        System.out.println("======================================================");
        System.out.println("       1. Change first name of staff member");
        System.out.println("       2. Change last name of staff member");
        System.out.println("       3. Change title of staff member");
        System.out.println("       4. Change phone number of staff member");
        System.out.println("       5. Change salary of staff member" + "\n");
        System.out.println("      Press enter to exit to administer staff");
        System.out.println("======================================================");
        System.out.println(

        );
        switch (stringInput()) {
            case "1":
                System.out.println("Please input new first name");
                staff.setFirstName(stringInput());
                manageStaff();
                break;
            case "2":
                System.out.println("Please input new last name");
                staff.setLastName(stringInput());
                manageStaff();
                break;
            case "3":
                System.out.println("Please input new title");
                staff.setTitle(stringInput());
                manageStaff();
                break;
            case "4":
                System.out.println("Please input new phone number");
                staff.setPhoneNumber(stringInput());
                break;
            case "5":
                System.out.println("Please input new salary");
                staff.setSalary(doubleInput());
                break;

            case "":
                administerStaff();
                break;

            default:
                System.out.println("Wrong input, please try again! ");
                administerStaff();
                break;
        }

        FileIo.databaseSerialization(dataBase);

        //returns to administer staff menu
        administerStaff();
    }

    //Creates staff, save it into file
    public void createStaff() {
        //Get database from file
        DataBase dataBase = FileIo.databaseDeserialization();
        ArrayList<Staff> staffList = dataBase.getStaffList();

        //Create Staff
        System.out.println("======================================================");
        System.out.println("            Please input first name: ");
        String firstName = stringInput();
        System.out.println("            Please input last name: ");
        String lastName = stringInput();
        System.out.println("            Please input title: ");
        String title = stringInput();
        System.out.println("            Please input phone number: ");
        String phoneNumber = stringInput();
        System.out.println("            Please input salary: ");
        double salary = doubleInput();
        System.out.println("======================================================");

        //Create Id
        int id = -1;
        int checkId = 1;

        //Traversing through staffList to find ID
        for (Staff staff : staffList) {
            if (checkId == staff.getId()) {
                checkId++;
            } else {
                id = checkId;
            }
        }
        if (id == -1) {
            id = staffList.size() + 1;
        }
        //Create new Staff object
        Staff staff = new Staff(id, firstName, lastName, title, salary, phoneNumber);
        staffList.add(staff);

        //Organize staffList
        ArrayList<Integer> idList = new ArrayList<>();
        for (Staff s : staffList) {
            idList.add(s.getId());
        }
        Collections.sort(idList);
        ArrayList<Staff> organisedStaffList = new ArrayList<>();
        for (int i : idList) {
            for (Staff s : staffList) {
                if (i == s.getId()) {
                    organisedStaffList.add(s);
                }
            }
        }
        //Save database into file
        dataBase.setStaffList(organisedStaffList);
        FileIo.databaseSerialization(dataBase);
        System.out.println("-Database is saved-");

        //Return main menu
        administerStaff();
    }


    public void removeStaff() {
        //Get database from file
        DataBase dataBase = FileIo.databaseDeserialization();
        ArrayList<Staff> staffList = dataBase.getStaffList();

        System.out.println("Staff list: ");
        Staff.printStaffList(staffList);

        System.out.println("Chose the ID of the staff member you wish to remove: ");
        int ID = intInput();


        //remove if: for each staff, remove staff which equals the ID input by user
        staffList.removeIf(staff -> staff.getId() == (ID));
        //we then set the staffList
        dataBase.setStaffList(staffList);
        //and serialise it
        FileIo.databaseSerialization(dataBase);

        System.out.println("Staff member has now been removed!");

        //returns to administer staff menu
        administerStaff();
    }

    //-------------------------GUEST MENU PART----------------------------------------
    public void administerGuest() {
        DataBase dataBase = FileIo.databaseDeserialization();
        System.out.println("======================================================");
        System.out.println("               G U E S T   M E N U ");
        System.out.println("======================================================");
        System.out.println("              1. Add new guest");
        System.out.println("              2. Manage guests");
        System.out.println("              3. Remove guest");
        System.out.println("              4. Show all guests" + "\n");
        System.out.println("         Press \"enter\" to exit to main menu ");
        System.out.println("======================================================");
        System.out.println();
        System.out.println("Please input choice");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        switch (input) {
            case "1":
                createGuest();
                break;
            case "2":
                manageGuest();
                break;

            case "3":
                removeGuest();
                break;

            case "4":
                Guest.printGuestList(dataBase.getGuestList());
                administerGuest();
                break;

            case "":
                mainMenu();
                break;

            default:
                System.out.println("Wrong input, please try again! ");
                administerGuest();
                break;
        }

    }

    //Creates guest, save it into file
    public void createGuest() {
        //Get database from file
        DataBase dataBase = FileIo.databaseDeserialization();
        ArrayList<Guest> guestList = dataBase.getGuestList();

        //Create Guest
        System.out.println("======================================================");
        System.out.println("            Please input first name: ");
        String firstName = stringInput();
        System.out.println("            Please input last name: ");
        String lastName = stringInput();
        System.out.println("            Please input address: ");
        String address = stringInput();
        System.out.println("            Please input phone number: ");
        String phoneNumber = stringInput();
        System.out.println("======================================================");

        //Create Id
        int id = -1;
        int checkId = 1;

        //Going through guestList to find ID
        for (Guest guest : guestList) {
            if (checkId == guest.getId()) {
                checkId++;
            } else {
                id = checkId;
            }
        }
        if (id == -1) {
            id = guestList.size() + 1;
        }

        Guest guest = new Guest(id, firstName, lastName, address, phoneNumber);
        guestList.add(guest);

        //Organize staffList
        ArrayList<Integer> idList = new ArrayList<>();
        for (Guest g : guestList) {
            idList.add(g.getId());
        }
        Collections.sort(idList);
        ArrayList<Guest> organisedGuestList = new ArrayList<>();
        for (int i : idList) {
            for (Guest g : guestList) {
                if (i == g.getId()) {
                    organisedGuestList.add(g);
                }
            }
        }

        //Saving it into file
        dataBase.setGuestList(organisedGuestList);
        FileIo.databaseSerialization(dataBase);

        //Return to main menu
        administerGuest();
    }

    public void manageGuest() {

        DataBase dataBase = FileIo.databaseDeserialization();
        ArrayList<Guest> guestList = dataBase.getGuestList();

        //chose ID of staff we want to change
        System.out.println("Chose the ID of the Guest you want to administer: ");
        int inputID = intInput() - 1;
        Guest guest = dataBase.getGuestList().get(inputID);

        System.out.println();
        System.out.println("======================================================");
        System.out.println("       1. Change first name of guest");
        System.out.println("       2. Change last name of guest");
        System.out.println("       3. Change phone number of guest");
        System.out.println("       4. Change address of guest" + "\n");
        System.out.println("      Press enter to exit to administer guest");
        System.out.println("======================================================");
        System.out.println();

        switch (stringInput()) {
            case "1":
                System.out.println("Please input new first name of guest" + "\n");
                guest.setFirstName(stringInput());
                break;
            case "2":
                System.out.println("Please input new last name of guest" + "\n");
                guest.setLastName(stringInput());
                break;
            case "3":
                System.out.println("Please input new phone number of guest" + "\n");
                guest.setPhoneNumber(stringInput());
                break;
            case "4":
                System.out.println("Please input new address of guest" + "\n");
                guest.setAddress(stringInput());
                break;
            case "":
                administerGuest();
                break;

            default:
                System.out.println("Wrong input, please try again! ");
                manageGuest();
                break;
        }

        //We save the updated dataBase into File
        FileIo.databaseSerialization(dataBase);

        //returns to administer guest menu
        administerGuest();
    }

    public void removeGuest() {
        //Get database from file
        DataBase dataBase = FileIo.databaseDeserialization();
        ArrayList<Guest> guestList = dataBase.getGuestList();

        System.out.println("Guest list: ");
        Guest.printGuestList(guestList);

        System.out.println("Chose the ID of the guest you wish to remove: ");
        int ID = intInput();


        //remove if: for each guest, remove guest which equals the ID input by user
        guestList.removeIf(guest -> guest.getId() == (ID));
        //we then set the guestList
        dataBase.setGuestList(guestList);
        //and serialise it
        FileIo.databaseSerialization(dataBase);

        System.out.println("Guest member has now been removed!");

        //returns to administer guest menu
        administerGuest();
    }

    //Method for user string input
    public String stringInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }

    //Method for user double input
    public Double doubleInput() {
        Scanner scanner = new Scanner(System.in);
        Double input = scanner.nextDouble();
        return input;
    }

    public int intInput() {
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        return input;
    }

}








