package category.BinarySearch;

import java.util.Random;

/**
 * https://leetcode.com/problems/random-pick-with-weight/discuss/154044/Java-accumulated-freq-sum-and-binary-search
 *
 * Created by brianzhang on 11/3/19.
 */
public class RandomPickWithWeight {

    public static void main(String[] args) {
        RandomPickWithWeight test = new RandomPickWithWeight(new int[]{2,5,3,4});
        System.out.println(test.pickIndex());
        System.out.println(test.pickIndex());
    }

    Random random = new Random();
    int[] prefixSum; // prefix sum for the weight of indexes

    public RandomPickWithWeight(int[] w) {
        // accumulate the the array to get a proportion range for each index - prefix sum
        for(int i=1; i< w.length; i++){
            w[i] = w[i] + w[i-1];
        }
        this.prefixSum = w;
    }

    // time complexity: logN
    public int pickIndex() {
        int idx = random.nextInt(prefixSum[prefixSum.length-1]) + 1;
        int left = 0, right = prefixSum.length - 1;

        while(left < right){
            int mid = left + (right - left)/2;
            if(prefixSum[mid] == idx) return mid;

            if(idx < prefixSum[mid]){
                right = mid;
            }else{
                left = mid +1;
            }
        }

        return left;
    }
}
