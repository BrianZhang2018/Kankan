package category.PriorityQueue;

import java.util.*;

/**
 * https://leetcode.com/problems/top-k-frequent-words/
 *
 * Time complexity: NlogK
 * Created by brianzhang on 1/29/19.
 */
public class TopKFrequentWords {
    public static void main(String[] args) {
        String[] words = {"the", "day", "is", "sunny", "the", "the", "sunny", "is", "is"};
        System.out.println(topKFrequent(words, 2));
    }

    public static List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> counter = new HashMap();
        for (String word : words) {
            counter.put(word, counter.getOrDefault(word, 0) + 1);
        }
        // minHeap
        PriorityQueue<Map.Entry<String, Integer>> queue = new PriorityQueue<>(
                (w1, w2) -> w1.getValue() == w2.getValue() ?
                        w2.getKey().compareTo(w1.getKey()) :
                        w1.getValue() - w2.getValue());
        // pop out the word with low frequency
        for (Map.Entry<String, Integer> entry : counter.entrySet()) {
            queue.offer(entry);

            if (queue.size() > k) queue.poll();
        }

        List<String> ans = new ArrayList();
        while (!queue.isEmpty())
            ans.add(0, queue.poll().getKey());

        return ans;
    }
}
/**
 * Time Complexity detailed explanation for priority queue:
 *
 * In case of a max heap, we need to insert all the elements into the priority queue first and then pick the
 * topmost k elements ( as the max elements will always be at the top). Hence, the size of your queue will be N
 * and every subsequent insertion/removal will cost logN resulting in total cost of NlogN. On the other hand,
 * using a min queue requires you to maintain the queue size at 'K' as at any point in time you will remove the
 * top min element (with max elements always present at the bottom of the queue). So, eventually you would have traversed
 * all the n elements in the array and would have removed the min elements from the top of the queue , still maintaining
 * its size at K. Hence, every operation of removal/insertion then will be logK and for N elements the final
 * time complexity will be NlogK.
 * <p>
 * https://leetcode.com/problems/top-k-frequent-elements/discuss/81635/3-java-solution-using-array-maxheap-treemap/606153
 */