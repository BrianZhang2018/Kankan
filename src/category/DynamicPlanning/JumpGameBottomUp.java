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
        JumpGameBottomUp test = new JumpGameBottomUp();
        System.out.println(test.canJump(new int[]{2, 4, 2, 1, 0, 2, 0}));
    }
    Index[] memo;

    public boolean canJump(int[] nums) {
        memo = new Index[nums.length];
        Arrays.fill(memo, Index.UNKNOWN);
        memo[nums.length-1] = Index.GOOD;

        for(int i=nums.length-2; i>=0; i--){
            int furthestJump = Math.min(i+nums[i], nums.length-1);
            for(int j=i+1; j<=furthestJump; j++){
                if(memo[j] == Index.GOOD){
                    memo[i] = Index.GOOD;
                    break;
                }
            }
        }
        return memo[0] == Index.GOOD ? true : false;
    }
}
