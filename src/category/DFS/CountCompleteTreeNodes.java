package category.DFS;

import category.model.TreeNode;

/**
 * https://leetcode.com/problems/count-complete-tree-nodes/
 *
 * Created by brianzhang on 2/8/20.
 */
public class CountCompleteTreeNodes {
    public static void main(String[] args) {

        int start = 1;
        int backoffrate = 2;
        int waitSecond = 3;

        for(int i=0;i<9; i++){
            waitSecond = waitSecond * backoffrate;
            System.out.println(waitSecond);
        }
    }

    public int countNodes(TreeNode root) {
        return root != null ? 1 + countNodes(root.left) + countNodes(root.right): 0;
    }
}
