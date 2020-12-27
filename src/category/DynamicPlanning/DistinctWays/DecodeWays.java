package category.DynamicPlanning.DistinctWays;

/**
 * https://leetcode.com/problems/decode-ways/
 * 
 * dp[1] means the way to decode a string of size 1. 
 * Then check one digit and two digit combination and save the results along the way.
 * As the range of number is 1-26, if the nubmer beyond is 3 digit, which is not decoded.
 * In the end, dp[n] will be the end result
 * 
 * https://leetcode.com/problems/decode-ways/discuss/30358/Java-clean-DP-solution-with-explanation
 */
public class DecodeWays {
    public static void main(String[] args){
        DecodeWays test = new DecodeWays();
        System.out.println(test.numDecodings("102213"));
    }

    public int numDecodings(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n+1];
        dp[0] = 1; // we consider the empty string is one decode way
        dp[1] = s.charAt(0) != '0' ? 1 : 0;
        for(int i = 2; i <= n; i++) {

            int oneDigit = Integer.valueOf(s.substring(i-1, i));
            int towDigits = Integer.valueOf(s.substring(i-2, i));
            if(oneDigit != 0) {
               dp[i] += dp[i-1];  
            }
            // we have got the "dp" value for "single digit" from above
            // the below is for the twoDigits = dp[i-1] + dp[i-2]
            if(towDigits >= 10 && towDigits <= 26) {
                dp[i] += dp[i-2];
            }
        }
        return dp[n];
    }
}