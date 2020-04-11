package Database;

public class DatabaseApp {
    public static void main(String[] args) {
       Database d1 = new Database();

       d1.FillDatabase("Database.txt", "192748749364");
       System.out.println(d1.browseFile("ccc@ddd.com","Database.txt", "192748749364"));
       d1.DeleteElement("ccc@ddd.com","Database.txt", "192748749364");
    }
}
