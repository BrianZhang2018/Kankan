package category.model;

/**
 * Created by brianzhang on 7/18/18.
 */

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    public static void preOrderTraversal(TreeNode node){
        if(node == null){
            System.out.println("null");
            return;
        }
        System.out.println(node.val);
        preOrderTraversal(node.left);
        preOrderTraversal(node.right);
    }

}