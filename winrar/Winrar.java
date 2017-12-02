package winrar;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Jackery on 2017/11/30.
 */
public class Winrar {
    public static long getCost() {
        return cost;
    }

    private static long cost = -1;
    private static String dist;
    private static String src;

    public static String getRoot() {
        return root;
    }

    public static void setRoot(String root) {
        Winrar.root = root;
    }

    private static String root = "";

    private static String cmdRarRes = "";

    public static void compress (String dist, String src) {
        dist = root + dist;
        src = root + src;
        Winrar.dist = dist;
        Winrar.src = src;
        long start = System.currentTimeMillis();
        String cmd = root + "Rar.exe a " + dist + " " + src;
        try {
            Process proc = Runtime.getRuntime().exec(cmd);
            BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream(),"GBK"));
            String readLine = br.readLine();
            while (readLine != null) {
                readLine = br.readLine();
                //System.out.println(readLine);
                cmdRarRes += readLine;
            }
            if(br!=null){
                br.close();
            }
            proc.destroy();
        }catch (IOException IOE){
            IOE.printStackTrace();
        }
        long end = System.currentTimeMillis();
        cost = end - start;
        System.out.println(cost);
        System.out.println(getCompressRate());
    }

    public static void main (String[] agvs) {
        root = "G:/project_hk/huffman/";
        compress("cacm", "cacm.all");
    }

    public static double getCompressRate(){
        File origin = new File(src);
        File compressd = new File(dist + ".rar");
        return origin.length()/compressd.length();
    }
}
