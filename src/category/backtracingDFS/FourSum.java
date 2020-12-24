package category.BacktracingDFS;

import java.util.*;

/**
 * https://leetcode.com/problems/4sum/
 *
 * time complexity: 2^k, worst case
 * this can be turned into the K Sum solution
 */
public class FourSum {
    public static void main(String[] args) {
        int[] data = {1, 0, 1, 0, -2, 2};
        System.out.println(new FourSum().fourSum(data, 0));
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return Collections.EMPTY_LIST;
        }

        Arrays.sort(nums);
        Set<List<Integer>> set = new HashSet<>();
        List<Integer> subList = new ArrayList<>();
        findCombinations(set, subList, nums, 0, 0, target);
        return new ArrayList(set);
    }

    private void findCombinations(Set<List<Integer>> set, List<Integer> subList, int[] nums, int sum, int start, int target) {
        if (subList.size() == 4) {
            if (sum == target) {
                set.add(new ArrayList<>(subList));
            }
            return;
        }

        if (start == nums.length) return;

        for (int i = start; i < nums.length; i++) {
            // the below conditions are used for "pruning" (剪枝) otherwise backtrack will timeout
            // nums[i] is too small (maxVal * the number of available slots in the subList (4-subList.size() -1(num[i]))
            if (sum + nums[i] + nums[nums.length-1] * (4-subList.size()-1) < target) continue;

            // nums[i] is too big as the latter number is greater than num[i] in sorted list (this is more effective compare to above one)
            if (sum + nums[i] * (4 - subList.size()) > target) return;

            subList.add(nums[i]);
            findCombinations(set, subList, nums, sum + nums[i], i+1, target);
            subList.remove(subList.size() - 1);
        }
    }
}