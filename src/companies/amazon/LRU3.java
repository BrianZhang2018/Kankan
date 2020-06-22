package companies.amazon;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * HashMap + LinkedList
 *
 * Created by brianzhang on 6/14/20.
 */
public class LRU3 {

    private Map<Integer, LinkedList<Integer>> map = new HashMap<>();
    private int cacheSize;
    private LinkedList start, end;


    public LRU3(int capacity) {
        this.cacheSize = capacity;
    }

    /*public int get(int key) {

    }*/

    public void put(int key, int value) {
        if(map.containsKey(key)){
            LinkedList<Integer> ll = map.get(key);
            ll.remove(key);
            ll.add(key);



            //if(map.s)
        }

    }
}
