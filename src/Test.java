class Solution {
    int idx = 0;
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, inorder, 0, inorder.length-1);
    }
    
    private TreeNode buildTree(int[] preorder, int[] inorder, int i, int j) {
        if(i > j)
            return null;
        
        int mid = j;
        while(mid>=i && preorder[idx]!=inorder[mid]) {
            mid--;
        }
        
        TreeNode root = new TreeNode(preorder[idx++]);
        root.left = buildTree(preorder, inorder, i, mid-1);
        root.right = buildTree(preorder, inorder, mid+1, j);
        
        return root;
    }


    public TreeNode buildNodes(int[] preorder, int[] inorder, int preStart, int inOrderEnd){
        
        if(preStart > inOrderEnd)
            return null;
        
        int mid = inOrderEnd;
        while(mid >=preStart && preorder[idx] != inorder[mid]){
            mid--;
        }
        
        TreeNode root = new TreeNode(preorder[idx++]);
        rbuildNodes(preorder, inorder, preStart+1, mid-1);
        buildNodes(preorder, inorder, mid+1, inOrderEnd);
        
        return root;
    }
    
}