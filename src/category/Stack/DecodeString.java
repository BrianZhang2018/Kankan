package category.Stack;

import java.util.*;

/**
 * https://leetcode.com/problems/decode-string/
 *
 * Cruise, Apple OA
 */
public class DecodeString{
    public static void main(String[] args){
        System.out.println(decodeStringDFS("3[a]2[bc]")); // aaabcbc
        System.out.println(decodeStringBFS("3[a2[c]]")); // accaccacc
    }
    // Solution-1 : DFS
    // how to track the progress: 1.global index; 2.queue, popout the visited character
    static int index; // global index
    public static String decodeStringDFS(String s) {
        StringBuilder sb = new StringBuilder();
        int num = 0; // repeat time
        while (index < s.length()) {
            char c = s.charAt(index++);
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            } else if (c == '[') {
                String sub = decodeStringDFS(s); // pass the original string
                while(num-- >0){
                    sb.append(sub);
                }
                num = 0;
            } else if (c == ']') {
                break;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    // dfs-solution-2: queue to manage the character that pop out the visited character
    public static String decodeStringDFS1(String s) {
        Deque<Character> dq = new ArrayDeque<>();
        for(Character c : s.toCharArray()) dq.add(c);
        return dfsHelper(dq);
    }
    public static String dfsHelper(Deque<Character> dq) {
        StringBuilder sb = new StringBuilder();
        int num = 0; // repeat time
        while (!dq.isEmpty()) {
            char c = dq.poll();
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            } else if (c == '[') {
                String sub = dfsHelper(dq);
                while(num-- > 0) { // num got reset to 0
                    sb.append(sub);
                }
            } else if (c == ']') {
                return sb.toString();
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    // BFS Solution: two stacks - BFS
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
