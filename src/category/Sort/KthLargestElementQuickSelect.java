package category.Sort;

/**
 * Quick select algorithm
 *
 * https://leetcode.com/problems/kth-largest-element-in-an-array/description/
 */
public class KthLargestElementQuickSelect {
    public static void main(String[] args) {
        KthLargestElementQuickSelect quickSelect = new KthLargestElementQuickSelect();
        int arr[] = {10, 7, 8, 9, 1, 5};
        System.out.println(quickSelect.findKthLargest(arr, 4));
    }

    public int findKthLargest(int[] nums, int k) {
        int start = 0, end = nums.length - 1, index = nums.length - k;
        while (start < end) {
            int pivot = partition(nums, start, end);
            if (pivot < index) start = pivot + 1;
            else if (pivot > index) end = pivot - 1;
            else return nums[pivot];
        }
        return nums[start];
    }

    private int partition(int[] nums, int start, int end) {
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
}