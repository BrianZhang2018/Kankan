package category.binarySearchAndTree;

import amazon.model.TreeNode;

import java.util.*;

/**
 * Created by brianzhang on 2/11/19.
 */
public class ClosestBinarySearchTreeValueIIWithoutGlobalVar {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        ClosestBinarySearchTreeValueIIWithoutGlobalVar closestBinarySearchTreeValueII = new ClosestBinarySearchTreeValueIIWithoutGlobalVar();
        System.out.println(closestBinarySearchTreeValueII.closestKValues(root, 3.714286, 3));
    }

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        PriorityQueue<Double> maxHeap = new PriorityQueue<Double>(k, Collections.reverseOrder());
        Set<Integer> set = new HashSet<Integer>();
        rec(root, target, k, maxHeap, set);

        return new ArrayList<Integer>(set);
    }

    private void rec(TreeNode root, double target, int k, PriorityQueue<Double> maxHeap, Set<Integer> set) {
        if (root == null)
            return;

        double diff = Math.abs(root.val - target);
        if (maxHeap.size() < k) {
            maxHeap.offer(diff);
            set.add(root.val);
        } else if (diff < maxHeap.peek()) {
            double x = maxHeap.poll();
            if (!set.remove((int) (target + x)))
                set.remove((int) (target - x));
            maxHeap.offer(diff);
            set.add(root.val);
        }
        rec(root.left, target, k, maxHeap, set);
        rec(root.right, target, k, maxHeap, set);
    }
}
