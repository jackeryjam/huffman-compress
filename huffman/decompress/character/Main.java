package huffman.decompress.character;

import huffman.compress.character.CharCounter;

import java.io.*;

/**
 * Created by Jackery on 2017/12/1.
 */
public class Main {
    private CharCounter counter;
    private static HuffmanTree huffmanTree;

    public static void decompress(String dest, String src) {

        try{
            BufferedInputStream BIS = new BufferedInputStream(new FileInputStream(src));
            BufferedOutputStream BOS = new BufferedOutputStream(new FileOutputStream(dest));
            byte[] buffer = new byte[1024];
            int len;
            while((len = BIS.read(buffer)) != -1){
                String result = decode(buffer);
                BOS.write(result.getBytes());
            }
            BOS.close();
            BIS.close();
        } catch(IOException IOE){
            IOE.printStackTrace();
        }
    }

    private static String decode(byte[] bytes){
        int len = bytes.length;
        String str = "";
        for(int i = 0; i < len; i++){
            str += huffmanTree.decode(bytes[i] & 0x80);
            str += huffmanTree.decode(bytes[i] & 0x40);
            str += huffmanTree.decode(bytes[i] & 0x20);
            str += huffmanTree.decode(bytes[i] & 0x10);
            str += huffmanTree.decode(bytes[i] & 0x08);
            str += huffmanTree.decode(bytes[i] & 0x04);
            str += huffmanTree.decode(bytes[i] & 0x02);
            str += huffmanTree.decode(bytes[i] & 0x01);
        }
        return str;
    }

    public static void main(String[] agvs){
        CharCounter.setRoot("G:/project_hk/huffman/");
        CharCounter counter = new CharCounter("cacm.all");
        huffmanTree = new HuffmanTree(counter);
        decompress("decompress.all","cacm.qg");
    }
}
