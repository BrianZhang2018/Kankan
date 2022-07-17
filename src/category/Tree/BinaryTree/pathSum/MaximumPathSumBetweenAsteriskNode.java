package category.Tree.BinaryTree.pathSum;

import java.util.*;
/**
 * https://www.1point3acres.com/bbs/thread-796259-1-1.html
 * Given a binary tree with alive nodes, alive node can only be leaf nodes and marked with asterisk mask. Find the maximum path length between two alive nodes.
 *
 # Example Tree 2
 #          500*
 #       /        \
 #     *2          80
 #    /   \      /    \
 # *100 *50   *200    151
                        \
                        100
 * Created by brianzhang on 9/11/21.
 */
public class MaximumPathSumBetweenAsteriskNode {

    public static void main(String[] args) {
        // test case for question-2, doesn't fit for question-1
        AliveTreeNode root = new AliveTreeNode(500, true);
        root.left = new AliveTreeNode(2, true);
        root.right = new AliveTreeNode(80, false);
        root.left.left = new AliveTreeNode(100, true);
        root.left.right = new AliveTreeNode(50, true);
        root.right.left = new AliveTreeNode(200, true);
        root.right.right = new AliveTreeNode(151, false);
        root.right.right.right = new AliveTreeNode(100, false);
        System.out.println(new MaximumPathSumBetweenAsteriskNode().solution(root));
    }

    private int max = Integer.MIN_VALUE;
    Map<Integer, Integer> visited = new HashMap<>();
    public int solution(AliveTreeNode root) {
        if(root == null) return 0;
        dfs(root);
        return max;
    }

    // 1. original question: only leaf can be alive (*) node
    public void dfs(AliveTreeNode node) {
        if(node == null) return;
        dfsGetMaxPathSum1(node);
        //dfsGetMaxPathSum(node); // need separate test case
    }

    //TODO: Fix this
    public int dfsGetMaxPathSum(AliveTreeNode node) {
        if(node == null) return 0;
        if(visited.containsKey(node.val)) { // if all the node value is unique
            return visited.get(node.val);
        }
        int left = dfsGetMaxPathSum(node.left);
        int right = dfsGetMaxPathSum(node.right);

        max = Math.max(left + right + node.val, max);
        visited.put(node.val, Math.max(left + right, Math.max(left, right)) + node.val);
        return node.val + Math.max(left, right);
    }

    // 2. followup question: alive node can be any node, the below solution still consider all the leaf node marked with asterisk, so not perfect
    // refer the
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
    AliveTreeNode left, right;
    AliveTreeNode(int x, boolean a) {
        this.val = x;
        this.isA = a;
    }
}
