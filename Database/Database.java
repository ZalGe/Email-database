package Database;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Database
{
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
        int defaultPasswordLength = 10;
        this.password = setPass(defaultPasswordLength);

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
        StringBuilder output=new StringBuilder();
        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(file)))
            {
                /* Transferring data from file to List */
                encrypt = reader.lines().collect(Collectors.toList());
                for(String element: encrypt)
                {
                    /* Decrypting data */
                    decrypt.add(Encrypting.decryptIt(element,key));
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        for(String element: decrypt)
        {
            output.append(element);
            output.append(", ");
        }
        return output.toString();

    }

    /**
     * Delete 1 element from the database
     */
    public void DeleteElement(String AccountName, String file, String key)
    {
        /* Transferring data from file to map to map */
        String[] pairs=this.ReadDatabase(file,key).split(", ");
        Map<String, String> myMap = new HashMap<>();
        for (String pair : pairs) {
            String[] keyVal = pair.split(" - ");
            myMap.put(keyVal[0], keyVal[1]);
        }
        /* Function to sort map */
        Map<String, String> sortedMap = new TreeMap<>(myMap);
        /* Deleting old file */
        sortedMap.remove(AccountName);
        File f = new File(file);
        if(f.delete())
        {
            try
            {
                /* Creating new file, with same name as the old one */
                BufferedWriter bf = new BufferedWriter(new FileWriter(file, true));
                for(Map.Entry<String, String> entry : sortedMap.entrySet())
                {
                    /* Encrypting data */
                    String a = Encrypting.encryptIt(entry.getKey() + " - " + entry.getValue(),key);
                    bf.write(a);
                    bf.newLine();
                }
                bf.flush();
                bf.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            System.out.println("Deleted");
        }
        else {
            System.out.println("Something went wrong, try again!");}
    }

    /**
     * Read 1 element from database
     */
    public String browseFile(String AccountName, String file, String key) {
        /* Transferring data from file to map */
        Map<String, String> database = new HashMap<>();
        String[] pairs=this.ReadDatabase(file,key).split(", ");
        for (String pair : pairs) {
            String[] keyValue = pair.split(" - ");
            database.put(keyValue[0], keyValue[1]);
        }
        /* return the wanted account password */
        return database.get(AccountName);

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
            /* Generating random password */
            String PassSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz" + "1234567890" + "1!@&#%ß";
            char [] password = new char[lenght];
            for (int i = 0; i<lenght; ++i) {
                int rand = (int) (Math.random() * PassSet.length());
                password[i] = PassSet.charAt(rand);
            }
            return new String(password);
        }
        else{return "Something went wrong! Process was terminated! Try to restart the program.";}
    }
}
