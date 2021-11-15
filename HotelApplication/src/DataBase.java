import java.util.ArrayList;

public class DataBase {

    private ArrayList<Staff> staffList;
   // private Arraylist <Guest> guestList;
   // private Arraylist <Room> guestList;

    public DataBase(){
        this.staffList = getStaffList();
    }

    public static ArrayList<Staff> getStaffList(){
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
}
