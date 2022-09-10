package category.PriorityQueue.differentAjacentCharacter;

import java.util.*;

/**
 * https://leetcode.com/problems/rearrange-string-k-distance-apart/
 *
 * Given a non-empty string s and an integer k, rearrange the string such that the same characters are at least distance k from each other.
 *
 * Input: s = "aabbcc", k = 3
 * Output: "abcabc"
 * Explanation: The "same" letters are at least distance 3 from each other.
 *
 * Created by brianzhang on 2/2/21.
 */
public class RearrangeStringKDistanceApart {

    public String rearrangeString(String s, int k) {
        Map<Character, Integer> countMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }
        PriorityQueue<Character> maxHeap = new PriorityQueue<>((a, b) -> countMap.get(b) - countMap.get(a));
        maxHeap.addAll(countMap.keySet());

        Queue<Character> wait = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        while(!maxHeap.isEmpty()) {
            Character c = maxHeap.poll();
            sb.append(c);
            countMap.put(c, countMap.get(c) - 1);
            wait.offer(c);

            if (wait.size() < k) { // k
                continue;
            }

            Character front = wait.poll();
            if (countMap.get(front) > 0) {
                maxHeap.offer(front);
            }
        }

        return sb.length() == s.length() ? sb.toString() :"";
    }
}
