package Database;

import javax.swing.*;

public class Encrypting
{
    /**
     * Function encrypts a password
     * @param Input input string a.k.a. password
     * @param Key coding number
     * @return encrypted string
     */
    public static String encryptIt(String Input, String Key)
    {
       //position of char in string (0,1,2, ...)
        int numberPos=0;
        // ascii index of a char in string
        int asciIndex;
        //output string
        StringBuilder Output=new StringBuilder();

        // encrypting engine:
        for(int i=0;i<Input.length();++i)
         {
          //transforms char to ascii index
           asciIndex=Input.charAt(i);

           /*if position of char in string is less than size of Key (example: Key=1356 has size of 4 numbers),
             we add an element value of Key (at index=numberPos) to the original asciIndex, then we increment value of number pos
             F. example: First element in message has asciIndex 0, our Key is 1234. The new asciIndex would be 0+Key[0]=>0+1=1
                         Second element in message has asciIndex 2, the new asciIndex would be 2+Key[1]=>2+2=4
                         and so on...
            */
           if(numberPos<Key.length())
            {
                 asciIndex+=(Key.charAt(numberPos));
                 Output.append((char)asciIndex);
                 numberPos+=1;

             }
        /*
            if the numberPos is greather than a size of coding key, we reset number pos by removing the size of Ke.y
            Remember, we have to be bounded in a size of key
         */
        else
            {
                numberPos -= Key.length();
                asciIndex+=(int)(Key.charAt(numberPos));
                Output.append((char)asciIndex);
                numberPos+=1;
            }
       }
        return Output.toString();
       }


    /**
     * Function decrypts a password
     * @param Input input string a.k.a. password
     * @param Key coding number
     * @return decrypted string
     */


     public static String decryptIt(String Input, String Key)
     {


         int numberPos=0;
         int asciIndex;
         StringBuilder Output=new StringBuilder();
         for(int i=0;i<Input.length();++i)
         {
             asciIndex=Input.charAt(i);
             if(numberPos<Key.length())
             {


                //the only difference is, that we wrote -= insted of += (reverse operation)
                 asciIndex-=(Key.charAt(numberPos));

                 Output.append((char)asciIndex);
                 numberPos+=1;

             }

             else
             {
                 numberPos -= Key.length();
                 asciIndex-=(int)(Key.charAt(numberPos));
                 Output.append((char)asciIndex);
                 numberPos+=1;
             }
         }
         return Output.toString();
     }

    }

