package huffman.compress.character;

/**
 * Created by Jackery on 2017/12/1.
 */
public class Test {
    public static void main(String[] agvs){
        CharCounter.setRoot("G:/project_hk/huffman/");
        CharCounter test = new CharCounter("test.txt");
        System.out.println(test.getCharMap().toString());

        HuffmanTree huffmanTree = new HuffmanTree(test);
        System.out.println(huffmanTree.getHuffmanCodeMap().toString());
    }
}
