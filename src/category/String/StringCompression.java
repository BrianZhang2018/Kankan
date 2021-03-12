package category.String;

/**
 * https://leetcode.com/problems/string-compression/
 *
 * Created by brianzhang on 10/16/19.
 */
public class StringCompression {

    public static void main(String[] args) {
        System.out.println(compress("aaabbc")); // a3b2c
        // System.out.println(test.compress(new char[]{'a','a','b','b','c','c','c'}));
    }

    // Compress: e.g. aaabb -> a3b2, abb -> ab2
    public static String compress(String s) {

        char[] charArr = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        int index = 0, count = 0;
        while (index < charArr.length) {
            char c = charArr[index];
            while (index < charArr.length && c == charArr[index]) {
                index++;
                count++;
            }
            sb.append(c);
            if (count != 1) {
                sb.append((char) (count + '0'));
            }
            count = 0;
        }
        return sb.toString();
    }

}