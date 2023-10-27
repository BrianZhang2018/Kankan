package category.Tree.BinaryTree.boundaryView;

import category.model.TreeNode;

import java.util.*;

public class BinaryTreeLeftSideView {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(20);
        root.right = new TreeNode(30);
        root.left.left = new TreeNode(40);
        root.left.right = new TreeNode(60);
        root.right.left = new TreeNode(70);
        root.right.right = new TreeNode(80);
        BinaryTreeLeftSideView btr = new BinaryTreeLeftSideView();
        for(Integer i : btr.leftSideViewDFS(root)) System.out.println(i);
        for(Integer i : btr.leftSideViewBFS(root)) System.out.println(i);
    }

    public List<Integer> leftSideViewDFS(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;

        int level = 0;
        dfs(root, level, res);
        return res;
    }

    public void dfs(TreeNode root, int level, List<Integer> res) {
        if(root == null) return;

        if(res.size() == level) res.add(root.val);

        dfs(root.left, level + 1, res); // left subtree first, if right view, will dfs the right subtree first
        dfs(root.right, level +1 , res);
    }

    // solution-2: bfs, level order traverse - iterative
    public static List<Integer> leftSideViewBFS(TreeNode root) {
        if(root == null) return Collections.emptyList();

        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0; i<size; i++){
                TreeNode node = queue.poll();
                if(i == 0) res.add(node.val); // 重点, record the left most node's value

                if(node.left != null)
                    queue.add(node.left);

                if(node.right != null)
                    queue.add(node.right);
            }
        }
        return res;
    }
}
