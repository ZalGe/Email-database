package Database;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

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
    public String ReadDatabase(String file, String key)
    {
        List<String> encrypt;
        List<String> decrypt = new ArrayList<>();
        try {
            try (BufferedReader reader = new BufferedReader(new FileReader("Database.txt")))
            {
                encrypt = reader.lines().collect(Collectors.toList());
                for(String element: encrypt)
                {
                    decrypt.add(Encrypting.decryptIt(element,key));
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return decrypt.toString();

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

    public String browseFile(String accountName) throws IOException
    {
        Map<String,String> database;

       return null;

    }
}
