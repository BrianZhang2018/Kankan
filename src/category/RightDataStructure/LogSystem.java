package category.RightDataStructure;

import java.util.*;

/**
 * https://leetcode.com/problems/design-log-storage-system/
 *
 * Created by brianzhang on 2/8/21.
 */
public class LogSystem {
    public static void main(String[] args) {
        LogSystem ls = new LogSystem();
        ls.put(1, "2017:01:01:23:59:59");
        ls.put(2, "2017:01:01:22:59:59");
        ls.put(3, "2016:01:01:00:00:00");
        System.out.println(ls.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Year"));
        System.out.println(ls.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Hour"));
    }

    private String min, max;
    private HashMap<String, Integer> map;
    private TreeMap<String, LinkedList<Integer>> logs;

    public LogSystem() {
        min = "2000:01:01:00:00:00";
        max = "2017:12:31:23:59:59";
        map = new HashMap<>();
        map.put("Year", 4); // "2000.
        map.put("Month", 7); // "2000.12.
        map.put("Day", 10);
        map.put("Hour", 13);
        map.put("Minute", 16);
        map.put("Second", 19);
        logs = new TreeMap<>();
    }

    public void put(int id, String timestamp) {
        if(!logs.containsKey(timestamp))
            logs.put(timestamp, new LinkedList<>());

        logs.get(timestamp).add(id);
    }

    public List<Integer> retrieve(String s, String e, String gra) {
        int index = map.get(gra);
        String start = s.substring(0, index) + min.substring(index), end = e.substring(0, index) + max.substring(index);
        NavigableMap<String, LinkedList<Integer>> sub = logs.subMap(start, true, end, true);
        LinkedList<Integer> ans = new LinkedList<>();
        for (Map.Entry<String, LinkedList<Integer>> entry : sub.entrySet()) {
            ans.addAll(entry.getValue());
        }
        return ans;
    }
}
