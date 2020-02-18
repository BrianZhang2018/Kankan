package category.binaryTreeAndBinarySerach.inoutdegree;

/**
 * Created by brianzhang on 2/17/20.
 *
 * //https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/discuss/78552/JAVA-Counting-Indegree-and-Outdegree-SIMPLE-and-CLEAR!
 *
 * Since this is a preorder serialization, degrees are calculated in a top-down fashion, and, tree is a structure that each node has only one indegree and at most two outdegree.
 * Positive degree means there are more indegree than outdegree, which violates the definition.
*/

public class VerifyPreOrderSerializationOfABinaryTree {

    public boolean isValidSerialization(String preorder) {
        String[] nodes = preorder.split(",");
        int degree = -1;

        for(String node : nodes){
            degree++;

            if(degree>0){
                return false;
            }

            if(!node.equals("#")){
                degree-=2;
            }
        }

        return degree == 0;

    }
}
