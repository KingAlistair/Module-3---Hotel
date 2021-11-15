import java.io.*;
//hellow
public class FileIo {
    private static final String file = "database.ser";

    public static void databaseSerialization(DataBase database) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(database);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            System.out.println("ERROR IN SAVING DATABASE");
        }
    }


    public static DataBase databaseDeserialization() {
        DataBase database = new DataBase();
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            database = (DataBase) objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("ERROR ");
        }
        return database;
    }
}

/*

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
*/

