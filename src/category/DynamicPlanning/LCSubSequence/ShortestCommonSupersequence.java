package category.DynamicPlanning.LCSubSequence;

/**
 * https://leetcode.com/problems/shortest-common-supersequence/submissions/
 * https://leetcode.com/problems/shortest-common-supersequence/discuss/319439/Java-DP-bottom-up-(2D-matrix)
 * 
 * 解题思路: the path to get the length of LCSubSequence is the shortest common superSequence.
 * so, we firstly get the LCSubSequence, then bottom-up to get the reverse string of shortest common superSequence, then reverse it which is the result
 */
public class ShortestCommonSupersequence {
    public static void main(String[] args) {
        System.out.println(solution("XMJYAUZ", "MZJAWXU"));
    }
    
    public static String solution(String str1, String str2) {
        int n1 = str1.length();
        int n2 = str2.length();
        int[][] mem = new int[n1+1][n2+1];
        int i, j;

        for(i=1; i<=n1; i++) {
            for(j=1; j<=n2; j++) {
                char ch1 = str1.charAt(i-1);
                char ch2 = str2.charAt(j-1);
                if(ch1 == ch2) {
                    mem[i][j] = 1 + mem[i-1][j-1];
                } else {
                    mem[i][j] = Math.max(mem[i-1][j], mem[i][j-1]);
                }
            }
        }
        i=n1;
        j=n2;
        StringBuilder str = new StringBuilder();
        while (i > 0 && j > 0) {
            //compare with top cell
            if(mem[i][j] == mem[i-1][j]) {
                str.append(str1.charAt(i-1));
                i--;
            } else if(mem[i][j-1] == mem[i][j]) {// compare with left neighbor cell
                str.append(str2.charAt(j-1));
                j--;
            } else {
                str.append(str1.charAt(i-1));
                i--;
                j--;
            }
        }
        while(i-- > 0) {
            str.append(str1.charAt(i));
        }
        while(j-- > 0) {
            str.append(str2.charAt(j));
        }
        return str.reverse().toString();
    }
}