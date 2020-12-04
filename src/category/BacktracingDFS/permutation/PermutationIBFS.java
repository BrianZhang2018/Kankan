package category.BacktracingDFS.permutation;

import java.util.*;

/**
 * https://leetcode.com/problems/permutations/
 * Created by brianzhang on 12/3/20.
 */
public class PermutationIBFS {
    public static void main(String[] args) {

    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        if(nums == null || nums.length == 0) return res;

        Queue<List<Integer>> queue = new ArrayDeque();
        for(int i : nums){
            queue.add(Arrays.asList(i));
        }

        while(!queue.isEmpty()){
            List<Integer> next = queue.poll();
            if(next.size() == nums.length) {
                res.add(next);
                continue;
            }
            for(int i : nums){
                if(next.contains(i)) continue;
                List<Integer> tmp = new ArrayList(next);
                tmp.add(i);
                queue.add(tmp);
            }
        }

        return res;
    }
}
