package category.javabasic;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/**
 * https://leetcode.com/problems/sort-characters-by-frequency/
 */
public class SortHashMapByValue{
    public String frequencySort(String s) {
        if(s == null || s.length() == 0)
            return "";
        
        Map<Character, Integer> map = new HashMap();
        for(Character c : s.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) +1);
        }
        
        List<Map.Entry<Character, Integer>> list = new ArrayList<Map.Entry<Character, Integer>>(map.entrySet());

        
        Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
               return  o2.getValue().compareTo(o1.getValue());
            }
        });

        // Sort option - 2 
        // List<Map.Entry<Character, Integer>> list = new ArrayList(map.entrySet());
        // Comparator<Map.Entry<Character, Integer>> comparator = Comparator.comparing(Map.Entry::getValue);
        // Collections.sort(list, comparator.reversed());
        
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<Character, Integer> entry : list){
            for(int i=0; i<entry.getValue(); i++)
                sb.append(entry.getKey());
        }
        
        return sb.toString();
    }



}