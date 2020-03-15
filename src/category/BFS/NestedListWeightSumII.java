package category.BFS;

import category.model.NestedInteger;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.lintcode.com/problem/nested-list-weight-sum-ii/description
 * https://yeqiuquan.blogspot.com/2017/07/364-nested-list-weight-sum-ii.html
 * Linkedin
 *
 * bottom-up
 * Traverse the nested list level by level.
 * keep a cumulative sum by adding all integers from level 1 to the current level.
 * After traversing each level, we add this cumulative sum to the final result.
 * thus, after traversing all level, the elements in each level are added the corresponding times.
 *
 * Created by brianzhang on 3/7/20.
**/

public class NestedListWeightSumII {

    public int depthSumInverse(List<NestedInteger> nestedList) {
        // Write your code here.
        if(nestedList == null || nestedList.size() == 0) {
            return 0;
        }

        int sum = 0;
        int cumulative = 0;         //bottom-up
        while (!nestedList.isEmpty()) {
            List<NestedInteger> next = new ArrayList<>();
            for (NestedInteger n : nestedList) {
                if (n.isInteger()) {
                    cumulative += n.getInteger();
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