package category.Array;

import java.util.Arrays;

/**
 * Merges two sorted arrays nums1 and nums2 into nums1 in-place, 
 * The algorithm merges from the end of the arrays to avoid overwriting elements in nums1, always placing the largest remaining value at the current last position.
 * 
 */
public class MergeSortedArray {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1,2,3,0,0,0};
        merge(nums1, 3, new int[]{2,5,6}, 3);
        System.out.println(Arrays.toString(nums1));
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int last = m + n - 1;
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
