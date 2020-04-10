package Database;


public class DatabaseApp {
    public static void main(String[] args) {
       Database d1 = new Database();
       d1.FillDatabase();
        System.out.println(Encrypting.encryptIt(new String("Ahoj Filip, finguje to!"),"192748749364"));
        System.out.println(Encrypting.decryptIt("r¡¡¡T~  ¢£bT\u0097¢ \u009E©¢\u009CT\u00AD¢W","192748749364" ));
    }
}
