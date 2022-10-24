package category.Tree.BinaryTree.VerticalOrder.one;

import category.model.TreeNode;

import java.util.*;

/**
 * https://www.lintcode.com/problem/binary-tree-vertical-order-traversal/
 * My solution - good job, man.
 * 
 * binaryTree node's degree for vertical order
 * binaryTree node's level for level order traverse
 * dfsHelper recursive + customized LevelNode object + List comparator
 */
public class BinaryTreeVerticalOrderTraversal1DFS {

    public List<List<Integer>> verticalOrder(TreeNode root) {
        TreeMap<Integer, LinkedList<LevelNode>> map = new TreeMap<>();
        printVertically(root, map, 0, 0);
        
        List<List<Integer>> res = new ArrayList<>();
        
        for(LinkedList<LevelNode> list : map.values()){
            Collections.sort(list, (ln1, ln2) -> ln1.level - ln2.level);
            
            List<Integer> temp = new ArrayList<>();
            for(LevelNode ln : list){
                temp.add(ln.value);
            }
            res.add(temp);
        }
        
        return res;
    }

    public void printVertically(TreeNode root, TreeMap<Integer, LinkedList<LevelNode>> map, int level, int degree) {
        if (root == null) return;

        map.putIfAbsent(degree, new LinkedList<>());
        map.get(level).add(new LevelNode(level, root.val));
        printVertically(root.left, map, level+1, degree - 1);
        printVertically(root.right, map, level+1, degree + 1);
    }
}
    
// LevelNode is used to record the node's level so that we can
// sort the node as the level to keep a right order for same degree nodes.
class LevelNode{
    public int level;
    public int value;
    
    public LevelNode(int level, int value){
        this.level = level;
        this.value = value;
    }
}