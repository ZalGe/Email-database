package Database;

import java.util.Scanner;

public class Execute
{
    Database d = new Database();

    String file;
    String key;
    String AccountName;
    int choice;

    public void ExecuteApp()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter database name (form: DATABASE_NAME.txt): ");
        file = sc.nextLine();
        System.out.println("Enter cypher key: ");
        key = sc.nextLine();
        System.out.println("What would you like to do?\nChoose from following options:\n1 - Enter new element\n" +
                "2 - Show password to account\n3 - Delete element\n4 - Show whole database");
        choice = sc.nextInt();
        if(choice == 1){d.FillDatabase(file,key);}
        else if(choice == 2){
            Scanner sc1 = new Scanner(System.in);
            System.out.println("Password to which account would you like to know?");
            AccountName = sc1.nextLine();
            System.out.println(d.browseFile(AccountName,file,key));
        }
        else if(choice == 3){
            Scanner sc2 = new Scanner(System.in);
            System.out.println("Which account would you like to delete?");
            AccountName = sc2.nextLine();
            d.DeleteElement(AccountName,file,key);
        }
        else if(choice == 4){
            System.out.println(d.ReadDatabase(file,key));
        }
        else{
            System.out.println("Something went wrong! Try again!");
        }
    }
}