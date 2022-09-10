package category.DFS;

import java.util.*;
/**
 * https://leetcode.com/problems/different-ways-to-add-parentheses/
 */
public class DifferentWaysTOAddParentheses {

    HashMap<String, List<Integer>> memo = new HashMap<>();

    public List<Integer> diffWaysToCompute(String input) {
        if (memo.containsKey(input)) {
            return memo.get(input);
        }

        List<Integer> res = new LinkedList<>();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '-' ||
                    input.charAt(i) == '*' ||
                    input.charAt(i) == '+') {
                String part1 = input.substring(0, i), part2 = input.substring(i + 1);
                List<Integer> part1Res = diffWaysToCompute(part1);
                List<Integer> part2Res = diffWaysToCompute(part2);
                for (Integer p1 : part1Res) {
                    for (Integer p2 : part2Res) {
                        int sum = 0;
                        switch (input.charAt(i)) {
                            case '+':
                                sum = p1 + p2;
                                break;
                            case '-':
                                sum = p1 - p2;
                                break;
                            case '*':
                                sum = p1 * p2;
                                break;
                        }
                        res.add(sum);
                    }
                }
            }
        }

        if (res.size() == 0) {
            res.add(Integer.valueOf(input));
        }

        memo.put(input, res);
        return res;
    }
}
