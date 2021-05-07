package category.greedy.farCanReach;

/**
 * https://leetcode.com/problems/jump-game-ii/
 *
 * Greedy
 * Created by brianzhang on 4/7/19.
 */
public class JumpGameII {
    public static void main(String[] args){
        System.out.println(jump(new int[]{2,3,1,1,4}));
    }

    public static int jump(int[] nums) {
        if(nums == null || nums.length == 0) return 0;

        int jump = 0, currEnd = 0, currFurthest=0;
        for(int i=0; i<nums.length-1; i++){
            currFurthest = Math.max(i + nums[i], currFurthest);
            if(i == currEnd){
                jump++;
                currEnd = currFurthest;
            }
        }
        return jump;
    }
}
