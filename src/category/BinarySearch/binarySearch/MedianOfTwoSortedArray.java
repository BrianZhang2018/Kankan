package category.BinarySearch.binarySearch;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 *
 * Intuition: Merge two sorted array
 */
public class MedianOfTwoSortedArray{

    public static void main(String[] args) {
        MedianOfTwoSortedArray test = new MedianOfTwoSortedArray();
        System.out.println(test.findMedianSortedArrays(new int[]{1, 2}, new int[]{3,4}));

        Map<Integer, Integer> map = new HashMap<>();
        System.out.println(map.put(1,10));
        System.out.println(map.put(1,3));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length+nums2.length];
        int i=0, j=0, k=0;
        while(i < nums1.length && j < nums2.length) {
            if(nums1[i] >= nums2[j]) {
                res[k] = nums2[j];
                k++; j++;
            }
            else {
                res[k] = nums1[i];
                k++;
                i++;
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
        int n = res.length;
        int mid = n/2;
        if(n % 2 == 0)
            return (res[mid]+res[mid-1])/2.0;
        else
            return res[mid];
    }
}