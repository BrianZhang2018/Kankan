package category.Stack;

import java.util.*;
import java.lang.*;

/**
 * https://leetcode.com/problems/decode-string/
 *
 * Cruise OA
 */
public class DecodeString{
    public static void main(String[] args){
        DecodeString test = new DecodeString();
        System.out.println(test.decodeString("3[a]2[bc]"));
        System.out.println(test.decodeString("10[bc]")); // this is why do "k= k*10 +ch -'0'"
        System.out.println(test.decodeStringDFS("3[a]2[bc]"));
    }

    public String decodeStringDFS(String s) {
        // use a queue to pop character rather than loop a string to avoid append same character multiple times
        Deque<Character> dq = new ArrayDeque<>();
        for(Character c : s.toCharArray()) dq.add(c);
        return dfs(dq);
    }

    public String dfs(Deque<Character> dq) {
        int num = 0;
        StringBuilder sb = new StringBuilder();

        while(!dq.isEmpty()){
            char c = dq.pop(); // got character remove from queue
            if(Character.isDigit(c)){
                num = num*10 + c - '0';
            }else if(c == '['){
                String sub = dfs(dq);
                for(int j = num; j>0; j--){
                    sb.append(sub);
                }
                num=0;  // don't forget here, e.g. 3[a]2[b], when finish 3[a], we need reset num=0.
            }else if(c == ']'){
                break;
            }else{
                sb.append(c);
            }
        }

        return sb.toString();
    }

    // two stacks
    public String decodeString(String s) {
        Stack<Integer> numStack = new Stack<>();
        Stack<StringBuilder> strStack = new Stack<>();
        StringBuilder curr = new StringBuilder();

        int k = 0;
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                k = k * 10 + ch - '0';  // for the case like "10[bc]"
            } else if ( ch == '[') {
                numStack.push(k);
                strStack.push(curr);
                curr = new StringBuilder();
                k = 0;
            } else if (ch == ']') {
                StringBuilder prev = strStack.pop();
                for (k = numStack.pop(); k > 0; --k)
                    prev.append(curr);
                
                curr = prev;
            } else 
                curr.append(ch);
        }
        return curr.toString();
    }
}
