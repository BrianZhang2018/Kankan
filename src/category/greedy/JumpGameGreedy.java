package category.greedy;

/**
 * https://leetcode.com/problems/jump-game/
 * 
 * Created by brianzhang on 4/6/19.
 */
public class JumpGameGreedy {

    public boolean canJump(int[] nums) {

        if(nums == null || nums.length == 0)
            return true;
        int lastPos = nums.length-1;
        for(int i=nums.length-2; i>=0; i--){
            if(i+nums[i] >= lastPos){
                lastPos = i;
            }
        }
        return lastPos == 0;
    }
}
