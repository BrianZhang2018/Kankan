package category.DivideAndConquer;

import java.util.List;
import java.util.LinkedList;

/**
 * Recursive track:
 * 2-1-2-3
 * 2-(1-2-3)
 *    1-(2-3)
 *       2-3
 *    1-(-1)  = 2
 * 
 *    (1-2)-3
 *     1-2
 *       -1-3  =-4
 * 
 * 2- (list(2, -4))  
 * 
 * https://leetcode.com/problems/different-ways-to-add-parentheses/
 */ 

class DifferentWayToAddParentheses {
    public static void main(String[] args) {
        System.out.println(diffWaysToCompute("2-1-2-3"));
    }

    public static List<Integer> diffWaysToCompute(String input) {
        List<Integer> ret = new LinkedList<Integer>();
        for (int i=0; i<input.length(); i++) {
            if (input.charAt(i) == '-' || input.charAt(i) == '*' || input.charAt(i) == '+' ) {

                String part1 = input.substring(0, i);
                String part2 = input.substring(i + 1);
                List<Integer> part1Ret = diffWaysToCompute(part1);
                List<Integer> part2Ret = diffWaysToCompute(part2);
                // System.out.println("1:  " + part1Ret);
                // System.out.println("2:  " + part2Ret);
                for (Integer p1 :   part1Ret) {
                    for (Integer p2 :   part2Ret) {
                        int c = 0;
                        switch (input.charAt(i)) {
                            case '+': c = p1+p2;
                                break;
                            case '-': c = p1-p2;
                                break;
                            case '*': c = p1*p2;
                                break;
                        }
                        ret.add(c);
                        System.out.println("result list: " + ret);
                    }
                }
            }
        }
        if (ret.size() == 0) {
            //System.out.println("input  " + input);
            ret.add(Integer.valueOf(input));
        }
        return ret;
    }
}