package category.Tree.BinaryTree;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-string/
 *
 * recursive
 * Created by brianzhang on 7/1/20.
 */
public class ConstructBinaryTreeFromStringWithParenthesis {
    public static void main(String[] args) {
        // str2tree("-4(2(3)(1))(6(5))");
        str2treeByDong("-4(2(3)(1))(6(5))");
    }

    public static TreeNode str2tree(String s) {
        if(s == null || s.length() ==0) return null;

        int leftSubTreeStart = s.indexOf('(');
        int val = leftSubTreeStart == -1 ? Integer.valueOf(s) :
                Integer.valueOf(s.substring(0, leftSubTreeStart));
        TreeNode root = new TreeNode(val);
        if(leftSubTreeStart == -1) return root;

        int cnt = 0, rightSubTreeStart = 0;
        for (int right = leftSubTreeStart; right < s.length(); right++) {
            if (s.charAt(right) == '(') cnt++;
            else if (s.charAt(right) == ')') cnt--;

            if (cnt == 0 && rightSubTreeStart == 0) {
                System.out.println("left: " + s.substring(leftSubTreeStart + 1, right));
                root.left = str2tree(s.substring(leftSubTreeStart + 1, right));
                rightSubTreeStart = right + 1;
            } else if (cnt == 0) {
                System.out.println("right: " + s.substring(rightSubTreeStart + 1, right));
                root.right = str2tree(s.substring(rightSubTreeStart + 1, right));
            }
        }

        return root;
    }

// my refactor based on above solution
    public static TreeNode str2treeByDong(String s) {
        if (s == null || s.length() == 0) return null;

        int leftSubtreeStart = s.indexOf('('); // left subtree start index
        int val = leftSubtreeStart == -1 ? Integer.valueOf(s) :
                Integer.valueOf(s.substring(0, leftSubtreeStart));
        TreeNode root = new TreeNode(val);
        if (leftSubtreeStart == -1) return root;

        int cnt = 1, lefSubTreeEnd = leftSubtreeStart;
        while (lefSubTreeEnd++ < s.length()) {
            char c = s.charAt(lefSubTreeEnd);
            if (c == '(') cnt++;
            else if (c == ')') cnt--;

            if (cnt == 0) {
                //System.out.println(s.substring(firstLeftParIndex + 1, i));
                root.left = str2treeByDong(s.substring(leftSubtreeStart + 1, lefSubTreeEnd));
                if (lefSubTreeEnd + 2 < s.length()) {
                    //System.out.println("right: " +s.substring(i + 2, s.length()-1));
                    root.right = str2treeByDong(s.substring(lefSubTreeEnd + 2, s.length() - 1));
                }
                break;
            }
        }
        return root;
    }
}
