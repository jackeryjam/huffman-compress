package huffman.compress.character;

import java.io.*;
import java.util.HashMap;

/**
 * Created by Jackery on 2017/12/1.
 */
public class Main {
    private static HuffmanTree huffmanTree;
    private static class ExtractResult{
        private String left;
        private byte[] buffer;

        public ExtractResult(String left, byte[] buffer) {
            this.left = left;
            this.buffer = buffer;
        }

        public String getLeft() {
            return left;
        }

        public byte[] getBuffer() {
            return buffer;
        }
    }

    private static String encode(String str){
        HashMap<Character,String> huffmanCodeMap = huffmanTree.getHuffmanCodeMap();
        String res = "";
        for(int i = 0; i < str.length(); i++){
            res += huffmanCodeMap.get(str.charAt(i));
        }
        return res;
    }

    private static ExtractResult extractByte(String str){
        int len = str.length() / 8;
        String left = str.substring(len * 8, str.length());
        byte[] buffer = new byte[len];
        for(int i = 0; i < len; i++){
            String target = str.substring(8*i,8*(i+1));
            buffer[i] = 0;
            for(int j = 0; j < 8; j++){
                char c = target.charAt(j);
                if(c == '0'){
                    continue;
                } else {
                    byte tmp = buffer[i];
                    switch(j){
                        case 0: tmp |= 0x80;break;
                        case 1: tmp |= 0x40;break;
                        case 2: tmp |= 0x20;break;
                        case 3: tmp |= 0x10;break;
                        case 4: tmp |= 0x08;break;
                        case 5: tmp |= 0x04;break;
                        case 6: tmp |= 0x02;break;
                        case 7: tmp |= 0x01;break;
                    }
                    buffer[i] =  tmp;
                }
            }
        }
        return new ExtractResult(left, buffer);
    }

    public static void compress(String dest, String src){
        CharCounter.setRoot("G:/project_hk/huffman/");
        CharCounter counter = new CharCounter(src);
        huffmanTree = new HuffmanTree(counter);

        try{
            BufferedInputStream BIS = new BufferedInputStream(new FileInputStream(src));
            BufferedOutputStream BOS = new BufferedOutputStream(new FileOutputStream(dest));
            byte[] buffer = new byte[1024];
            int len;
            String left = "";
            while((len = BIS.read(buffer)) != -1){
                String chunk = new String(buffer, 0, len);
                left += encode(chunk);
                ExtractResult extractResult = extractByte(left);
                left = extractResult.getLeft();
                BOS.write(extractResult.getBuffer());
            }
            if(left.length()>0){
                left += "00000000";
                BOS.write(extractByte(left).getBuffer());
            }
            BOS.close();
            BIS.close();
        } catch(IOException IOE){
            IOE.printStackTrace();
        }
    }

    public static void main(String[] agvs){
        compress("cacm.qg","cacm.all");
    }
}
