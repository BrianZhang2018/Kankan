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
        groupAnagrams(new String[] { "eat", "tea", "tan", "ate", "nat", "bat" })
                .forEach(a -> System.out.println(Arrays.toString(a.toArray())));
    }

    // sorting the string, O(nklogk), n is number of strings, k is the max length of
    // a string
    public List<List<String>> groupAnagramsBruteForce(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] ca = str.toCharArray();
            Arrays.sort(ca);
            String str1 = new String(ca);
            map.computeIfAbsent(str1, (k) -> new ArrayList<>()).add(str);
        }

        return new ArrayList<>(map.values());
    }

    // Instead of sorting, we can also build the key string in this way.
    // O(nk), n is number of strings, k is the max length of a string
    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0)
            return new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] ca = new char[26];
            for (char c : str.toCharArray())
                ca[c - 'a']++;
            String key = String.valueOf(ca);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(map.values());
    }
}
