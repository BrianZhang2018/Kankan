package javabasic;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

/**
 * https://leetcode.com/problems/powx-n/discuss/252769/Simple-Java-Solution-that-beats-100
 *
 * Created by brianzhang on 11/6/20.
 */
public class Pow {

    public static void main(String[] args) {
        System.out.println(myPow0(2, 8));
        System.out.println(myPow1(2, -4));
        System.out.println(myPow2(2, 5));

        ArrayDeque<String> s = new ArrayDeque();
        s.push("1");
        s.push("2");

        for (String i : s) System.out.println(i);
    }

    // brute force timeout solution. time complexity: O(n)
    public static double myPow0(double x, int n) {
        if(n == 0) return 1;
        if(n == 1) return x;

        double base = x;
        for(int i=0; i<Math.abs(n)-1; i++)
            x *= base;

        return n < 0 ? 1/x : x;
    }

    // cut it into half, half since X^8 = X^4 * X^4. time complexity: O(logn)
    public static double myPow1(double x, int n) {
        if(n == 1) return x;
        if(n == -1) return 1 / x;
        if(n == 0) return 1.0;

        double half = myPow1(x, n / 2);
        return half * half * myPow1(x, n % 2);
    }

    // iterative solution
    public static double myPow2(double x, int n) {
        double result = 1;

        for(int i=n; i!=0; i/=2, x*=x) { // same idea, half, half
            if(i%2 !=0) {
                result *= x;
            }
        }

        return n<0 ? 1/result : result;
    }
}
