package category.Slidingwindow.templateSolution.template1;

/**
 * https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/
 *
 * Return the maximum number of vowel letters in any substring of s with length k.
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

        while(r < s.length()){
            while(r-l<k){
                if(vowels.indexOf(s.charAt(r)) != -1)
                    count++;
                r++;
            }
            max = Math.max(max, count);
            if(vowels.indexOf(s.charAt(l)) >= 0 && count > 0)
                count--;

            l++;
        }
        return max;
    }

}
