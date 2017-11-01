
/**
 * Write a description of testCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class testCaesarCipher {
    private CaesarCipher cc;
    private int key;
    
    public testCaesarCipher(){
        key = 4;
        cc = new CaesarCipher(key);
    }
    
    public void encrypt(){
        FileResource fr = new FileResource();
        String ss = fr.asString();
        String encryptMesg = cc.encrypt(ss);
        String decryptMesg = cc.decrypt(encryptMesg);
        
        System.out.println("Here is the original content:");
        System.out.println(ss);
        System.out.println("Here is the encrypted message with key = " + key);
        System.out.println(encryptMesg);
        System.out.println("Here is the decrypted message ");
        System.out.println(decryptMesg);
    }
}
