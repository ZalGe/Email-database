package Database;

public class InterfaceTexts
{


   /* executeApp texts */
   static String programName = "DATABAZORIUM ";

   static String loginMenu = "Login menu\n1 - Register new database\n2 - Login to an  existing database\n" +
                              "0 - Quit program";

   static String mainMenu = "What would you like to do?\nChoose from following options:\n1 - Enter new element\n" +
                             "2 - Show password to account\n3 - Delete element\n4 - Show the whole database\n"+
                              "8 - To login menu\n0 - End the program\n";

   static String bottomMenu = "9 - back to main" + " 8 - to login" + " 0 - end the program\n";

   static String generalErrorText = "Something went wrong! Try again.\n";
   static String accountToBrowseAsk = "Password to which account would you like to know?";
   static  String accountToDeleteAsk = "Which account would you like to delete?";


  /*  createDatabase & loadDatabase texts */

   static String databaseNameAsk = "Enter your database name: ";
   static String newPasswordAsk = "Create new password: ";
   static String confirmPasswordAsk = "Confirm your password: ";
   static String databasePasswordAsk = "Enter your database password: ";
   static String loadingFileError = "Couldn't load your file! Try again!\n ";
   static String wrongPasswordError = "Wrong password !!! Try again !\n";
   static String mismatchPassword = "Confirmed password doesn't match with origin one! Try again !\n";

   /* fillDatabase & setPass texts*/

    static String accountNameAsk = "Enter your Account name/E-mail address: ";
    static String passwortExistsAsk = "Do you have a password?   y/n ";
    static String accountPasswordAsk = "Enter your account password:";


   /* texts for deleteElement() */

   static String safetyQuestion ="Are you sure that you whant to dalete this item?    y/n ";
   static String noRemovedFinalMessage = "OK, nothing happened !\n";
   static String RemovedFinalMessage ="Account was deleted sucessfuly !\n";

}
