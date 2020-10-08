package category.javabasic;

/**
 * https://leetcode.com/problems/number-of-1-bits/
 *
 * https://stackoverflow.com/questions/26315782/binary-presentation-of-negative-integer-in-java
 *
 * Created by brianzhang on 9/17/20.
 */
public class NumberOf1Bits {

    public static void main(String[] args) {
        Integer i = 00001111110055;

        System.out.println(i);
       /* System.out.println(hammingWeight(i));
        System.out.println("+++++++++++++++++++");
        System.out.println(hammingWeight(j));*/
        System.out.println(4>>>1);
        System.out.println(-4<<1);
    }

    public static int hammingWeight(int n) {
        int count = 0;

        while (n != 0) {
            count += (n & 1);
            n >>>= 1;
            System.out.println(n);
        }

        return count;
    }

    static void bin(int n)
    {
        if (n > 1)
            bin(n/2);

        System.out.print(n % 2);
    }
}