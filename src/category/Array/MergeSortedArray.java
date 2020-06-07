package category.Array;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/merge-sorted-array/
 *
 * Start in-place insert from the end of array
 *
 * Created by brianzhang on 6/2/20.
 */
public class MergeSortedArray {

    public static void main(String[] args) {
        int[] nums1 = new int[]{1,2,3,0,0,0};
        merge(nums1, 3, new int[]{2,5,6}, 3);
        System.out.println(Arrays.toString(nums1));
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {

        int last = m + n -1;
        m--; n--;

        while(n>=0 && last>=0){
            if(m<0 || nums2[n] > nums1[m]){
                nums1[last--] = nums2[n--];
            }else{
                nums1[last--] = nums1[m--];
            }
        }
    }
}
