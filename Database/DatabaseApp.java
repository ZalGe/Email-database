package Database;


import java.io.IOException;

public class DatabaseApp {
    public static void main(String[] args) throws IOException {
       Database d1 = new Database();
       //d1.FillDatabase("Database.txt", "192748749364");

        System.out.println(d1.browseFile("ccc@ddd.com","Database.txt", "192748749364"));



       /* "192748749364" */

    }
}
