package category.Stack.pair;

import javafx.util.Pair;
import java.util.Stack;

/**
 * https://leetcode.com/discuss/interview-question/380650/bloomberg-phone-screen-candy-crush
 *
 * Bloomberg OA
 *
 * Stack<Pair<>> data structure
 * Created by brianzhang on 6/14/20.
 */
public class CandyCrushString {

    public static void main(String[] args) {
        System.out.println(candyCrushString("acbbbccddd"));
    }

    public static String candyCrushString(String str){

        StringBuilder res = new StringBuilder();
        Stack<Pair<Character, Integer>> stack = new Stack<>(); //key: letter, value: counter

        for(int i=0; i<str.length(); i++){
            if(stack.isEmpty()){
                stack.push(new Pair<>(str.charAt(i), 1));
                continue;
            }

            if(stack.peek().getKey() == str.charAt(i)){
                if(stack.peek().getValue() + 1 == 3){
                    while(stack.peek().getKey() == str.charAt(i)){
                        stack.pop();
                    }
                }else{
                    stack.push(new Pair<>(str.charAt(i), stack.peek().getValue()+1));
                }
            }else{
                stack.push(new Pair<>(str.charAt(i), 1));
            }
        }

        while(!stack.isEmpty()){
            res.append(stack.pop().getKey());
        }

        return res.toString();
    }
}
