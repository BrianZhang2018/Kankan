package category.TwoPointer;

/**
 * https://leetcode.com/problems/valid-palindrome-ii/
 * 
 * */
public class ValidPalindromeII {
    public static void main(String[] args) {
        System.out.println(validPalindrome("abca"));
    }
    
    public static boolean validPalindrome(String s) {
        int l = 0, r= s.length()-1;
        while(l < r) {
            if(s.charAt(l) != s.charAt(r)) {
                return isPalindrome(s, l+1, r) || isPalindrome(s, l, r-1);
            }
            l++; r--;
        }

        return true;
    }

    public static boolean isPalindrome(String s, int l, int r) {
         while(l < r) {
            if(s.charAt(l++) != s.charAt(r--)) {
                return false;
            }
        }

        return true;
    }
}
