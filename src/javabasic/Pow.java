package javabasic;

/**
 * https://leetcode.com/problems/powx-n/discuss/252769/Simple-Java-Solution-that-beats-100
 *
 * Created by brianzhang on 11/6/20.
 */
public class Pow {

    public static void main(String[] args) {
        System.out.println(myPow(2, 3));
        System.out.println(myPow2(2, 3));
    }

    public static double myPow2(double x, int n) {
        double result = 1;

        for(int i=n; i!=0; i/=2,x*=x) {
            if(i%2 !=0) {
                result *= x;
            }
        }

        return n<0 ? 1/result : result;
    }

    public static double myPow(double x, int n) {
        if(n == 1) return x;
        if(n == -1) return 1 / x;
        if(n == 0) return 1.0;
        double half = myPow(x, n / 2);
        return half * half * myPow(x, n % 2);
    }


}
