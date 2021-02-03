package category.PriorityQueue.differentAjacentCharacter;

import java.util.*;

/**
 *
 * Given a non-empty string s and an integer k, rearrange the string such that the same characters are at least distance k from each other.
 * All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".
 *
 * Input: s = "aabbcc", k = 3
 * Output: "abcabc"
 * Explanation: The same letters are at least distance 3 from each other.
 *
 * Created by brianzhang on 2/2/21.
 */
public class RearrangeStringKDistanceApart {

    public String rearrangeString(String s, int k) {
        Map<Character, Integer> count = new HashMap<>();

        for (char c : s.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Character> maxHeap = new PriorityQueue<>((a, b) -> count.get(b) - count.get(a));
        maxHeap.addAll(count.keySet());

        Queue<Character> wait = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        while(!maxHeap.isEmpty()) {
            Character current = maxHeap.poll();
            sb.append(current);
            count.put(current, count.get(current) - 1);
            wait.offer(current);

            if (wait.size() < k) {
                continue;
            }

            Character front = wait.poll();
            if (count.get(front) > 0) {
                maxHeap.offer(front);
            }
        }

        return sb.length() == s.length() ? sb.toString() :"";
    }
}
