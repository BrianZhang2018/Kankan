package category.DynamicPlanning.easyDP;

/**
 * https://leetcode.com/problems/house-robber/
 * 
 * 详细解析5种做法
 * https://leetcode.com/problems/house-robber/discuss/156523/From-good-to-great.-How-to-approach-most-of-DP-problems.
 * 
 * https://leetcode.com/problems/house-robber/discuss/55695/JAVA-DP-Solution-O(n)-runtime-and-O(1)-space-with-inline-comment
 * */
public class HouseRobber {

    public static void main(String[] args){
        System.out.println(rob(new int[]{1,2,3,1}));
    }
    
    // iterative - bottom up
    public static int rob(int[] nums) {
        int rob = 0; // max money can get if rob current house
        int notRob = 0; // max money can get if not rob current house
        for(int i=0; i<nums.length; i++) {
            int curRob = notRob + nums[i]; // if rob current value, previous house must not be robbed
            notRob = Math.max(rob, notRob); // if don't rob ith house, take the max value of (robbed (i-1)th house, not rob (i-1)th house)
            rob = curRob;
        }
        return Math.max(rob, notRob);
    }

    // recursive - top down - timeout solution
    public static int rob1(int[] nums) {
        return helper(nums, nums.length-1);
    }

    public static int helper(int[] nums, int i){
        if(i < 0) return 0;

        return Math.max(helper(nums, i-2) + nums[i], helper(nums, i-1));
    }
}