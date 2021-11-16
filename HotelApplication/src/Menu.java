import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public void mainMenu() {


        System.out.println("*** HOTEL MÅSKEPARADISE ***");
        System.out.println("1 Administer Booking");
        System.out.println("2 Administer Staff ");
        System.out.println("3 Administer Guests");
        System.out.println("4 Administer Room");
        System.out.println("Press \"r\" to reset Database");
        System.out.println("Press 5 to quit");
        System.out.println("***************************");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        switch (input) {
            case "1":
                break;

            case "2":
                administerStaff();
                break;

            case "3":

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

        System.out.println("S T A F F  M E N U ");
        System.out.println("1. Add new staff member");
        System.out.println("2. Manage staff Member");
        System.out.println("3. Remove Staff member");
        System.out.println("4. Show all staff");
        System.out.println("5. Exit to main menu ");
        System.out.println();
        System.out.println();
        System.out.println("Please input choice");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        switch (input) {
            case "1":
                createStaff();
                break;

            case "2":
                System.out.println("Manage staff");
                break;
            case "3":
                System.out.println("Remove from staff");
                break;

            case "4":
                Staff.printStaffList(dataBase.getStaffList());
                break;

                case "5":
                System.exit(1);
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

        Scanner input = new Scanner(System.in);

        System.out.println("Please input first name");
        String firstName = input.nextLine();
        System.out.println("Please input last name");
        String lastName = input.nextLine();
        System.out.println("Please input title");
        String title = input.nextLine();
        System.out.println("Please input phone number");
        String phoneNumber = input.nextLine();
        System.out.println("Please input salary");
        double salary = input.nextDouble();
        Staff staff = new Staff(firstName,lastName,title,salary,phoneNumber);

        //Saving it into file
        staffList.add(staff);
        dataBase.setStaffList(staffList);
        FileIo.databaseSerialization(dataBase);

        //Return main menu
        mainMenu();
    }
}








