package category.Sort.quickSelect;

import java.util.Random;

/**
 * Created by brianzhang on 7/2/21.
 */
public class FindKthLargestElementWithShuffle {

    public static void main(String[] args) {
        System.out.println(new FindKthLargestElementWithShuffle().findKthLargest(new int[]{3,2,1,5,6,4}, 2));
    }

    public int findKthLargest(int[] nums, int k) {
        shuffle(nums);
        int n = nums.length;
        int lo = 0, hi = n - 1;
        k = n - k;
        while(lo <= hi) {
            int j = partition(nums, lo, hi);
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

    private int partition(int[] nums, int lo, int hi) {
        int pivot = nums[lo];
        while (lo < hi) {
            while (lo < hi && nums[hi] >= pivot) {
                hi--;
            }
            nums[lo] = nums[hi];

            while (lo < hi && nums[lo] <= pivot) {
                lo++;
            }
            nums[hi] = nums[lo];
        }

        nums[lo] = pivot;
        return lo;
    }

    // shuffle will help to improve the overall performance in all test cases
    public void shuffle(int[] nums) {
        Random rng = new Random();
        for(int i = 1; i < nums.length; i++) {
            swap(nums, i, rng.nextInt(i+1));
        }
    }

    public void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
}
