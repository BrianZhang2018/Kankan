package category.javabasic;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by brianzhang on 3/9/19.
 */
public class NumberToHexDecimal {

    public static void main(String[] args) {
        //System.out.println(toHex(26));
        System.out.println(Math.sqrt(9));
        System.out.println(Math.round(Math.sqrt(10)));
        Set<String> set = new HashSet<String>();
        //set.contains()

    }

    static char[] map = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
    public static String toHex(int num) {

        String res = "";
        if(num == 0)
            return "0";

        while(num != 0){
            res = map[num&15] + res;
            num = num>>>4;
        }

        return res;
    }
}
