package Database;

import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

public class Database
{
    Scanner sc = new Scanner(System.in);
    private String password;

    /**
     * Attach new element to database, asks for password, generates password
     * @param file database name
     * @param key usser password
     */
    public void FillDatabase(String file, String key) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException {
        /* Constructor to receive email */
        System.out.println("Enter your Account name/E-mail address:");
        String email = sc.nextLine();

        /* Call a method asking for password */
        int defaultPasswordLength = 10;
        this.password = setPass(defaultPasswordLength);

        /* Writing to the file accounts + passwords */

            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            BufferedReader reader = new BufferedReader(new FileReader(file));

          int lastLineIndex=1;
           while(reader.readLine()!=null)
           {
               ++lastLineIndex;
           }


            writer.write(Encrypting.encryptIt(email + " - " + password,key,lastLineIndex) + "\n");
            writer.close();
    }

    public void FillLine(String text, String key, int lineIndex) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException {

             /* Writing to the file accounts + passwords */
            BufferedWriter writer = new BufferedWriter(new FileWriter(text, true));
            writer.write(Encrypting.encryptIt(text, key,lineIndex) +"\n");
            writer.close();

    }

    /**
     * Reads the whole database according to first cipher line:
     * @param file database name
     * @param key  user password
     * @return decrypted database
     */
    public String ReadDatabase(String file, String key) throws IOException
    {
        //Collections for encrypted and decrypted database:
        List<String> encrypt;
        List<String> decrypt = new ArrayList<>();
        //StringBuilder for output
        StringBuilder output=new StringBuilder();
        //Buffered reader i init. by file:
        BufferedReader reader = new BufferedReader(new FileReader(file));
        //variable which stores 1st. line:
        String line1=reader.readLine();
       //Fills Collection by encrypted message by lines:
        encrypt = reader.lines().collect(Collectors.toList());
       //Removing first (cipher) line:
        encrypt.remove(line1);

        int lineIndex=2;
        for(String element: encrypt)
        {
            //decrypting data

            decrypt.add(Encrypting.decryptIt(element,key,lineIndex));
            ++lineIndex;
        }

        for(String element: decrypt)
        {
            //storing data to output
            output.append(element);
            output.append(", ");
        }
        reader.close();
        return output.toString();
    }

    /**
     * Delete 1 element from the database
     */
    public void DeleteElement(String AccountName, String file, String key) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException
    {
        BufferedReader br=new BufferedReader(new FileReader(file));
        String codeLine=br.readLine()+"\n";
        /* Transferring data from file to map to map */
        String[] pairs=this.ReadDatabase(file,key).split(", ");
        Map<String, String> myMap = new HashMap<>();
        for (String pair : pairs)
        {
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
                /* Creating new file, with same name as the old one */
                BufferedWriter bf = new BufferedWriter(new FileWriter(file, true));
                int lineIndex=2;
                bf.write(codeLine);


                for(Map.Entry<String, String> entry : sortedMap.entrySet())
                {
                    /* Encrypting data */

                    String a = Encrypting.encryptIt(entry.getKey() + " - " + entry.getValue(),key, lineIndex);
                    assert a != null;
                    bf.write(a);
                    bf.newLine();
                    ++lineIndex;
                }
                bf.flush();
                bf.close();

            System.out.println("Deleted");
        }
        else
            {
              System.out.println("Something went wrong, try again!");
            }
    }

    /**
     * Read 1 element from database
     */
    public String browseFile(String AccountName, String file, String key) throws IOException
    {
        /* Transferring data from file to map */
        Map<String, String> database = new HashMap<>();
        String[] pairs=this.ReadDatabase(file,key).split(", ");
        for (String pair : pairs)
        {
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
        else {return "Something went wrong! Process was terminated! Try to restart the program.";}
    }
}
