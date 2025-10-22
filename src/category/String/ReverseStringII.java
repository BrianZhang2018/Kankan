package category.String;

/**
 * https://leetcode.com/problems/reverse-string-ii/
 */
public class ReverseStringII {
    public static void main(String[] args) {
        System.out.println(new ReverseStringII().reverseStr("abcdefg", 2));
    }

    public String reverseStr(String s, int k) {
        char[] ca = s.toCharArray();
        for (int i = 0; i < s.length(); i += 2 * k) {
            swap(ca, i, i + k);
        }
        return String.valueOf(ca);
    }

    public void swap(char[] ca, int start, int end) {
        end = Math.min(ca.length, end) - 1;
        while (start < end) {
            char temp = ca[start];
            ca[start] = ca[end];
            ca[end] = temp;
            start++;
            end--;
        }
    }
}
