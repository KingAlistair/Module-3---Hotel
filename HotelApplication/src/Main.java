import javax.xml.crypto.Data;
import java.util.ArrayList;

public class Main {

    public static void  main(String[] args){
        DataBase dataBase = new DataBase();
        FileIo.databaseSerialization(dataBase);
        System.out.println(dataBase.getStaffList().size());



        Menu menu = new Menu();
        menu.mainMenu();
    }
}

//To do list:

//Singletome pattern