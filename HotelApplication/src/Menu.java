import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Scanner;


public class Menu {
    public void mainMenu() {
        System.out.println();
        System.out.println("======================================================");
        System.out.println("     H O T E L   M Å S K E P A R A D I S E  " );
        System.out.println("======================================================");
        System.out.println("             1 Administer Booking");
        System.out.println("             2 Administer Staff ");
        System.out.println("             3 Administer Guests");
        System.out.println("             4 Administer Room");
        System.out.println("             Press \"r\" to reset Database");
        System.out.println("             5 to quit");
        System.out.println("======================================================");
        System.out.println();
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        switch (input) {
            case "1":
                break;

            case "2":
                administerStaff();
                break;

            case "3":
                manageStaff();

            case "r":
                DataBase dataBase = new DataBase();
                FileIo.databaseSerialization(dataBase);
                mainMenu();
                break;

            case "6":
                System.exit(1);
                break;

            default:
                System.out.println("Wrong input ");
                mainMenu();
                break;
        }
    }

    public void administerStaff() {
        DataBase dataBase = FileIo.databaseDeserialization();
        System.out.println("======================================================");
        System.out.println("               S T A F F  M E N U ");
        System.out.println("======================================================");
        System.out.println("              1. Add new staff member");
        System.out.println("              2. Manage staff Member");
        System.out.println("              3. Remove Staff member");
        System.out.println("              4. Show all staff" + "\n");
        System.out.println("         Press \"enter\" to exit to main menu ");
        System.out.println("======================================================");
        System.out.println();
        System.out.println("Please input choice");

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
                System.out.println("Wrong input ");
                mainMenu();
                break;
        }

    }

    //Creates staff, save it into file
    public void createStaff() {
        //Get database from file
        DataBase dataBase = FileIo.databaseDeserialization();
        ArrayList<Staff> staffList = dataBase.getStaffList();

        //Create Staff
        System.out.println("Please input first name");
        String firstName = stringInput();
        System.out.println("Please input last name");
        String lastName = stringInput();
        System.out.println("Please input title");
        String title = stringInput();
        System.out.println("Please input phone number");
        String phoneNumber = stringInput();
        System.out.println("Please input salary");
        double salary = doubleInput();

        //Create Id
        int id = staffList.size()+1;
        String stringId = Integer.toString(id);

        Staff staff = new Staff(stringId ,firstName, lastName, title, salary, phoneNumber);

        //Saving it into file
        staffList.add(staff);
        dataBase.setStaffList(staffList);
        FileIo.databaseSerialization(dataBase);

        //Return main menu
        administerStaff();
    }

    public void manageStaff() {
        System.out.println("1. Change first name of staff member");
        System.out.println("2. Change last name of staff member");
        System.out.println("3. Change title of staff member");
        System.out.println("4. Change phone number of staff member");
        System.out.println("5. Change salary of staff member");
        System.out.println("Press enter to exit to administer staff");

        switch (stringInput()) {
            case "1":
                System.out.println("Changing first name of staff"+ "\n");
                break;
            case "2":
                System.out.println("changing last name of staff"+ "\n");
                break;
            case "3":
                System.out.println("changing title of staff"+ "\n");
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
                System.out.println("Wrong input ");
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

        System.out.println("Chose the ID of the staff member you wish to remove");
        String ID = stringInput();



        //remove if: for each staff, remove staff which equals the ID input by user
        staffList.removeIf(staff -> staff.getId().equals(ID));
        //we then set the staffList
        dataBase.setStaffList(staffList);
        //and serialise it
        FileIo.databaseSerialization(dataBase);

        System.out.println("Staff member has now been removed");

        //returns to administer staff menu
        administerStaff();
    }


    public String stringInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }

    public Double doubleInput() {
        Scanner scanner = new Scanner(System.in);
        Double input = scanner.nextDouble();
        return input;
    }
}








