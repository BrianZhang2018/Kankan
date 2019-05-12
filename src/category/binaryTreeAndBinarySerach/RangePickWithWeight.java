package category.binaryTreeAndBinarySerach;

import java.util.Random;
import java.util.Stack;

/**
 * Created by brianzhang on 2/26/19.
 */
public class RangePickWithWeight {

    public static void main(String[] args) {
        RangePickWithWeight test = new RangePickWithWeight(new int[]{1, 5, 2, 2});
        System.out.println(test.pickIndex());

        Stack<Character> haha = new Stack();
    }

    Random random;
    int[] wSums;

    public RangePickWithWeight(int[] w) {
        this.random = new Random();
        //accumulated sum
        for (int i = 1; i < w.length; ++i)
            w[i] += w[i - 1];
        this.wSums = w;
    }

    public int pickIndex() {
        int len = wSums.length;
        int idx = random.nextInt(wSums[len - 1]) + 1;
        int left = 0, right = len - 1;
        // search position
        while (left < right) {
            //avoid the integer is too big too cause the overflow issue
            int mid = left + (right - left) / 2;
            if (wSums[mid] == idx)
                return mid;
            else if (wSums[mid] < idx)
                left = mid + 1;
            else
                right = mid;
        }
        return left;
    }
}
