package category.string;

import java.util.Stack;

/**
 * Created by brianzhang on 3/2/19.
 */
public class ValidateParenthesisString {

    public static void main(String[] args) {
        ValidateParenthesisString test = new ValidateParenthesisString();
        System.out.println(test.checkValidString("(*)"));
    }
    public boolean checkValidString(String s) {

        if(s == null)
            return true;

        char left = '(';
        char right = ')';
        char star = '*';
        char[] charArr = s.toCharArray();
        Stack<Character> stack = new Stack<Character>();
        int i=0;
        for(char temp : charArr){
            i++;
            if(temp == left){
                stack.push(right);
            }else if(temp == star){
                stack.push(star);
            }else{
                if(stack.isEmpty()){
                    stack.push(temp);
                }else{
                    Character peek = stack.pop();
                    while(!stack.isEmpty() && charArr.length-i+1 == stack.size() && peek == star){
                        if(!stack.isEmpty())
                            peek = stack.pop();
                        if(!stack.isEmpty())
                            peek = stack.pop();
                    }
                    if(peek != temp && peek != star){
                        return false;
                    }
                }
            }
        }

        if(!stack.isEmpty()){
            Character last;
            while(!stack.isEmpty() && (last = stack.peek()) == star){
                last = stack.pop();
                if(!stack.isEmpty())
                    last = stack.pop();
            }
        }

        return stack.isEmpty();
    }
}
