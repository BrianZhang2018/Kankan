package category.BFS;

import category.model.NestedInteger;
import java.util.*;

/**
 * https://www.lintcode.com/problem/nested-list-weight-sum-ii/description
 * https://yeqiuquan.blogspot.com/2017/07/364-nested-list-weight-sum-ii.html
 *
 * Linkedin, facebook
 *
 * 这个weight的计算和NestedListWeightSumI是相反的
 *
 * Traverse the nested list level by level.
 * keep a cumulative sum by adding all integers from level 1 to the current level.
 * After traversing each level, we add this cumulative sum to the final result.
 * thus, after traversing all level, the elements in each level are added the corresponding times.
 *
 * Complexity Analysis
 * Let N be the total number of nested elements in the input list.
 * For example, the list [[[[[1]]]], 2] contains 44 nested lists and 22 nested integers (11 and 22), so NN is 66, for list [[[[1, [2]]]], [3, [4]]] there are 66 nested list and 44 integers, hence N is 10.
 * Time complexity: O(N)O(N)
 * Each nested element is put in the queue and removed from the queue exactly once.
 *
 * Space complexity: O(N)O(N)
 * The worst-case for space complexity in BFS occurs where the majority of elements are at the same depth, as all of the elements at that depth will be in the queue at the same time. Therefore worst-case space complexity is O(N)O(N).
 * Created by brianzhang on 3/7/20.
**/

public class NestedListWeightSumII {

    public static void main(String[] args) {}

    public int depthSumInverse(List<NestedInteger> nestedList) {
        if(nestedList == null || nestedList.size() == 0) return 0;

        int sum = 0, cumulative = 0;
        while (!nestedList.isEmpty()) {
            List<NestedInteger> next = new ArrayList<>();
            for (NestedInteger n : nestedList) {
                if (n.isInteger()) {
                    cumulative += n.getInteger(); // key point: the value will be counted depth times from bottom to up
                }
                else {
                    next.addAll(n.getList());
                }
            }
            sum += cumulative;
            nestedList = next;
        }
        return sum;
    }
}