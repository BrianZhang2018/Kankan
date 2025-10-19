package company;

import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Collections;

public class MinimizeCost {
    public static void main(String[] args) {
        System.out.println("=== Testing All Five Solutions ===\n");
        // Test case 1: Example from problem statement
        int[] test1 = new int[] { 25, 10, 20 };
        System.out.println("Test 1 - [25, 10, 20]:");
        System.out.println("  Solution 1 (PriorityQueue):    " + mizimizeCost(test1));
        System.out.println("  Solution 2 (ArrayList+Sort):   " + minimizeCostWithArrayList(test1));
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

    // Solution 2: Using ArrayList with sorting - O(n^2 log n)
    // Simpler but less efficient than PriorityQueue
    public static int minimizeCostWithArrayList(int[] array) {
        if (array.length <= 1)
            return 0;

        // Convert array to ArrayList for easier manipulation
        ArrayList<Integer> list = new ArrayList<>();
        for (int num : array) {
            list.add(num);
        }

        int totalCost = 0;
        while (list.size() > 1) {
            // Sort the list to find two smallest elements
            Collections.sort(list);

            // Take two smallest elements
            int first = list.remove(0);
            int second = list.remove(0);

            int cost = first + second;
            totalCost += cost;

            // Add combined cost back to list
            list.add(cost);
        }

        return totalCost;
    }
}
