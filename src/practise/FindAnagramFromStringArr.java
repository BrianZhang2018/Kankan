package practise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by brianzhang on 11/24/18.
 */
public class FindAnagramFromStringArr {
    public static void main(String[] args) {
        System.out.println(find2("abc", new String[]{"bba", "aab", "cab", "bba", "cba", "aaa", "bac"}).size());
    }

    /**
     * DataStructure: HashMap<Character, Integer>
     */
    public static List<String> find(String target, String[] arr) {
        List<String> result = new ArrayList<>();

        if (target == null || target.length() == 0) {
            result.addAll(Arrays.asList(arr));
            return result;
        }

        if (arr.length == 0)
            return null;

        HashMap<Character, Integer> tarMap = new HashMap();
        for (Character c : target.toCharArray()) {
            if (tarMap.get(c) != null) {
                tarMap.put(c, tarMap.get(c) + 1);
            } else {
                tarMap.put(c, 1);
            }
        }

        for (String str : arr) {
            HashMap<Character, Integer> temp = new HashMap();
            for (Character c : str.toCharArray()) {
                if (tarMap.get(c) != null) {
                    tarMap.put(c, tarMap.get(c) + 1);
                } else {
                    tarMap.put(c, 1);
                }
            }

            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (tarMap.get(str.charAt(i)) == null || tarMap.get(c) != temp.get(c)) {
                    break;
                }
                if (i == str.length() - 1) {
                    result.add(str);
                }
            }
        }
        return result;
    }

    /**
     * DataStructure: int[]
     */
    public static List<String> find2(String target, String[] arr) {
        List<String> result = new ArrayList<>();

        if (target == null || target.length() == 0) {
            result.addAll(Arrays.asList(arr));
            return result;
        }

        if (arr.length == 0)
            return null;

        int[] tarArr = new int[26];
        fillArr(tarArr, target);

        for (String str : arr) {
            int[] temp = new int[26];
            fillArr(temp, str);

            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (tarArr[c - 'a'] == 0 || tarArr[c - 'a'] != temp[c - 'a']) {
                    break;
                }
                if (i == str.length() - 1) {
                    result.add(str);
                }
            }
        }
        return result;
    }

    public static void fillArr(int[] arr, String str) {
        for (Character c : str.toCharArray()) {
            List<Character> list;
            if (arr[c - 'a'] != 0) {
                arr[c - 'a']++;
            } else {
                arr[c - 'a'] = 1;
            }
        }
    }
}