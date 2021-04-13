package category.DynamicPlanning;

/**
 * https://leetcode.com/problems/regular-expression-matching/
 *
 * Compare with: /Users/brianzhang/workspace/WaWahaha/src/category/DynamicPlanning/WildcardMatching.java
 * "*" can't be at the first character in this question, but legal in WildcardMathing question
 *
 * dp[i][j]: first i number of characters in string whether match first j number of characters in patter string
 * e.g. dp[0][0] = true, dp[3][3] = true if str: "abcde", pattern="abc"
 *
 * Created by brianzhang on 3/10/19.
 */
public class RegularExpressionMatching {
    public static void main(String[] args) {
        System.out.println(isMatch("aa", "a*"));
        System.out.println(isMatch2("aaaaaa", "c*a*"));
        System.out.println(isMatch3("aab", "c*a*b"));
    }

    // Solution-1
    // https://www.youtube.com/watch?v=DqhPJ8MzDKM
    public static boolean isMatch(String s, String p) {
        if(p == null && p.isEmpty()) return s.isEmpty();

        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        for(int i=2; i<=p.length(); i++) { // '*' can't be in the first character, so start from "2"
            if(p.charAt(i-1) == '*'){
                dp[0][i] = dp[0][i-2];  // e.g. pattern "c*" can match empty ("") string
            }
        }

        for(int si=1; si<=m; si++) {
            for(int pj=1; pj<=n; pj++) {

                if(p.charAt(pj-1) == '.' || p.charAt(pj-1) == s.charAt(si-1)) {   // case 1 - match
                    dp[si][pj] = dp[si-1][pj-1]; // 需要往前看，因为当前是match, 前面也match整体才match
                }
                else if(p.charAt(pj-1) == '*'){                                  // case 2
                    // scenario-1: '*' match preceding element
                    if(p.charAt(pj-2) == s.charAt(si-1) || p.charAt(pj-2) == '.'){
                        // match zero, e.g. (pattern: abcde* MATCH string: abcd) => dp[si][pj-2]
                        // match preceding, e.g. (abcdd -> (si-1) = abcd MATCH abcd*) => dp[si-1][pj]
                        dp[si][pj] = dp[si][pj-2] || dp[si-1][pj];
                    }
                    else{  // scenario-2: '*' means zero
                        dp[si][pj] = dp[si][pj-2];
                    }
                }
                else{                                                            // case 3
                    dp[si][pj]=false;
                }

            }
        }
        return dp[m][n];
    }

    // Solution-2
    public static boolean isMatch2(String text, String pattern) {
        boolean[][] dp = new boolean[text.length()+1][pattern.length()+1];
        dp[text.length()][pattern.length()] = true;

        for (int i = text.length(); i >= 0; i--){
            for (int j = pattern.length() - 1; j >= 0; j--){

                boolean firstMatch = (i < text.length() && (pattern.charAt(j) == text.charAt(i) || pattern.charAt(j) == '.'));
                if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
                    dp[i][j] = dp[i][j+2] || firstMatch && dp[i+1][j];
                } else {
                    dp[i][j] = firstMatch && dp[i+1][j+1];
                }
            }
        }
        return dp[0][0];
    }

    // Solution-3: recursion
    public static boolean isMatch3(String text, String pattern) {
        if (pattern.isEmpty()) return text.isEmpty();

        boolean firstMatch = (!text.isEmpty() && (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

        // case 1: "*" is the second character of pattern which match the zero or more preceding element
        if (pattern.length() >= 2 && pattern.charAt(1) == '*'){
            if(isMatch3(text, pattern.substring(2))){  // match the remaining target string
                return true;
            }else{
                // remove the head character until got a remaining string which match the pattern that removed the "star to head" characters ->(pattern.substring(2))
                return firstMatch && isMatch3(text.substring(1), pattern);
            }
        } else {
            return firstMatch && isMatch3(text.substring(1), pattern.substring(1));
        }
    }
}
