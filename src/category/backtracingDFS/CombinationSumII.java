package category.backtracingDFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by brianzhang on 3/17/19.
 */
public class CombinationSumII {

    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList();
        List<Integer> temp = new ArrayList();
        Arrays.sort(nums);
        dfs(nums, target, 0, temp, res);
        return res;
    }

    //recursive dfs with backtracking
    private void dfs(int[] nums, int target, int start, List<Integer> temp, List<List<Integer>> res) {
        if (target < 0)
            return;
        else if (target == 0) {
            res.add(new ArrayList<Integer>(temp));
        } else {
            for (int i = start; i < nums.length; i++) {
                if(i>start && nums[i] == nums[i-1])
                    continue;
                temp.add(nums[i]);
                dfs(nums, target - nums[i], start+1, temp, res);
                temp.remove(temp.size() - 1);
            }
        }
    }

    private void printout(List<Integer> list){
        System.out.println(Arrays.toString(list.toArray()));
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 2, 3, 6, 7};
        int target = 7;
        System.out.println(new CombinationSumII().combinationSum(arr, target));
    }
}
