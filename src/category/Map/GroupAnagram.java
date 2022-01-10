package category.Map;

import java.util.*;

/**
 * https://leetcode.com/problems/group-anagrams/
 * Created by brianzhang on 11/24/18.
 */
public class GroupAnagram {

    public static void main(String[] args) {
        groupAnagramsUniqueHash(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}).forEach(a -> System.out.println(Arrays.toString(a.toArray())));
    }

    /**
     *  time complexity: O(NKlogK)
     *  The outer loop has complexity O(N) as we iterate through each string.
     *  Then, we sort each string in O(KlogK) time as Arrays.sort is O(KlogK), K is the maximum length of string
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return null;

        List<List<String>> res = new ArrayList();
        Map<String, List<String>> map = new HashMap();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sortedStr = new String(chars);
            List<String> temp = map.getOrDefault(sortedStr, new ArrayList<>());
            temp.add(str);
            map.put(sortedStr, temp);
        }

        res.addAll(map.values());
        return res;
    }

    // Solution-2: better performance
    public static List<List<String>> groupAnagramsUniqueHash(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] ca = new char[26];
            for (char c : s.toCharArray()) ca[c - 'a']++;
            String keyStr = String.valueOf(ca);
            if (!map.containsKey(keyStr)) map.put(keyStr, new ArrayList<>());
            map.get(keyStr).add(s);
        }
        return new ArrayList<>(map.values());
    }
}
