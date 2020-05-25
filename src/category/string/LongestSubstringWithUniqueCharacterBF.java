package category.string;

import java.util.*;

/**
 * Brute force solution
 * Created by brianzhang on 3/3/19.
 */
public class LongestSubstringWithUniqueCharacterBF {

    public static void main(String[] args) {
        lengthOfLongestSubstringKDistinct("nutdrgzdrkrvfdfcvzuunxwlzegjukhkjpvqruitobiahxhgdrpqttsebjsg", 11);

        HashSet<Character> set = new HashSet<>();
        String s = "zegjukhkjpvqruitobiahxhgdrpqttsebjsg";
        for(Character c : s.toCharArray())
            set.add(c);

        System.out.println(set.size());
    }

    public static void lengthOfLongestSubstringKDistinct(String s, int k) {
        List<String> substrings = new ArrayList<String>();
        Map<Integer, List<String>> map = new HashMap<>();

        //get all the substring
        for(int i=0; i< s.length(); i++) {
            for(int j = i+k+1; j<=s.length(); j++) {
                substrings.add(s.substring(i, j));
            }
        }

        int max = -1;
        for(String str : substrings){
            if(getUniqueCharacter(str) == k){
                int l = str.length();
                List<String> curr = map.getOrDefault(l, new ArrayList<>());
                curr.add(str);
                map.put(l, curr);

                if(max < str.length())
                    max = str.length();
            }
        }

        for(String str : map.get(max)){
            System.out.println(str);
        }
    }

    public static int getUniqueCharacter(String str){
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int currIndex = 0;
        while(currIndex<str.length()) {
            char c = str.charAt(currIndex);
            if (!map.containsKey(c)) {
                map.put(c, 1);
            }
            currIndex++;
        }
        return map.size();
    }
}
