import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    public void mainMenu() {
        ArrayList<Staff> staffList = FileIo.staffListDeserialization();

        System.out.println("Press 1 to print out the list of staff: ");
        System.out.println("Press 3 to save staff list");
        System.out.println("Press 6 to quit");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        switch (input) {
            case "1":
                Staff.printStaffList(staffList);
                break;

            case "3":
                FileIo.staffListSerialization(DataBase.getStaffList());
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
