package category.Map;

import java.util.*;

/**
 * https://leetcode.com/problems/group-anagrams/
 *
 * kind of similar with
 * Created by brianzhang on 11/24/18.
 */
public class GroupAnagram {
    public static void main(String[] args) {
        groupAnagramsUniqueHash(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"})
                .forEach(a -> System.out.println(Arrays.toString(a.toArray())));

        char[] cs = new char[22];
        System.out.println(cs[0]++);
        System.out.println(cs[0]++);
        System.out.println(cs[0]);
        System.out.println(cs);
    }

    public static List<List<String>> groupAnagramsUniqueHash(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] ca = new char[26];
            for (char c : str.toCharArray()) {
                ca[c - 'a']++;
            }
            String keyStr = String.valueOf(ca);
            System.out.println(keyStr);
            map.putIfAbsent(keyStr, new ArrayList<>());
            map.get(keyStr).add(str);
        }
        return new ArrayList<>(map.values());
    }
}
