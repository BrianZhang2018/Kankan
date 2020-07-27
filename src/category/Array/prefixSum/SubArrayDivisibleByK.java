package category.Array.prefixSum;

/**
 * https://leetcode.com/problems/subarray-sums-divisible-by-k/discuss/217980/Java-O(N)-with-HashMap-and-prefix-Sum
 *
 * prefix-sum of modulus, 对余数进行prefix-sum
 *
 * Created by brianzhang on 7/12/20.
 */
public class SubArrayDivisibleByK {

    // HashMap <余数, frequency>
    public int subarraysDivByK(int[] A, int K) {
        int[] map = new int[K];
        map[0] = 1;
        int count = 0, sum = 0;
        for(int a : A) {
            sum = (sum + a) % K;
            if(sum < 0) sum += K;  // Because -1 % 5 = -1, but we need the positive mod 4
            count += map[sum];
            map[sum]++;
        }
        return count;
    }
}
