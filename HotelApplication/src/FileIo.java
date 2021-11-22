import java.io.*;

public class FileIo {

    public static void databaseSerialization(DataBase database) {
        try {
            FileOutputStream fileOut = new FileOutputStream("database.ser");
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
