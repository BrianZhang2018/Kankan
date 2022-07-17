package category.Tree.nary;

import java.util.*;

/**
 * https://leetcode.com/problems/n-ary-tree-preorder-traversal/
 *
 * Created by brianzhang on 6/8/20.
 */
public class NaryPreorderTraversal {

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        System.out.println(queue.isEmpty());
    }

    List<Integer> list = new ArrayList<>();
    public List<Integer> preOrder(Node root) {
        helper(root);
        return list;
    }

    public void helper(Node node){
        if(node == null) return;
        list.add(node.val);

        for(Node sub : node.children){
            helper(sub);
        }
    }
}

// n-ary tree
class Node {
    public int val;
    public List<Node> children;

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, List<Node> children) {
        this.val = val;
        this.children = children;
    }
};
