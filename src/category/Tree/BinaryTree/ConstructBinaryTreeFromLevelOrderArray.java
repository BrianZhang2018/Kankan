package category.Tree.BinaryTree;

/**
 * input array is a "level order" fashion
 */
public class ConstructBinaryTreeFromLevelOrderArray {
    TreeNode root;
    public TreeNode insertLevelOrder(int[] arr, int i) {
        TreeNode root = null;
        if (i < arr.length && arr[i] != -1) {
            root = new TreeNode(arr[i]);
            root.left = insertLevelOrder(arr, 2 * i + 1);
            root.right = insertLevelOrder(arr, 2 * i + 2);
        }
        return root;
    }

    public int getHeight(TreeNode root) {
        if(root == null) return 0;

        int left = getHeight(root.left);
        int right = getHeight(root.right);
        return Math.max(left, right) + 1;
    }

    // print out
    public void inOrder(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + " ");
            inOrder(root.left);
            inOrder(root.right);
        }
    }

    public static void main(String args[]) {
        ConstructBinaryTreeFromLevelOrderArray test =
                new ConstructBinaryTreeFromLevelOrderArray();
        int arr[] = {1, 2, 3, 4, -1, -1, -1};
        test.root = test.insertLevelOrder(arr, 0);
        test.inOrder(test.root);

        System.out.println("\n" + test.getHeight(test.root));
    }
}
