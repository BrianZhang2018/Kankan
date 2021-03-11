package category.BinaryTree;

import java.util.*;
/**
 * https://leetcode.com/problems/count-of-smaller-numbers-after-self/
 *
 * Good explanation, but a little redundant solution
 * https://leetcode.com/problems/count-of-smaller-numbers-after-self/discuss/76580/9ms-short-Java-BST-solution-get-answer-when-building-BST
 *
 * Created by brianzhang on 5/20/20.
 */
public class CountSmallerNumberOnRight {
    public static void main(String[] args) {
        CountSmallerNumberOnRight test = new CountSmallerNumberOnRight();
        System.out.println(Arrays.toString(test.countSmaller(new int[]{10,2,5,6,1,1}).toArray()));
    }

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if(nums == null || nums.length == 0) return res;
        TreeNode root = new TreeNode(nums[nums.length - 1]);
        res.add(0);
        for(int i = nums.length - 2; i >= 0; i--) {
            int count = insertNode(root, nums[i]);
            res.add(count);
        }
        Collections.reverse(res);
        return res;
    }

    public int insertNode(TreeNode root, int val) {
        int greaterNumCounter = 0;
        while(true) {
            if(val <= root.val) {
                root.count++; // 重点2 - count the number of nodes which less than or equal to current root
                if(root.left == null) {
                    root.left = new TreeNode(val);
                    break;
                } else {
                    root = root.left;
                }
            } else { // 重点1 - When go to the right subtree which means current node is less than inserted val, so we increase the counter
                greaterNumCounter += root.count;
                if(root.right == null) {
                    root.right = new TreeNode(val);
                    break;
                } else {
                    root = root.right;
                }
            }
        }
        return greaterNumCounter;
    }
}

class TreeNode {
    TreeNode left;
    TreeNode right;
    int val;
    int count = 1; // count the number of nodes which less than or equal to current root
    public TreeNode(int val) {
        this.val = val;
    }
}