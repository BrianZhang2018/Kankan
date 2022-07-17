package category.Tree.BinaryTree;

import category.model.TreeNode;

import java.util.*;

/**
 * https://leetcode.com/problems/maximum-width-of-binary-tree/
 * https://leetcode.com/problems/maximum-width-of-binary-tree/discuss/106654/JavaC%2B%2B-Very-simple-dfs-solution
 *
 * Created by brianzhang on 4/8/20.
 */
public class MaximumWidthBinaryTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(9);

        MaximumWidthBinaryTree test = new MaximumWidthBinaryTree();
        System.out.println(test.widthOfBinaryTree(root));
    }

    public int widthOfBinaryTree(TreeNode root) {
        return dfs(root, 0, 1, new ArrayList<>());
    }

    public int dfs(TreeNode root, int level, int index, List<Integer> starts){
        if(root == null) return 0;
        if(starts.size() == level) // record the start index of each level (actually record the index of first left node in each level)
            starts.add(index);

        int curr = index - starts.get(level) + 1;  //index is a end index, so width = end - start +1

        int left = dfs(root.left, level+1, 2*index, starts);
        int right = dfs(root.right, level+1, 2*index+1, starts);

        return Math.max(curr, Math.max(left, right));
    }
}
