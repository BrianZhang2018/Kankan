package category.linkedList.intersection;

import java.util.*;

/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iii/
 *
 * FB, Linkedin, MS
 * Created by brianzhang on 8/18/21.
 */
public class LowestCommonAncestorBTIII {

    public Node lowestCommonAncestor(Node p, Node q) {
        Set<Node> set = new HashSet();

        while(p != null) {
            set.add(p);
            p = p.parent;
        }

        while(q!= null) {
            if(set.contains(q)) {
                return q;
            }

            q = q.parent;
        }

        return null;
    }

    public Node lowestCommonAncestor1(Node p, Node q) {
        Node a = p;
        Node b = q;

        while( a != b) {
            a = a == null ? q : a.parent;
            b = b == null ? p : b.parent;
        }

        return b;
    }
}

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
};
