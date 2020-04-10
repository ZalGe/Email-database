package Database;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Database {
    Scanner sc = new Scanner(System.in);
    private String password;

    /**
     * Enter new elements to the database
     */
    public void FillDatabase()
    {
        /* Constructor to receive email */
        System.out.println("Enter your E-mail:");
        String email = sc.nextLine();
        //System.out.println(email);

        /* Call a method asking for password */
        int defaultPasswordLenght = 10;
        this.password = setPass(defaultPasswordLenght);
        System.out.println(password);

        /* Writing to the file */
        try
        {
            FileWriter writer = new FileWriter("Database.txt", true);
            writer.write(Encrypting.encryptIt((email + " - " + password), "192748749364") + "\n");
            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Read elements from the database
     */
    public void ReadDatabase()
    {
        String encrypt;
        String decrypt;
        try {
            FileReader reader = new FileReader("Database.txt");
            Scanner scan = new Scanner(reader);
            while(scan.hasNextLine())
            {
                encrypt = scan.nextLine();
                decrypt = Encrypting.decryptIt((encrypt), "192748749364");
                System.out.println(decrypt);
            }
            reader.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    /**
     * Constructor for asking or generating password
     */
    public String setPass(int lenght)
    {
        System.out.println("Do you have a password?   y/n");
        String input = sc.nextLine();
        if(input.equals("y"))
        {
            System.out.println("Enter your password:");
            password = sc.nextLine();
            return password;
        }
        else if (input.equals("n"))
        {
            String PassSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz" + "1234567890" + "1!@&#%ß";
            char [] password = new char[lenght];
            for (int i = 0; i<lenght; ++i) {
                int rand = (int) (Math.random() * PassSet.length());
                password[i] = PassSet.charAt(rand);
            }
            return new String(password);
        }
        else{return "Something went wrong. Process was terminated. Try to restart the program.";}
    }
}
