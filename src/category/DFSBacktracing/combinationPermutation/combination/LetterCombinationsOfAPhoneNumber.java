package category.DFSBacktracing.combinationPermutation.combination;

import java.util.*;

/**
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 *
 * Time complexity: O(4^N)
 * There are ultimately 4^N combinations (worst case) of strings generated,
 * and for each of those combinations you have to actually construct the string.
 * Constructing a string of N length is an O(N) operation. So thus 4^N * N.
 *
 * Space complexity: O(N), where N is the length of digits.
 *
 * Not counting space used for the output, the extra space we use relative to input
 * size is the space occupied by the recursion call stack. It will only go as deep as
 * the number of digits in the input since whenever we reach that depth, we backtrack.
 *
 * Classic DFS
 * Created by brianzhang on 10/9/18.
 */
public class LetterCombinationsOfAPhoneNumber {
    public static void main(String[] args) {
        System.out.println(new LetterCombinationsOfAPhoneNumber().letterCombinations("234"));
    }

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList();
        if (digits == null || digits.length() == 0) return result;

        String[] digitToLetters = {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs",
                "tuv", "wxyz"};
        char[] inputDigits = digits.toCharArray();
        dfs(digitToLetters, inputDigits, new StringBuilder(), result, 0);
        return result;
    }

    // backtracking
    private void dfs(String[] digitToLetters, char[] inputDigits, StringBuilder curr,
                     List<String> result, int index) {
        if (curr.length() == inputDigits.length) {
            result.add(curr.toString());
            System.out.println(curr);
            return;
        }
        // inputDigits[index], e.g. 2->"abc"
        for (Character c : digitToLetters[inputDigits[index] - '0'].toCharArray()) {
            curr.append(c);
            dfs(digitToLetters, inputDigits, curr, result, index + 1); // index + 1: point to the next phoneLetters
            curr.deleteCharAt(curr.length() - 1);
        }
    }
}
