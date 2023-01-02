package category.Array;

import java.util.*;
/**
 * https://leetcode.com/problems/intersection-of-two-arrays-ii/description/
 *
 * 问题想清楚了，code也就出来了
 */
public class IntersectionOfTwoArraysII {
    public static void main(String[] args) {
        System.out.println(intersect(new int[]{4,9,5}, new int[]{9,4,9,8,4}));
    }

    public static int[] intersect(int[] nums1, int[] nums2) {
        if(nums1.length < nums2.length) return intersect(nums2, nums1);
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> res = new ArrayList<>();
        int i=0, j =0;
        while(i < nums1.length && j < nums2.length) {
            if(nums1[i] == nums2[j]) {
                res.add(nums1[i]);
                i++;j++;
            } else if(nums1[i] > nums2[j]) {
                j++;
            } else if(nums1[i] < nums2[j]){
                i++;
            }
        }

        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}
