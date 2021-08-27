package category.Stack.twoStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/design-browser-history/
 *
 * Two special cases:
 You must always have at least one element in the history stack which is the page that you are currently at.
 But for the forward stack, this condition is not necessary.
 *
 * Created by brianzhang on 8/22/21.
 */
public class DesignBrowserHistory {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        list.add(1, 100);

        System.out.println(list);

    }

    Stack<String> history = new Stack();
    Stack<String> forward = new Stack();

    public DesignBrowserHistory(String homepage) {
        history.push(homepage);
        forward = new Stack();
    }

    public void visit(String url) {
        history.push(url);
        forward = new Stack();
    }

    public String back(int steps) {
        while(steps-- > 0 && history.size() >1) { // Always keep at least one element in the stack.
            forward.push(history.pop());
        }

        return history.peek();
    }

    public String forward(int steps) {
        System.out.println(forward.size());
        while(steps-- > 0 && forward.size() >0) {
            history.push(forward.pop());
        }

        return history.peek();
    }
}
