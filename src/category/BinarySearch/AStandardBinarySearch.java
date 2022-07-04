package category.BinarySearch;

import java.util.Arrays;

/**
 * Created by brianzhang on 1/21/20.
 */
public class AStandardBinarySearch {
    public static void main(String[] args) {
        System.out.println(bs(new int[]{9, 4, 7, 8, 0, 23, 54}, 9));
        System.out.println(FindFirstAndLastPositionOfElement(new int[]{1, 4, 7, 8, 9, 9, 23, 54}, 9));
        System.out.println(FindFirstAndLastPositionOfElement1(new int[]{1, 4, 7, 8, 9, 9, 23, 54}, 9)); // find "rightmost" target
    }

    // standard binary search
    public static int bs(int[] arr, int target) {
        int left = 0, right = arr.length;
        Arrays.sort(arr);
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target)
                return mid;

            if (target > arr[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    // FindFirstAndLastPositionOfElement
    // omit (arr[mid] == target) check, find "leftmost" target
    public static int FindFirstAndLastPositionOfElement(int[] arr, int target) {
        int left = 0, right = arr.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (target > arr[mid]) {
                left = mid + 1;
            } else { // target <= arr[mid], when equal do "right = mid"
                right = mid;
            }
        }
        return left;
    }

    // find "rightmost" target
    public static int FindFirstAndLastPositionOfElement1(int[] arr, int target) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (target < arr[mid]) {
                right = mid;
            } else { // target >= arr[mid], when equal do "left = mid+1"
                left = mid + 1;
            }
        }
        return right - 1; // or left - 1
    }
}
