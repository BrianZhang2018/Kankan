package category.Tree.serializeDeserialize;

import java.util.*;

/**
 * https://leetcode.com/problems/serialize-and-deserialize-n-ary-tree/
 * PreOrder + Queue
 * Idea: preorder recursive traversal; add number of children after root val, in order to know when to terminate.
 * Example: The example in description is serialized as: "1,3,3,2,5,0,6,0,2,0,4,0"
 *
 * Created by brianzhang on 5/23/21.
 */
public class SerializeDeserializeNaryTreePreOrder {
    // Encodes a tree to a single string.
    public String serialize(Node root) {
        List<String> list=new LinkedList<>();
        serializeHelper(root, list);
        return String.join(",",list);
    }

    private void serializeHelper(Node root, List<String> list){
        if(root==null) return;
        list.add(String.valueOf(root.val)); // value
        list.add(String.valueOf(root.children.size())); // the num of children
        for(Node child : root.children){
            serializeHelper(child, list);
        }
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if(data.isEmpty()) return null;
        String[] ss=data.split(",");
        Queue<String> q=new LinkedList<>(Arrays.asList(ss));
        return deserializeHelper(q);
    }

    private Node deserializeHelper(Queue<String> q){
        Node root = new Node();
        root.val = Integer.parseInt(q.poll());
        int size = Integer.parseInt(q.poll());
        root.children = new ArrayList<>(size);
        for(int i=0; i<size; i++){
            root.children.add(deserializeHelper(q));
        }
        return root;
    }
}

class Node {
    public int val;
    public List<Node> children;
    public Node() {}
}
