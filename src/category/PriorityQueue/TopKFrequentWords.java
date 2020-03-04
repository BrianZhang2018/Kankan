package category.PriorityQueue;

import java.util.*;

/**
 * https://leetcode.com/problems/top-k-frequent-words/
 *
 * Created by brianzhang on 1/29/19.
 */
public class TopKFrequentWords {

    public static void main(String[] args) {
        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        System.out.println(topKFrequent(words, 2));

        String test1 = "abc";
        String test2 = "aac";
        System.out.println(test1.compareTo(test2));

        PriorityQueue<Integer> tq = new PriorityQueue<>((i1, i2) -> i2-i1);
        tq.add(5);
        tq.add(1);
        tq.add(2);

        while(!tq.isEmpty()){
            System.out.println(tq.poll());
        }
    }

    public static List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> counter = new HashMap();
        for (String word : words) {
            counter.put(word, counter.getOrDefault(word, 0) + 1);
        }

        // min heap, so "counter.get(w1) - counter.get(w2)"
        PriorityQueue<String> heap = new PriorityQueue<>(
                (w1, w2) -> counter.get(w1) == counter.get(w2) ? w2.compareTo(w1) : counter.get(w1) - counter.get(w2));

        //pop out the word with low frequency
        for (String word : counter.keySet()) {
            heap.offer(word);
            if (heap.size() > k)
                heap.poll();
        }

        List<String> ans = new ArrayList();
        while (!heap.isEmpty())
            ans.add(heap.poll());

        Collections.reverse(ans);
        return ans;
    }
}
