package category.Tree.BinaryTree.pathSum;

import category.model.TreeNode;
import javafx.util.Pair;

/**
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/discuss/39875/Elegant-Java-solution
 * 
 * you can imagine the dfsHelper() function goes from the bottom of the binaryTree to the top, it's in "post-order" manner.
 * At every node, we need to make a decision, if the sum comes from the left path larger than the right path,
 * we pick the left path and plus the current node's value, this recursion goes all the way up to the root node.
 */
public class BinaryTreeMaximumPathSum {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(maxPathSum(root));
        System.out.println("Max sum path is: " + maxSumPath.toString());

        long x = 3;
        System.out.println(3.0/2);
    }

    static int maxSum = Integer.MIN_VALUE;
    public static int maxPathSum(TreeNode root) {
        if(root == null) return 0;
        //dfs(root);
        dfsRecordPath(root); // follow-up question
        return maxSum;
    }

    // it's actually a post-order traversal
    public static int dfs(TreeNode root) {
        if(root == null) return 0;
        int left = Math.max(dfs(root.left), 0);   // avoid the negative value, it's Kadane algorithm idea
        int right = Math.max(dfs(root.right), 0);
        maxSum = Math.max(maxSum, root.val + left + right);
        return root.val + Math.max(left, right);
    }

    // follow-up-1: get maximum path sum for n-ary tree

    // follow-up-2: printout path
    static StringBuilder maxSumPath = new StringBuilder();
    public static Pair<Integer, StringBuilder> dfsRecordPath(TreeNode root) {
        if(root == null) return new Pair(0, new StringBuilder());

        Pair<Integer, StringBuilder> left = dfsRecordPath(root.left); // "key" is the cumulative sum, "value" is path track
        Pair<Integer, StringBuilder> right = dfsRecordPath(root.right);

        int leftVal = Math.max(left.getKey(), 0);
        int rightVal = Math.max(right.getKey(), 0);

        if(root.val + leftVal + rightVal > maxSum) {
            maxSum = root.val + leftVal + rightVal;
            maxSumPath = new StringBuilder();
            if(leftVal >= 0) {
                maxSumPath.append(left.getValue());
            }
            maxSumPath.append(root.val+",");
            if(rightVal >= 0) {
                maxSumPath.append(right.getValue());
            }
        }

        return new Pair<>(root.val+Math.max(leftVal, rightVal),
                // if "left.getKey()<=0", 意味着这段path就不需要了，剪掉
                leftVal>=rightVal ? new StringBuilder().append(root.val+",").append(left.getKey()<=0 ? "" : left.getValue())
                                : new StringBuilder().append(root.val+",").append(right.getKey()<=0 ? "" : right.getValue()));
    }

}