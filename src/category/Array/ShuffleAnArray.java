package category.Array;

import java.util.Arrays;
import java.util.Random;

/**
 * https://leetcode.com/problems/shuffle-an-array/
 *
 * 要看solution里的动图解释
 *
 * discussion里的解释：
 * Suppose that len(nums) = n.
 Algorithm:
 ○ for i in 0 ... n - 1: swap(nums[i], nums[random.randint(i, n - 1)]). # uniform sampling in the closed interval [i, n - 1]

 Proof:
 ○ To generate one sequence, there are n ways of generating the first number, n - 1 ways of generating the second number, n - 2 ways of generating the third number, ..., 1 way of generating the last number. Propability for any particular sequence is (1/n) * (1/(n - 1)) * (1/(n - 2)) * ... * 1/1 = 1/n!.
 ○ Any generated sequence must be of the same probability 1/n! In fact, we also know that there are n! possible sequences (permutations).
 *
 * Created by brianzhang on 6/14/20.
 */
public class ShuffleAnArray {

    public static void main(String[] args) {
        ShuffleAnArray test = new ShuffleAnArray(new int[]{1,2,3});
        System.out.println(Arrays.toString(test.shuffle()));
    }

    private int[] nums;
    private Random rand;

    public ShuffleAnArray(int[] nums) {
        this.nums = nums;
        rand = new Random();
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return nums;
    }

    // 容易理解的版本
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        if(nums == null || nums.length<2)
            return nums;

        int[] copy = nums.clone();
        for(int i=0; i<copy.length; i++){
            int r = rand.nextInt(i + 1);
            int temp = copy[r];
            copy[r] = copy[i];
            copy[i] = temp;
        }

        return copy;
    }

    /** Simple Version **/
    /*
    int[] nums;
    public ShuffleAnArray(int[] nums) {
        this.nums = nums;
    }

    public int[] reset() {
        return nums;
    }

    public int[] shuffle() {
        if(nums == null || nums.length<2)
            return nums;

        int[] rand = new int[nums.length];
        for(int i=0; i<nums.length; i++){
            int r = (int)(Math.random() * (i+1));
            rand[i] = rand[r];
            rand[r] = nums[i];
        }

        return rand;
    }*/
}
