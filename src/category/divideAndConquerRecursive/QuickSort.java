package category.DivideAndConquerRecursive;

import java.util.Arrays;
/**
 * Created by brianzhang on 11/12/18.
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {9, 2, 4, 7, 3, 7, 10};
        //quickSort(arr, 0, arr.length - 1);
        quickSort1(arr, 0, arr.length - 1);
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

// Lomuto partition scheme
    static void quickSort1(int []arr, int low,
                          int high)
    {
        if (low < high)
        {
            int pi = partition(arr, low, high);
            quickSort1(arr, low, pi - 1);
            quickSort1(arr, pi + 1, high);
        }
    }

    static int partition(int []arr, int low,
                         int high)
    {
        int pivot = arr[high];

        // Index of smaller element
        int i = (low - 1);

        for (int j = low; j <= high- 1; j++)
        {
            // If current element is smaller
            // than or equal to pivot
            if (arr[j] <= pivot)
            {
                i++; // increment index of
                // smaller element
                Swap(arr, i, j);
            }
        }
        Swap(arr, i + 1, high);
        return (i + 1);
    }

    static void Swap(int[] array,
                     int position1,
                     int position2)
    {
        // Swaps elements in an array

        // Copy the first position's element
        int temp = array[position1];

        // Assign to the second element
        array[position1] = array[position2];

        // Assign to the first element
        array[position2] = temp;
    }

}
