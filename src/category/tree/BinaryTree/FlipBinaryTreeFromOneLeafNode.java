package category.tree.BinaryTree;

/**
 * https://www.careercup.com/question?id=6252860207529984
 * https://leetcode.com/discuss/interview-question/371640/google-onsite-flip-binary-tree
 *
 * Created by brianzhang on 8/8/21.
 */
class FlipBinaryTreeFromOneLeafNode {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        TreeNode leftLeaf = new TreeNode(4);
        root.left.left = leftLeaf;

        dropTree(root, leftLeaf);
        preOrderPrint(leftLeaf);
    }

    public static boolean dropTree(TreeNode root, TreeNode leafNode){
        if(root.val == leafNode.val) return true;

        boolean isLeafNode = dropTree(root.left, leafNode);
        if(isLeafNode){
            dropLeftTree(root.left, root);
            return isLeafNode;
        }

        isLeafNode = dropTree(root.right, leafNode);
        if(isLeafNode){
            dropRightTree(root.right, root);
            return isLeafNode;
        }
        return false;
    }

    // only go either dropLeftTree or dropLeftTree base on the leaf on left or right

    private static void dropLeftTree(TreeNode leftNode, TreeNode root) {
        leftNode.right = root;
        root.left = root.right;
        root.right = null;
    }

    private static void dropRightTree(TreeNode rightNode, TreeNode root) {
        rightNode.left = root;
        root.right = root.left;
        root.left = null;
    }

    // util function
    public static void preOrderPrint(TreeNode root) {
        if(root == null) return;
        System.out.println(root.val);
        preOrderPrint(root.left);
        preOrderPrint(root.right);
    }
}