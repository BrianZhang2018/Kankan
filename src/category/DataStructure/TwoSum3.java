package category.DataStructure;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by brianzhang on 10/26/18.
 */
public class TwoSum3 {

    HashMap<Integer, Integer> map = new HashMap();

    public void add(int number) {
        // write your code here
        map.put(number, map.getOrDefault(number, 0) + 1);
    }

    /**
     * @param value: An integer
     * @return: find if there exists any pair of numbers which sum is equal to the value.
     */
    public boolean find(int value) {
        // write your code here
        boolean res = false;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {

            int target = value - entry.getKey();
            if (map.containsKey(target)) {
                if (target == entry.getKey() && entry.getValue() >= 2) {
                    res = true;
                } else if (target != entry.getKey()) {
                    res = true;
                }
            }
        }
        return res;
    }

}
