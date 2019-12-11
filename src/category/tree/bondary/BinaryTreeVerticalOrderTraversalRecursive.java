package category.tree.bondary;

import java.util.*;
import java.util.TreeMap;
import java.util.ArrayList;
import companies.amazon.model.TreeNode;

/**
 * My solutiion - good job, man.
 * 
 * binaryTree node's degree for vertical order
 * binaryTree node's level for level order traverse
 * dfs recursive + customized LevelNode object + List comparator
 */
public class BinaryTreeVerticalOrderTraversalRecursive{
    /**
     * @param root: the root of binaryTree
     * @return: the vertical order traversal
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        // write your code here
        TreeMap<Integer, LinkedList<LevelNode>> map = new TreeMap<>();
        printVertically(root, map, 0, 0);
        
        List<List<Integer>> res = new ArrayList<>();
        
        for(LinkedList<LevelNode> list : map.values()){
            
            Collections.sort(list, new Comparator<LevelNode>(){
                public int compare(LevelNode ln1, LevelNode ln2){
                    return ln1.level - ln2.level;
                }
            });
            
            List<Integer> temp = new ArrayList<>();
            for(LevelNode ln : list){
                temp.add(ln.value);
            }
            res.add(temp);
        }
        
        return res;
    }

    public void printVertically(TreeNode root, TreeMap<Integer, LinkedList<LevelNode>> result, int level, int degree) {
        if (root == null) {
            return;
        }

        LinkedList<LevelNode> list = result.get(degree);
        
        if (list == null) {
            list = new LinkedList<LevelNode>();
        }
        
        list.add(new LevelNode(level, root.val));
        result.put(degree, list);

        printVertically(root.left, result, level+1, degree - 1);
        printVertically(root.right, result, level+1, degree + 1);
    }
}
    
//LevelNode is used to record the node's level so that we can sort the node as the level to keep a right order for same degree nodes.
class LevelNode{
    public int level;
    public int value;
    
    public LevelNode(int level, int value){
        this.level = level;
        this.value = value;
    }
}