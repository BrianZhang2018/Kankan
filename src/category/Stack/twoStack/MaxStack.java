package category.Stack.twoStack;

import java.util.Stack;

/**
 * https://www.lintcode.com/problem/max-stack/description
 *
 * f: 5, linkedin
 * Created by brianzhang on 11/12/20.
 */
public class MaxStack {
    public static void main(String[] args) {
        MaxStack stack = new MaxStack();
        stack.push(5);
        stack.push(1);
        stack.push(5);
        stack.push(2);
        System.out.println(stack.top()); // -> 2
        System.out.println(stack.popMax());//  -> 5
        System.out.println(stack.top()); // -> 2
        System.out.println(stack.peekMax()); // -> 5
        System.out.println(stack.popMax()); // -> 5
        System.out.println(stack.peekMax()); // -> 2
    }

    Stack<Integer> data = new Stack<>();
    Stack<Integer> maxTrack = new Stack<>(); // track the current maximum value when push a new element into stack
    public MaxStack() {}
    // O(1)
    public void push(int x) {
        data.push(x);
        if (maxTrack.isEmpty())
            maxTrack.push(x);
        else
            maxTrack.push(Math.max(x, maxTrack.peek()));
    }
    // O(1)
    public int pop() {
        maxTrack.pop();
        return data.pop();
    }
    // O(1)
    public int top() {
        return data.peek();
    }
    // O(1)
    public int peekMax() {
        return maxTrack.peek();
    }
    // O(n)
    public int popMax() {
        int max = maxTrack.peek();
        Stack<Integer> beforeMax = new Stack<>();
        while (top() != max) {
            beforeMax.push(pop());
        }
        pop(); // remove the max
        while (!beforeMax.isEmpty()) { // put the elements back to stack
            push(beforeMax.pop());
        }
        return max;
    }
}
