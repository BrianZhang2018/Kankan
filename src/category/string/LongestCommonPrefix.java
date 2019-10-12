package category.string;

import java.util.Arrays;
/**
 * Created by brianzhang on 8/12/18.
 * https://leetcode.com/problems/longest-common-prefix/solution/
 */
public class LongestCommonPrefix {
    public static void main(String[] args) {
        System.out.println(longestCommonPrefix1(new String[]{"flower","flow","flight", "fpop"}));
    }

    public static String longestCommonPrefix1(String[] strs) {
        if (strs.length == 0) 
            return "";
        String pre = strs[0];
        for (int i = 1; i < strs.length; i++)
            while(strs[i].indexOf(pre) != 0)
                pre = pre.substring(0, pre.length()-1);
        return pre;
    }
}