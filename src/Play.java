import java.util.BitSet;

public class Play {
    public static void main(String[] args){

        int[][] dirs = new int[][]{{0,1}, {1,0}, {-1, 0}, {0,-1}};
        System.out.println(dirs.length);
        BitSet bitSet = new BitSet(8);
        bitSet.set(2);
        System.out.println(bitSet.get(1));

    }
}