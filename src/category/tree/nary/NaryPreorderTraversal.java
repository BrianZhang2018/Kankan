package category.tree.nary;

import java.util.*;

/**
 * https://leetcode.com/problems/n-ary-tree-preorder-traversal/
 *
 * Created by brianzhang on 6/8/20.
 */
public class NaryPreorderTraversal {

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

class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
