import java.io.*;
public class FileIo {

    public static void databaseSerialization(DataBase database) {
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("database.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(database);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }


    public static DataBase databaseDeserialization() {
        DataBase database = new DataBase();
        try {
            FileInputStream fileInputStream = new FileInputStream("database.ser");
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

