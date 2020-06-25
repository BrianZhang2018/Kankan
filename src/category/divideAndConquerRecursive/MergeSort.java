package category.DivideAndConquerRecursive;

import java.util.Arrays;

/**
 * MergeSort is Divide and Conquer algorithm
 * <p>
 * Time Complexity:	O(nlog(n))
 */
public class MergeSort {
    public static void main(String a[]) {
        MergeSort mms = new MergeSort();
        int[] inputArr = new int[]{2,3,25,25,6};
        mms.sort(inputArr);
        System.out.println(Arrays.toString(inputArr));
    }

    private int[] array;
    private int[] tempMergArr;
    private int length;

    public void sort(int inputArr[]) {
        this.array = inputArr;
        this.length = inputArr.length;
        this.tempMergArr = new int[length];
        mergeSortHelper(0, length - 1);
    }

    //do merge sort
    private void mergeSortHelper(final int low, final int high) {
        // check if low is smaller then high
        if (low < high) {
            int middle = low + (high - low) / 2;

            //sorts the left side of the array
            mergeSortHelper(low, middle);
            //sorts the right side of the array
            mergeSortHelper(middle + 1, high);
            // Now merge both sides
            merge(low, middle, high);
        }
    }

    private void merge(final int low, final int middle, final int high) {

        // Copy both parts into the dfs - tempMergArr array
        for (int i = low; i <= high; i++) {
            tempMergArr[i] = array[i];
        }
        int i = low;
        int j = middle + 1;
        int k = low;
        // Copy the smallest values from either the left or the
        // right side back to the original array
        while (i <= middle && j <= high) {        //difference with quickSort
            if (tempMergArr[i] <= tempMergArr[j]) {
                array[k++] = tempMergArr[i++];
            } else {
                array[k++] = tempMergArr[j++];
            }
        }
        // Copy the rest of the left side of the array into the target array
        while (i <= middle) {
            array[k++] = tempMergArr[i++];
        }

        // Copy the rest of the right side of the array into the target array
        while (j <= high) {
            array[k++] = tempMergArr[j++];
        }
    }
}
