package category.Map.treeMap;

import java.util.*;

/**
 * https://leetcode.com/problems/time-based-key-value-store/
 *
 * Created by brianzhang on 2/9/21.
 */
public class TimeBasedKeyValueStore {
    public static void main(String[] args) {
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(1, "aa");
        map.put(2, "bb");
        System.out.println(map.floorKey(2));
        System.out.println(map.ceilingKey(2));
    }
    Map<String, TreeMap<Integer, String>> map;

    /** Initialize your data structure here. */
    public TimeBasedKeyValueStore() {
        map = new HashMap();
    }

    public void set(String key, String value, int timestamp) {
        if(!map.containsKey(key)){
            map.put(key, new TreeMap());
        }

        map.get(key).put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        if(!map.containsKey(key)) return "";

        TreeMap<Integer, String> tm = map.get(key);
        Integer t = tm.floorKey(timestamp);
        return t != null ? tm.get(t) : "";
    }
}
