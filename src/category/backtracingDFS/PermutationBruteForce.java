package category.backtracingDFS;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brianzhang on 7/26/18.
 * <p>
 * https://leetcode.com/problems/permutations/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partioning)
 * <p>
 * DFS & backtrack
 * 深度搜素到底，然后在回溯，遍历每一层，重复这个动作
 * <p>
 */
public class PermutationBruteForce {

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        // Arrays.sort(nums); // not necessary
        backtrack(list, new ArrayList<>(), nums);
        return list;
    }

    private static void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums) {
        if (tempList.size() == nums.length) {
            list.add(new ArrayList<>(tempList));
        } else {
            //遍历每一个数字(i), 每一个数字当成一层，遍历所有数字在这一层, 找出组合，然后回溯到上一层(上一个数字)
            for (int i = 0; i < nums.length; i++) {
                //看看SubsetII, you will understand
                if (tempList.contains(nums[i])) {
                    continue; // element already exists, skip
                }
                tempList.add(nums[i]);
                backtrack(list, tempList, nums);
                        System.out.println("i = : " + i);
                        System.out.println("remove before: " + tempList);
                tempList.remove(tempList.size() - 1);
                         System.out.println("              remove after: " + tempList);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3};

        System.out.println(permute(arr));

    }
}