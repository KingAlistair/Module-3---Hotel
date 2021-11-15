import java.io.Serializable;
import java.util.ArrayList;

public class Staff implements java.io.Serializable {

    private String firstName;
    private String lastName;
    private String title;
    private String salary;
    private String phoneNumber;

    public Staff(String firstName, String lastName, String title, String salary, String phoneNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.salary = salary;
        this.phoneNumber = phoneNumber;
    }

    public void printStaff(){
        System.out.println("Name: " + firstName + " " + lastName + "\n"
        + title + " monthly salary DKK: " + salary + " DKK");
    }

    public static void printStaffList(ArrayList<Staff> staffList){
        for (Staff staff : staffList) {
            staff.printStaff();
        }
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

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}


