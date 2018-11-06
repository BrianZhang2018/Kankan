package category.sort;

/**
 * Created by brianzhang on 10/8/18.
 * <p>
 * <p>
 * Qucik select algorithm
 * <p>
 * https://leetcode.com/problems/kth-largest-element-in-an-array/description/
 */
public class FindKthLargest {

    public int findKthLargest(int[] nums, int k) {
        int start = 0, end = nums.length - 1, index = nums.length - k;
        while (start < end) {
            int pivot = partion(nums, start, end);
            if (pivot < index) start = pivot + 1;
            else if (pivot > index) end = pivot - 1;
            else return nums[pivot];
        }
        return nums[start];
    }

    private int partion(int[] nums, int start, int end) {
        int pivot = start, temp;
        while (start <= end) {
            while (start <= end && nums[start] <= nums[pivot]) start++;
            while (start <= end && nums[end] > nums[pivot]) end--;
            if (start > end) break;
            temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
        }
        temp = nums[end];
        nums[end] = nums[pivot];
        nums[pivot] = temp;
        return end;
    }

    public static void main(String[] args) {
        FindKthLargest quickSelect = new FindKthLargest();

        int arr[] = {10, 7, 8, 9, 1, 5};
        System.out.println(quickSelect.findKthLargest(arr, 4));
    }

}