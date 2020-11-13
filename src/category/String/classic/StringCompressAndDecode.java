package category.String.classic;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Similar leetcode problem: https://leetcode.com/problems/string-compression/
 * Created by brianzhang on 10/16/19.
 */
public class StringCompressAndDecode {

    public static void main(String[] args) {
        StringCompressAndDecode test = new StringCompressAndDecode();
        System.out.println(test.compress("aaabbc"));
        System.out.println(test.decodeString("a1b2c3"));
       // System.out.println(test.compress(new char[]{'a','a','b','b','c','c','c'}));
        System.out.println(test.decodeString2("3[a]2[bc]"));
        System.out.println(test.decodeString2("5[bc]")); // this is why we do " k= k*10 +ch -'0' "
    }

    // Compression: aaabb -> a3b2, abb -> ab2
    public String compress(String s) {

        char[] charArr = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        int index = 0, count = 0;
        while (index < charArr.length) {
            char c = charArr[index];
            while (index < charArr.length && c == charArr[index]) {
                index++;
                count++;
            }
            sb.append(c);
            if (count != 1) {
                sb.append((char) (count + '0'));
            }
            count = 0;
        }
        return sb.toString();
    }

    // Decode string Aa1b2c2x -> Aabbccx
    public String decodeString(String str) {
        StringBuilder sb = new StringBuilder();
        int k = 0;

        for (char ch : str.toCharArray()) {
            if (Character.isLetter(ch)) {
                if (k >= 1) {
                    Character c = sb.charAt(sb.length() - 1);
                    while(k-- >1)           // reset k to `0`. if k=1, won't append the character into string.
                        sb.append(c);
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

    // "3[a]2[bc]"
    public String decodeString2(String s) {
        Deque<Character> queue = new ArrayDeque();
        for(char c : s.toCharArray()) queue.add(c);
        return dfs(queue);
    }

    public String dfs(Deque<Character> dq){
        int num = 0;
        StringBuilder sb = new StringBuilder();
        while(!dq.isEmpty()){
            char c = dq.poll();
            if(Character.isDigit(c)){
                num = num*10 + c - '0';
            }else if (c == '[') {
                String sub = dfs(dq);
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