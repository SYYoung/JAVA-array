import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder newMsg = new StringBuilder();
        for (int k=whichSlice; k<message.length(); k+=totalSlices){
            newMsg.append(message.charAt(k));
        }
        return newMsg.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        String[] msgs = new String[klength];
        //1. create substrings
        for (int k=0; k<klength; k++){
            msgs[k] = sliceString(encrypted, k, klength);
        }
        //2. feed each substrings to CaesarCracker
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for (int k=0; k<klength; k++){
            key[k] = cc.getKey(msgs[k]);
        }
        
        return key;
    }
    
    private void printKey(int[] keys){
        System.out.println("The keys are : ");
        for (int k=0; k<keys.length; k++){
            System.out.print(keys[k] +  ", ");
        }
    }

    public void breakVigenere () {
        // to read the encrypted message file
        FileResource fr = new FileResource();
        String encryptedMsg = fr.asString();
        // to read the dictionary
        FileResource dictFr = new FileResource();
        HashSet<String> dictionary = new HashSet<String>();
        dictionary = readDictionary(dictFr);
        String decryptedMsg = breakForLanguage(encryptedMsg, dictionary);
        
        System.out.println("Here is the encrypted message : ");
        System.out.println(encryptedMsg);
        System.out.println("Here is the decrypted message : ");
        System.out.println(decryptedMsg);
    }
    
    public void breakVigenereOld() {
        // to read the encrypted message file
        FileResource fr = new FileResource();
        String encryptedMsg = fr.asString();
         
        char mostCommon = 'e';
        int klength = 4;
        int[] keys = tryKeyLength(encryptedMsg, klength, mostCommon);
        printKey(keys);
        
        // now use the keys to decrypt the message
        VigenereCipher vc = new VigenereCipher(keys);
        String decryptedMsg = vc.decrypt(encryptedMsg);
        System.out.println("Here is the encrypted message : ");
        System.out.println(encryptedMsg);
        System.out.println("Here is the decrypted message : ");
        System.out.println(decryptedMsg);
    }
    
    public HashSet<String> readDictionary (FileResource fr) {
        HashSet<String> dict = new HashSet<String>();
        for (String line : fr.lines()){
            dict.add(line.toLowerCase());
        }
        return dict;
    }
    
    public  int countWords (String message, HashSet<String> dictionary) {
        String[] words;
        words = message.split("\\W+");
        int numRealWord = 0;
        // check how many words are real words
        for (int k=0; k<words.length; k++){
            if (dictionary.contains(words[k].toLowerCase())){
                numRealWord++;
            }
        }
        return numRealWord;
    }
    
    public String breakForLanguage (String encrypted, HashSet<String> dictionary) {
        int maxKeyLen = 1000;
        int[] keys = {0,0};
        int[] realKeys;
        char mostCommon = 'e';
        String decryptedMsg = "", realDecryptedMsg = "";
        int numWord = 0;
        int maxMatch = 0;
        
        
        for (int keyLen=1; keyLen<=maxKeyLen; keyLen++){
            keys = tryKeyLength(encrypted, keyLen, mostCommon);
            VigenereCipher vc = new VigenereCipher(keys);
            decryptedMsg = vc.decrypt(encrypted);
            numWord = countWords(decryptedMsg, dictionary);
            if (numWord > maxMatch){
                realDecryptedMsg = decryptedMsg;
                realKeys = keys;
                maxMatch = numWord;
            }
        }
        System.out.println("Here is the keys: " );
        for (int i=0; i<keys.length; i++){
            System.out.print(keys[i]);
        }
        System.out.println("total length of key is : " + keys.length);
        System.out.println("# of valid words are in decrypted string: " + maxMatch);
        
        return realDecryptedMsg;
    }
    
    public char mostCommonCharIn (HashSet<String> dictionary) {
        HashMap<Character,Integer> charFreq = new HashMap<Character, Integer>();
        for (String word : dictionary) {
            for (int k=0; k<word.length(); k++){
                if (charFreq.containsKey(word.charAt(k))){
                    
                }
                else {
                    
                }
            }
        }
    }
}
