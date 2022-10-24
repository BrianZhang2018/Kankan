package category.DynamicPlanning.DistinctWays;

import java.math.BigInteger;

/**
 * Created by brianzhang on 7/23/18.
 */
public class Fibonacci {
    public static void main(String args[]) {
        System.out.println(fibonacci(5));
        getFibonacci(5);
    }

    // DP solution
    public static int fibonacci(int n) {
        // Declare an array to store Fibonacci numbers
        int f[] = new int[n + 1];
        int i;

        f[0] = 0; f[1] = 1; // 0th and 1st number of the series are 0 and 1
        for (i = 2; i <= n; i++) {
            f[i] = f[i - 1] + f[i - 2]; // Add the previous 2 numbers in the series and store it
        }

        return f[n];
    }

    // dfs solution
    static void getFibonacci(int n){
        for(int i=0; i<n; i++){
            System.out.println(fibonacci1(i));
        }
    }

    // 面试题目：fibonacci print first 100 numbers.
    // BigInteger can be used to represent the number beyond all the primitive types
    static BigInteger fibonacci1(int n)
    {
        if(n < 2) return BigInteger.valueOf(n);

        return fibonacci1(n-1).add(fibonacci1(n-2));
    }
}
