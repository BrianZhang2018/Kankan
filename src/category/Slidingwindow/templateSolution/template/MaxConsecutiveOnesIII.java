package category.Slidingwindow.templateSolution.template;

/**
 * https://leetcode.com/problems/max-consecutive-ones-iii/
 * Problem Translation: Find the longest subarray with at most K zeros.
 *
 * https://leetcode.com/problems/max-consecutive-ones-iii/discuss/247564/JavaC%2B%2BPython-Sliding-Window
 * Created by brianzhang on 11/24/20.
 */
public class MaxConsecutiveOnesIII {

    public static void main(String[] args) {
        System.out.println(longestOnes(new int[]{1,1,1,0,0,0,1,1,1,1,0}, 2));
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
