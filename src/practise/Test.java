package practise;

import java.util.BitSet;

class Test {
    public static void main(String[] args){
       
        Test test = new Test();
        System.out.println(2147483647 * 2);

        BitSet bits1 = new BitSet(16);
        BitSet bits2 = new BitSet(16);

        bits1.set(1);
        System.out.println(bits1);
        System.out.println(bits1.get(2));
    }



}