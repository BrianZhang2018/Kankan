package category.BinarySearch.template;

import java.util.Arrays;

/**
 * Use the "left < right" way, not the "left <=right ... right = mid-1":
 * 1. not able to reach the mid
 * 2. left and right is not the same at the end (right = left - 1, since right = mid-1)
 * 3. if left equal to the right at the end, will cause infinite loop since while (left<=right)
 *
 * while(left < right) ...:
 * left and right are the same at the end
 *
 * Created by brianzhang on 1/21/20.
 */
public class StandardBinarySearch {
    public static void main(String[] args) {
/*        System.out.println(binarySearch(new int[]{9, 4, 7, 8, 0, 10, 6, 6}, 6));
        System.out.println(binarySearch(new int[]{1,2,5,6}, 4));*/
        System.out.println(findMin(new int[] {3,4,5,1,2}));
    }

    // standard binary search
    public static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length;
        Arrays.sort(arr);
        while (left < right) {
            int mid = left + (right - left) / 2;
            // if we comment out the if (arr[mid] == target), we will find the left/rightmost position
            if (arr[mid] == target) {
                return mid;
            }
            if (target > arr[mid]) {
                left = mid + 1;
            } else { // 隐含了, if found first occurrence of the target, then move left since right = mid
                right = mid;
            }
            System.out.println("mid: " + mid);
            System.out.println("left: " + left);
            System.out.println("right " + right);
        }
        // "left and right is the same" at the end when use "left < right" in while condition,
        // but right = target position(left)-1 if use "left <= right, ... right=mid-1", since when "equal" will run the right=mid-1.
        return left;
    }

    // "left<=right ... right = mid -1" Not working example
    // mid need be included, like below leetcode 153. Find Minimum in Rotated Sorted Array
    // so "right = mid" since the mid-value need to be considered
    public static int findMin(int[] nums) {
        int left = 0, right = nums.length-1;
        // the "left" and "right" is equal at the end which will cause infinite loop if use while (left <=right)
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        System.out.println("left: " + left);
        System.out.println("right " + right);
        return nums[right];
    }

    // have to be "left<=right ... right = mid -1", since need run the loop when left == right, so need left<=right condition
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int left=0, right = n*m -1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            int val = matrix[mid/n][mid%n];

            if(val == target) return true;

            if(val < target) {
                left = mid+1;
            }else{
                right = mid-1;
            }
        }

        return false;
    }
}
