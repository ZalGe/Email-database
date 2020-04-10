package Database;

import java.util.Scanner;

public class Database {
    Scanner sc = new Scanner(System.in);
    private String email;
    private String password;
    private int DefaultPasswordLenght = 10;
    /**
     * Enter new elements to the database
     */
    public void FillDatabase()
    {
        /* Constructor to receive email */
        System.out.println("Enter your E-mail:");
        email = sc.nextLine();
        //System.out.println(email);

        /* Call a method asking for password */
        this.password = setPass(DefaultPasswordLenght);
        System.out.println(password);
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
