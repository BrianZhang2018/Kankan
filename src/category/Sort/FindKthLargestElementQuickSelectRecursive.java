package category.Sort;

/**
 * https://leetcode.com/problems/kth-largest-element-in-an-array/discuss/60333/Concise-JAVA-solution-based-on-Quick-Select
 *
 * Created by brianzhang on 6/26/20.
 */
public class FindKthLargestElementQuickSelectRecursive {

    public static void main(String[] args) {
        FindKthLargestElementQuickSelectRecursive test = new FindKthLargestElementQuickSelectRecursive();
        System.out.println(test.findKthLargest(new int[]{3,2,1,5,6,4}, 2));
    }

    public int findKthLargest(int[] nums, int k) {
        if(nums == null || nums.length ==0) return 0;
        return quickSelect(nums, 0, nums.length-1, nums.length - k);
    }

    public int quickSelect(int nums[], int start, int end, int target){
        if(start > end) return Integer.MAX_VALUE;

        int pivot=nums[end]; // Take A[end] as the pivot
        int left = start;

        for(int i=start;i<end;i++){
            if(nums[i]<pivot){ // Put numbers < pivot to pivot's left
                swap(nums, left++, i);
            }
        }

        swap(nums, left, end); // swap pivot to the right position (current left)

        if(left == target)
            return nums[left];
        else if(left < target){ // Check right part
            return quickSelect(nums, left+1, end, target);
        }else{ // Check left part
            return quickSelect(nums, start, left-1, target);
        }
    }

    public void swap(int[] nums, int l, int r){
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }
}
