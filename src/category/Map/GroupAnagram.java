package category.Map;

import java.util.*;

/**
 * https://leetcode.com/problems/group-anagrams/
 *
 * time complexity: O(NKlogK)
 * The outer loop has complexity O(N) as we iterate through each string.
 * Then, we sort each string in O(KlogK) time as Arrays.sort is O(KlogK), K is the maximum length of string
 *
 * Created by brianzhang on 11/24/18.
 */
public class GroupAnagram {

    public static void main(String[] args) {
        groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}).forEach(a -> System.out.println(Arrays.toString(a.toArray())));
    }

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
    public List<List<String>> groupAnagramsUniqueHash(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        Map<Integer, List<String>> map = new HashMap<>();

        for(String str: strs){

            // take the sum of all values and product of all values and add together basically creating a unique hash
            int sum = 1;
            int total = 0;
            for(int i=0; i < str.length(); i++){
                int intVal = str.charAt(i);
                sum = sum *intVal;
                total = total + intVal;
            }
            sum = sum + total;

            if(map.containsKey(sum)){
                map.get(sum).add(str);
            }else{
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(sum, list);
                result.add(list);
            }
        }
        return result;
    }
}
