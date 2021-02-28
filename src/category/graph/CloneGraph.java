package category.graph;

import java.util.*;

/**
 * https://leetcode.com/problems/clone-graph/
 *
 * Created by brianzhang on 2/28/21.
 */
public class CloneGraph {


    Map<Integer, Node> map = new HashMap<>();

    public Node cloneGraph(Node node) {
        if(node == null)
            return null;

        if(map.containsKey(node.val)){
            return map.get(node.val);
        }

        Node newNode = new Node(node.val, new ArrayList<>());
        map.put(node.val, newNode);

        for(Node neighbor : node.neighbors){
            newNode.neighbors.add(cloneGraph(neighbor));
        }

        return newNode;
    }
}

class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {}

    public Node(int _val,List<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}