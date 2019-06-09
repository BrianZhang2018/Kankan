package category.tree;

import companies.amazon.model.TreeNode;

import java.util.Stack;

/**
 * Created by brianzhang on 7/21/18.
 */

/*
如hint所给出，这道题就是使用先序遍历，遍历到的值作为新的右孩子存起来，左孩子变为空。
注意的是，因为右孩子会更新，所以为了递归右子树，要在更新之前提前保存右孩子。
整个程序需要维护一个全局变量，保存当前所遍历的节点。

 */
public class FlattenLinkedList {

    TreeNode lastVisited = null;

    public void flatten(TreeNode root) {

        if (root == null)
            return;

        TreeNode right = root.right;
        if (lastVisited != null) {

            //important point - root, not root.left
            //遍历到的值作为新的右孩子存起来，左孩子变为空
            lastVisited.right = root;
            lastVisited.left = null;
        }

        lastVisited = root;
        flatten(root.left);
        flatten(right);
    }

    //Asolution 2
    /*
        此题还有不用递归方法解决的方法，那就是使用栈。
        对整棵树一直向右子树方向遍历。当遍历的节点有右孩子时，就将其入栈。有左孩子时，将其更新为当前节点的右孩子，左孩子置空。当左孩子为空时而栈不空时，
        就弹出栈，作为右孩子。代码如下：
     */
    public void flatten2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;

        while (p != null || !stack.isEmpty()) {

            if (p.right != null) {
                stack.add(p.right);
            }

            if (p.left != null) {
                p.right = p.left;
                p.left = null;

            } else if (!stack.isEmpty()) {
                p.right = stack.pop();
            }
            p = p.right;
        }
    }
}
