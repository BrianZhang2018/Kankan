package category.Stack.twoStack;

import java.util.Stack;

/**
 * ArrayDeque with recording the current minimum solution
 *
 * Created by brianzhang on 3/6/21.
 */
public class MinStack {

    public static void main(String[] args) {
        MinStack ms = new MinStack();
        ms.push(-2);
        ms.push(0);
        ms.push(-3);
        System.out.println(ms.getMin());
        ms.pop();
        System.out.println(ms.top());
        System.out.println(ms.getMin());
    }

    public MinStack() {}

    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>(); // track the minimal val

    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x);
        }
    }

    public void pop() {
        if (stack.peek().equals(minStack.peek())) {
            minStack.pop();
        }
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
