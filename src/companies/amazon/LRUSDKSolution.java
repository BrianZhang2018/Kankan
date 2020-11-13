package companies.amazon;

import java.util.*;

/**
 * HashSet + LinkedList
 *
 * Created by brianzhang on 6/14/20.
 */
public class LRUSDKSolution {
    
    public static void main(String[] args) {}

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
        doublyQueue.push(page);
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
