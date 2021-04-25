package category.Slidingwindow.templateSolution.template1;

/**
 * https://leetcode.com/problems/max-consecutive-ones-iii/discuss/247564/JavaC%2B%2BPython-Sliding-Window
 * Problem Translation: Find the longest subarray with at most 'K' zeros.
 *
 * Created by brianzhang on 11/24/20.
 */
public class MaxConsecutiveOnesIII {

    public static void main(String[] args) {
        System.out.println(longestOnes(new int[]{1,0,0,0,1,1,1,0,0,0}, 2));

        int a =1, b=1;
        if(a > 1 && b-- > 0) System.out.println(11);
        else System.out.println(b);
    }

    public static int longestOnes(int[] A, int K) {

        if(A == null || A.length == 0) return 0;
        int i=0, j=0;

        while(j<A.length){
            if(A[j++] == 0) K--;

            if(K<0 && A[i++] == 0) K++;
        }

        return j-i;
    }
}
