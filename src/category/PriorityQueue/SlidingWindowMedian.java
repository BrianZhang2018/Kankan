package category.PriorityQueue;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/sliding-window-median/
 */
public class SlidingWindowMedian {
    public static void main(String[] args){
        SlidingWindowMedian test = new SlidingWindowMedian();
        double[] res = test.medianSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 4);
        for(double val : res){
            System.out.println(val);
        }
    }

    /**
     * e.g.
     *  left     right
     *   -1        1
     *   -3        3
     *  median = (left.peek() + right.peek()) / 2
     */
    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] result = new double[nums.length - k + 1];
        //left is the heap which store the nums that smaller than median, so median is on the top (odd nums)
        PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());
        //right is the heap which store the nums that greater than median, so one of median on the top (even nums)
        PriorityQueue<Integer> right = new PriorityQueue<>();
        
        for(int i = 0; i < nums.length; i++) {
            if(left.size() <= right.size()) { //so, finally, the left.size() - right.size() = 0 or 1
                right.add(nums[i]);
                left.add(right.remove());
            } else {
                left.add(nums[i]);
                right.add(left.remove());
            }
        
            if(left.size() + right.size() == k) {
                double median;
                if(left.size() == right.size()) {
                    median = (double) ((long)left.peek() + (long) right.peek()) / 2;
                } else {
                    median = (double) left.peek();
                }
                
                int start = i - k + 1;
                result[start] = median;
                if(!left.remove(nums[start])) {
                    right.remove(nums[start]);
                }
            }
        }
        return result;
    }
}