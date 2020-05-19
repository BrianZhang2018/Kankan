package category.BinaryTreeAndBinarySerach.binarySearch;

/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
 * This is a follow up problem to Search in Rotated Sorted Array, where nums may contain duplicates.
 *
 * Created by brianzhang on 5/10/20.
 */
public class SearchInRotatedSortedArrayII {

    public static void main(String[] args) {
        SearchInRotatedSortedArrayII test = new SearchInRotatedSortedArrayII();
        System.out.println(test.search(new int[]{2,5,6,0,0,1,2}, 0));
    }

    public boolean search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end -start) / 2;
            if (target == nums[mid]) {
                return true;
            }

            if (nums[mid] > nums[start]) {
                if (target >= nums[start] && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }

            } else if(nums[mid] < nums[start]) {
                if (target > nums[mid] && target <=nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            } else {
                // means nums[start] == nums[mid], but != target, we need move the pointer start++ to skip the duplicate value
                start++;
            }
        }
        return false;
    }
}
