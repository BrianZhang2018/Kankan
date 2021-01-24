package category.RightDataStructure.LRU;

import java.util.*;

/**
 * HashSet + LinkedList
 *
 * 这个就是简单的single val list的LRU
 * Created by brianzhang on 6/14/20.
 */
public class LRUSDKSolution {
    public static void main(String[] args) {
        Deque<Integer> test = new ArrayDeque<>();
        test.push(1);
        test.push(2);
        System.out.println(test.removeFirst());
    }

    private Deque<Integer> doublyQueue;
    private HashSet<Integer> hashSet;
    private final int CACHE_SIZE;

    LRUSDKSolution(int capacity) {
        doublyQueue = new LinkedList<>();
        hashSet = new HashSet<>();
        CACHE_SIZE = capacity;
    }

    public void add(int page) {
        if (!hashSet.contains(page)) {
            if (doublyQueue.size() == CACHE_SIZE) {
                int last = doublyQueue.removeLast();
                hashSet.remove(last);
            }
        }
        else {
            doublyQueue.remove(page);
        }
        doublyQueue.push(page); // similar with the stack push will put the element at the front of queue
        hashSet.add(page);
    }

    // display contents of cache
    public void display() {
        Iterator<Integer> itr = doublyQueue.iterator();
        while (itr.hasNext()) {
            System.out.print(itr.next() + " ");
        }
    }
}
