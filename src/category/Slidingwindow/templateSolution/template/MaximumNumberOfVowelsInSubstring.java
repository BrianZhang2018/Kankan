package category.Slidingwindow.templateSolution.template;

/**
 * https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/
 *
 * I solved this problem quickly
 *
 * Created by brianzhang on 6/8/20.
 */
public class MaximumNumberOfVowelsInSubstring {

    public static void main(String[] args) {
        System.out.println(maxVowels( "abciiidef",  3));
    }

    public static int maxVowels(String s, int k) {
        String vowels = "aeiou";
        int l = 0, r = 0;
        int max = 0, count = 0;

        while(r<s.length()){
            while(r-l<k){
                if(vowels.indexOf(s.charAt(r)) >=0)
                    count++;
                r++;
            }
            max = Math.max(max, count);
            if(vowels.indexOf(s.charAt(l)) >=0 && count >0)
                count--;

            l++;
        }
        return max;
    }

/*    public int maxVowels(String s, int k) {
        int ans = 0;
        // Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        var vowels = Set.of('a', 'e', 'i', 'o', 'u'); // Java 11 Collection factory method, credit to @Sithis
        for (int i = 0, winCnt = 0; i < s.length(); ++i) {
            if (vowels.contains(s.charAt(i))) {
                ++winCnt;
            }
            if (i >= k && vowels.contains(s.charAt(i - k))) {
                --winCnt;
            }
            ans = Math.max(winCnt, ans);
        }
        return ans;
    }*/

}
