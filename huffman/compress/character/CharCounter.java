package huffman.compress.character;

import java.io.*;
import java.util.HashMap;

/**
 * Created by Jackery on 2017/11/30.
 */
public class CharCounter {
    private File file;
    private HashMap<Character,Integer> charMap = new HashMap<Character, Integer>();
    private static String root = "";

    public static String getRoot() {
        return root;
    }

    public static void setRoot(String root) {
        CharCounter.root = root;
    }

    public CharCounter(String fileName) {
        file = new File(root + fileName);
        exeCount();
    }

    public HashMap<Character, Integer> getCharMap() {
        return charMap;
    }

    private void addCharToMap(char c) {
        if(charMap.containsKey(c)){
            Integer val = charMap.get(c);
            charMap.replace(c,val + 1);
        } else {
            charMap.put(c,1);
        }
    }

    public void exeCount(){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String readLine = null;
            while ((readLine = br.readLine()) != null) {
                for(char c: readLine.toCharArray()){
                    addCharToMap(c);
                }
                addCharToMap('\n');
            }
        }catch (IOException IOE){
            IOE.printStackTrace();
        }
    }

}