package category.javabasic;

import java.util.BitSet;

/**
 * Created by brianzhang on 5/4/20.
 */
public class BitSetExample {

    public static void main(String[] args) {
        BitSet bitSet = new BitSet(10);
        bitSet.set(0);
        System.out.println(bitSet.get(0));
        System.out.println(bitSet.get(1));
    }

}
