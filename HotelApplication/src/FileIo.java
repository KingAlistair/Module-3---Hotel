import java.io.*;
import java.util.ArrayList;

public class FileIo {

    public static void staffListSerialization(ArrayList<Staff> staffList) {
        try {
            FileOutputStream fos = new FileOutputStream("staffList.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(staffList);
            fos.close();
            oos.close();

        } catch (IOException ioe) {
            System.out.println(ioe + "- staffListSerialization");
        }
    }

        public static ArrayList<Staff> staffListDeserialization () {
            ArrayList<Staff> staffList = null;
            try {
                FileInputStream fis = new FileInputStream("staffList.ser");
                ObjectInputStream ois = new ObjectInputStream(fis);
               staffList = (ArrayList) ois.readObject();
                ois.close();
                fis.close();

            } catch (IOException ioe) {
                ioe.printStackTrace();
            } catch (ClassNotFoundException c) {
                System.out.println("Class not found");
                c.printStackTrace();
            }
            return staffList;
        }
    }
