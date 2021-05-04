package category.BinarySearch.binarySearch;

/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 *
 * Binary Search for Rotated array
 *
 * The idea is if it's a rotating the array, there must be one half of the array which is in sorted order
 * Created by brianzhang on 2/27/19.
 */
public class SearchInRotatedSortedArray {
    public static void main(String[] args) {
        System.out.println(search(new int[]{4, 5, 6, 7, 8, 9, 0, 1, 2}, 7));
    }

    public static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while(left <= right){
            int mid = left + (right - left)/2;
            if(nums[mid] == target) return mid;

            // find the sub sorted array, then check whether has the target in sorted array.
            if(nums[left] <= nums[mid]) { // 思路：means left part is sorted in rotated array
                if(target >= nums[left] && target < nums[mid]) // target is in sorted part
                    right = mid - 1;
                else
                    left = mid + 1;
            }else {  // right part is sorted
                if(target > nums[mid] && target <= nums[right]) // target is in sorted part
                    left = mid + 1;
                else
                    right = mid - 1;
            }
        }

        return -1;
    }


}
