
/**
 * Write a description of testCaesarCracker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class testCaesarCracker {
    private CaesarCracker cc;
    
    public testCaesarCracker(){
        cc = new CaesarCracker();
    }
    
    public void testDecrypt(){
        FileResource fr = new FileResource();
        String encryptMesg = fr.asString();
        String decryptMesg = cc.decrypt(encryptMesg);
        
        System.out.println("Here is the encrypted message : ");
        System.out.println(encryptMesg);
        System.out.println("Here is the decrypted message : ");
        System.out.println(decryptMesg);
    }
}
