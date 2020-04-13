package Database;
import java.io.BufferedReader;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

class Encrypting
{
    static private Cipher cipher;


    public static String encryptIt(String plainText, String password, int lineNumber) throws NoSuchPaddingException, NoSuchAlgorithmException {
        try
        {

            //initialization cipher
            Encrypting.cipher = Cipher.getInstance("AES");
            // initialization of secret key factory
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");




            KeySpec spec = new PBEKeySpec(password.toCharArray(),Integer.toString(lineNumber).getBytes(), 65536, 128);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey= new SecretKeySpec(tmp.getEncoded(), "AES");
            //converts text to bites
            byte[] plainTextBite = plainText.getBytes();
            //initialization of cipher engine
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            //encoding text and saving to an array
            byte[] encryptedByte = cipher.doFinal(plainTextBite);
            //initialization of converter to string
            Base64.Encoder encoder = Base64.getEncoder();
            //conversion to string
            String out=encoder.encodeToString(encryptedByte);


        return out;
        }
        catch(Exception e)
        {
            System.out.println(e.fillInStackTrace());
            System.out.println(" Error: Somethig was wrong!");
            return null;
        }

    }


    public static String decryptIt(String encryptedText, String password, int lineNumber)
    {


        try
        {

            //initialization cipher
            Encrypting.cipher = Cipher.getInstance("AES");
            // initialization of secret key factory
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");



            KeySpec spec = new PBEKeySpec(password.toCharArray(),(Integer.toString(lineNumber)).getBytes(), 65536, 128);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey= new SecretKeySpec(tmp.getEncoded(), "AES");

                Base64.Decoder decoder = Base64.getDecoder();
                byte[] encryptedTextByte = decoder.decode(encryptedText);
                cipher.init(Cipher.DECRYPT_MODE, secretKey);
                byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
                String out=new String(decryptedByte);

            return out;
        }
        catch (Exception e)
        {
            e.fillInStackTrace();
            System.out.println(" Error: Somethig was wrong!");
            return null;
        }
    }
}
