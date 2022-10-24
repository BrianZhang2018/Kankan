package category.BinarySearch.rotatedArray;

/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 * Binary Search for Rotated array
 *
 * if it's a rotating the array, there must be one half of the array which is in sorted order
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
            // 思路：find the sub sorted array, then check whether it has the target in there.
            if(nums[left] <= nums[mid]) { // means left part is sorted in rotated array
                if(nums[left] <= target && target < nums[mid]) // target resides in sorted part
                    right = mid - 1;
                else
                    left = mid + 1;
            }else {  // right part is sorted
                if(nums[mid] < target && target <= nums[right]) // target resides in sorted part
                    left = mid + 1;
                else
                    right = mid - 1;
            }
        }

        return -1;
    }

}
