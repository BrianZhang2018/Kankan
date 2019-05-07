package category.DynamicPlanning.easyDP;

/**
 * https://leetcode.com/problems/house-robber/
 * https://leetcode.com/problems/house-robber/discuss/55695/JAVA-DP-Solution-O(n)-runtime-and-O(1)-space-with-inline-comment
 * https://leetcode.com/problems/house-robber/discuss/156523/From-good-to-great.-How-to-approach-most-of-DP-problems.
 * */
public class HouseRobber {
    
    public int rob(int[] nums) {
        int rob =0, notrob = 0;
        
        for(int i=0; i<nums.length; i++){
            int currRob = notrob + nums[i];
            notrob = Math.max(notrob, rob);
            rob = currRob;
        }
        
        return Math.max(rob, notrob);
    }
}