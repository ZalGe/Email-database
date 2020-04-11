package Database;

import java.io.*;
import java.util.Scanner;

public class Database {
    Scanner sc = new Scanner(System.in);
    private String password;

    /**
     * Enter new elements to the database
     */
    public void FillDatabase(String file, String key)
    {
        /* Constructor to receive email */
        System.out.println("Enter your Account name/E-mail address:");
        String email = sc.nextLine();

        /* Call a method asking for password */
        int defaultPasswordLenght = 10;
        this.password = setPass(defaultPasswordLenght);

        /* Writing to the file accounts + passwords */
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(Encrypting.encryptIt(email + " - " + password, key) + "\n");
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
    public void ReadDatabase(String file, String key)
    {
        String encrypt;
        String decrypt;
        try {
            FileReader reader = new FileReader(file);
            Scanner scan = new Scanner(reader);
            while(scan.hasNextLine())
            {
                encrypt = scan.nextLine();
                decrypt = Encrypting.decryptIt((encrypt), key);
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
