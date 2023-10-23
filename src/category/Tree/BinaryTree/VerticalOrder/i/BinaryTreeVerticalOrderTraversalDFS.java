package category.Tree.BinaryTree.VerticalOrder.i;

import category.model.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * https://www.lintcode.com/problem/binary-tree-vertical-order-traversal/
 * My solution - good job, man.
 *
 * 重点：Need the level info to sort the element in the same column
 * 
 * node's degree (offset) for vertical order
 * node's level for level order
 *
 * dfsHelper recursive + customized LevelNode object + List comparator
 */
public class BinaryTreeVerticalOrderTraversalDFS {

    public List<List<Integer>> verticalOrder(TreeNode root) {
        TreeMap<Integer, LinkedList<LevelNode>> map = new TreeMap<>();
        dfs(root, map, 0, 0);

        List<List<Integer>> res = new ArrayList<>();
        for(LinkedList<LevelNode> list : map.values()){
            Collections.sort(list, Comparator.comparingInt(n -> n.level));
            res.add(list.stream().map(n -> n.val).collect(Collectors.toList()));
        }
        return res;
    }

    public void dfs(TreeNode root, TreeMap<Integer, LinkedList<LevelNode>> map, int level, int offset) {
        if (root == null) return;

        map.putIfAbsent(offset, new LinkedList<>());
        map.get(level).add(new LevelNode(level, root.val));
        dfs(root.left, map, level+1, offset - 1);
        dfs(root.right, map, level+1, offset + 1);
    }
}
    
// Record the node's level so that we can "sort" the node by level to keep a right top-down order for the nodes in the same column
class LevelNode{
    public int level;
    public int val;
    public LevelNode(int level, int value){
        this.level = level;
        this.val = value;
    }
}