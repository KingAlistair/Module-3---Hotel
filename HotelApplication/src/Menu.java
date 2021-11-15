import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private Staff staff;

    public void mainMenu() {
        DataBase database = new DataBase();
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
                FileIo.databaseSerialization(database);
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
        DataBase dataBase = new DataBase();
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

                for (Staff staff : dataBase.getStaffList()) {
                    staff.printStaff();
                }
                break;
                case "5":
                System.exit(1);
                break;

            default:
                System.out.println("Wrong input ");
                mainMenu();
                break;


        }
        //y no return ma friend?!
     return;
    }

    public void createStaff() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please input title");
        //staff.setTitle(input.nextLine());
        String title = input.nextLine();
        System.out.println("Please input first name");
        String firstName = input.nextLine();
        //staff.setFirstName(input.nextLine());
        System.out.println("Please input last name");
        String lastName = input.nextLine();
        //staff.setLastName(input.nextLine());
        System.out.println("Please input phone number");
        String phoneNumber = input.nextLine();
        //staff.setPhoneNumber(input.nextLine());
        System.out.println("Please input salary");
        String salary = input.nextLine();
        //staff.setSalary(input.nextLine());
        Staff staff = new Staff(title, firstName, lastName, salary, phoneNumber);

    }

}








