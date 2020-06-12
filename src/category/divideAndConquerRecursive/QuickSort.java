package category.DivideAndConquerRecursive;

import java.util.Arrays;
/**
 * Created by brianzhang on 11/12/18.
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {9, 2, 4, 7, 3, 7, 10};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (arr == null || arr.length == 0) return;

        if (low >= high) return;

        // pick the pivot
        int middle = low + (high - low) / 2;
        int pivot = arr[middle];

        // make left < pivot and right > pivot
        int i = low, j = high;
        while (i <= j) {
            while (arr[i] < pivot) {
                i++;
            }

            while (arr[j] > pivot) {
                j--;
            }

            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        System.out.println(Arrays.toString(arr));

        // recursively sort two sub parts
        if (low < j) {
            System.out.println("low < j:  " + low + "   " + j);
            quickSort(arr, low, j);
        }

        if (i < high) {
            System.out.println("i > high:  " + i + "   " + high);
            quickSort(arr, i, high);
        }
    }
}
