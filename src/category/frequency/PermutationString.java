package category.frequency;
/**
 * https://leetcode.com/problems/permutation-in-string/
 * this question is the same with https://leetcode.com/problems/find-all-anagrams-in-a-string/
 *
 * idea: one string will be a permutation of another string only if both of them
 *       contain the same characters with the "same frequency".
 */
public class PermutationString {
    public static void main(String[] args) {
        System.out.println(checkInclusion("ab", "eidbaooo"));
    }

    public static boolean checkInclusion(String s1, String s2) {
        int[] target = new int[26];
        for (char c : s1.toCharArray()) {
            target[c - 'a']++;
        }
        for (int i = 0; i <= s2.length() - s1.length(); i++) {
            int[] window = new int[26];
            for (int j = i; j < i + s1.length(); j++) {
                window[s2.charAt(j) - 'a']++;
            }

            if (match(target, window)) return true;
        }
        return false;
    }

    public static boolean match(int[] target, int[] window) {
        for (int i = 0; i < target.length; i++) {
            if (target[i] != window[i]) return false;
        }
        return true;
    }
}
