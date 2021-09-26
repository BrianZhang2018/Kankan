package category.String;

/**
 * https://leetcode.com/problems/string-compression/
 *
 * Created by brianzhang on 10/16/19.
 */
public class StringCompression {

    public static void main(String[] args) {
        System.out.println(compress(new char[]{'a','a','b','b','c','c','c'}));
        //System.out.println(compress("aaabbc")); // a3b2c
    }

    // input is char array and compress by modifying the array directly
    public static int compress(char[] chars) {
        int n = chars.length;
        int left= 0, right = 0;

        while(right<n) {
            int count = 0;
            char curr = chars[right];

            while(right < n && curr == chars[right]){
                count++;
                right++; // careful: do "++" here rather than inside of while condition
            }

            chars[left++] = curr;
            if(count > 1){
                char[] counts = String.valueOf(count).toCharArray();
                for(char c : counts)
                    chars[left++] = c;
            }
        }

        return left;
    }

    // Compress: e.g. aaabb -> a3b2, abb -> ab2
    public static String compress(String s) {
        StringBuilder sb = new StringBuilder();
        char[] ca = s.toCharArray();
        int index=0, count=0;
        while (index < ca.length) {
            char c = ca[index];
            while (index < ca.length && c == ca[index]) {
                index++;
                count++;
            }
            sb.append(c);
            if (count > 1) {
                sb.append((char) (count + '0'));
            }
            count = 0;
        }
        return sb.toString();
    }

}