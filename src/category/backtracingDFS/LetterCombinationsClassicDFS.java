package category.BacktracingDFS;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brianzhang on 10/9/18.
 *
 * Classic DFS
 */
public class LetterCombinationsClassicDFS {

    public static void main(String[] args) {
        LetterCombinationsClassicDFS test = new LetterCombinationsClassicDFS();
        System.out.println(test.letterCombinations("23"));
    }

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList();
        if (digits == null || digits.length() == 0) return result;

        String[] phoneLetters = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        char[] digitArr = digits.toCharArray();
        dfs(phoneLetters, digitArr, new StringBuilder(), result, 0);

        return result;
    }

    // dfs backtracking
    private void dfs(String[] letters, char[] digitArr, StringBuilder curr, List<String> result, int index) {
        if (curr.length() == digitArr.length) {
            result.add(curr.toString());
            return;
        }

        // index represent the digit number in the phone which associated with a letter string, e.g. 2->"abc"
        for (Character c : letters[digitArr[index] - '0'].toCharArray()) {
            curr.append(c);
            dfs(letters, digitArr, curr, result, index + 1);    //index +1: point to the next phoneLetters
            curr.deleteCharAt(curr.length() - 1);
        }
    }
}
