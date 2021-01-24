package category.RightDataStructure.OoneProblems;

import java.util.*;
/**
 * https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/
 * 
 * Freq 2, compass, linkedin
 *
 * Created by brianzhang on 11/24/20.
 */
public class InsertDeleteRandom381 {
    Map<Integer, Set<Integer>> map;
    List<Integer> list;
    Random rand = new Random();

    /** Initialize your data structure here. */
    public InsertDeleteRandom381() {
        map = new HashMap();
        list = new ArrayList();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean contain = map.containsKey(val);
        if(!contain) {
            map.put(val, new LinkedHashSet<>());
        }
        map.get(val).add(list.size());
        list.add(val);
        return !contain;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val)) return false;

        int idx = map.get(val).iterator().next(); // return the first element from linkedHashSet
        map.get(val).remove(idx); // remove the first element in LinkedList structure is O(1)
        if(idx < list.size()-1){
            int lastElement = list.get(list.size()-1);
            list.set(idx, lastElement);
            map.get(lastElement).remove(list.size()-1);
            map.get(lastElement).add(idx);
        }
        list.remove(list.size()-1);
        if(map.get(val).isEmpty()) map.remove(val);
        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }
}
