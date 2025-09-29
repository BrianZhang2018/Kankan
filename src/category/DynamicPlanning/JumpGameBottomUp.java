package category.DynamicPlanning;

import java.util.Arrays;

/**
 * bottom up has the better performance as don't need the recursion
 * Done this referring the leetcode 55: https://leetcode.com/articles/jump-game/
 *
 * The observation to make here is that we only ever jump to the right.
 * This means that if we start from the right of the array, every time we will query a position to our right,
 * that position has already be determined as being GOOD or BAD. (Since we initiated the last position with Index.GOOD)
 * This means we don't need to recurse anymore, as we will always hit the memo table. (awesome)
 *
 * Created by brianzhang on 4/5/19.
 */
public class JumpGameBottomUp {
    public static void main(String[] args) {
        System.out.println(new JumpGameBottomUp().canJump1(new int[]{2, 4, 2, 1, 0, 2, 0}));
    }

    public boolean canJump1(int[] nums) {
        boolean[] dp = new boolean[nums.length];
        dp[0] = true;
        
        for(int i=1; i< nums.length; i++){
            for(int j = i-1; j>=0; j--){
                if(dp[j] && nums[j] >= (i-j)) // see below explanation
                    dp[i] = true;
            }
        }
        
        return dp[nums.length-1];
    }

    /**
     * Explanation of nums[j] >= (i-j):
     * This condition is checking whether we can jump from position j to position i.
     *
     * Breaking it down:
     * nums[j] = The maximum number of steps we can jump from position j
     * (i-j) = The distance from position j to position i
     * nums[j] >= (i-j) = Can we jump from j to i?
     */

}
