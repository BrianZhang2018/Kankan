package category.DynamicPlanning.String.LongestCommonSubSequence;

/**
 * https://leetcode.com/problems/delete-operation-for-two-strings/
 *
 * LCSubSequence 变形
 */
public class DeleteLetterMakeTwoStringEqual {
    
    public int minDistance(String word1, String word2) {
        
        int m = word1.length();
        int n = word2.length();
          
        int[][] dp = new int[m+1][n+1];
          
        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] +1;
                }else{
                     dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
                    
            }
        }
          
        return (m + n - dp[m][n] *2);
      }
}