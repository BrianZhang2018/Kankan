package category.array.tricky;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/range-sum-query-immutable/
 * <p>
 * Created by brianzhang on 12/29/18.
 */
public class RangeSumQueryPrefixSum {

    public static void main(String[] args) {
        PriorityQueue<String> heap = new PriorityQueue<>();
    }
    private int[] sums;

    public RangeSumQueryPrefixSum(int[] nums) {

        if (nums == null)
            return;
        for (int i = 1; i < nums.length; i++)
            nums[i] += nums[i - 1];
        this.sums = nums;
    }

    public int sumRange(int i, int j) {
        if (i == 0)
            return sums[j];
        return sums[j] - sums[i - 1];
    }
}
