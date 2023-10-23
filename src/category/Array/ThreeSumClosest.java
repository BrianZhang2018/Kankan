package category.Array;

import java.util.Arrays;

/**
 * Similar to 3 Sum problem, use 3 pointers to point current element, next element and
 * the last element.
 * If the sum is less than target, it means we have to add a larger element so next
 * element move to the next.
 * If the sum is greater, it means we have to add a smaller element so last element
 * move to the second last element.
 * doing this until the end. Each time compare the difference between sum and target,
 * if it is less than minimum difference so far, then replace result with it, otherwise
 * keep iterating.
 * <p>
 * https://leetcode.com/problems/3sum-closest/discuss/7872/Java-solution-with-O(n2)
 */

public class ThreeSumClosest {
    public static void main(String[] args) {
        System.out.println(threeSumClosest(new int[]{1, 1, -1, -1, 3}, -1));
    }

    public static int threeSumClosest(int[] num, int target) {
        if (num == null || num.length < 3){
            return 0;
        }
        // give arbitrary result
        int result = num[0] + num[1] + num[num.length - 1];
        Arrays.sort(num);
        for (int left = 0; left < num.length - 2; left++) {
            int right = left + 1, end = num.length - 1;
            while (right < end) {
                int sum = num[left] + num[right] + num[end];
                if (sum > target) {
                    end--;
                } else {
                    right++;
                }
                if (Math.abs(sum - target) < Math.abs(result - target)) {
                    result = sum;
                }
            }
        }

        return result;
    }
}