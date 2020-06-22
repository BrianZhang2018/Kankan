package category.HashMap;

import java.util.*;

/**
 * Analyse log problem:
 *
 * 1. count
 * 2. sort
 *
 * Postmates, Rally
 * Sort by value is not straightforward for map, so use the customized object with collections (pq)
 * Created by brianzhang on 6/21/20.
 */
public class MapSortByValue {

    public static void main(String[] args) {
        // easy way = counterMap with customized object + bq
        TreeMap<String, ServerLog> countMap = new TreeMap();
        countMap.put("333", new ServerLog("333", 1, 1));
        countMap.put("222", new ServerLog("222", 1, 1));
        countMap.put("111", new ServerLog("111", 2, 1));

        // use pq to sort
        PriorityQueue<ServerLog> pq = new PriorityQueue<>((a, b) -> {
            if(a.freq == b.freq){
                return b.fileName.compareTo(a.fileName); //reverse order
            }else{
                return b.freq - a.freq; // reverse order
            }
        });

        for(Map.Entry<String, ServerLog> entry : countMap.entrySet()){
            pq.add(entry.getValue());
        }
        //countMap.entrySet().stream().forEach(a -> pq.add(a.getValue())); //alternative way

        while(!pq.isEmpty()){
            System.out.println(pq.poll().fileName);
        }

        // sdk way - complicated
/*
        TreeMap<String, ServerLog> tm = new TreeMap();
        tm.put("222", new ServerLog("222", 1, 1));
        tm.put("111", new ServerLog("111", 1, 1));

        System.out.println("Before");
        for(Map.Entry<String, ServerLog> entry: tm.entrySet())
            System.out.println(entry.getKey() + "  " + entry.getValue());

        System.out.println("After");
        for(Map.Entry<String, ServerLog> entry: sortByValue(tm).entrySet())
            System.out.println(entry.getKey() + "  " + entry.getValue());
*/

    }

/*    public static Map<String, ServerLog> sortByValue(Map<String, ServerLog> unsortMap) {

        List<Map.Entry<String, ServerLog>> list = new LinkedList<>(unsortMap.entrySet());

        Collections.sort(list, (a, b) -> a.getValue().compareTo(b.getValue()));

        Map<String, ServerLog> result = new LinkedHashMap<>();
        for (Map.Entry<String, ServerLog> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }*/

}

class ServerLog implements Comparable<ServerLog>{
    String fileName;
    int freq;
    int byteAmount;

    public ServerLog(String fn, int freq, int byteAmount){
        this.fileName = fn;
        this.freq = freq;
        this.byteAmount = byteAmount;
    }

    @Override  // is used by sdk way
    public int compareTo(ServerLog o) {
        return o.fileName.compareTo(this.fileName);
    }
}
