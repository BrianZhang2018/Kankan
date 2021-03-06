package category.Stack;

import java.util.*;
import java.lang.*;

/**
 * https://leetcode.com/problems/decode-string/
 *
 * Cruise, Apple OA
 */
public class DecodeString{
    public static void main(String[] args){
        System.out.println(decodeStringBFS("3[a2[c]]"));
    }

    // Solution-1 : DFS
    public static String decodeStringDFS(String s) {
        // use a queue to manage the character which will remove the element in recursive
        Deque<Character> dq = new ArrayDeque<>();
        for(Character c : s.toCharArray()) dq.add(c);
        return dfsHelper(dq);
    }

    public static String dfsHelper(Deque<Character> dq) {
        StringBuilder sb = new StringBuilder();
        int num = 0;
        while(!dq.isEmpty()){
            char c = dq.poll();
            if(Character.isDigit(c)){
                num = num*10 + c - '0';
            }else if(c == '['){
                String sub = dfsHelper(dq);
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

    // Solution-2: two stacks - BFS
    public static String decodeStringBFS(String s) {
        Stack<Integer> numStack = new Stack<>();
        Stack<StringBuilder> strStack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        int k = 0;
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                k = k * 10 + ch - '0';  // for the case like "10[bc]"
            } else if (ch == '[') {
                numStack.push(k);
                strStack.push(sb);
                sb = new StringBuilder();
                k = 0;
            } else if (ch == ']') {
                StringBuilder prev = strStack.pop();
                int numOfRepeat = numStack.pop();
                for (int n = numOfRepeat; n > 0; --n)
                    prev.append(sb);
                
                sb = prev;
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}
