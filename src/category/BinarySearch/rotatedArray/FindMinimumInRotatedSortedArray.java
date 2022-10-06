package category.BinarySearch.rotatedArray;

/**
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 * this problem is actually to find the rotated point which is minimum
 *
  If nums[mid] < nums[hi], nums[mid] or some value before it could be our inflection point. Therefore, let hi = mid, including mid in our new search space.
  If nums[mid] > nums[hi], nums[mid] cannot be our inflection point. Candidates are to the right of it. Let lo = mid + 1, exluding the mid from the search space.
 */
public class FindMinimumInRotatedSortedArray {
    public static void main(String[] args) {
        System.out.println(findMin(new int[] {3,4,5,1,2}));
    }

    public static int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while(left < right) {
            int mid = left + (right - left) / 2;

            if(nums[mid] < nums[right]) { // means rotated point in the left of "mid"
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return nums[left];
    }
}
