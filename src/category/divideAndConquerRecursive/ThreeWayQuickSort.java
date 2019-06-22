package category.divideAndConquerRecursive;

/**
 * 3 way quick sort partition
 * https://leetcode.com/problems/sort-colors/discuss/148221/Java-2-pass-counting-sort-and-1-pass-quick-partition-(with-video-tutorial-links)
 * Created by brianzhang on 8/5/18.
 */
public class ThreeWayQuickSort {

    public static void main(String[] args) {
        
        ThreeWayQuickSort sort = new ThreeWayQuickSort();
        int[] test = new int[]{2,0,2,1,1,0};
        sort.sortColors(test);
        for(int val : test){
            System.out.println(val);
        }
    }

    public void sortColors(int[] nums) {
        int lt = 0, i = 1, gt = nums.length - 1;
        while (i <= gt) { // i is the represent the scanner which increase step by step (i++) to traverse the nums array
            if (nums[i] == 0) {
                swap(nums, lt++, i++); //lt is the pointer that all the number "before" lt is less than pivot (1), 
                                       //lt only able to point 0 or 1 as if when i point to 2 which will be sawp with the latter number (gt pointing)
            } else if (nums[i] == 2) {
                swap(nums, i, gt--);  // gt is also the pointer that all the number "after" gt is greater than pivot (1)
            } else {  // nums[i] == 1
                i++;
            }
        }
    }

    /**
     * Interesting swap way
     */
    public void swap(int[] nums, int i,int j){
        nums[i]=nums[i]+nums[j]-(nums[j]=nums[i]);
    }
}