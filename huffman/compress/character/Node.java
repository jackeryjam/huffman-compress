package huffman.compress.character;

/**
 * Created by Jackery on 2017/11/30.
 */
public class Node {
    private int weight;
    private int parent;
    private int lchild;
    private int rchild;

    public Node(int weight, int parent, int lchild, int rchild) {
        this.weight = weight;
        this.parent = parent;
        this.lchild = lchild;
        this.rchild = rchild;
    }

    public Node(int weight) {
        this.weight = weight;
        this.parent = 0;
        this.lchild = 0;
        this.rchild = 0;
    }

    public int getWeight() {
        return weight;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public int getLchild() {
        return lchild;
    }

    public void setLchild(int lchild) {
        this.lchild = lchild;
    }

    public int getRchild() {
        return rchild;
    }

    public void setRchild(int rchild) {
        this.rchild = rchild;
    }

    public boolean isLeaf(){
        if(getLchild() == 0 && getRchild() == 0){
            return true;
        } else {
            return false;
        }
    }
}
