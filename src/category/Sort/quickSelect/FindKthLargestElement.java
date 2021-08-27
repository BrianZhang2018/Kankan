package category.Sort.quickSelect;

import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/kth-largest-element-in-an-array/discuss/60333/Concise-JAVA-solution-based-on-Quick-Select
 *
 * QuickSelect solution:
 * Discard half in each iteration: n+(n/2)+(n/4)..1, the sum of that series will never reach 2*N.
 * https://stackoverflow.com/questions/56940793/quickselect-time-complexity-explained
 *
 * Time complexity =O(n)
 * Worst case: O(n*n): Continuously pick the largest or smallest element on each iteration
 *
 * Created by brianzhang on 6/26/20.
 */
public class FindKthLargestElement {
    public static void main(String[] args) {
        System.out.println(findKthLargestPriorityQueue(new int[]{5,6,3,2,1,4}, 2));
        System.out.println(new FindKthLargestElement().findKthLargest(new int[]{3,2,1,5,6,4}, 2));
    }

    // Solution-1: time complexity: N*logK, O(K) memory
    public static int findKthLargestPriorityQueue(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue();
        for(int num : nums){
            pq.add(num);
            if(pq.size() > k)
                pq.poll();
        }
        return pq.peek();
    }

    // Solution-2: QuickSelect - O(n)
    public int findKthLargest(int[] nums, int k) {
        if(nums == null || nums.length ==0) return 0;
        quickSelect(nums, 0, nums.length-1, nums.length - k);
        return nums[nums.length - k];
    }

    public void quickSelect(int nums[], int start, int end, int target){
        if(start > end) return;

        int mid = start + (end-start)/2;  // Randomized the pivot
        swap(nums, mid, end);

        int pivot=nums[end];
        int left = start;
        for(int i=start; i<end; i++){
            if(nums[i]<pivot){ // Put numbers < pivot to pivot's left
                swap(nums, left++, i);
            }
        }

        swap(nums, left, end); // swap pivot to the right position (current left)
        if(left == target){
            return;
        } else if (left < target){     // Check right part
            quickSelect(nums, left+1, end, target);
        } else {                      // Check left part
            quickSelect(nums, start, left-1, target);
        }
    }

    public void swap(int[] nums, int l, int r){
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }
}
