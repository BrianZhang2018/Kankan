package category.DynamicPlanning;

/**
 * DP: sub problem to solve the big problem
 * time complexity: O(n^2), space: O(n)
 * 
 * https://leetcode.com/problems/unique-binary-search-trees/
 * good video: https://www.youtube.com/watch?v=GgP75HAvrlY
 * Catalan Number: http://www-math.mit.edu/~rstan/ec/catalan.pdf
 */
public class UniqueBinarySearchTrees{
    public static void main(String[] args){
        System.out.println(numTrees(3));
    }

    public static int numTrees(int n) {
        if(n == 0)
            return 0;
        
        // dp[k] represents the number of BST trees built from 1....n (like, 3)
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1; //only have one node
        
        for(int high=2; high<=n; high++){ // start from have 2 nodes, then 3 nodes calculated based on has 2 and 1 nodes cases
            for(int root=1; root<=high; root++){
                // dp[high] accumulate all the value in current high 
                // dp[root-1] left subtree, dp[high-root] right subtree
                dp[high]+= dp[root-1] * dp[high-root]; 
            }
        }
        
        return dp[n];
    }
}