package category.DivideAndConquer;

import java.util.Arrays;

/**
 * MergeSort is Divide and Conquer algorithm
 *
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
    private int[] temp;
    private int length;

    public void sort(int inputArr[]) {
        this.array = inputArr;
        this.length = inputArr.length;
        this.temp = new int[length];
        mergeSortHelper(0, length - 1);
    }

    private void mergeSortHelper(final int low, final int high) {
        // check if low is smaller then high
        if (low < high) {
            int middle = low + (high - low) / 2;
            mergeSortHelper(low, middle);  //sorts the left side of the array
            mergeSortHelper(middle + 1, high);  //sorts the right side of the array
            merge(low, middle, high);  // Now merge both sides
        }
    }

    private void merge(final int low, final int middle, final int high) {
        // Copy both parts into the dfsHelper - temp array
        for (int i = low; i <= high; i++) {
            temp[i] = array[i];
        }
        int i = low, j= middle + 1;
        int currIndex = low;
        // Copy the smallest values from either the left or the right side back to the original array
        while (i <= middle && j <= high) {        //difference with quickSort
            if (temp[i] <= temp[j]) {
                array[currIndex++] = temp[i++];
            } else {
                array[currIndex++] = temp[j++];
            }
        }
        // Copy the rest of the left side of the array into the target array
        while (i <= middle) {
            array[currIndex++] = temp[i++];
        }

        // Copy the rest of the right side of the array into the target array
        while (j <= high) {
            array[currIndex++] = temp[j++];
        }
    }
}
