package category.Array;

/**
 * Calculate the maximum difference between two elements in an array such that a smaller element appears before a larger element
 *
 * Created by brianzhang on 2/26/21.
 */
public class MaximumDifferenceTwoElementsInArray {

    public static void main(String[] args)
    {
        int[] A = { 2, 7, 9, 5, 1, 3, 5 };
        System.out.println("The maximum difference is " + diff(A));
        System.out.println("The maximum difference is " + diff1(A));
    }

    // from right to left
    public static int diff(int[] A)
    {
        int diff = Integer.MIN_VALUE;
        int n = A.length;
        int max_so_far = A[n-1]; // track maximum value

        // traverse the array from the right and keep track of the maximum element
        for (int i = n - 2; i >= 0; i--)
        {
            // update `max_so_far` if the current element is greater than the maximum element
            if (A[i] > max_so_far) {
                max_so_far = A[i];
            }
            // if the current element is less than the maximum element, then update the difference if required
            else {
                diff = Integer.max(diff, max_so_far - A[i]);
            }
        }

        return diff;
    }

    // from left to right
    public static int diff1(int[] A)
    {
        int diff = Integer.MIN_VALUE;
        int min_so_far = A[0]; // track minimum value

        for (int i = 1; i < A.length; i++)
        {
            if (A[i] < min_so_far) {
                min_so_far = A[i];
            }
            else {
                diff = Integer.max(diff, A[i] - min_so_far);
            }
        }

        return diff;
    }

}
