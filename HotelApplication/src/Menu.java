import javax.xml.crypto.Data;
import java.util.ArrayList;
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
                //administerBooking();
                break;

            case "2":
                administerStaff();
                break;

            case "3":
                administerGuest();

            case "r":
                DataBase dataBase = new DataBase();
                FileIo.databaseSerialization(dataBase);
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
        System.out.println("======================================================");
        System.out.println("              B O O K I N G  M E N U ");
        System.out.println("======================================================");
        System.out.println("              1. Add new booking");
        System.out.println("              2. Manage booking");
        System.out.println("              3. Remove booking");
        System.out.println("              4. Show all bookings" + "\n");
        System.out.println("         Press \"enter\" to exit to main menu ");
        System.out.println("======================================================");
        System.out.println();
        System.out.println("Please input choice: ");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        switch (input) {
            case "1":
                ;
                break;

            case "2":

                break;
            case "3":

                break;

            case "4":

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
        int id = staffList.size() + 1;
        String stringId = Integer.toString(id);

        Staff staff = new Staff(stringId, firstName, lastName, title, salary, phoneNumber);

        //Saving it into file
        staffList.add(staff);
        dataBase.setStaffList(staffList);
        FileIo.databaseSerialization(dataBase);

        //Return main menu
        administerStaff();
    }

    public void manageStaff() {
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
                System.out.println("Changing first name of staff" + "\n");
                break;
            case "2":
                System.out.println("changing last name of staff" + "\n");
                break;
            case "3":
                System.out.println("changing title of staff" + "\n");
                break;
            case "4":
                System.out.println("Changing phone number of staff" + "\n");
                break;
            case "5":
                System.out.println("changing salary of staff" + "\n");

            case "":
                administerStaff();
                break;

            default:
                System.out.println("Wrong input, please try again! ");
                administerStaff();
                break;
        }
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
        String ID = stringInput();


        //remove if: for each staff, remove staff which equals the ID input by user
        staffList.removeIf(staff -> staff.getId().equals(ID));
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
        int id = guestList.size() + 1;
        String stringID = Integer.toString(id);

        Guest guest = new Guest(stringID, firstName, lastName, address, phoneNumber);

        //Saving it into file
        guestList.add(guest);
        dataBase.setGuestList(guestList);
        FileIo.databaseSerialization(dataBase);

        //Return to main menu
        administerGuest();
    }

    public void manageGuest() {
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
                System.out.println("Changing first name of guest" + "\n");
                break;
            case "2":
                System.out.println("changing last name of guest" + "\n");
                break;
            case "3":
                System.out.println("changing phone number of guest" + "\n");
                break;
            case "4":
                System.out.println("Changing address of guest" + "\n");
                break;
            case "":
                administerGuest();
                break;

            default:
                System.out.println("Wrong input, please try again! ");
                manageGuest();
                break;
        }
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
        String ID = stringInput();


        //remove if: for each guest, remove guest which equals the ID input by user
        guestList.removeIf(guest -> guest.getId().equals(ID));
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

}








