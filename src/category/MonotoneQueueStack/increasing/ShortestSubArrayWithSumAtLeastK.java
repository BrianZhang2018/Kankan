package category.MonotoneQueueStack.increasing;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/discuss/204290/Monotonic-Queue-Summary
 * Maintain a "monoqueue" of indices of P: a deque of indices x_0, x_1, ... such that P[x_0], P[x_1], ... is increasing.
 * 单调递增queue:
 * 找到两个indexes(i, j) which corresponding prefixSum之间的大于K(prefixSum[j]-prefixSum[j]>=K)
 * remove the previous (x1) value which greater than current (x2) from queue as lager number(Y) which Y-X2 > Y-X1,
 * 所以Y-X2可以去到更短的subArray
 *
 * 套路：
 * 1. maintain an increasing queue which store the indexes, not the value
 *
 * Created by brianzhang on 4/12/20.
 */
public class ShortestSubArrayWithSumAtLeastK {

    public static void main(String[] args) {
        // System.out.println(shortestSubArray(new int[]{1,-1,2,1}, 3));
        System.out.println(shortestSubArray(new int[]{2,-1,2}, 3));
    }

    public static int shortestSubArray(int[] A, int K) {

        int n = A.length;
        int ans = n + 1;
        int[] prefixSum = new int[n+1];

        for(int i=0; i< n; i++){
            prefixSum[i+1] = prefixSum[i] + A[i];
        }

        Deque<Integer> queue = new ArrayDeque<>();
        for(int i=0; i<prefixSum.length; i++){

            while(!queue.isEmpty() && prefixSum[i] <= prefixSum[queue.getLast()]){
                queue.removeLast();
            }

            while(!queue.isEmpty() && prefixSum[i] - prefixSum[queue.getFirst()] >= K){
                ans = Math.min(ans, i - queue.removeFirst());
            }
            // store the index of array
            queue.addLast(i);
        }

        return ans <= n ? ans : -1;
    }
}
