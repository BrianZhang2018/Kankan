package category.TwoPointer;

/**
 * DP: Top-down
 * https://leetcode.com/problems/wildcard-matching/
 *
 * dp[i][j] denotes whether s[0....i-1] matches p[0.....j-1]
 * dp[i][j]: true if the first i char in String s matches the first j chars in String p
 * Base case:
 *      origin: dp[0][0]: they do match, so dp[0][0] = true
 *      first row: dp[0][j]: except for String p starts with *, otherwise all false
 *      first col: dp[i][0]: can't match when p is empty. All false.
 * cases:
 *      for(row:i ...)  这个题就有下面的三种case
 *          for(column:j ...)
 *      if(s[i] == p[j] || p[j] == '?'){
             dp[i][j] = dp[i-1][j-1];
        }else if (p[j]== '*' ){
             dp[i][j] = dp[i-1][j] || dp[i][j-1];
        }else{ // case 3: two characters are not equal
             dp[i][j] = false;
         }
 *
 * Similar problem: /Users/brianzhang/workspace/WaWahaha/src/category/DynamicPlanning/RegularExpressionMatching.java
 * 解法分析：https://www.youtube.com/watch?v=3ZDZ-N0EPV0&t=76s
 * Created by brianzhang on 4/13/19.
 */
public class WildcardMatching {
    public static void main(String[] args) {
      //  System.out.println(isMatch("acdcb", "a*c?b"));
        System.out.println(isMatch("abcde", "*?*?*?*?"));
    }

    // greedy: two pointer + backtracking, preferred by confluent
    static boolean isMatch(String str, String pattern) {
        int s = 0, p = 0, lastStrMatchIdx = 0, lastStarIdx = -1;
        while (s < str.length()){
            // advancing both pointers
            if (p < pattern.length() && (pattern.charAt(p) == '?' || str.charAt(s) == pattern.charAt(p))){
                s++;
                p++;
            }
            // * found, only advancing pattern pointer
            else if (p < pattern.length() && pattern.charAt(p) == '*'){
                lastStarIdx = p;
                lastStrMatchIdx = s;
                p++;
            }
            // last pattern pointer was *, advancing string pointer
            else if (lastStarIdx != -1){
                p = lastStarIdx + 1;
                lastStrMatchIdx++;
                s = lastStrMatchIdx;
            }
            //current pattern pointer is not star, last patter pointer was not *
            //characters do not match
            else return false;
        }

        //check for remaining characters in pattern
        while (p < pattern.length() && pattern.charAt(p) == '*')
            p++;

        return p == pattern.length();
    }

    // DP
    public static boolean isMatchDP(String s, String p) {
        if(p == null) return true;

        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;
        // initiate the first row
        for(int j=1; j<=p.length(); j++){
            if(p.charAt(j-1) == '*')
                dp[0][j] = dp[0][j-1];
        }
        for(int i=1; i<=s.length(); i++){
            for(int j=1; j<=p.length(); j++){
                if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?'){  // case 1: two characters are equal
                    dp[i][j] = dp[i-1][j-1];
                }else if (p.charAt(j-1) == '*' ){                            // case 2: *
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                    // '*' represent a sequence: dp[i-1][j] e.g: abcd : ab*d， 要看前一个字符的状态去推出当前状态
                    // '*' represent zero sequence: dp[i][j-1] e.g: abc : ab*c , 要看前一个pattern的状态去推出当前状态
                }else{                                                       // case 3: two characters are not equal
                    dp[i][j] = false;
                }
            }
        }
        return dp[s.length()][p.length()];
    }
}