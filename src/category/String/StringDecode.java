package category.String;

import java.util.*;

/**
 * Created by brianzhang on 3/11/21.
 */
public class StringDecode {

    public static void main(String[] args) {
        System.out.println(decodeString("a1b2c3")); // abbccc
        System.out.println(decodeString2("2[a2[bc]]")); // abcbcabcbc
    }

    // Decode: Aa1b2c2x -> Aabbccx
    public static String decodeString(String str) {
        StringBuilder sb = new StringBuilder();
        int k = 0;

        for (char ch : str.toCharArray()) {
            if (Character.isLetter(ch)) {
                if (k >= 1) {
                    char c = sb.charAt(sb.length() - 1);
                    while(k-- >1) sb.append(c);   // will reset k to `0`. if k=1, won't append the character into string.
                }
                sb.append(ch);
            } else {
                k = k * 10 + ch - '0';
            }
        }

        if(k>0){
            char c = sb.charAt(sb.length()-1);
            while(k-- >1) sb.append(c);
        }

        return sb.toString();
    }

    // Decode: "3[a]2[bc]"
    public static String decodeString2(String s) {
        Deque<Character> queue = new ArrayDeque();
        for(char c : s.toCharArray()) queue.add(c);
        return dfsHelper(queue);
    }

    public static String dfsHelper(Deque<Character> dq){
        StringBuilder sb = new StringBuilder();
        int num = 0;

        while(!dq.isEmpty()){
            char c = dq.poll();
            if(Character.isDigit(c)){
                num = num*10 + c - '0';
            }else if (c == '[') {
                String sub = dfsHelper(dq); // recursive
                for(int j=0; j<num; j++){
                    sb.append(sub);
                }
                num = 0;
            }else if(c == ']'){
                break;
            }else{
                sb.append(c);
            }
        }

        return sb.toString();
    }
}
