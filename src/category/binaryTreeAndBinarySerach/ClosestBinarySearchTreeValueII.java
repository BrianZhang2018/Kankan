package category.binaryTreeAndBinarySerach;

import category.model.TreeNode;
import java.util.*;

/**
 * https://www.lintcode.com/problem/closest-binary-search-tree-value-ii/description
 * Created by brianzhang on 2/11/19.
 */
public class ClosestBinarySearchTreeValueII {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        ClosestBinarySearchTreeValueII closestBinarySearchTreeValueII = new ClosestBinarySearchTreeValueII();
        System.out.println(closestBinarySearchTreeValueII.closestKValues(root, 3.714286, 3));
    }

    private PriorityQueue<Double> pq = new PriorityQueue<>(Collections.reverseOrder());
    private HashMap<Double, Integer> map = new HashMap<>();
    private double target = 0.0;
    private int k = 0;
    private double threshold = Integer.MIN_VALUE;

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        // write your code here
        if (root == null)
            return null;

        this.target = target;
        this.k = k;

        preOrder(root);
        return (new ArrayList(map.values()));
    }

    public void preOrder(TreeNode root) {
        if (root == null)
            return;

        add(root);
        preOrder(root.left);
        preOrder(root.right);
    }

    public void add(TreeNode root) {
        double abs = Math.abs(root.val - this.target);
        if (pq.size() == this.k && abs < this.threshold) {
            Double removed = pq.poll();
            map.remove(removed);
            pq.add(abs);
            this.threshold = pq.peek();
            map.put(abs, root.val);
        } else if (pq.size() < this.k) {
            pq.add(abs);
            map.put(abs, root.val);
            if (abs > this.threshold)
                this.threshold = abs;
        }
    }

}
