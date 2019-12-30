package category.DynamicPlanning;

/**
 * 重要: amazon 电面
 * DP: sub problem to solve the big problem. Time complexity: O(n^2), space: O(n)
 *
 * G(n): the number of unique BST for a sequence of length n.
   F(i, n), 1 <= i <= n: the number of unique BST, where the number i is the root of BST, and the sequence ranges from 1 to n.
 * DP formula: G(n) = F(1, n) + F(2, n) + ... + F(n, n).
 *
 * https://leetcode.com/problems/unique-binary-search-trees/
 * https://leetcode.com/problems/unique-binary-search-trees/discuss/31666/DP-Solution-in-6-lines-with-explanation.-F(i-n)-G(i-1)-*-G(n-i)
 * Good video explanation: https://www.youtube.com/watch?v=GgP75HAvrlY
 *
 * Catalan Number: http://www-math.mit.edu/~rstan/ec/catalan.pdf
 */
public class UniqueBinarySearchTrees{
    public static void main(String[] args){
        System.out.println(numTrees(3));
    }

    public static int numTrees(int n) {
        if(n == 0)
            return 0;
        
        // dp[n] represents the number of BST trees built from 1....n (like, 3)
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1; //only have one node

        // start from have 2 nodes, then 3 nodes calculated based on has 2 and 1 nodes cases
        for(int high=2; high<=n; high++){
            for(int root=1; root<=high; root++){
                // dp[high] accumulate all the value in current high 
                // dp[root-1] left subtree (1..root-1), dp[high-root] right subtree (root+1....high)
                dp[high]+= dp[root-1] * dp[high-root]; 
            }
        }
        
        return dp[n];
    }
}