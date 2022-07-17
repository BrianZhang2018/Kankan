package category.Tree.BinaryTree;

import java.util.Stack;
import category.model.TreeNode;

/**
 * Created by brianzhang on 5/25/20.
 */
public class BinaryTreeUtil {

    //preOrderPrint
    public static void preOrderPrintOut(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        System.out.println("Preorder printout: ");
        while(!stack.isEmpty()){
            TreeNode curr = stack.pop();
            System.out.println(curr.val + "  ");

            if(curr.right != null){
                stack.add(curr.right);
            }

            if(curr.left != null){
                stack.add(curr.left);
            }
        }
    }

    public static void inOrderPrint(TreeNode node) {
        if(node == null) return;

        inOrderPrint(node.left);
        System.out.println(node.val);
        inOrderPrint(node.right);
    }

    public static void printDLL(TreeNode node){
        while(node != null){
            System.out.print(node.val + " ");
            node = node.right;
        }
    }
}

