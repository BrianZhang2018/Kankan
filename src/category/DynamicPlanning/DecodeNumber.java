package category.DynamicPlanning;

/**
 * https://leetcode.com/problems/decode-ways/
 * 
 * dp[1] means the way to decode a string of size 1. 
 * Then check one digit and two digit combination and save the results along the way.
 * As the range of number is 1-26, if the nubmer beyond is 3 digit, which is not decoded.
 * In the end, dp[n] will be the end result
 * https://leetcode.com/problems/decode-ways/discuss/30358/Java-clean-DP-solution-with-explanation
 */
public class DecodeNumber {
    public static void main(String[] args){
        DecodeNumber test = new DecodeNumber();
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
            //First check if substring (i-1,i) is 0 or not. If it is 0, skip it, continue right to check substring (i-2,i), cuz 0 can only be decode by being together with the char before 0.
            //Second, check if substring (i-2,i) falls in 10~26. If it does, means there are dp[i-2] more new decode ways.
            int first = Integer.valueOf(s.substring(i-1, i));
            int second = Integer.valueOf(s.substring(i-2, i));
            if(first != 0) { // check if the number is valid
               dp[i] = dp[i-1];  
            }
            if(second >= 10 && second <= 26) {// check if the number is valid
                dp[i] += dp[i-2];
            }
        }
        return dp[n];
    }
}