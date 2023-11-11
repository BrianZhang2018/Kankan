package category.Map;

import java.util.*;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/group-shifted-strings/
 *
 * get an "offset" string for each input string element, and use map to collect the elements with same offset string
 *
 * e.g.  eg. "abc" -> "0,1,2,"  "am"->"0,12,"
 */
public class GroupShirtString {
    public static void main(String[] args) {
        groupStrings(new String[]{"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"}).forEach(System.out::println);
    }

    public static List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap();
        for (String str : strings) {
            String key = "";
            char first = str.charAt(0);
            for (int i = 0; i < str.length(); i++) {
                // calculate the offset string by minus the first character from each character in the string
                char c = str.charAt(i);
                key += (c - first < 0 ? c - first + 26 : c - first) + ",";
            }
            map.putIfAbsent(key, new ArrayList());
            map.get(key).add(str);
        }

        return map.values().stream().collect(Collectors.toList());
    }
}
