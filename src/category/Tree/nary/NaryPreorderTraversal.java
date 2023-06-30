package category.Tree.nary;

import java.util.*;
/**
 * https://leetcode.com/problems/n-ary-tree-preorder-traversal/
 *
 * Created by brianzhang on 6/8/20.
 */
public class NaryPreorderTraversal {
    public static void main(String[] args) {}
    // dfs solution
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

    // bfs solution
    public List<Integer> preorderBFS(Node root) {
        List<Integer> res = new ArrayList();
        Stack<Node> stack = new Stack();
        if(root == null) return res;
        stack.push(root);

        while(!stack.isEmpty()) {
            Node n = stack.pop();
            res.add(n.val);

            if(n.children == null) continue;
            for(int i=n.children.size()-1; i>=0; i--) {
                stack.push(n.children.get(i));
            }
        }

        return res;
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
}
