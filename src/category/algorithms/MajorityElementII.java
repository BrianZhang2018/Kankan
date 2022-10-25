package category.algorithms;

import java.util.*;

/**
 * https://leetcode.com/problems/majority-element-ii/
 * Created by brianzhang on 2/9/20.
 */
public class MajorityElementII {
    public static void main(String[] args) {
        for(int n: majorityElement(new int[]{2,2,1,1,1,2,2})){
            System.out.println(n);
        }
    }

    // there should be at most 2 different elements in nums more than n/3
    // so we can find at most 2 elements based on Boyer-Moore Majority Vote algo
    public static List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if(nums == null || nums.length ==0)
            return res;

        int count1=0, count2=0;
        int m1=0, m2=0;
        // no parallel if here, all is if ... else if ...
        for(int n : nums){
            if(m1 == n){
                count1++;
            }else if(m2 == n){
                count2++;
            }
            else if(count1 == 0){
                m1 = n;
                count1++;
            }else if(count2 == 0){
                m2 = n;
                count2++;
            }else{
                count1--;
                count2--;
            }
        }

        count1 = 0;
        count2 = 0;

        for(int n : nums){
            if(n == m1) count1++;
            else if(n == m2) count2++; //here is `else if` to avoid if m1 == m2 which are duplicate value
        }

        if(count1> nums.length/3) res.add(m1);
        if(count2> nums.length/3) res.add(m2);

        return res;
    }
}
