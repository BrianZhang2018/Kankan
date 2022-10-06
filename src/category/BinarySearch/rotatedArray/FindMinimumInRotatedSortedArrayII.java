package category.BinarySearch.rotatedArray;

/**
 * If array contains dup number
 * nice analysis: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/solution/
 */
class FindMinimumInRotatedSortedArrayII {
    public static void main(String[] args) {
        System.out.println(findMin(new int[] {2,2,2,0,1}));
    }

    public static int findMin(int[] nums) {
        if (nums.length == 1) return nums[0];

        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == nums[right]) {
                right--;
                continue;
            }
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }
}
