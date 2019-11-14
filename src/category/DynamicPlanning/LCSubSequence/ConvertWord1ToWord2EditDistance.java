package category.DynamicPlanning.LCSubSequence;

/**
 * https://leetcode.com/problems/edit-distance/discuss/25849/Java-DP-solution-O(nm)
 *
 * Created by brianzhang on 11/3/19.
 */
public class ConvertWord1ToWord2EditDistance {

    public static void main(String[] args) {
        System.out.println(minDistance("horse", "ros"));
    }

    public static int minDistance(String word1, String word2) {

        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m+1][n+1];

        //base case: f(0, k) = f(k, 0) = k
        for(int i=1; i<=word1.length(); i++){
            dp[i][0] = i;
        }
        for(int j=1; j<=word2.length(); j++){
            dp[0][j] = j;
        }

        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{

                    int insert = dp[i][j-1];
                    int delete = dp[i-1][j];
                    int replace = dp[i-1][j-1];
                    dp[i][j] = Math.min(replace, Math.min(insert, delete)) + 1;
                }
            }
        }

        return dp[m][n];
    }

}
