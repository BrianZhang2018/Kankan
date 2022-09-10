package category.BinarySearch.template;

import java.util.Arrays;

/**
 * Created by brianzhang on 1/21/20.
 */
public class StandardBinarySearch {
    public static void main(String[] args) {
        System.out.println(bs(new int[]{9, 4, 7, 8, 0, 10, 6, 11, 15, 23, 54, 100, 200}, 9));
        // System.out.println(FindFirstAndLastPositionOfElement(new int[]{1, 4, 7, 8, 9, 9, 23, 54}, 9));
        // System.out.println(FindFirstAndLastPositionOfElement1(new int[]{1, 4, 7, 8, 9, 9, 23, 54}, 9)); // find "rightmost" target
    }

    // standard binary search
    public static int bs(int[] arr, int target) {
        int left = 0, right = arr.length;
        Arrays.sort(arr);
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid;
            }
            if (target > arr[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
                // equally if "left < right" above, then "right = mid".
                // one thing should be noticed, the "left == right" in the end for left<right,
                // but "left > right" by the end for left<=right
            }

            System.out.println("left: " + left);
            System.out.println("right " + right);
        }
        return left;
    }

    // variation problem: mid need be included, 153. Find Minimum in Rotated Sorted Array
    // reason of "right = mid" is the mid-value need to be considered
    public static int findMin(int[] nums) {
        int left = 0, right = nums.length-1;
        // the reason here is not "left <=right" as the "left" and "right" will eventually be equal at the end of binary search,
        // so it will cause the infinite while here since we don't have an exit check here like standard one which is to find a target and there has a target exist, so able to exit.
        while(left < right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] > nums[right]) {
                left = mid+1;
            }else{
                right = mid;
            }
        }

        return nums[right];
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
