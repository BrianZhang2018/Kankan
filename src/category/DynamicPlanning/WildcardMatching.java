package category.DynamicPlanning;

/**
 * DP: Top-down
 * https://leetcode.com/problems/wildcard-matching/
 *    '*' Matches any sequence of characters (including the empty sequences)
 *              ==
 *    '*' match '0' or more sequence

 *    这个题的套路是：
 *    1. initiate a two dimensional boolean array, text is row, pattern is column
 *    2. initiate the first row
 *    3. for(row:i ...)  这个题就有下面的三种case
 *          for(column:j ...)
 *      if(s[i] == p[j] || p[j] == '?'){
             dp[i][j] = dp[i-1][j-1];
        }else if (p[j]== '*' ){
             dp[i][j] = dp[i-1][j] || dp[i][j-1];
        }else{ // case 3: two characters are not equeal
             dp[i][j] = false;
         }
 *
 * Similar problem: /Users/brianzhang/workspace/WaWahaha/src/category/DynamicPlanning/RegularExpressionMatching.java
 * 解法分析：https://www.youtube.com/watch?v=3ZDZ-N0EPV0&t=76s
 * Created by brianzhang on 4/13/19.
 */

public class WildcardMatching {
    public boolean isMatch(String s, String p) {
        if(p == null)
            return true;

        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;

        //initiate the first row
        for(int j=1; j<=p.length(); j++){
            if(p.charAt(j-1) == '*')
                dp[0][j] = dp[0][j - 1];
        }

        for(int i=1; i<=s.length(); i++){
            for(int j=1; j<=p.length(); j++){
                if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?'){  // case 1: two characters are equeal
                    dp[i][j] = dp[i-1][j-1];
                }else if (p.charAt(j-1) == '*' ){                            // case 2: *
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                    // '*' represent a sequence: dp[i-1][j] e.g: abcd : ab*d， 要看前一个字符的状态去推出当前状态
                    // '*' represent zero sequence: dp[i][j-1] e.g: abc : ab*c , 要看前一个pattern的状态去推出当前状态
                }else{                                                       // case 3: two characters are not equeal
                    dp[i][j] = false;
                }
            }
        }

        return dp[s.length()][p.length()];
    }
}