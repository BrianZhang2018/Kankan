package category.easy;

import java.util.Stack;

/**
 * Created by brianzhang on 1/14/19.
 */
public class QueueImpyByStack {

    Stack<Integer> input = new Stack<Integer>();
    Stack<Integer> output = new Stack<Integer>();
    int peek = 0;

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        if (input.isEmpty())
            peek = x;
        input.push(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        while (!input.isEmpty()) {
            output.push(input.pop());
        }
        int res = output.isEmpty() ? 0 : output.pop();
        peek = output.isEmpty() ? 0 : output.peek();
        while (!output.isEmpty()) {
            input.push(output.pop());
        }
        return res;
    }

    /**
     * Get the front element.
     */
    public int peek() {
        return peek;
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return output.isEmpty() && input.isEmpty();
    }
}
