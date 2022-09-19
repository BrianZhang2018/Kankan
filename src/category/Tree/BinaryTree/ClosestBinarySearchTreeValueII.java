package category.Tree.BinaryTree;

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
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        System.out.println(closestKValues(root, 3.714286, 3));
    }

    // in-order traverse
    public static List<Integer> closestKValues(TreeNode root, double target, int k) {
        Queue<Integer> queue = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();

        TreeNode curr = root;
        while(!stack.isEmpty() || curr != null){
            if(curr != null){
                stack.push(curr);
                curr = curr.left;
            }else{
                curr = stack.pop();
                if(queue.size() < k){
                    queue.offer(curr.val);
                }else{
                    if(Math.abs(queue.peek() - target) > Math.abs(curr.val - target)){
                        queue.poll();
                        queue.offer(curr.val);
                    }else{
                        break;
                    }
                }

                curr = curr.right;
            }
        }

        return (List<Integer>) queue;
    }
}
