package category.linkedList;

import java.util.*;

/**
 * https://leetcode.com/problems/shortest-word-distance-ii/
 *
 * Created by brianzhang on 6/7/21.
 */
public class ShortestWordDistanceII {

    public static void main(String[] args) {
        ShortestWordDistanceII swd = new ShortestWordDistanceII(new String[]{"practice", "makes", "perfect", "coding", "makes"});
        System.out.println(swd.shortest("coding", "practice"));
    }

    Map<String, List<Integer>> map = new HashMap();

    public ShortestWordDistanceII(String[] wordsDict) {
        int i=0;
        for(String str : wordsDict) {
            map.putIfAbsent(str, new ArrayList());
            map.get(str).add(i++);
        }

        System.out.println(map);
    }

    public int shortest(String word1, String word2) {

        List<Integer> l1 = map.get(word1);
        List<Integer> l2 = map.get(word2);

        int i=0, j=0;
        int min = Integer.MAX_VALUE;
        while(i<l1.size() && j<l2.size()) {
            int m = l1.get(i), n = l2.get(j);
            min = Math.min(min, Math.abs(m-n));

            if(m < n){
                i++;
            }else{
                j++;
            }
        }

        return min;
    }
}
