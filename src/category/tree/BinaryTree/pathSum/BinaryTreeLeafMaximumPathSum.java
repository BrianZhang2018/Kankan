package category.tree.BinaryTree.pathSum;

import java.util.*;
/**
 * https://www.1point3acres.com/bbs/thread-796259-1-1.html
 *
 * Created by brianzhang on 9/11/21.
 */
public class BinaryTreeLeafMaximumPathSum {

    public static void main(String[] args) {
        AliveTreeNode root = new AliveTreeNode(5, false);
        root.left = new AliveTreeNode(2, true);
        root.right = new AliveTreeNode(0, false);
        root.left.left = new AliveTreeNode(100, true);
        root.left.right = new AliveTreeNode(50, true);
        root.right.left = new AliveTreeNode(14, true);
        root.right.right = new AliveTreeNode(15, true);

        System.out.println(new BinaryTreeLeafMaximumPathSum().solution(root));
    }

    private int max = Integer.MIN_VALUE;
    Map<Integer, Integer> visited = new HashMap<>();

    public int solution(AliveTreeNode root) {
        if(root == null) return 0;
        dfs(root);
        return max;
    }

    public void dfs(AliveTreeNode node) {
        if(node == null) return;

        if(node.left != null || node.right == null) {
            dfsGetMaxPathSum1(node); //  dfsGetMaxPathSum1(node) -> question-1
        }
        dfs(node.left);
        dfs(node.right);
    }

    // initial question: only leaf can be alive node
    public int dfsGetMaxPathSum(AliveTreeNode node) {
        if(node == null) return 0;

        if(visited.containsKey(node.val)) {
            return visited.get(node.val);
        }

        int left = dfsGetMaxPathSum(node.left);
        int right = dfsGetMaxPathSum(node.right);

        max = Math.max(left + right + node.val, max);
        visited.put(node.val, Math.max(left + right, Math.max(left, right)) + node.val);

        return node.val + Math.max(left, right);
    }

    // followup question: alive node can be any node
    public int dfsGetMaxPathSum1(AliveTreeNode node) {
        if(node == null) return 0;

        int left = dfsGetMaxPathSum1(node.left);
        int right = dfsGetMaxPathSum1(node.right);

        if(node.isA) {
            max = Math.max(Math.max(left, right) + node.val, max);
        }else{
            max = Math.max(left + right + node.val, max);
        }

        if(node.isA) return node.val; // 两个alive nodes路径上不能有其它的alive nodes

        return node.val + Math.max(left, right);
    }
}

class AliveTreeNode {
    int val;
    boolean isA;
    AliveTreeNode left;
    AliveTreeNode right;
    AliveTreeNode(int x, boolean a) {
        this.val = x;
        this.isA = a;
    }
}
