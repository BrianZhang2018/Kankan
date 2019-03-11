package category.DynamicPlanning;

/**
 * Created by brianzhang on 3/10/19.
 */
public class RegularExpressionMatching {

    //https://www.youtube.com/watch?v=DqhPJ8MzDKM
    //top-down from two dimensional matrix
    public boolean isMatch(String s, String p) {

        if(p == null && p.isEmpty()){
            return s.isEmpty();
        }
        //dp state:
        boolean[][] dp = new boolean[s.length() + 1][p.length() +1];
        //dp initial:
        dp[0][0] = true;
        for(int i=1; i<=p.length(); i++){
            if(p.charAt(i-1) == '*'){
                dp[0][i] = dp[0][i-2];
            }
        }
        //logic for dp transition
        for(int si=1; si<=s.length(); si++){
            for(int pj=1; pj<=p.length(); pj++){
                if( p.charAt(pj-1) == '.' || p.charAt(pj-1) == s.charAt(si-1) ){
                    dp[si][pj] = dp[si-1][pj-1]; //a[2][2] = a[1][1]
                }else if(p.charAt(pj-1) == '*'){
                    //case 1: '*' represent multiple preceding element
                    if(p.charAt(pj-2) == s.charAt(si-1) || p.charAt(pj-2) == '.'){
                        //向前match //e.g. abcd      //e.g. abcdd -> (si-1) = abcd  向后match
                                    //     abcde*    //     abcd* -> (pj) = abcd*
                        dp[si][pj] = dp[si][pj-2] || dp[si-1][pj];
                    }else{//case 2: '*' means zero

                        dp[si][pj] = dp[si][pj-2];
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    //bottom-up
    public boolean isMatch2(String text, String pattern) {
        boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
        dp[text.length()][pattern.length()] = true;

        for (int i = text.length(); i >= 0; i--){
            for (int j = pattern.length() - 1; j >= 0; j--){
                boolean first_match = (i < text.length() &&
                        (pattern.charAt(j) == text.charAt(i) ||
                                pattern.charAt(j) == '.'));
                if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
                    dp[i][j] = dp[i][j+2] || first_match && dp[i+1][j];
                } else {
                    dp[i][j] = first_match && dp[i+1][j+1];
                }
            }
        }
        return dp[0][0];
    }

    //recursion
    public boolean isMatch3(String text, String pattern) {
        if (pattern.isEmpty())
            return text.isEmpty();
        boolean first_match = (!text.isEmpty() &&
                (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

        //case 1: "*" is the second character of pattern which match the zero or more preceding element
        if (pattern.length() >= 2 && pattern.charAt(1) == '*'){
            //match the remaining target string
            if(isMatch(text, pattern.substring(2))){
                return true;
            }else{
                //remove the head character until got a remaining string which match the pattern that removed the "star to head" characters ->(pattern.substring(2))
                return first_match && isMatch(text.substring(1), pattern);
            }
        } else {
            return first_match && isMatch(text.substring(1), pattern.substring(1));
        }
    }
}
