package category.BacktracingDFS.permutation;

/**
 * Similar with the PermutationI1.java
 *
 * Created by brianzhang on 10/8/18.
 */
public class PermutationI2 {
    public static void main(String[] args) {
        String str = "ABC";
        PermutationI2 permutation = new PermutationI2();
        permutation.permute(str, 0, str.length() - 1);
    }

    // l - starting index, r - end index
    private void permute(String str, int l, int r) {
        if (l == r)
            System.out.println(str);
        else {
            for (int i = l; i <= r; i++) {
                str = swap(str, l, i);
                permute(str, l + 1, r);
                str = swap(str, l, i);
            }
        }
    }

    public String swap(String a, int i, int j) {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }
}
