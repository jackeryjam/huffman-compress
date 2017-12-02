package huffman.compress.character;

import java.util.HashMap;

/**
 * Created by Jackery on 2017/11/30.
 */
public class HuffmanTree {
    private CharCounter counter;
    private HashMap<Character,Integer> charMap;
    private int size;
    private Node[] HT;
    private Character HC[];
    private HashMap<Character,String> huffmanCodeMap;

    public HuffmanTree(CharCounter counter) {
        this.counter = counter;
        charMap = counter.getCharMap();
        size = charMap.size();
        buildTree();
        buildCodeMap();
    }

    public HashMap<Character, String> getHuffmanCodeMap() {
        return huffmanCodeMap;
    }

    private int[] select(int n){
        int[] mins = new int[2];
        int[] weights = new int[2];
        weights[0] = weights[1] = Integer.MAX_VALUE;
        for(int i = 1; i <= n; i++) {
            if(HT[i].getParent() != 0) {
                continue;
            } else {
                if(HT[i].getWeight() < weights[0]) {
                    weights[1] = weights[0];
                    mins[1] = mins[0];
                    weights[0] = HT[i].getWeight();
                    mins[0] = i;
                } else if(HT[i].getWeight() < weights[1]) {
                    weights[1] = HT[i].getWeight();
                    mins[1] = i;
                }
            }
        }
        return mins;
    }

    public void buildTree(){
        if(size <= 1) return;
        int n = size;
        int m = 2*n - 1;
        HT = new Node[m + 1];
        HC = new Character[m + 1];
        int i = 1;
        for(HashMap.Entry<Character, Integer> entry: charMap.entrySet()){
            HT[i] = new Node(entry.getValue());
            i++;
        }
        for(;i <= m; i++){
            int[] mins = select(i - 1);
            Node min1 = HT[mins[0]];
            min1.setParent(i);
            Node min2 = HT[mins[1]];
            min2.setParent(i);
            HT[i] = new Node(min1.getWeight()+min2.getWeight(), 0, mins[0], mins[1]);
        }
    }

    private String getCode(Character c, int index){
        String code = new String();
        Node p = HT[index];
        while(p.getParent() != 0){
            int pre = index;
            index = p.getParent();
            p = HT[p.getParent()];
            if(p.getLchild() == pre) {
                code = code.concat("0");
            } else {
                code = code.concat("1");
            }
        }
        return new StringBuffer(code).reverse().toString();
    }

    public void buildCodeMap(){
        huffmanCodeMap = new HashMap<Character, String>();
        int i = 1;
        for(HashMap.Entry<Character, Integer> entry: charMap.entrySet()){
            Character key = entry.getKey();
            String code = getCode(key, i);
            huffmanCodeMap.put(key,code);
            i++;
        }
    }

    public String getCode(Character c){
        return huffmanCodeMap.get(c);
    }
}
