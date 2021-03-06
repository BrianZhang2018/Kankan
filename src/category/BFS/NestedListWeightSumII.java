package category.BFS;

import category.model.NestedInteger;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.lintcode.com/problem/nested-list-weight-sum-ii/description
 * https://yeqiuquan.blogspot.com/2017/07/364-nested-list-weight-sum-ii.html
 *
 * Linkedin, facebook
 *
 * Input: nestedList = [[1,1],2,[1,1]]
 * Output: 8
 * Explanation: four 1's at depth 1, one 2 at depth 2
 *
 * 这个depth的计算和NestedListWeightSumI是相反的which consider 1's depth is 2, 2's depth is 1
 *
 * Traverse the nested list level by level.
 * keep a cumulative sum by adding all integers from level 1 to the current level.
 * After traversing each level, we add this cumulative sum to the final result.
 * thus, after traversing all level, the elements in each level are added the corresponding times.
 *
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