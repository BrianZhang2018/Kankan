package companies.amazon;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * HashMap + LinkedList
 * <p>
 * Created by brianzhang on 6/14/20.
 */
public class LRU3 {

    public static void main(String[] args) {
        LinkedList<Integer> ll = new LinkedList<>();
        System.out.println(ll.size());
    }

    private List<Integer> list = new ArrayList<>();
    private int cacheSize;
    private LinkedList<Integer> ll = new LinkedList<>();

    public LRU3(int capacity) {
        this.cacheSize = capacity;
    }

    /*public int get(int key) {

    }*/

    public void put(int key, int value) {
        if (list.contains(key)) {
            int val = ll.get(key);
            ll.remove(key);
            ll.add(0, val);

        }

    }
}
