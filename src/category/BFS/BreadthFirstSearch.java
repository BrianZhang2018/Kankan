package category.BFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by brianzhang on 10/21/18.
 */
public class BreadthFirstSearch {

    public static void bfs(Node root) {
        Queue<Node> queue = new LinkedList();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.println(node.data);
            if (node.left != null)
                queue.add(node.left);
            if (node.right != null)
                queue.add(node.right);
        }
    }
    
    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(6);
        root.right = new Node(21);
        root.left.left = new Node(1);
        root.left.right = new Node(8);
        root.right.left = new Node(13);
        root.right.right = new Node(25);
        root.right.left.left = new Node(12);
        root.right.left.right = new Node(18);

        bfs(root);
    }
}

class Node {
    int data;
    Node left;
    Node right;
    public Node(int value) {
        data = value;
        left = right = null;
    }
}