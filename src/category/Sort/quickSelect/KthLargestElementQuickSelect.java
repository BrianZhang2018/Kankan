package category.Sort.quickSelect;

/**
 * https://leetcode.com/problems/kth-largest-element-in-an-array/description/
 *
 * Quick select algorithm
 * Time complexity: O(N) in the average case, O(N^2) in the worst case.
 * Worst case: Worst case occurs when we pick the largest/smallest element as pivot.
 */
public class KthLargestElementQuickSelect {
    public static void main(String[] args) {
        KthLargestElementQuickSelect quickSelect = new KthLargestElementQuickSelect();
        int arr[] = {3, 2, 1, 5, 6, 4};
        System.out.println(quickSelect.findKthLargest(arr, 2));
    }

    public int findKthLargest(int[] nums, int k) {
        int start = 0, end = nums.length - 1, index = nums.length - k;
        while (start < end) {
            int pivot = partition1(nums, start, end);
            if (pivot < index)
                start = pivot + 1;
            else if (pivot > index)
                end = pivot - 1;
            else
                return nums[pivot];
        }
        return nums[start];
    }

    private int partition(int[] nums, int start, int end) {
        int pivot = start;
        while (start <= end) {
            while (start <= end && nums[start] <= nums[pivot]) start++;
            while (start <= end && nums[end] > nums[pivot]) end--;

            if (start > end) break;
            swap(nums, start, end);
        }

        swap(nums, pivot, end);
        return end;
    }

    // another way for partition
    private int partition1(int[] nums, int lo, int hi) {
        int pivot = nums[hi];
        int left = lo;
        for (int j = lo; j < hi; j++) {
            if (nums[j] <= pivot) {
                swap(nums, left, j);
                left++;
            }
        }
        swap(nums, left, hi);
        return left;
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}