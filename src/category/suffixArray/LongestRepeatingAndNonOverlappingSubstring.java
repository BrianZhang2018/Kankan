package category.suffixArray;

/**
 * solution source:
 * https://www.geeksforgeeks.org/longest-repeating-and-non-overlapping-substring/
 *
 * algorithm video course:
 * https://www.coursera.org/lecture/cs-algorithms-theory-machines/longest-repeated-substring-hkJBt
 *
 * DP + Suffix String
 * Created by brianzhang on 3/3/19.
 * reference:
 * https://hackerranksolutionc.blogspot.com/2018/02/longest-repeating-and-non-overlapping.html
 */
public class LongestRepeatingAndNonOverlappingSubstring {

    public static void main(String[] args) {
        System.out.println(solution("geeksforgeeks"));
    }

    public static String solution(String str){

        int n = str.length();
        int[][] LRCRe = new int[n+1][n+1];

        String res = "";
        int resLength = 0;
        int index =0;
        //i and j is the index of character in String, LRCRe[i][j] store the length of LRCRe which 'i; is ending index of of prefix string
        // and 'j' is the ending index for suffix string.
        for(int i =1; i<=n ;i++){
            for(int j = i+1; j<=n; j++){
                if(str.charAt(i-1) == str.charAt(j-1) && LRCRe[i-1][j-1] < (j-i)){
                    LRCRe[i][j] = LRCRe[i-1][j-1] +1;

                    if( LRCRe[i][j] > resLength){
                        resLength = LRCRe[i][j];
                        index = Math.max(i, index);
                    }
                }else{
                    LRCRe[i][j] = 0;
                }
            }
        }
        if (resLength > 0) {
            for (int i = index - resLength + 1; i <= index; i++) {
                res += str.charAt(i - 1);
            }
        }

        return res;
    }
}
