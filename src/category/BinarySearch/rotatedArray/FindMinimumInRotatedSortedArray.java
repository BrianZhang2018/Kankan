package category.BinarySearch.rotatedArray;

// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
// right rotation: rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
// left rotation: rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[1], a[2], ..., a[n-1], a[0]].
public class FindMinimumInRotatedSortedArray {
    public static void main(String[] args) {
        System.out.println(findMin(new int[] {3,4,5,1,2}));
    }

    public static int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while(left < right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return nums[left];
    }
}
