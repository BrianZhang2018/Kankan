package category.binaryTreeAndBinarySerach;

/**
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 *
 * Created by brianzhang on 1/22/20.
 */
public class FindFirstAndLastPositionOfElement {

    public static void main(String[] args) {
        FindFirstAndLastPositionOfElement test = new FindFirstAndLastPositionOfElement();
        test.searchRange(new int[]{5,7,7,8,8,10}, 8);
    }

    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        if(nums == null || nums.length ==0)
            return result;

        int leftIndex = bs(nums, target, true);
        if(leftIndex == nums.length || nums[leftIndex] != target){
            return result;
        }

        result[0] = leftIndex;
        int rightIndex = bs(nums, target, false)-1;
        result[1] = rightIndex;

        return result;
    }

    public int bs(int[] nums, int target, boolean left){
        int lo = 0;
        int hi = nums.length;

        while(lo<hi){  // lo<=hi, 不适用于这里，会在下面line39出现越界问题

            int mid = lo + (hi-lo)/2;
            if(nums[mid] > target || left && target == nums[mid]){
                hi = mid;
            }else{
                lo = mid+1;
            }
        }

        return lo;
    }
}

