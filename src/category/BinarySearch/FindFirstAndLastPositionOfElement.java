package category.BinarySearch;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 *
 * Created by brianzhang on 1/22/20.
 */
public class FindFirstAndLastPositionOfElement {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(searchRange(new int[]{5,7,7,8,8,10}, 8)));
        // System.out.println(Arrays.toString(searchRange1(new int[]{5,7,7,8,8,10}, 8)));
    }

    // Solution-1
    public static int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        if(nums == null || nums.length ==0) return result;

        int leftIndex = binarySearch(nums, target, true);
        // nums[leftIndex] != target to avoid the case like [[5,6,7,8,9], 4] the leftIndex will be "0"
        if(leftIndex == nums.length || nums[leftIndex] != target) return result;

        result[0] = leftIndex;
        result[1] = binarySearch(nums, target, false) - 1; // rightIndex
        return result;
    }

    public static int binarySearch(int[] nums, int target, boolean left){
        int lo = 0, hi = nums.length;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            // 在找leftmost Or rightmost target时候stay对应的left/right subArray when target == nums[mid]
            // left=true && target == nums[mid], left is true means we are finding the leftmost target, so we need stay on left side, so hi = mid
            // left=false && target == nums[mid], left is false means we are finding the rightmost target, so we need stay on right side, so lo = mid + 1
            if (nums[mid] > target || left && target == nums[mid]) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo; // return lo, hi doesn't matter
    }

    // Solution-2， 这个更直观一些，但其实是一样的解法
    public static int[] searchRange1(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        int[] result = {-1, -1};
        if (nums == null || nums.length == 0) return result;

        // left side
        while(lo < hi){
            int mid = lo + (hi - lo)/2;
            if(target > nums[mid]){
                lo = mid + 1;
            }else{ // 解题思路：target == nums[mid] will do "hi=mid" so that we can keep loop on left side to get leftmost target
                hi = mid;
            }
        }
        if (target == nums[lo]) {
            result[0] = lo;
        } else {
            result[0] = -1;
        }

        // right side
        hi = nums.length - 1;
        while(lo < hi){
            // if u don't do "+1", the mid always tends to the left, by adding 1, we ask mid to go to right, which is what we want.
            // + 1 is like the divide operator then takes the next whole number
            int mid = (lo + (hi - lo)/2) + 1;
            if(target < nums[mid]){
                hi = mid - 1;
            }else{ //  解题思路：target == nums[mid] will do "low=mid", will keep loop the right side so that we can find the rightmost target
                lo = mid;
            }
        }
        if (target == nums[hi]) {
            result[1] = hi;
        } else {
            result[1] = -1;
        }

        return result;
    }
}

