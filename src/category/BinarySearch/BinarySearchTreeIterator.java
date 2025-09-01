package category.BinarySearch;

import java.util.ArrayList;
import java.util.List;
import category.model.TreeNode;

/**
 * https://leetcode.com/problems/binary-search-tree-iterator/
 * */
 class Test {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(3);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(20);
        BinarySearchTreeIterator bstIterator = new BinarySearchTreeIterator(root);
        System.out.println(bstIterator.next());
        System.out.println(bstIterator.next());
    }
}

public class BinarySearchTreeIterator {
    TreeNode currRoot = null;
    List<Integer> list = new ArrayList<>();
    int index = -1;
    public BinarySearchTreeIterator(TreeNode root) {
        currRoot = root;
        dfs(root);
    }
    
    /** @return the next smallest number */
    public int next() {
        if(currRoot == null || index > list.size() -1) return -1;
        
        return list.get(++index);
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return index+1 < list.size();
    }
    
    public void dfs(TreeNode node){
        if(node == null) return;

        dfs(node.left);
        list.add(node.val);
        dfs(node.right);
    }
}
