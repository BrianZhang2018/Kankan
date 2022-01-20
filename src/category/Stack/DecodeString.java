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
        System.out.println(decodeStringDFS("3[a]2[bc]"));
        System.out.println(decodeStringBFS("3[a2[c]]"));
    }

    // Solution-1 : DFS
    public static String decodeStringDFS(String s) {
        Deque<Character> dq = new ArrayDeque<>(); // use a queue to manage the character which will be removed after visited
        for(Character c : s.toCharArray()) dq.add(c);
        return dfsHelper(dq);
    }

    public static String dfsHelper(Deque<Character> dq) {
        StringBuilder sb = new StringBuilder();
        int num = 0; // repeat times
        while(!dq.isEmpty()){
            char c = dq.poll();
            if(Character.isDigit(c)){
                num = num*10 + c - '0';
            }else if(c == '['){
                String sub = dfsHelper(dq);
                for(int j = num; j>0; j--){
                    sb.append(sub);
                }
                num=0;  // don't forget here, reset repeat to 0.
            }else if(c == ']'){
                return sb.toString();
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
        int num = 0;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';  // for the case like "10[bc]"
            } else if (c == '[') {
                numStack.push(num);
                strStack.push(sb);
                sb = new StringBuilder();
                num = 0;
            } else if (c == ']') {
                StringBuilder prev = strStack.pop();
                int numOfRepeat = numStack.pop();
                for (int n = numOfRepeat; n > 0; --n){
                    prev.append(sb);
                }
                sb = prev;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
