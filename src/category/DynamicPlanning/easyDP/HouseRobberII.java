package category.DynamicPlanning.easyDP;

/**
 * 
 */
public class HouseRobberII {
    
    public int rob(int[] nums) {
        if(nums.length == 1)
            return nums[0];
        
        return Math.max(robHelper(nums, 0, nums.length-1), 
                        robHelper(nums, 1, nums.length));
    }
    
    public int robHelper(int[] nums, int start, int end){
        int rob = 0;
        int notrob = 0;
        for(int i=start; i<end; i++){
            int currRob = notrob + nums[i];
            notrob = Math.max(notrob, rob);
            rob = currRob;
        }

        return Math.max(rob, notrob);
    }
}