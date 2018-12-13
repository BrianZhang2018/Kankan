package category.DataStructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by brianzhang on 11/24/18.
 */
public class GroupAnagram {

    public static void main(String[] args) {
        System.out.println(groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}).size());
    }

    public static List<List<String>> groupAnagrams(String[] strs) {

        if (strs == null || strs.length == 0)
            return null;

        List<List<String>> res = new ArrayList();
        HashMap<String, List<String>> map = new HashMap();
        for (String str : strs) {
            char[] charArr = str.toCharArray();
            Arrays.sort(charArr);
            List temp;
            if (map.get(String.valueOf(charArr)) != null) {
                temp = map.get(String.valueOf(charArr));
            } else {
                temp = new ArrayList();
            }
            temp.add(str);
            map.put(String.valueOf(charArr), temp);
        }

        res.addAll(map.values());
        return res;
    }
}
