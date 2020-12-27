package category.BinarySearch;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 *
 * Created by brianzhang on 1/22/20.
 */
public class FindFirstAndLastPositionOfElement {
    public static void main(String[] args) {
        FindFirstAndLastPositionOfElement test = new FindFirstAndLastPositionOfElement();
        System.out.println(Arrays.toString(test.searchRange(new int[]{5,7,7,8,8,10}, 4)));
        System.out.println(Arrays.toString(test.searchRange1(new int[]{5,7,7,8,8,10}, 8)));
    }

    // solution-1
    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        if(nums == null || nums.length ==0) return result;

        int leftIndex = binarySearch(nums, target, true);
        if(leftIndex == nums.length || nums[leftIndex] != target){ // nums[leftIndex] != target to avoid the case like [[5,6,7,8,9], 4] the leftIndex will be "0".
            return result;
        }

        result[0] = leftIndex;
        int rightIndex = binarySearch(nums, target, false) - 1;
        result[1] = rightIndex;

        return result;
    }

    public int binarySearch(int[] nums, int target, boolean left){
        int lo = 0;
        int hi = nums.length;

        while(lo<hi){  // lo<=hi, 不适用于这里，会在下面 if(nums[mid] > target || left && target == nums[mid]) 出现越界问题
            int mid = lo + (hi-lo)/2;
            if(nums[mid] > target || left && target == nums[mid]) { //解题思路: "left && target == nums[mid]"和上面的解题思路其实是一样的，都是要保证在找leftmost or rightmost target时候stay对应的left/right subArray当target == nums[mid]时候。
                hi = mid;
            }else{
                lo = mid+1;
            }
        }

        return lo; // return lo, hi doesn't matter
    }
    // if(nums[mid] > target || left && target == nums[mid])
    // left=true && target == nums[mid], left is true means we are finding the leftmost target, so we need stay on left side, so hi = mid
    // left=false && target == nums[mid], left is false means we are finding the rightmost target, so we need stay on right side, so lo = mid + 1

    // solution-2， 这个更直观一些，但其实是一样的解题方法
    public static int[] searchRange1(int[] nums, int target) {
        int low=0, hi = nums.length - 1;
        int[] rs = new int[2];
        // base case
        if(nums == null || nums.length == 0) return new int[]{-1, -1};

        //left side
        while(low < hi){
            int mid = low + (hi - low)/2;
            if(target > nums[mid]){
                low = mid + 1;
            }else{ // 解题思路：target == nums[mid] will do "hi=mid" so that we can keep loop on left side to get leftmost target
                hi = mid;
            }
        }
        if(target == nums[low]){
            rs[0] = low;
        }else{
            rs[0] = -1;
        }

        // right side
        hi = nums.length - 1;
        while(low < hi){
            int mid = (low + (hi - low)/2) + 1; // if u don't do "+1", the mid always tends to the left, by adding 1, we asking mid to go to right, which is what we want. + 1 is like the divide operator then takes the next whole number

            if(target < nums[mid]){
                hi = mid - 1;
            }else{ //  解题思路：target == nums[mid] will do "low=mid", will keep loop the right side so that we can find the rightmost target
                low = mid;
            }
        }
        if(target == nums[hi]){
            rs[1] = hi;
        }else{
            rs[1] = -1;
        }

        return rs;
    }
}

