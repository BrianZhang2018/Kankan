package category.javabasic.stack;

import java.util.*;
import java.lang.*;

public class DecodeString{
    public static void main(String[] args){
        DecodeString test = new DecodeString();
        System.out.println(test.decodeString("3[a]2[bc]"));
        System.out.println(test.decodeString("10[bc]")); // why we do " k= k*10 +ch -'0' "
    }

    public String decodeString(String s) {
        Stack<Integer> intStack = new Stack<>();
        Stack<StringBuilder> strStack = new Stack<>();
        StringBuilder cur = new StringBuilder();
        int k = 0;
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                k = k*10 + ch - '0';
            } else if ( ch == '[') {
                intStack.push(k);
                strStack.push(cur);
                cur = new StringBuilder();
                k = 0;
            } else if (ch == ']') {
                StringBuilder tmp = cur;
                cur = strStack.pop();
                for (k = intStack.pop(); k > 0; --k) cur.append(tmp);
            } else cur.append(ch);
        }
        return cur.toString();
    }

}
