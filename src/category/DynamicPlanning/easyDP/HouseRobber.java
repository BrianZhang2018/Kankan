package category.DynamicPlanning.easyDP;

/**
 * Problem：
 * https://leetcode.com/problems/house-robber/
 * 
 * 详细解析5种做法
 * https://leetcode.com/problems/house-robber/discuss/156523/From-good-to-great.-How-to-approach-most-of-DP-problems.
 * 
 * https://leetcode.com/problems/house-robber/discuss/55695/JAVA-DP-Solution-O(n)-runtime-and-O(1)-space-with-inline-comment
 * */
public class HouseRobber {

    public static void main(String[] args){
        HouseRobber hr = new HouseRobber();
        System.out.println(hr.rob1(new int[]{1,2,3,1}));
    }
    
    //iterative - bottom up
    public int rob(int[] nums) {
        int rob = 0; //max monney can get if rob current house
        int notrob = 0; //max money can get if not rob current house
        for(int i=0; i<nums.length; i++) {
            int currob = notrob + nums[i]; //if rob current value, previous house must not be robbed
            notrob = Math.max(notrob, rob); //if not rob ith house, take the max value of robbed (i-1)th house and not rob (i-1)th house
            rob = currob;
        }
        return Math.max(rob, notrob);
    }

    //recursive - top down
    public int rob1(int[] nums) {
        return helper(nums, nums.length-1);
    }

    public int helper(int[] nums, int i){
        if(i < 0){
            return 0;
        }
        return Math.max(helper(nums, i-2) + nums[i], helper(nums, i-1));
    }
}