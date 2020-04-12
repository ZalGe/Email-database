package Database;



import java.io.*;
import java.util.Scanner;

public class Execute
{
    Database d = new Database();

     String file;
     String key;
     String AccountName;

    public void ExecuteApp() throws FileNotFoundException {

        Scanner sc = new Scanner(System.in);

        System.out.println("DATABAZORIUM");
        System.out.println("Main menu");
        System.out.println("Create new database(1), Load database(2)");
        int choice1=sc.nextInt();

        switch(choice1)
        {
            case(1):
                System.out.println("Enter your database name: ");
                file=sc.next();

                while(true)
               {

                   System.out.println("Create new password: ");
                   key = sc.next();
                   System.out.println("Confirm your password: ");
                   String keyConfirmed=sc.next();
                   if(key.equals(keyConfirmed))
                   {
                       d.FillFirstLine(file, key,"Database"+file);
                       break;
                   }
                   System.out.println("Confirmed password doesn't match with origin one! Try again !");
               }
                break;

            case(2):
                       System.out.println("Enter your database name");
                       file = sc.next();
                       File FILE = new File(file);
                       while(!FILE.exists())
                       {
                           System.out.println("Couldn't load your file! Try again!");
                           sc.reset();
                           System.out.println("Enter your database name");
                           file = sc.next();
                           FILE=new File(file);
                       }

                       Scanner passwordScanner=new Scanner(FILE);
                       String text=passwordScanner.nextLine();
                     while(true)
                     {
                         System.out.println("Enter your password: ");
                         key=sc.next();
                         if(Encrypting.decryptIt(text,key)==null)
                         {
                             System.out.println("Wrong password !!! Try again !");
                             sc.reset();
                             continue;
                         }
                         break;
                     }
        }


        System.out.println("What would you like to do?\nChoose from following options:\n1 - Enter new element\n" +
                "2 - Show password to account\n3 - Delete element\n4 - Show whole database");

       while(true)
       {
           int choice2 = sc.nextInt();
           switch (choice2) {
               case (1):
                   d.FillDatabase(file, key);
                   System.out.println("What would you like to do?\nChoose from following options:\n1 - Enter new element\n" +
                           "2 - Show password to account\n3 - Delete element\n4 - Show whole database");
                   break;
               case (2):
                   Scanner sc1 = new Scanner(System.in);
                   System.out.println("Password to which account would you like to know?");
                   AccountName = sc1.nextLine();
                   System.out.println(d.browseFile(AccountName, file, key));
                   System.out.println("What would you like to do?\nChoose from following options:\n1 - Enter new element\n" +
                           "2 - Show password to account\n3 - Delete element\n4 - Show whole database");

                   break;
               case (3):
                   Scanner sc2 = new Scanner(System.in);
                   System.out.println("Which account would you like to delete?");
                   AccountName = sc2.nextLine();
                   d.DeleteElement(AccountName, file, key);
                   System.out.println("What would you like to do?\nChoose from following options:\n1 - Enter new element\n" +
                           "2 - Show password to account\n3 - Delete element\n4 - Show whole database");

                   break;
               case (4):
                   System.out.println("ACCOUNT - PASSWORD");
                   String[] pairs = d.ReadDatabase(file, key).split(", ");
                   for (String pair : pairs) {
                       System.out.println(pair);
                   }
                   System.out.println("back(9)" + "   " + "end(0)");
                   break;
               case (9):
                   System.out.println("What would you like to do?\nChoose from following options:\n1 - Enter new element\n" +
                           "2 - Show password to account\n3 - Delete element\n4 - Show whole database");
                   continue;
               case(0):
                   System.exit(0);

               default:
                   System.out.println("Something went wrong! Try again!");
                   System.out.println("back(9)" + "   " + "end(0)");
                   choice2=sc.nextInt();





           }
       }
    }
}
