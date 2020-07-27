package category.DynamicPlanning;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/
 * Created by brianzhang on 7/18/20.
 */
public class MinimumSwapMakeSequenceIncreasing {

    public static void main(String[] args) {
        int[] A = {1, 3, 5, 4}, B = {1, 2, 3, 7};
        System.out.println(minSwap(A, B));
    }

    public static int minSwap(int[] A, int[] B) {
        int n = A.length;
        int not_swap[] = new int[n];
        //not_swap[i] -> min swaps to make {A[0]~A[i]} and {B[0]~B[i]} without swapping A[i] and B[i]
        int swap[] = new int[n];
        //swap[i] -> min swaps to make {A[0]~A[i]} and {B[0]~B[i]} with swapping A[i] and B[i]
        Arrays.fill(not_swap, Integer.MAX_VALUE);
        Arrays.fill(swap, Integer.MAX_VALUE);
        not_swap[0] = 0;
        swap[0] = 1;

        for (int i = 1; i < n; i++) {
            if (A[i - 1] < A[i] && B[i - 1] < B[i]) {
                swap[i] = swap[i - 1] + 1; //swap both A[i - 1], B[i - 1] & A[i], B[i]
                not_swap[i] = not_swap[i - 1]; //don't swap both A[i - 1], B[i - 1] & A[i], B[i]
            }
            if (A[i] > B[i - 1] && B[i] > A[i - 1]) {
                swap[i] = Math.min(swap[i], not_swap[i - 1] + 1); //if we swap A[i],B[i], we don't need to swap A[i - 1],B[i - 1]
                //not_swap[i - 1] + 1 because we didn't swap A[i - 1] & B[i - 1] and +1 for current swap
                not_swap[i] = Math.min(not_swap[i], swap[i - 1]); //if we swap A[i - 1],B[i - 1], we don't need to swap A[i],B[i]
            }
        }

        return Math.min(swap[n - 1], not_swap[n - 1]);
    }
}
