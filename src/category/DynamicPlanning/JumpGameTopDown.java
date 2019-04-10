package category.DynamicPlanning;

import java.util.Arrays;

/**
 * Created by brianzhang on 4/5/19.
 */
public class JumpGameTopDown {

    public static void main(String[] args) {
        JumpGameTopDown jumpGame = new JumpGameTopDown();
        jumpGame.canJump(new int[]{2, 4, 2, 1, 0, 2, 0});
    }

    Index[] memo;
    public boolean canJump(int[] nums) {
        if(nums == null || nums.length ==0)
            return true;

        memo = new Index[nums.length];
        Arrays.fill(memo, Index.UNKNOWN);
        memo[nums.length-1] = Index.GOOD;
        return helper(0, nums);
    }

    public boolean helper(int position, int[] nums){
        //memorization help you to record the result from previous loop to save ur time here
        //just like this question, it will help you skip some loop which already run before.
        if(memo[position] != Index.UNKNOWN){
            return memo[position] == Index.GOOD ? true : false;
        }

        int furthestJump=Math.min(position + nums[position], nums.length-1);
        for(int nextStep=position+1; nextStep<=furthestJump; nextStep++){
            if(helper(nextStep, nums)){
                memo[nextStep] = Index.GOOD;
                return true;
            }
        }
        memo[position] = Index.BAD;
        return false;
    }
}

enum Index{
    GOOD, BAD, UNKNOWN;
}