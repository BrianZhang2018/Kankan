package category.DynamicPlanning;

/**
 * Created by brianzhang on 7/23/18.
 */
public class FibonacciDynaMemoSolution {

    public static int fibArr[] = new int[99];

    public static int fibonacci(int n) {
        int fibVal = 0;
        if (n < 2) {
            return n;
        }

        if (fibArr[n] != 0) {
            return fibArr[n];
        } else {
            fibVal = fibArr[n - 1] + fibArr[n - 2];
            fibArr[n] = fibVal;
            return fibVal;
        }
    }

    public static void main(String args[]) {
        fibArr[0] = 1;
        fibArr[1] = 1;
        long preTime = System.currentTimeMillis();
        System.out.println("Value of 25th number in Fibonacci series->" + fibonacci(25));
        long postTime = System.currentTimeMillis();
        System.out.println("Time taken to compute in milliseconds->" + (postTime - preTime));
    }

}
