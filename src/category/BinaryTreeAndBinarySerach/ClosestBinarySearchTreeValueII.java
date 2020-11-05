package category.BinaryTreeAndBinarySerach;

import category.model.TreeNode;
import javafx.util.Pair;
import java.util.*;

/**
 * https://www.lintcode.com/problem/closest-binary-search-tree-value-ii/description
 * Created by brianzhang on 2/11/19.
 */
public class ClosestBinarySearchTreeValueII {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        ClosestBinarySearchTreeValueII closestBinarySearchTreeValueII = new ClosestBinarySearchTreeValueII();
        System.out.println(closestBinarySearchTreeValueII.closestKValues(root, 3.714286, 3));
    }

    PriorityQueue<Pair<Integer, Double>> pq = new PriorityQueue<Pair<Integer, Double>>(3, (a,b) -> {
        if(a.getValue()-b.getValue() >0)
            return 1;
        else if(a.getValue()-b.getValue() <0 )
            return -1;

        return 0;
    });

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        if(root == null) return null;

        preOrder(root, target);
        List<Integer> list = new ArrayList();

        while(k-- > 0){
            list.add(pq.poll().getKey());
        }
        return list;
    }

    public void preOrder(TreeNode root, double target){
        if(root == null) return;

        add(root, target);
        preOrder(root.left, target);
        preOrder(root.right, target);
    }

    public void add(TreeNode root, double target) {
        double abs = Math.abs(root.val - target);
        pq.add(new Pair(root.val, abs));
    }
}
