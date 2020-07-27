package category.BacktracingDFS;

import java.util.*;

/**
 * this can be turned into the K Sum solution
 */
public class FourSum {
    public static void main(String[] args) {
        int[] data = {1, 0, 1, 0, -2, 2};
        System.out.println(new FourSum().fourSum(data, 0));
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {

        if (nums == null || nums.length == 0) return Collections.EMPTY_LIST;

        Arrays.sort(nums);
        Set<List<Integer>> set = new HashSet<>();
        backtrack(set, new ArrayList<>(), nums, target, 0);
        return new ArrayList<>(set);
    }

    public void backtrack(Set<List<Integer>> set, List<Integer> tempList, int[] nums, int sum, int index) {
        if (tempList.size() == 4) { // 4 can be K to turn this to K sum
            if (sum == 0) {
                set.add(new ArrayList<>(tempList));
            }
            return;
        }

        for (int i = index; i < nums.length; i++) {
            tempList.add(nums[i]);
            sum -= nums[i];
            backtrack(set, tempList, nums, sum, i + 1);
            sum += nums[i];
            tempList.remove(tempList.size() - 1);
        }
    }

}