package category.DataStructure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brianzhang on 11/24/18.
 */
public class FindAllAnagramsFromAString {
    public static void main(String[] args) {
        System.out.println(findAnagrams("cbaaebabacd", "abc"));
    }

    public static List<Integer> findAnagrams(String s, String p) {
        char[] pstr = p.toCharArray();
        char[] sstr = s.toCharArray();
        int[] hash = new int[26];
        for (char c : pstr) {
            hash[c - 'a']++;
        }
        List<Integer> res = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < sstr.length; i++) {
            int index = sstr[i] - 'a';
            hash[index]--;
            //遇到一个新字符，先在对应位置上减掉

            while (hash[index] < 0) {//如果是有效的字符，就不会小于零，所以如果小于零就会破坏应该有的hash结构，要依次一路加回来，将start移到当前位置，因为有一个不是有效的就会破坏这一条链上的情况，所以整个中间都作废了
                hash[sstr[start] - 'a']++;
                start++;
            }

            if (i - start + 1 == pstr.length) {//如果此时这条链的长度和p一样，此时之前已经筛掉了hash中有小于0的情况，而如果还有hash值大于零，则不可能链的长度会根p一样，所以此时的hash已经都被抵消了
                res.add(start);
                hash[sstr[start] - 'a']++;
                start++;
            }
        }
        return res;
    }
}
