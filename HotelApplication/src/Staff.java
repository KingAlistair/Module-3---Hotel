import java.util.ArrayList;

public class Staff implements java.io.Serializable {

    private String id;
    private String firstName;
    private String lastName;
    private String title;
    private double salary;
    private String phoneNumber;

    public Staff(String id, String firstName, String lastName, String title, double salary, String phoneNumber){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.salary = salary;
        this.phoneNumber = phoneNumber;
    }

    public void printStaff(){
        System.out.println( "====================" +
                            "\nID: " + id +
                            "\n" +"Name: " + firstName + " " + lastName +
                            "\nTitle: " + title +
                            "\nSalary: " + salary + " DKK\n"
                            );
    }

    public static void printStaffList(ArrayList<Staff> staffList){
        for (Staff staff : staffList) {
            staff.printStaff();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}


