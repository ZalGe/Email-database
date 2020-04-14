package Database;



import java.util.Scanner;
import static Database.InterfaceTexts.*;

public class Execute
{
    Database d = new Database();


    public void ExecuteApp() throws Exception

    {

        Scanner sc = new Scanner(System.in);
        System.out.println(programName);

        /* first user's choice number */
        String choice1;
        /* second user's choice number */
        String choice2;
        /* third user's choice number */
        String choice3;
        /* command for exit while loop1 */
        boolean stopLoop1=false;
       /* command for exit while loop2 */
        boolean stopLoop2=false;
        /* command for exit while loop3 */
        boolean stopLoop3;


while(true)
{
    stopLoop3=false;
    /* this is loop1  a.k.a. login menu */
    while (!stopLoop1)
    {

        System.out.println(loginMenu);

        /* loading user's  1st. choice */
        choice1=sc.next();

        switch (choice1)
        {
            case ("1"):
                d.createDatabase();
                stopLoop1 = true;
                break;
            case ("2"):
                d.loadDatabase();
                stopLoop1 = true;
                break;
            case ("0"):
                System.exit(0);
            default:
                System.out.println(generalErrorText);
                sc.reset();
        }
    }

   /* this is loop2 a.k.a. main menu */
    while (!stopLoop2)
    {

        System.out.println(mainMenu);
        choice2 = sc.next();
        switch (choice2)
        {
            case ("1"):
                d.FillDatabase();
                break;
            case ("2"):
                Scanner sc1 = new Scanner(System.in);
                System.out.println(accountToBrowseAsk);
                String accountName = sc1.nextLine();
                System.out.println(d.browseFile(accountName));

                break;
            case ("3"):
                Scanner sc2 = new Scanner(System.in);
                System.out.println(accountToDeleteAsk);
                String accountName2 = sc2.nextLine();
                d.DeleteElement(accountName2);

                break;
            case ("4"):
                System.out.println("ACCOUNT - PASSWORD");
                String[] pairs = d.ReadDatabase().split(", ");
                for (String pair : pairs) {
                    System.out.println(pair);
                }
                System.out.println(bottomMenu);
                stopLoop2 = true;
                break;
            case ("0"):
                System.exit(0);
            case ("8"):
                stopLoop2 = true;
                stopLoop1 = false;
                stopLoop3 = true;

                break;
            default:
                System.out.println(generalErrorText);
                sc.reset();
                break;


        }
    }

        /* this is bottom menu */

        while(!stopLoop3)
        {
            choice3=sc.next();
            switch (choice3)
            {
                case ("9"):
                    /* returns user to main menu*/

                    stopLoop3=true;
                    stopLoop2=false;
                    stopLoop1=true;
                    break;
                case ("0"):
                    System.exit(0);
                case ("8"):
                      /* returns user to login menu*/
                    stopLoop3=true;
                    stopLoop1=false;
                    stopLoop2=false;
                    break;
                default:
                    System.out.println(generalErrorText);
                    sc.reset();

            }
        }

    }



}




}

