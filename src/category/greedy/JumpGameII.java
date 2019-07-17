package category.greedy;

/**
 * Greedy
 * https://leetcode.com/problems/jump-game-ii/
 * Created by brianzhang on 4/7/19.
 */
public class JumpGameII {
    public static void main(String[] args){
        System.out.println(jump(new int[]{2,3,1,1,4}));
    }

    public static int jump(int[] nums) {
        if(nums == null || nums.length == 0)
            return 0;

        int jump = 0, currEnd = 0, currFurtherest=0;
        for(int i=0; i<nums.length-1; i++){
            currFurtherest = Math.max(i+nums[i], currFurtherest);
            if(i == currEnd){
                jump++;
                currEnd = currFurtherest;
            }
        }
        return jump;
    }
}
