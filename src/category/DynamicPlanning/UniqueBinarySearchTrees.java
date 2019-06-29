package category.DynamicPlanning;

/**
 * DP: sub problem to solve the big problem
 * 
 * https://leetcode.com/problems/unique-binary-search-trees/
 * good video: https://www.youtube.com/watch?v=GgP75HAvrlY
 */
public class UniqueBinarySearchTrees{
    public static void main(String[] args){
        System.out.println(numTrees(3));
    }

    public static int numTrees(int n) {
        if(n == 0)
            return 0;
        
        // dp[k] represents the number of BST trees built from 1....k
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        
        for(int high=2; high<=n; high++){
            for(int root=1; root<=high; root++){
                // dp[high] accumulate all the value in current high 
                // dp[root-1] left subtree, dp[high-root] right subtree
                dp[high]+= dp[root-1] * dp[high-root]; 
            }
        }
        
        return dp[n];
    }
}