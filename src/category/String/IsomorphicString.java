package category.String;

import java.util.*;

/**
 * Created by brianzhang on 2/17/20.
 */
public class IsomorphicString {

    public static void main(String[] args) {
        System.out.println(isIsomorphic("egg", "add"));
    }

    public static boolean isIsomorphic(String s, String t) {
        if(s == null || t == null || s.length() != t.length())
            return false;

        if(s.length() == 0 && t.length()==0)
            return true;

        Map<Character, Integer> sMap = new HashMap();
        Map<Character, Integer> tMap = new HashMap();

        for(Integer i=0; i<s.length(); i++){
            Integer m1 = sMap.put(s.charAt(i), i);
            Integer m2 =  tMap.put(t.charAt(i), i);

            if(m1 != m2){
                return false;
            }

        }
        return true;
    }
}
