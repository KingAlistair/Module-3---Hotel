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
        System.out.println("                2 Administer Staff");
        System.out.println("                3 Administer Guests");
        System.out.println("                4 Administer Room");
        System.out.println("                Press \"r\" to reset Database");
        System.out.println("                5 to quit");
        System.out.println("======================================================");
        System.out.println();
        System.out.println();

        String input = stringInput();
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

            case "4":
                administerRoom();
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
        System.out.println("              4. Show all bookings (" + (bookingList.size()) + ")\n");
        System.out.println("         Press \"enter\" to exit to main menu ");
        System.out.println("======================================================");
        System.out.println();
        System.out.println("Please input choice: ");

        String input = stringInput();
        switch (input) {
            case "1":
                createBooking();
                administerBooking();
                break;

            case "2":
                Booking.printBookingList(bookingList);
                manageBooking();
                break;
            case "3":
                deleteBooking();
                break;

            case "4":
                Booking.printBookingList(bookingList);
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
        ArrayList<Room> roomList = dataBase.getRoomList();
        ArrayList<Booking> bookingList = dataBase.getBookingList();
        ArrayList<Guest> currentGuest = new ArrayList<>();

        //Choose the number of guests
        System.out.println("How many guest would you like to add to the booking? (1-3 guests)");
        int numberOfGuests = intInput();
        if (numberOfGuests < 1 || numberOfGuests > 3) {
            System.out.println("Invalid input! Try again!");
            createBooking();
        }

        //Create or add guests
        for (int i = 0; i < numberOfGuests; i++) {

            //Load guestList to have the updated list from previous iteration - to avoid same ID's and have correct ID order
            ArrayList<Guest> guestList = FileIo.databaseDeserialization().getGuestList();
            Guest newGuest = null;

            //Asks if new guest
            System.out.println("Do you want to create a new guest for the booking? y/n");
            Scanner scanner = new Scanner(System.in);
            String input = stringInput();
            System.out.println(input);
            if (input.equalsIgnoreCase("y")) {

                //Create Guest
                System.out.println("======================================================");
                System.out.println("            Please input first name: ");
                String firstName = stringInput();
                System.out.println("            Please input last name: ");
                String lastName = scanner.nextLine();
                System.out.println("            Please input address: ");
                String address = stringInput();
                System.out.println("            Please input phone number: ");
                String phoneNumber = stringInput();
                System.out.println("======================================================");

                //Create ID If new guest
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
                //New Guest created
                Guest guest = new Guest(id, firstName, lastName, address, phoneNumber);
                guestList.add(guest);

                //Organize guestList
                ArrayList<Integer> idList = new ArrayList<>();
                for (Guest g : guestList) {
                    idList.add(g.getId());
                }
                Collections.sort(idList);
                ArrayList<Guest> organisedGuestList = new ArrayList<>();
                for (int x : idList) {
                    for (Guest g : guestList) {
                        if (x == g.getId()) {
                            organisedGuestList.add(g);
                        }
                    }
                }
                dataBase.setGuestList(organisedGuestList);
                FileIo.databaseSerialization(dataBase);
                currentGuest.add(guest);
            } else {
                //print out list of guests, chose guest from guestList
                Guest.printGuestList(guestList);
                System.out.println("Please input Id of Guest #" + (i + 1));

                int userInput = intInput();
                for (Guest guest : guestList
                ) {
                    if (userInput == guest.getId()) {
                        newGuest = guest;
                    }
                }
                currentGuest.add(newGuest);
            }
        }

        //Set up variables for while loop if room is not available
        Booking booking = null;
        boolean loop = true;

        //Here comes the while loop baby!
        while (loop) {
            boolean isBeforeStartDate = true;
            boolean isAfterEndDate = true;

            //Create available rooms list
            ArrayList<Room> availableRooms = new ArrayList<>();
            for (Room room : roomList
            ) {
                if (numberOfGuests == room.getAmountOfBeds()) {
                    availableRooms.add(room);
                }
            }
            System.out.println("======================================================");
            Room.printRoomList(availableRooms);
            System.out.println("Please choose room number");
            // get user input to choose a room number
            int roomNum = intInput();
            Room room = null;
            for (Room room1 : availableRooms) {
                if (roomNum == room1.getRoomNumber()) {
                    room = room1;
                }
            }

            //Create ID for new booking
            int id = -1;
            int checkId = 1;

            //Going through booking list to find missing ID
            for (Booking booking0 : bookingList) {
                if (checkId == booking0.getId()) {
                    checkId++;
                } else {
                    id = checkId;
                }
            }
            if (id == -1) {
                id = bookingList.size() + 1;
            }

            //Loop so user can't write in negative days (end date before start date)
            LocalDate start = null;
            LocalDate end = null;
            boolean loop2 = true;
            while (loop2) {
                System.out.println("Please input check in date: Year \"enter\" + Month \"Enter\" + Day \"Enter\"");
                System.out.println("Year:");
                int startYear = intInput();
                System.out.println("Month:");
                int startMonth = intInput();
                System.out.println("Day");
                int startDay = intInput();
                System.out.println("Please input check out date: Year \"enter\" + Month \"Enter\" + Day \"Enter\"");
                System.out.println("Year:");
                int endYear = intInput();
                System.out.println("Month:");
                int endMonth = intInput();
                System.out.println("Day");
                int endDay = intInput();

                start = LocalDate.of(startYear, startMonth, startDay);
                end = LocalDate.of(endYear, endMonth, endDay);

                if (end.isBefore(start)) {
                    loop2 = true;
                    System.out.println("Your can't check out before you check in, you silly goat! Try again!\n");
                } else {
                    loop2 = false;
                }
                //End of while loop if loop is false
            }
            System.out.println("======================================================");

            booking = new Booking(id, currentGuest, room, start, end);

            //Get bookings from bookingList with the same room number as the new booking
            ArrayList<Booking> bookingsWithTheSameRoom = new ArrayList<>();
            for (Booking booking2 : bookingList) {
                if (booking.getRoom().getRoomNumber() == booking2.getRoom().getRoomNumber()) {
                    bookingsWithTheSameRoom.add(booking2);
                }
            }

            //Checks the new booking with old bookings if one of the statement is false, it stays false.
            for (Booking booking3 : bookingsWithTheSameRoom) {
                //Checks if the start and end date is before the booked start date
                if (booking.getStartDate().isBefore(booking3.getStartDate()) && booking.getEndDate().isBefore(booking3.getStartDate())) {
                } else {
                    isBeforeStartDate = false;
                }
                //Checks if the start date is after the booked end date
                if (booking.getStartDate().isAfter(booking3.getEndDate())) {
                } else {
                    isAfterEndDate = false;
                }
            }
            //If one of the statement is true booking is available,
            if (isBeforeStartDate || isAfterEndDate) {
                System.out.println("Good job lad, the room is available!");
                loop = false;
            } else {
                System.out.println("I'm terribly sorry to inform you, that room number " + booking.getRoom().getRoomNumber() + " is not available during that time!\n" +
                        "Please try to choose a different room or a different date!");
                loop = true;
            }
            //Here ends the while loop, if loop is true -> loop continues
        }

        booking.setGuestList(currentGuest);
        booking.bookingReceipt();
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
        ArrayList<Booking> bookingList = dataBase.getBookingList();


        //chose ID of booking we want to change
        System.out.println("Choose the ID of the Booking you want to change: ");
        int inputID = intInput() - 1;
        Booking booking = dataBase.getBookingList().get(inputID);

        System.out.println();
        System.out.println("======================================================");
        System.out.println("            1. Change check out date" + "\n");
        System.out.println("            2. Print booking" + "\n");
        System.out.println("      Press enter to exit to administer booking");
        System.out.println("======================================================");
        System.out.println();

        switch (stringInput()) {

            case "1":
                changeEndDate(booking);
                break;

            case "2":
                Booking.printBookingList(bookingList);
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

        //returns to administer booking menu
        administerBooking();
    }
    //hopefully it is here

    public void changeEndDate(Booking booking) {
        DataBase dataBase = FileIo.databaseDeserialization();
        ArrayList<Booking> bookingList = dataBase.getBookingList();

        boolean badDay = false;

        booking.printBooking();

        //Start of while loop to check if the booking end date is before start date
        LocalDate newEndDate = null;


        System.out.println("Please input new check out date: Year \"enter\" + Month \"Enter\" + Day \"Enter\"");
        System.out.println("Year:");
        int endYear = intInput();
        System.out.println("Month:");
        int endMonth = intInput();
        System.out.println("Day:");
        int endDay = intInput();
        newEndDate = LocalDate.of(endYear, endMonth, endDay);

        if (newEndDate.isBefore(booking.getStartDate())) {
            System.out.println("You can not check out before checking in you dumdum!");
            changeEndDate(booking);
        }

        ArrayList<Booking> sameRoomBookingList = new ArrayList<>();
        for (Booking booking1 : bookingList) {
            if (booking.getRoom().getRoomNumber() == booking1.getRoom().getRoomNumber()) {
                if (booking.getId() == booking1.getId()) {}
                    else{
                    sameRoomBookingList.add(booking1);
                }
            }
        }

        for (Booking booking2 : sameRoomBookingList) {
            if (booking.getEndDate().isAfter(booking2.getStartDate()) && booking.getStartDate().isAfter(booking2.getEndDate())) {
            } else {
                if (booking.getEndDate().isBefore(booking2.getStartDate())) {
                } else {
                    System.out.println("End date is invalid! Room is occupied at that time!");
                    badDay = true;
                }
            }
        }

        if (badDay) {
            changeEndDate(booking);
        }
        booking.setEndDate(newEndDate);

        for (int i = 0; i < bookingList.size(); i++) {
            if (booking.getId() == bookingList.get(i).getId()) {
                bookingList.set(i, booking);
            }
        }
        //Set bookingList
        dataBase.setBookingList(bookingList);

        //Save into file
        FileIo.databaseSerialization(dataBase);
    }

    public void deleteBooking() {

        //Get database from file
        DataBase dataBase = FileIo.databaseDeserialization();
        ArrayList<Booking> bookingList = dataBase.getBookingList();

        System.out.println("Booking list: ");
        Booking.printBookingList(bookingList);

        System.out.println("Chose the ID of the booking you wish to remove: ");
        int ID = intInput();

        //remove if: for each booking, remove booking which equals the ID input by user
        bookingList.removeIf(booking -> booking.getId() == (ID));
        //we then set the bookingList
        dataBase.setBookingList(bookingList);
        //and serialise it
        FileIo.databaseSerialization(dataBase);

        System.out.println("The booking has now been removed!");

        //returns to administer booking menu
        administerBooking();
    }


    //-------------------------------STAFF MENU PART----------------------------------------
    public void administerStaff() {
        DataBase dataBase = FileIo.databaseDeserialization();
        ArrayList<Staff> staffList = dataBase.getStaffList();

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
                Staff.printStaffList(staffList);
                manageStaff();
                break;
            case "3":
                removeStaff();
                break;

            case "4":
                Staff.printStaffList(staffList);
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

    public void manageStaff() {
        //Get database from file
        DataBase dataBase = FileIo.databaseDeserialization();
        ArrayList<Staff> staffList = dataBase.getStaffList();

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
        System.out.println();

        switch (stringInput()) {
            case "1":
                System.out.println("Please input new first name");
                staff.setFirstName(stringInput());
                break;
            case "2":
                System.out.println("Please input new last name");
                staff.setLastName(stringInput());
                break;
            case "3":
                System.out.println("Please input new title");
                staff.setTitle(stringInput());
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


        for (int i = 0; i < staffList.size(); i++) {
            if (staff.getId() == staffList.get(i).getId()) {
                staffList.set(i, staff);
            }
        }

        dataBase.setStaffList(staffList);
        FileIo.databaseSerialization(dataBase);

        //returns to administer staff menu
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

    //-------------------------GUEST MENU PART------------------------------------------------------------------------
    public void administerGuest() {
        DataBase dataBase = FileIo.databaseDeserialization();
        ArrayList<Guest> guestList = dataBase.getGuestList();

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
                Guest.printGuestList(guestList);
                manageGuest();
                break;

            case "3":
                removeGuest();
                break;

            case "4":
                Guest.printGuestList(guestList);
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

        //The loop looks for the guest that's data is changed and sets it to the arraylist
        for (int i = 0; i < guestList.size(); i++) {
            if (guest.getId() == guestList.get(i).getId()) {
                guestList.set(i, guest);
            }
        }
        //Sets the guestList into the database
        dataBase.setGuestList(guestList);
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

    //-------------------------Room MENU PART------------------------------------------------------------------------

    public void administerRoom() {
        DataBase dataBase = FileIo.databaseDeserialization();
        ArrayList<Room> roomList = dataBase.getRoomList();
        System.out.println("======================================================");
        System.out.println("                 R O O M  M E N U");
        System.out.println("======================================================");
        System.out.println("                  1. Print rooms");
        System.out.println("                  2. Change room price \n");
        System.out.println("             Press \"enter\" to exit to main menu ");
        System.out.println("======================================================");

        String input = stringInput();

        switch (input) {
            case "1":
                Room.printRoomList(roomList);
                administerRoom();
                break;

            case "2":
                Room.printRoomList(roomList);
                changePrice();
                administerRoom();
                break;

            case "":
                mainMenu();
                break;

            default:
                System.out.println("Wrong input, please try again! ");
                administerRoom();
                break;
        }
    }

    //Method to change the price of the room
    public void changePrice() {
        DataBase dataBase = FileIo.databaseDeserialization();
        ArrayList<Room> roomList = dataBase.getRoomList();

        //User chooses room
        System.out.println("Choose a room number");
        int input = intInput();
        Room room = null;

        for (Room room2 : roomList) {
            if (input == room2.getRoomNumber()) {
                room = room2;
            }
        }

        System.out.println("New price for room:");
        input = intInput();
        room.setPrice(input);

        //Set room into roomList
        for (int i = 0; i < roomList.size(); i++) {

            if (room.getRoomNumber() == roomList.get(i).getRoomNumber()) {
                roomList.set(i, room);
            }
        }
        //Set roomlist to database and save into file
        dataBase.setRoomList(roomList);
        FileIo.databaseSerialization(dataBase);
    }

    //-------------------------Scanner PART------------------------------------------------------------------------

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