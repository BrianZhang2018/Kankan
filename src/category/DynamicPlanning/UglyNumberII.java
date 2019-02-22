package category.DynamicPlanning;

/**
 * https://www.geeksforgeeks.org/finding-n-th-number-made-prime-digits/
 *
 * Created by brianzhang on 2/20/19.
 */
public class UglyNumberII {

    public int nthUglyNumber(int n) {

        int[] ugly = new int[n];
        ugly[0] = 1;
        int index2 = 0, index3 = 0, index5 = 0;
        int nextMultiple2 = 2;
        int nextMultiple3 = 3;
        int nextMultiple5 = 5;

        for (int i = 1; i < n; i++) {
            int min = Math.min(Math.min(nextMultiple2, nextMultiple3), nextMultiple5);
            ugly[i] = min;
            if (min == nextMultiple2) {
                index2++;
                nextMultiple2 = ugly[index2] * 2;
            }

            if (min == nextMultiple3) {
                index3++;
                nextMultiple3 = ugly[index3] * 3;
            }

            if (min == nextMultiple5) {
                index5++;
                nextMultiple5 = ugly[index5] * 5;
            }

        }

        return ugly[n - 1];
    }
}
