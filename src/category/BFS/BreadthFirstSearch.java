package category.BFS;

import java.util.*;

/**
 * Created by brianzhang on 10/21/18.
 */
public class BreadthFirstSearch {
    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(6);
        root.right = new Node(21);
/*        root.left.left = new Node(1);
        root.left.right = new Node(8);
        root.right.left = new Node(13);
        root.right.right = new Node(25);
        root.right.left.left = new Node(12);
        root.right.left.right = new Node(18);*/

        //bfs(root);
       // widthOfBinaryTree(root);

        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.set(0,1);

        for(Integer i : list){
            System.out.println(i);
        }
    }

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

    public int dfs(Node root, int level, int order, List<Integer> start, List<Integer> end){
        if(root == null)return 0;
        if(start.size() == level){
            start.add(order); end.add(order);
        } else end.set(level, order);

        int cur = end.get(level) - start.get(level) + 1;
        int left = dfs(root.left, level + 1, 2*order, start, end);
        int right = dfs(root.right, level + 1, 2*order + 1, start, end);
        return Math.max(cur, Math.max(left, right));
    }

    public static int widthOfBinaryTree(Node root) {

        LinkedList<Node> q = new LinkedList<>();
        q.add(root);
        int mw = 0;
        int level = 1;

        while(!q.isEmpty()){

            int size = q.size();
            int leftmost = 0, rightmost = 0;

            for(int i=0; i<size; i++){
                Node node = q.pop();
                if(node.left != null && leftmost == 0){
                    leftmost = level * 2 + i;
                    q.offer(node);
                }else if(node.left != null){
                    q.offer(node);
                }

                if(node.right != null){
                    rightmost = level * 2 + 1 + i;
                    q.offer(node);
                }
            }

            if(rightmost != 0 && leftmost != 0){
                mw = leftmost -rightmost +1;
            }else if(rightmost != 0 || leftmost !=0){
                mw= Math.max(1, mw);
            }
            level++;
            System.out.println(q.size());
        }
        return mw;
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