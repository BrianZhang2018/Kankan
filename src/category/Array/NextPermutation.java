package category.Array;

/**
 * https://leetcode.com/problems/next-permutation/discuss/13872/Easiest-JAVA-Solution
 * https://www.youtube.com/watch?v=quAS1iydq7U
 *
 * 非常好帮你理解permutation形成的过程
 *
 * Created by brianzhang on 2/23/20.
 */
public class NextPermutation {

    public static void main(String[] args) {

        NextPermutation test = new NextPermutation();
        test.nextPermutation(new int[]{1,2,3});
    }

    public void nextPermutation(int[] A) {
        if(A == null || A.length <= 1) return;
        int i = A.length - 2;
        while(i >= 0 && A[i] >= A[i + 1]) i--; // Find 1st id i that breaks descending order
        if(i >= 0) {                           // If not entirely descending
            int j = A.length - 1;              // Start from the end
            while(A[j] <= A[i]) j--;           // Find rightmost first larger id j
            swap(A, i, j);                     // Switch i and j
        }
        reverse(A, i + 1, A.length - 1);       // Reverse the descending sequence
    }

    public void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    public void reverse(int[] A, int i, int j) {
        while(i < j) swap(A, i++, j--);
    }
}
