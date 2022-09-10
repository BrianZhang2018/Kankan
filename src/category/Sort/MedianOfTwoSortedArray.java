package category.Sort;

/**
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 * Steps:
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
        int i = 0, j = 0, k = 0;
        while (i < nums1.length || j < nums2.length) {
            if (i == nums1.length) {
                res[k++] = nums2[j++];
            } else if (j == nums2.length) {
                res[k++] = nums1[i++];
            } else if (nums1[i] >= nums2[j]) {
                res[k++] = nums2[j++];
            } else {
                res[k++] = nums1[i++];
            }
        }

        // get median from merged array
        int n = res.length;
        return n % 2 == 0 ? (res[n/2] + res[n/2 - 1]) / 2.0 : res[n/2]; // be careful: "2.0" as denominator
    }
}