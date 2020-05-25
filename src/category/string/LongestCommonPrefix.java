package category.string;

/**
 * https://leetcode.com/problems/longest-common-prefix/solution/
 *
 * Created by brianzhang on 8/12/18.
 */
public class LongestCommonPrefix {
    public static void main(String[] args) {
        System.out.println(longestCommonPrefix1(new String[]{"flower","flow","flight", "flop"}));
    }

    public static String longestCommonPrefix1(String[] strs) {
        if (strs.length == 0) return "";

        String prev = strs[0];
        for (int i = 1; i < strs.length; i++)
            while(strs[i].indexOf(prev) != 0)
                prev = prev.substring(0, prev.length()-1);
        return prev;
    }
}