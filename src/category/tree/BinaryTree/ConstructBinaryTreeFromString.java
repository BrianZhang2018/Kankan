package category.tree.BinaryTree;

/**
 * https://www.lintcode.com/problem/construct-binary-tree-from-string/description
 *
 * recursive
 * Created by brianzhang on 7/1/20.
 */
public class ConstructBinaryTreeFromString {

    public static void main(String[] args) {
        ConstructBinaryTreeFromString test = new ConstructBinaryTreeFromString();
        test.str2tree("-4(2(3)(1))(6(5))");
    }

    public TreeNode str2tree(String s) {
        if(s == null || s.length() ==0)
            return null;

        int firstLeftParIndex = s.indexOf('(');
        int digit = firstLeftParIndex == -1 ? Integer.valueOf(s): Integer.valueOf(s.substring(0, firstLeftParIndex));
        TreeNode root = new TreeNode(digit);

        if(firstLeftParIndex == -1) return root;

        int count = 1, secondLeftParIndex = 0;

        for(int i= firstLeftParIndex +1; i< s.length(); i++){
            if(s.charAt(i) == '(') count++;
            else if(s.charAt(i) == ')') count--;

            if(count == 0 && secondLeftParIndex == 0){
                System.out.println(s.substring(firstLeftParIndex + 1, i));

                root.left = str2tree(s.substring(firstLeftParIndex + 1, i));
                secondLeftParIndex = i+1;
            }else if(count == 0){
                System.out.println("right: " +s.substring(secondLeftParIndex + 1, i));

                root.right = str2tree(s.substring(secondLeftParIndex+1, i));
            }
        }

        return root;
    }

    // my solution base on above one
    public TreeNode str2treeByDong(String s) {
        if (s == null || s.length() == 0)
            return null;
        int firstLeftParIndex = s.indexOf('(');
        int val = firstLeftParIndex == -1 ? Integer.valueOf(s) : Integer.valueOf(s.substring(0, firstLeftParIndex));
        TreeNode root = new TreeNode(val);
        if (firstLeftParIndex == -1)  return root;

        int count = 1, i = firstLeftParIndex + 1;

        while(i < s.length()){
            char c = s.charAt(i);
            if (c == '(') count++;
            else if (c == ')') count--;
            if (count == 0) {
                //System.out.println(s.substring(firstLeftParIndex + 1, i));
                root.left = str2tree(s.substring(firstLeftParIndex + 1, i));

                if(i+2 < s.length()) {
                    //System.out.println("right: " +s.substring(i + 2, s.length()-1));
                    root.right = str2tree(s.substring(i + 2, s.length()-1));
                }
                break;
            }
            i++;
        }
        return root;
    }
}
