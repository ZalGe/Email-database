package Database;

import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;
import static Database.InterfaceTexts.*;

public class Database
{
    private Scanner sc = new Scanner(System.in);
    private String file;
    private String database_password;


    public void loadDatabase() throws IOException
    {
        System.out.println(databaseNameAsk);
        file = sc.next();
        File f = new File(file);

        while(!f.exists())
        {
            System.out.println(loadingFileError);
            sc.reset();
            System.out.println(databaseNameAsk);
            file = sc.next();
            f=new File(file);
        }

        BufferedReader passwordScanner=new BufferedReader(new FileReader(f));
        String cryptedText=passwordScanner.readLine();
        while(true)
        {
            System.out.println(databasePasswordAsk);
            String password=sc.next();
            if(Encrypting.decryptIt(cryptedText,password,1)==null)
            {
                System.out.println(wrongPasswordError);
                sc.reset();
                continue;
            }
            database_password=password;
            break;
        }
        passwordScanner.close();
    }



    public void createDatabase() throws Exception //constructor for loading a database
    {
        System.out.println(databaseNameAsk);
        this.file=sc.next();


        while(true)
        {

            System.out.println(newPasswordAsk);
            String password1 = sc.next();
            System.out.println(confirmPasswordAsk);
            String password2 =sc.next();
            if(password1.equals(password2))
            {
                database_password=password1;
                BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
                writer.write(Encrypting.encryptIt(file, database_password,1) +"\n");
                writer.close();

                break;
            }
            System.out.println(mismatchPassword);
        }

    }



    /**
     * Attach new element to database, asks for password, generates password
     */
    public void FillDatabase() throws IOException
    {
        /* current account password */
        String account_password;
        /* constructor to receive email */
        System.out.println(accountNameAsk);
        String email = sc.next();
        /* Call a method asking for password */
        int defaultPasswordLength = 10;
        account_password = setPass(defaultPasswordLength);

        /* Writing to the file accounts + passwords */

        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        BufferedReader reader = new BufferedReader(new FileReader(file));

        /* if database is empty, it has 1 line -- database password  */
        int lastLineIndex=1;

        /* code, which allows us to detect last  line number */
        while(reader.readLine()!=null)
           {
               ++lastLineIndex;
           }

        /* Writing to the file accounts + passwords */
        writer.write(Encrypting.encryptIt(email + " - " + account_password,database_password,lastLineIndex) + "\n");
        writer.close();
        reader.close();
    }

    /**
     * Reads the whole database according to first cipher line:
     * @return decrypted database
     */
    public String ReadDatabase() throws IOException
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

            decrypt.add(Encrypting.decryptIt(element,database_password,lineIndex));
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
    public void DeleteElement(String AccountName) throws Exception, NoSuchAlgorithmException, NoSuchPaddingException
    {
        BufferedReader br=new BufferedReader(new FileReader(file));
        String codeLine=br.readLine()+"\n";
        /* Transferring data from file to map  */
        String[] pairs=this.ReadDatabase().split(", ");
        Map<String, String> myMap = new HashMap<>();
        for (String pair : pairs)
        {
            String[] keyVal = pair.split(" - ");
            myMap.put(keyVal[0], keyVal[1]);
        }
        /* Function to sort map */
        Map<String, String> sortedMap = new TreeMap<>(myMap);
        /* Deleting old file */
        System.out.println(InterfaceTexts.safetyQuestion);
        String answer=sc.next();
        String finalMessage="";

        switch(answer)
        {
            case ("n"):
                finalMessage+=InterfaceTexts.noRemovedFinalMessage;
                break;

            case("y"):
                finalMessage+=InterfaceTexts.RemovedFinalMessage;
                sortedMap.remove(AccountName);
                break;

            default:
       }
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
                    String a = Encrypting.encryptIt(entry.getKey() + " - " + entry.getValue(),database_password, lineIndex);
                    assert a != null;
                    bf.write(a);
                    bf.newLine();
                    ++lineIndex;
                }
            bf.flush();
            bf.close();
            System.out.println(finalMessage);
        }
        else { System.out.println(InterfaceTexts.generalErrorText); }
    }

    /**
     * Read 1 element from database
     */
    public String browseFile(String AccountName) throws IOException
    {
        /* Transferring data from file to map */
        Map<String, String> database = new HashMap<>();
        String[] pairs=this.ReadDatabase().split(", ");
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
        System.out.println(passwortExistsAsk);
        String input = sc.next();
        if(input.equals("y"))
        {
            System.out.println(accountPasswordAsk);
            String account_password = sc.next();

            return account_password;
        }
        else if (input.equals("n"))
        {
            return passGen(lenght);
        }
        else {return generalErrorText;}
    }
    public String passGen(int lenght)
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
}
