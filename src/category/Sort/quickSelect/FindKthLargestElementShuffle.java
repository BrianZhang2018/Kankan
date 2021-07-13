package category.Sort.quickSelect;

import java.util.Random;

/**
 * Created by brianzhang on 7/2/21.
 */
public class FindKthLargestElementShuffle {


    public int findKthLargest(int[] nums, int k) {
        shuffle(nums);
        int n = nums.length;
        int lo = 0;
        int hi = n - 1;
        k = n - k;
        while(lo <= hi) {
            int j = partitionH(nums, lo, hi);
            if(j == k) {
                break;
            }
            else if(j < k) {
                lo = j+1;
            }
            else {
                hi = j-1;
            }
        }
        return nums[k];
    }

    public int partition(int[] nums, int lo, int hi) {
        int pivot = nums[hi];
        int s = lo;
        for(int idx = lo; idx < hi; idx++) {
            if(nums[idx] < pivot) {
                swap(nums, s, idx);
                s++;
            }
        }
        swap(nums, s, hi);
        return s;
    }

    private int partitionH(int[] nums, int lo, int hi) {
        int pivot = nums[lo];
        while (lo < hi) {
            while (lo < hi && nums[hi] >= pivot ) hi--;
            nums[lo] = nums[hi];
            while (lo < hi && nums[lo] <= pivot ) lo++;
            nums[hi] = nums[lo];
        }
        nums[lo] = pivot;
        return lo;
    }

    public void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    public void shuffle(int[] nums) {
        Random rng = new Random();
        for(int i = 1; i < nums.length; i++) {
            swap(nums, i, rng.nextInt(i+1));
        }
    }
}
