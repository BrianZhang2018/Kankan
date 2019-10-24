package category.javabasic.stack;

import java.util.*;
import java.lang.*;

/**
 * Cruise
 * https://leetcode.com/problems/decode-string/
 */
public class DecodeString{
    public static void main(String[] args){
        DecodeString test = new DecodeString();
        System.out.println(test.decodeString("3[a]2[bc]"));
        System.out.println(test.decodeString("10[bc]")); // this is why we do " k= k*10 +ch -'0' "
    }

    public String decodeString(String s) {
        Stack<Integer> numStack = new Stack<>();
        Stack<StringBuilder> strStack = new Stack<>();
        StringBuilder cur = new StringBuilder();
        int k = 0;
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                k = k * 10 + ch - '0';  // e.g. 10[bc]
            } else if ( ch == '[') {
                numStack.push(k);
                strStack.push(cur);
                cur = new StringBuilder();
                k = 0;
            } else if (ch == ']') {
                StringBuilder pre = strStack.pop();
                for (k = numStack.pop(); k > 0; --k) 
                    pre.append(cur);
                
                cur = pre;
            } else 
                cur.append(ch);
        }
        return cur.toString();
    }

}
