package category.DynamicPlanning;

import java.util.*;
/**
 * LeetCode Weekly Contest 157
 * start form the end of array
 * the idea is to accumulate number which has the same difference between each other
 * e.g.     7->5->3->1
 *   dp[i]: 4  3  2  1
 */
public class LongestArithmeticSubSequenceOfGivenDifference {

    public static void main(String[] args){
        System.out.println(longestSubSequence(new int[]{1,5,7,8,5,3,4,2,1}, -2));
        //System.out.println(longestSubSequence(new int[]{1,2,3,4}, 1));
     }

    public static int longestSubSequence(int[] arr, int d){
        int m = arr.length;
        int[] dp = new int[m];
        HashMap<Integer, Integer> map = new HashMap<>();
        int best = 0;

        for(int i=m-1; i>=0; --i){ //start from the end of array
            dp[i] = 1;
            if(map.containsKey(arr[i] + d)){
                dp[i] = Math.max(dp[i], map.get(arr[i] + d) +1);
            }
            map.put(arr[i], Math.max(dp[i], map.getOrDefault(arr[i], 0)));
            best = Math.max(best, dp[i]);
        }
        return best;
    }

}