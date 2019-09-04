package category.string;

// leetcode: 28
// https://leetcode.com/problems/implement-strstr/discuss/13167/Java-KMP-algorithm
// https://www.youtube.com/watch?time_continue=139&v=kBW6oPaVjq0
// http://www.ruanyifeng.com/blog/2013/05/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm.html
public class KMPAlgorithm {
    public static void main(String[] args) {
        System.out.println(strStr("abcd abcdbabcda", "abcda"));
    }
    public static int strStr(String haystack, String needle) {
        int[] prefix = KMP(needle);
        int i = 0, j = 0;
        char[] charArr1 = haystack.toCharArray(), charArr2 = needle.toCharArray();
        while (i < haystack.length() && j < needle.length()) {
            if (charArr1[i] == charArr2[j]) {
                i++;
                j++;
            } else {
                if (j != 0) {
                    j = prefix[j - 1];
                } else {
                    i++;
                }
            }
        }
        if (j == needle.length()) {
            return i - j;
        } else {
            return -1;
        }
    }

    private static int[] KMP(String needle) {
        int[] res = new int[needle.length()];
        char[] charArr = needle.toCharArray();
        int i = 0, j = 1;
        while (j < needle.length()) {
            if (charArr[i] == charArr[j]) {
                res[j] = i + 1;
                i++;
                j++;
            } else {
                if (i != 0) {
                    i = res[i - 1];
                } else {
                    res[j] = 0;
                    j++;
                }
            }
        }
        return res;
    }
}