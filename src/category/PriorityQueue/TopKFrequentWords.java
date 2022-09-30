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
        // maxheap
        PriorityQueue<Map.Entry<String, Integer>> pq =
                new PriorityQueue<>((w1, w2) -> w1.getValue() == w2.getValue() ?
                                                w1.getKey().compareTo(w2.getKey()) :
                                                w2.getValue() - w1.getValue());

        pq.addAll(counter.entrySet());

        List<String> res = new ArrayList();
        while(k-- >0) {
            res.add(pq.poll().getKey());
        }
        return res;

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