package category.BacktracingDFS.permutation;

/**
 * Same with the Permutation.java
 *
 * Created by brianzhang on 10/8/18.
 */
public class PermutationI2 {
    public static void main(String[] args) {
        String str = "ABC";
        int n = str.length();
        PermutationI2 permutation = new PermutationI2();
        permutation.permute(str, 0, n - 1);
    }

    /**
     * @param l   starting index
     * @param r   end index
     */
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

    /**
     * Swap Characters at position
     * @param i position 1
     * @param j position 2
     */
    public String swap(String a, int i, int j) {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }

}