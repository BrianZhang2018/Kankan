package category.Map.treeMap;

import java.util.*;
/**
 * https://leetcode.com/problems/tweet-counts-per-frequency/discuss/503483/Java-simple-tree-mapeasy-to-understand
 *
 * Created by brianzhang on 2/10/21.
 */
public class TweetCounts {
    public static void main(String[] args) {
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        tm.put(1,1);
        tm.put(2,2);
        tm.put(3,3);
        tm.put(4,4);
        SortedMap<Integer, Integer> nm = tm.subMap(1,3);

        //System.out.println(tm.ceilingKey(2));

        for(Map.Entry<Integer, Integer> entry : tm.entrySet()){
            System.out.println(entry.getKey());
        }
    }

    Map<String, TreeMap<Integer, Integer>> map = new HashMap();
    public TweetCounts() {}

    public void recordTweet(String tweetName, int time) {
        map.putIfAbsent(tweetName, new TreeMap());
        TreeMap<Integer, Integer> tm = map.get(tweetName);
        tm.put(time, tm.getOrDefault(time, 0) + 1);
    }

    public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
        List<Integer> res = new ArrayList();
        if(!map.containsKey(tweetName)) return res;
        TreeMap<Integer, Integer> tm = map.get(tweetName);

        int gap = 0;
        if(freq.equals("minute")) gap = 60;
        else if(freq.equals("hour")) gap = 3600;
        else if(freq.equals("day")) gap = 24*3600;

        // calculate num of tweets in each interval
        for(int start=startTime; start<endTime+1; start+=gap){
            int sum = 0;
            int end = Math.min(start+gap, endTime+1);
            Map<Integer, Integer> sm = tm.subMap(start, end);
            for(int val : sm.values()) sum+= val;

            res.add(sum);
        }

        return res;
    }
}
