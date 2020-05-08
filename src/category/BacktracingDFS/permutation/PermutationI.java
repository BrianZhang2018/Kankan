package category.BacktracingDFS.permutation;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/permutations/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partioning)
 * brute force solution using DFS & backtrack
 * 深度搜素到底，然后在回溯，遍历每一层，重复这个动作
 *
 * Created by brianzhang on 7/26/18.
 */
public class PermutationI {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3};
        System.out.println(permute(arr));
    }

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
            //遍历每一个数字(i), 每一个数字当成一层，回溯遍历所有数字当前这一层, 找出排列, 然后程序回溯到根，开始遍历下一个数字
            for (int i = 0; i < nums.length; i++) {
                if (tempList.contains(nums[i])) { //看看SubsetII, you will understand
                    continue;
                }
                tempList.add(nums[i]);
                backtrack(list, tempList, nums);
                       // System.out.println("i = " + i);
                        System.out.println("remove before: " + tempList);
                tempList.remove(tempList.size() - 1);
                        System.out.println("               remove after: " + tempList);
            }
        }
    }

}
