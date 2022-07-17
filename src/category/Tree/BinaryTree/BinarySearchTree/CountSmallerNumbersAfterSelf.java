package category.Tree.BinaryTree.BinarySearchTree;

import java.util.*;
/**
 * https://leetcode.com/problems/count-of-smaller-numbers-after-self/discuss/76587/Easiest-Java-solution
 *
 * Merge Sort solution:
 * https://leetcode.com/problems/count-of-smaller-numbers-after-self/discuss/76583/11ms-JAVA-solution-using-merge-sort-with-explanation
 *
 * Created by brianzhang on 3/1/21.
 */
public class CountSmallerNumbersAfterSelf {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new CountSmallerNumbersAfterSelf().countSmaller(new int[]{5,4,1,2}).toArray()));
    }

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if(nums == null || nums.length == 0) return res;

        for(int i = nums.length - 1; i >= 0; i--) {
            int count = insertNode(nums[i]);
            res.add(count);
        }
        Collections.reverse(res);
        return res;
    }

    private TreeNode root; // Root of the Binary Search Tree

    public int insertNode(int val) {
        if(root == null) {
            root = new TreeNode(val);
            return 0;
        }

        TreeNode curr = root; // always start from root when insert a new node

        int count = 0;
        while(true) {
            if(val <= curr.val) {
                curr.count++;
                if(curr.left == null) {
                    curr.left = new TreeNode(val);
                    break;
                } else {
                    curr = curr.left;
                }
            } else {
                count += curr.count;
                if(curr.right == null) {
                    curr.right = new TreeNode(val);
                    break;
                } else {
                    curr = curr.right;
                }
            }
        }
        return count;
    }
}

class TreeNode {
    TreeNode left;
    TreeNode right;
    int val;
    int count = 1;
    public TreeNode(int val) {
        this.val = val;
    }
}
