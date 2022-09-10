package category.DFSBacktracing;

import java.util.*;

/**
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 *
 * Classic DFS
 * Created by brianzhang on 10/9/18.
 */
public class LetterCombinationsOfAPhoneNumber {

    public static void main(String[] args) {
        System.out.println(new LetterCombinationsOfAPhoneNumber().letterCombinations("23"));
    }

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList();
        if (digits == null || digits.length() == 0) return result;

        String[] digitToLetters = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        char[] digitArr = digits.toCharArray();
        dfs(digitToLetters, digitArr, new StringBuilder(), result, 0);

        return result;
    }

    // backtracking
    private void dfs(String[] digitToLetters, char[] digitArr, StringBuilder curr, List<String> result, int index) {
        if (curr.length() == digitArr.length) {
            result.add(curr.toString());
            System.out.println(curr);
            return;
        }

        // index represent the digit number in the phone which associated with a letter string, e.g. 2->"abc"
        for (Character c : digitToLetters[digitArr[index] - '0'].toCharArray()) {
            curr.append(c);
            dfs(digitToLetters, digitArr, curr, result, index + 1);    // index +1: point to the next phoneLetters
            curr.deleteCharAt(curr.length() - 1);
        }
    }
}
