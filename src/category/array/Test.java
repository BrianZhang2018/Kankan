package category.array;

import category.model.TreeNode;

class Test {
    public static void main(String[] args){
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.left = null;
        root.left.right = new TreeNode(2);

        Test test = new Test();
        System.out.println(test.kthSmallest2(root, 1));
    }

    int count = 0;
    int result = 0;
    
    //solution 1
    public int kthSmallest(TreeNode root, int k) {
        if(root == null)
            return 0;
        
        kthSmallest(root.left, k);
        if(++count == k){
            result = root.val;
            return result;
        }
        kthSmallest(root.right, k);
        
        return result;
    }

    //solution 2
    public int kthSmallest2(TreeNode root, int k) {
        if(root == null)
            return 0;
        inorder(root, k);
        
        return result;
    }

    /**
     * traditonal inorder
     */
    public void inorder(TreeNode node, int k){
        if(node == null || count == k){
            return;
        }
        inorder(node.left, k);
        if(++count == k){
            result = node.val;
            return;
        }
        inorder(node.right, k); 
    }

}