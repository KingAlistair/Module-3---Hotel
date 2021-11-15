import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

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
            case "2":
                for (Staff staff: database.getStaffList()) {
                    staff.printStaff();
                }
                break;

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
}
