package category.ImplDataStructure.OoneProblems;

import java.util.*;

/**
 * https://leetcode.com/problems/insert-delete-getrandom-o1/
 *
 * Freq 2, compass, linkedin
 *
 * follow up: allow duplicate number in the list
 *
 * Created by brianzhang on 11/30/20.
 */
public class InsertDeleteGetRandom {

    public static void main(String[] args) {}

    Map<Integer, Integer> map = new HashMap(); // <element, position in list>
    List<Integer> list = new ArrayList();   // store the elements

    public InsertDeleteGetRandom() {}

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(map.containsKey(val)){
            return false;
        }else{
            map.put(val, list.size());
            list.add(val);
            return true;
        }
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    // the idea is move the "last element" in the list to the removal element's position
    public boolean remove(int val) {
        if(!map.containsKey(val))
            return false;
        else{
            int index = map.get(val); //  get location of val in list
            int lastNum = list.get(list.size()-1);
            list.set(index, lastNum); // override the number with last number in the list
            map.put(lastNum, index);
            // remove the val from map and list
            map.remove(val);
            list.remove(list.size()-1);
            return true;
        }
    }

    /** Get a random element from the set. */
    public int getRandom() {
        Random r = new Random();
        return list.get(r.nextInt(list.size()));
    }
}
