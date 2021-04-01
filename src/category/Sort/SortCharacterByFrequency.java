package category.Sort;

import java.util.*;
/**
 * https://leetcode.com/problems/sort-characters-by-frequency/
 *
 * Created by brianzhang on 3/25/21.
 */
public class SortCharacterByFrequency {

    public static void main(String[] args) {
        System.out.println(frequencySort("tree"));
    }

    public static String frequencySort(String s) {
        if(s == null || s.length() == 0) return "";

        Map<Character, Integer> map = new HashMap();
        for(Character c : s.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) +1);
        }

        List<Map.Entry<Character, Integer>> list = new ArrayList(map.entrySet());
        Collections.sort(list, (o1, o2) -> o2.getValue()-o1.getValue());

        StringBuilder sb = new StringBuilder();
        for(Map.Entry<Character, Integer> entry : list){
            for(int i=0; i<entry.getValue(); i++)
                sb.append(entry.getKey());
        }

        return sb.toString();
    }
}
