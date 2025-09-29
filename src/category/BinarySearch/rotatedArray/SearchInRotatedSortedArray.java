package category.BinarySearch.rotatedArray;

/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 * Binary Search for Rotated array
 *
 * sub array is sorted in rotated sorted array, so we can use binary search to find target in sorted sub array
 * 因为binary search只能用在sorted array 所以：
 * 1. 确定sorted sub array 2. 用binary search去找target in sorted sub array
 *
 * Created by brianzhang on 2/27/19.
 */
public class SearchInRotatedSortedArray {
    public static void main(String[] args) {
        System.out.println(search(new int[]{4, 5, 6, 7, 8, 9, 0, 1, 2}, 7));
    }

    public static int search(int[] nums, int target) {
        int left=0, right=nums.length-1;

        while(left <= right){
            int mid = left + (right-left)/2;
            if(nums[mid] == target) return mid;

            if(nums[left] <= nums[mid]) { // means left part is sorted in rotated array
                if(nums[left] <= target && target < nums[mid]) // target in sorted part
                    right = mid - 1;
                else
                    left = mid + 1;
            } else { // means right part is sorted in rotated array
                if(nums[mid] < target && target <= nums[right])
                    left = mid + 1;
                else
                    right = mid - 1;
            }
        }

        return -1;
    }

}
