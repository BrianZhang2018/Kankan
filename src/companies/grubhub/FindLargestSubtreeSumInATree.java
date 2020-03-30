package companies.grubhub;

/**
 * Easy question
 */
public class FindLargestSubtreeSumInATree {
    // Structure of a binaryTree node.
    static class Node {
        int key;
        Node left, right;
    }

    static class Result {
        int v;

        Result(int a) {
            v = a;
        }
    }

    // Function to create new binaryTree node.
    static Node newNode(int key) {
        Node temp = new Node();
        temp.key = key;
        temp.left = temp.right = null;
        return temp;
    }

    // Helper function to find largest subtree sum recursively.
    static int findLargestSubtreeSumUtil(Node root, Result ans) {
        // If current node is null then return 0 to parent node.
        if (root == null)
            return 0;

        // Subtree sum rooted at current node.
        int currSum = root.key + findLargestSubtreeSumUtil(root.left, ans) + findLargestSubtreeSumUtil(root.right, ans);

        // Update answer if current subtree sum is greater than answer so far.
        ans.v = Math.max(ans.v, currSum);

        // Return current subtree sum to its parent node.
        return currSum;
    }

    // Function to find largest subtree sum.
    static int findLargestSubtreeSum(Node root) {
        // If binaryTree does not exist, then answer is 0.
        if (root == null)
            return 0;

        // Variable to store maximum subtree sum.
        Result ans = new Result(Integer.MIN_VALUE);

        // Call to recursive function to find maximum subtree sum.
        findLargestSubtreeSumUtil(root, ans);

        return ans.v;
    }

    public static void main(String args[]) {
        Node root = newNode(1);
        root.left = newNode(-2);
        root.right = newNode(3);
        root.left.left = newNode(4);
        root.left.right = newNode(5);
        root.right.left = newNode(-6);
        root.right.right = newNode(2);

        System.out.println(findLargestSubtreeSum(root));
    }

}