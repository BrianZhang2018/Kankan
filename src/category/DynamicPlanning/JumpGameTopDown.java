package category.DynamicPlanning;

/**
 * https://leetcode.com/articles/jump-game/
 *
 * Created by brianzhang on 4/5/19.
 */
public class JumpGameTopDown {
    public static void main(String[] args) {
        int[] testArray = {2, 4, 2, 1, 0, 2, 0};
        JumpGameTopDown jumpGame = new JumpGameTopDown();
        System.out.println("Can jump (bottom-up style): " + jumpGame.canJump(testArray));
        System.out.println("Can jump (simple DP): " + jumpGame.canJumpSimple(testArray));
    }

    // Simplified top-down approach that directly mirrors the bottom-up logic
    Boolean[] memo;
    public boolean canJump(int[] nums) {
        memo = new Boolean[nums.length];
        return canReachEnd(nums, nums.length - 1);
    }
    
    // This approach asks: "Can we reach the 'target' position from the start?"
    private boolean canReachEnd(int[] nums, int target) {
        // Base case: we're at the start position
        if (target == 0) {
            return true;
        }
        
        // Memoization check
        if (memo[target] != null) {
            return memo[target];
        }
        
        // Check all previous positions to see if any can reach this target
        // This directly mirrors the bottom-up logic: dp[i] = true if there exists j where dp[j] && nums[j] >= (i-j)
        for (int j = target - 1; j >= 0; j--) {
            // Can we reach position j AND jump from j to target?
            if (canReachEnd(nums, j) && nums[j] >= (target - j)) {
                return memo[target] = true;
            }
        }
        
        return memo[target] = false;
    }
    
    // Simple top-down DP approach
    Boolean[] memoSimple;
    public boolean canJumpSimple(int[] nums) {
        memoSimple = new Boolean[nums.length];
        return canReach(nums, 0);
    }
    
    private boolean canReach(int[] nums, int position) {
        // Base case: if we've reached or passed the last index
        if (position >= nums.length - 1) {
            return true;
        }
        
        // Memoization check
        if (memoSimple[position] != null) {
            return memoSimple[position];
        }
        
        // Try all possible jumps from current position
        for (int i = 1; i <= nums[position]; i++) {
            if (canReach(nums, position + i)) {
                return memoSimple[position] = true;
            }
        }
        
        return memoSimple[position] = false;
    }
}