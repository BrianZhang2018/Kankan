package category.string;

import java.util.Arrays;
/**
 * Created by brianzhang on 8/12/18.
 *
 * Dynamic programming
 *
 */
public class LongestCommonPrefix {
    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{"flower","flow","flight"}));
    }

    public static String longestCommonPrefix(String[] strs) {
        
        if(strs == null || strs.length == 0)
            return "";
        String res = "";
        
        //character
        for(int i=0; i<strs[0].length(); i++){
            //loop string in strs
            for(int j=0; j<strs.length-1; j++){

                 if(i >= strs[j].length() || i+1 >= strs[j+1].length())
                    return strs[0].substring(0, i);
                 if(strs[j].charAt(i) == strs[j+1].charAt(i)){
                    continue;
                 }else{
                    return strs[0].substring(0, i);
                 }
            }
            res = strs[0].substring(0, i+1);
        }
        
        return res;
    }
}