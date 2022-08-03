package category.BinarySearch.rotatedArray;

// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
// right rotation: rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
// left rotation: rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[1], a[2], ..., a[n-1], a[0]].
// this problem is actually to find the rotated point which is minimum
public class FindMinimumInRotatedSortedArray {
    public static void main(String[] args) {
        System.out.println(findMin(new int[] {3,4,5,1,2}));
       // System.out.println(String.valueOf(new int[] {3,3,5}).toString().contains("5"));
        System.out.println(Character.forDigit(1, 10));
    }

    public static int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while(left < right) {
            int mid = left + (right - left) / 2;

            if(nums[mid] < nums[right]) { // if true, means minimum in the left of "mid"
                right = mid;
            } else { // since it's left rotation, so this means the small sorted array in right,
                    // if nums[mid] > nums[right], we should continue look into the right side
                left = mid + 1;
            }
        }

        return nums[left];
    }
}
