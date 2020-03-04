package category.DynamicPlanning.easyDP;

/**
 * https://leetcode.com/problems/house-robber-ii/
 */
public class HouseRobberII {

    public static void main(String[] args) {
        HouseRobberII test = new HouseRobberII();
        System.out.println(test.rob(new int[]{2,3,2}));
    }
    
    public int rob(int[] nums) {
        if(nums.length == 1)
            return nums[0];
        
        return Math.max(robHelper(nums, 0, nums.length-1), 
                        robHelper(nums, 1, nums.length));
    }
    
    public int robHelper(int[] nums, int start, int end){
        int rob = 0;
        int notRob = 0;
        for(int i=start; i<end; i++){
            int currRob = notRob + nums[i];
            notRob = Math.max(notRob, rob);
            rob = currRob;
        }

        return Math.max(rob, notRob);
    }
}