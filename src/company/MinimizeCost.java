package company;

import java.util.PriorityQueue;

public class MinimizeCost {
    public static void main(String[] args) {
        int[] test1 = new int[] { 25, 10, 20 };
        System.out.println("  Solution 1 (PriorityQueue):    " + mizimizeCost(test1));
    }

    // Solution 1: Using PriorityQueue (Min-Heap) - O(n log n)
    public static int mizimizeCost(int[] array) {
        if (array.length <= 1)
            return 0;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : array) {
            minHeap.offer(num);
        }
        int totalCost = 0;
        while (minHeap.size() > 1) {
            int cost = minHeap.poll() + minHeap.poll();
            totalCost += cost;
            minHeap.add(cost);
        }

        return totalCost;
    }

}