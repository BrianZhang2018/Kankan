package category.Tree.BinaryTree;

import category.model.TreeNode;
/**
 * https://leetcode.com/problems/sum-root-to-leaf-numbers/description/
 */
public class SumRootToLeafNumbers {
    public static void main(String[] args) {}

    public static int sumNumbers(TreeNode root) {
        if(root == null) return 0;

        return dfs(root, 0, 0);
    }
    public static int dfs(TreeNode node, int num, int sum){
        if(node == null) return sum;

        num = num*10 + node.val;
        // leaf
        if(node.left == null && node.right == null) {
            sum += num;
            return sum;
        }
        // left subtree + right subtree
        return dfs(node.left, num, sum) + dfs(node.right, num, sum);
    }
}
