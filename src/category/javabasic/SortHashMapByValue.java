package category.javabasic;

import java.util.*;

/**
 *
 * https://leetcode.com/problems/sort-characters-by-frequency/
 */
public class SortHashMapByValue {

    public static void main(String[] args) {
        Map<Integer, List<String>> map = new HashMap<>();
        map.put(1, Arrays.asList("aa", "b", "c"));
        map.put(2, Arrays.asList("aa", "b"));

        List<Map.Entry<Integer, List<String>>> list = new ArrayList(map.entrySet());
        Collections.sort(list, (o1, o2) -> o2.getValue().size() - o1.getValue().size());

        for (Map.Entry<Integer, List<String>> entry : list) {
            System.out.println(entry.getKey());
            System.out.println(Arrays.toString(entry.getValue().toArray()));
        }

        System.out.println(frequencySort("tree"));
    }

    public static String frequencySort(String s) {
        if (s == null || s.length() == 0)
            return "";

        Map<Character, Integer> map = new HashMap();
        for (Character c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        // Sort option - 2
        // List<Map.Entry<Character, Integer>> list = new ArrayList(map.entrySet());
        // Comparator<Map.Entry<Character, Integer>> comparator = Comparator.comparing(Map.Entry::getValue);
        // Collections.sort(list, comparator.reversed());

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : list) {
            for (int i = 0; i < entry.getValue(); i++)
                sb.append(entry.getKey());
        }

        return sb.toString();
    }
}