
/**
 * Write a description of testVigenereBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
import java.util.*;

public class testVigenereBreaker {
    private VigenereBreaker vb;
    
    public testVigenereBreaker(){
        vb = new VigenereBreaker();
    }
    
    public void test(){
        String ss = "abcdefghijklm";
        int sliceLen = 5;
        String[] newSS = new String[sliceLen];
        for (int k = 0; k<sliceLen; k++) {
            newSS[k] = vb.sliceString(ss, k, sliceLen);
        }
        
        //print the results
        for (int k=0; k<sliceLen; k++) {
            System.out.println("with index " + k + ", the slice is : " + newSS[k]);
        }
    }
    
    public void testTryKeyLength(){
        FileResource fr = new FileResource();
        String encryptedMsg = fr.asString();
        int klen = 5;
        char mostCommon = 'e';
        int[] keys = new int[klen];
        
        keys = vb.tryKeyLength(encryptedMsg, klen, mostCommon);
        System.out.println("In tryKeyLength(), the keys are : ");
        for (int k=0; k<klen; k++){
            System.out.print(keys[k] + ",");
        }
    }
    
    public void testReadDictionary(){
        FileResource fr = new FileResource();
        HashSet<String> dictionary = new HashSet<String>();
        dictionary = vb.readDictionary(fr);
        System.out.println("Number of words in the dictionary is : " 
                            + dictionary.size());
        String msg = "This is a test aaa bbb word";
        int numRealWord = vb.countWords(msg, dictionary);
        System.out.println("The message is : " + msg);
        System.out.println("Number of real words = " + numRealWord);
    }
    
}
