package category.DataStructure;

import java.util.*;

/**
 * https://leetcode.com/problems/group-anagrams/
 *
 * Created by brianzhang on 11/24/18.
 */
public class GroupAnagram {

    public static void main(String[] args) {
        groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}).forEach(a -> System.out.println(Arrays.toString(a.toArray())));
    }

    public static List<List<String>> groupAnagrams(String[] strs) {

        if (strs == null || strs.length == 0)
            return null;

        List<List<String>> res = new ArrayList();
        Map<String, List<String>> map = new HashMap();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            List temp;
            String sortedStr = new String(chars);
            if (map.containsKey(sortedStr)) {
                temp = map.get(sortedStr);
            } else {
                temp = new ArrayList();
            }
            temp.add(str);
            map.put(sortedStr, temp);
        }

        res.addAll(map.values());
        return res;
    }
}
