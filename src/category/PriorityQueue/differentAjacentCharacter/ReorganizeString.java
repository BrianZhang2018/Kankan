package category.PriorityQueue.differentAjacentCharacter;

import java.util.*;

/**
 * https://leetcode.com/problems/reorganize-string/
 * CountMap + PQ(MaxHead) + Queue
 *
 * this question is the simple variation of RearrangeStringKDistanceApart
 * which you can translate the problem to the "same" letters are at least distance "2" from each other.
 *
 * Created by brianzhang on 2/1/21.
 */
public class ReorganizeString {
    public static void main(String[] args) {
        System.out.println(reorganizeString("aaabcd"));
    }

    public static String reorganizeString(String S) {
        Map<Character, Integer> countMap = new HashMap<>();
        for (char c : S.toCharArray()) {
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }
        PriorityQueue<Character> maxHeap = new PriorityQueue<>((a, b) -> countMap.get(b) - countMap.get(a));
        maxHeap.addAll(countMap.keySet());

        Queue<Character> wait = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            char c = maxHeap.poll();
            sb.append(c);
            countMap.put(c, countMap.get(c) - 1);
            wait.add(c);

            if (wait.size() < 2) { // "2" since 2 different adjacent characters
                continue;
            }

            char front = wait.poll();
            if (countMap.get(front) > 0) {
                maxHeap.offer(front);
            }
        }

        return sb.length() == S.length() ? sb.toString() : "";
    }
}
