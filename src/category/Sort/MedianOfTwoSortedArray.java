package category.Sort;

/**
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 *
 * 1. Merge two sorted array
 * 2. Get the median from merged array
 */
public class MedianOfTwoSortedArray {

    public static void main(String[] args) {
        System.out.println(findMedianSortedArrays(new int[]{1, 2}, new int[]{3,4}));
        System.out.println(findMedianSortedArrays(new int[]{1, 2}, new int[]{3,4,5}));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // merge two sorted array
        int[] res = new int[nums1.length + nums2.length];
        int i=0, j=0, k=0;
        while(i < nums1.length && j < nums2.length) {
            if(nums1[i] >= nums2[j]) {
                res[k] = nums2[j];
                k++; j++;
            }
            else {
                res[k] = nums1[i];
                k++; i++;
            }
        }

        if(i < nums1.length) {
            while(i < nums1.length) {
                res[k] = nums1[i];
                k++;i++;
            }
        }

        if(j < nums2.length) {
            while(j < nums2.length) {
                res[k] = nums2[j];
                k++;j++;
            }
        }

        // get median from merged array
        int n = res.length;
        int mid = n/2;
        if(n % 2 == 0) return (res[mid]+res[mid-1])/2.0;
        else return res[mid];
    }
}