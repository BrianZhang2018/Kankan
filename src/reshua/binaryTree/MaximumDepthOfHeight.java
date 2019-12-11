package reshua.binaryTree;

/**
 * Created by brianzhang on 12/8/19.
 */
public class MaximumDepthOfHeight {

    Node root;
    /* Driver program to test above functions */
    public static void main(String[] args)
    {
        MaximumDepthOfHeight tree = new MaximumDepthOfHeight();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);

        System.out.println("Height of binaryTree is : " + tree.getMaximumHeight(tree.root));
    }

    public int getMaximumHeight(Node root){

        if(root == null)
            return 0;
        else{
            int lh = getMaximumHeight(root.left);
            int rh = getMaximumHeight(root.right);

            if(lh>rh){
                return lh + 1;
            }else{
                return rh +1;
            }
        }
    }
}

class Node
{
    int data;
    Node left, right;

    Node(int item)
    {
        data = item;
        left = right = null;
    }
}